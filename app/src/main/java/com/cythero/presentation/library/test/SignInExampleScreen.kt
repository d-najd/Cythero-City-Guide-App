package com.cythero.presentation.library.test

import android.app.Application
import android.content.Context
import android.util.Log
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import com.cythero.util.toast
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.android.gms.common.api.ApiException
import com.google.android.gms.tasks.Task
import uy.kohesive.injekt.Injekt
import uy.kohesive.injekt.api.get

private const val TAG = "SignInActivity"

@Composable
fun SignInScreen() {
	val context = LocalContext.current
	val signInLauncher = rememberLauncherForActivityResult(
		contract = ActivityResultContracts.StartActivityForResult()
	) { result ->
		val task = GoogleSignIn.getSignedInAccountFromIntent(result.data)
		handleSignInResult(task, context)
	}

	Column {
		SignInButton {
			val googleSignInClient = GoogleSignIn.getClient(Injekt.get<Application>(), Injekt.get())
			val signInIntent = googleSignInClient.signInIntent
			signInLauncher.launch(signInIntent)
		}
	}
}

@Composable
fun SignInButton(onSignIn: () -> Unit) {
	Button(
		onClick = onSignIn,
	) {
		Text(text = "Sign In with Google")
	}
}

private fun handleSignInResult(
	completedTask: Task<GoogleSignInAccount>,
	context: Context,
) {
	try {
		val account = completedTask.getResult(ApiException::class.java)
		// Signed in successfully, show authenticated UI.
		Log.d(TAG, "signInResult: succeeded")
		val re = account?.email
		val idToken = account?.idToken
		val id = account?.id
		val se = re + idToken + id
	} catch (e: ApiException) {
		throw e
		context.toast(e.statusCode.toString())
	}
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
	SignInScreen()
}