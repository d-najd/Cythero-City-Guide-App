package com.cythero.cityguideapp

import android.os.Bundle
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.material3.ExperimentalMaterial3Api
import com.bluelinelabs.conductor.Conductor
import com.bluelinelabs.conductor.Router
import com.cythero.cityguideapp.databinding.MainActivityBinding
import com.cythero.cityguideapp.ui.base.controller.setRoot
import com.cythero.cityguideapp.ui.library.LibraryController
import com.cythero.domain.DomainModule
import uy.kohesive.injekt.Injekt

class MainActivity : AppCompatActivity() {
	private lateinit var binding: MainActivityBinding
	private lateinit var router: Router

	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)

		Injekt.importModule(DomainModule())

		binding = MainActivityBinding.inflate(layoutInflater)
		setContentView(binding.root)

		val container: ViewGroup = binding.controllerContainer
		router = Conductor.attachRouter(this, container, savedInstanceState)
			.setPopRootControllerMode(Router.PopRootControllerMode.NEVER)

		if(router.backstack.firstOrNull() == null) {
			router.setRoot(LibraryController())
			// router.setRoot(ProjectTableController(projectFake))
			// router.setRoot(TableTaskController(1L))
			// router.setRoot(ProjectSettingsController(projectFake))
			// router.setRoot(ProjectDetailsController(projectFake))
			// router.setRoot(ProjectUserManagementController(1L))
		}
	}
}
