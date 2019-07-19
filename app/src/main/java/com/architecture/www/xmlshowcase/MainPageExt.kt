package com.architecture.www.xmlshowcase

import kotlinx.coroutines.cancelChildren
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.channels.SendChannel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

/**
 * Logging variable
 * */
internal const val preTextSegment = "MainPage___"

/**
 * Coroutine testing function
 * */
internal fun MainPage.bufferSegment() = runBlocking {
    val channel = Channel<Int>(4)
    val sender = launch {
        repeat(5){
            println("$preTextSegment Send it $it")
            channel.send(it)
        }
    }
    delay(100L)
    sender.cancel()
}

/**
 * Coroutine testing function
 * */
internal suspend fun player(name: String, table: Channel<MainPage.Ball>){
    for(ball in table){
        ball.hits++
        println("$preTextSegment $name $ball")
        delay(300L)
        table.send(ball)
    }
}


/**
 * Coroutine testing function
 * */
internal fun MainPage.fibonacciSeries(){
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

/**
 * Coroutine testing function
 * */
internal fun MainPage.initateLongPrintMessage(){
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

/**
 * Coroutine testing function
 * */
internal fun MainPage.allocateDataManipulation() = runBlocking {
    val table = Channel<MainPage.Ball>()
    launch { player("ping",table) }
    launch { player("ping",table) }
    table.send(MainPage.Ball(0))
    delay(1000L)
    coroutineContext.cancelChildren()
}

/**
 * Coroutine testing function
 * */
internal suspend fun MainPage.player(name: String, table: Channel<MainPage.Ball>){
    for(ball in table){
        ball.hits++
        println("$preTextSegment $name $ball")
        delay(300L)
        table.send(ball)
    }
}

/**
 * Coroutine testing function
 * */
internal suspend fun MainPage.sendString(channel: SendChannel<String>, s: String, time: Long){
    while(true){
        delay(time)
        channel.send(s)
    }
}

/**
 * Coroutine testing function
 * */
internal fun MainPage.sendStringSegment() = runBlocking {
    val channel = Channel<String>()
    launch { sendString(channel,"Foo",200L) }
    launch { sendString(channel,"Bar",500L) }
    repeat(6){
        println("$preTextSegment "+ channel.receive())
    }
    coroutineContext.cancelChildren()
}