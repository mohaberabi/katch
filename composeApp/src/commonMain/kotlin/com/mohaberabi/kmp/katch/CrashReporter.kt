package com.mohaberabi.kmp.katch

object CrashReporter {


    fun report(error: Throwable) =
        println("Unhandled Exception MESSAGE:${error.message} , CAUSE :${error.cause}")

}