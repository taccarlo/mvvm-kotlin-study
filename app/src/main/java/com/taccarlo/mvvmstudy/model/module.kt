package com.taccarlo.mvvmstudy.model

import org.koin.dsl.module.module

//TODO: FIX override problem
val appModule = module()  {

    single()  { AuthorDisplay() }
    factory { MessageDisplay() }
    factory { Comment(get(), get()) }
    factory { Vote(get()) }
}