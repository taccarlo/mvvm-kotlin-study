package com.taccarlo.mvvmstudy.view.koinExample

import org.koin.dsl.module.module

//TODO: place this file somewhere
val appModule = module {
    single { Course() }
    factory { Friend() }
    factory { Student(get(), get()) }
}