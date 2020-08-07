package com.juul.pommel.sample

import com.juul.pommel.annotations.SoloModule
import javax.inject.Inject
import javax.inject.Named

@SoloModule
class Greeter @Inject constructor(
    private val welcomeProvider: WelcomeProvider,
    @Named("NameProvider") private val nameProvider: NameProvider
) {

    fun greet(): String {
        return "${welcomeProvider.greeting()}, ${nameProvider.name()}!"
    }
}
