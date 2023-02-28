package com.cythero

import com.github.javafaker.Faker
import uy.kohesive.injekt.Injekt
import uy.kohesive.injekt.api.get

fun getFaker(): Faker = Injekt.get()