package com.cythero.cityguideapp

import android.content.Context
import uy.kohesive.injekt.api.InjektModule
import uy.kohesive.injekt.api.InjektRegistrar
import uy.kohesive.injekt.api.addSingleton
import uy.kohesive.injekt.api.addSingletonFactory

class MainActivityModule(private val activity: MainActivity) : InjektModule {

	override fun InjektRegistrar.registerInjectables() {
		addSingleton(activity.application)

		addSingletonFactory<Context> {
			activity.application.applicationContext
		}
	}
}
