package com.cythero.cityguideapp

import android.os.Bundle
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import com.bluelinelabs.conductor.Conductor
import com.bluelinelabs.conductor.Router
import com.cythero.cityguideapp.databinding.MainActivityBinding
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

		/* TODO set controller
		if(router.backstack.firstOrNull() == null) {
			val projectFake = Project(
				id = 1,
				owner = "user1",
				title = "Fake Title",
			)

			router.setRoot(ProjectController())
			// router.setRoot(ProjectTableController(projectFake))
			// router.setRoot(TableTaskController(1L))
			// router.setRoot(ProjectSettingsController(projectFake))
			// router.setRoot(ProjectDetailsController(projectFake))
			// router.setRoot(ProjectUserManagementController(1L))
		}
		 */
	}
}
