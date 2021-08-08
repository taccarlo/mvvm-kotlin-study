package com.taccarlo.mvvmstudy.model

import org.koin.dsl.module.module

val appModule = module {

    single { AuthorDisplay() }
    factory { MessageDisplay() }
    factory { Comment(get(), get()) }
    factory { Vote(get()) }
}