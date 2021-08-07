package com.taccarlo.mvvmstudy.view.koinExample

import org.koin.dsl.module.module

val appModule = module {
    single { Course() }
    factory { Friend() }
    factory { Student(get(), get()) }
}