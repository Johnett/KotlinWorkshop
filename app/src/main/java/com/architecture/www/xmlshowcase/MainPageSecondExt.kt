package com.architecture.www.xmlshowcase

import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main_page.*
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

internal fun fibonacciSeries() {
    val n = 100
    var t1 = 0
    var t2 = 1
    for(i in 0..n ){
        println("$t1 +")
        val sum = t1 + t2
        t1 = t2
        t2 = sum
    }
}

internal fun MainPage.debounceButtonInitializer(){
    var count = 0
    btBounce.onClickDebounced {
        count += 1
        println(count)
        Toast.makeText(/* left=   */this,"testing", Toast.LENGTH_LONG).show()
    }
}

internal fun initateLongPrintMessage() {
    println("""
            |Invalid path string: "{{your path will be here}}".
            |For individual files, use the following syntax:
            |wire {
            |  sourcePath {
            |    srcDir 'dirPath'
            |    include 'relativePath'
            |  }
            |}
        """.trimMargin())
}

private fun MainPage.coroutineDestroyer() = runBlocking {
    repeat(100_000){i->
        launch {
            delay(1000L)
            println("$preTextSegment .$i")
        }
    }
}

private fun MainPage.threadDestroyer() = runBlocking {
    repeat(100_000){i ->
        val simpleThread = Thread()
        simpleThread.start()
        println("$preTextSegment .$i")
    }
}

private fun MainPage.cancellationDemo() = runBlocking {
    val job = launch {
        repeat(1000){
            println("$preTextSegment Im now $it...")
            delay(500L)
        }
    }
    delay(1300L)
    println("$preTextSegment Im tired of waiting")
    job.cancel()
    job.join()
    println("$preTextSegment now I can quit")
}