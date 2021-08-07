package com.taccarlo.mvvmstudy.model

import org.koin.dsl.module.module

val appModule = module {
    single { Author(get(), get()) }
    factory { Message(get(),get()) }
    factory { Comment(get(), get()) }
}