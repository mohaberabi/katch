package com.mohaberabi.kmp.katch

import androidx.compose.ui.window.ComposeUIViewController
import kotlinx.cinterop.CPointer
import kotlinx.cinterop.ExperimentalForeignApi
import kotlinx.cinterop.staticCFunction
import platform.Foundation.NSSetUncaughtExceptionHandler
import platform.Foundation.NSUncaughtExceptionHandler
import kotlin.experimental.ExperimentalNativeApi

@OptIn(ExperimentalNativeApi::class)
fun MainViewController() = ComposeUIViewController {
    setUnhandledExceptionHook { exception ->
        CrashReporter.report(exception)
        terminateWithUnhandledException(exception)
    }
    handleNSUncaughtException()
    App()
}

@OptIn(ExperimentalForeignApi::class)
private fun handleNSUncaughtException(
) {
    val handler: CPointer<NSUncaughtExceptionHandler> = staticCFunction { nsException ->
        val cause = Throwable(nsException?.reason)
        val throwable = Throwable(message = nsException?.name, cause)
        CrashReporter.report(throwable)
    }
    NSSetUncaughtExceptionHandler(handler)
}