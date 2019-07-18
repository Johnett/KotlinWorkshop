package com.architecture.www.xmlshowcase

import android.content.Intent
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.bugsnag.android.Bugsnag
import com.github.mikephil.charting.data.Entry
import com.github.mikephil.charting.data.LineData
import com.github.mikephil.charting.data.LineDataSet
import kotlinx.android.synthetic.main.activity_main_page.*
import kotlinx.android.synthetic.main.app_bar_layout.*
import kotlinx.coroutines.*
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.channels.ReceiveChannel
import kotlinx.coroutines.channels.SendChannel
import kotlinx.coroutines.channels.produce
import kotlin.math.abs

class MainPage : AppCompatActivity() {

    internal val preTextSegment = "MainPage___"
    @ExperimentalCoroutinesApi
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main_page)
        /**
         * Creating a leak for leakcanary testing
         */
//        val app= application as XmlShowCaseApplication
//        val leakedViews= findViewById<View>(R.id.item1)

        setUi()

        /**
         * Adding the view to create a mocked leak
         */
//        app.leakedViews.add(leakedViews)
        item1.setItemImage(R.drawable.ic_add)
        item1.setItemText(R.string.item1_text)
        item2.setItemImage(R.drawable.ic_view)
        item2.setItemText(R.string.item2_text)

        /**
         * Creating a test bug snag notification
         */
        Bugsnag.notify(RuntimeException("Test error"))

        val integers = intArrayOf(6, 5, -3, -4,0,6,-2,2,1,-3)
        ticketRequest(integers)

//        main()
//        mainAlternative()
//        studentRegistration()
//        coroutineDestroyer()
//        threadDestroyer()
//        cancellationDemo()
//        coRoutineSegment()
//        sendStringSegment()
//        bufferSegment()
//        allocateDataManipulation()
        setUpLineChart()
        fibonacciSeries()
        debounceButtonInitializer()
        initateLongPrintMessage()
    }

    private fun setUi(){

        toolbar.run {
            setNavigationIcon(R.drawable.ic_action_close)
            setNavigationOnClickListener { finish() }
        }
        toolbar_title.text = getString(R.string.main_page_toolbar_text)
        layout_shadow.visibility = View.VISIBLE

        item1.setOnClickListener {
            val intent = Intent(this,AddUser::class.java)
            startActivity(intent)
        }
        item2.setOnClickListener {
            val intent = Intent(this,UserMainActivity::class.java)
            startActivity(intent)
        }
//        user_list.onScroll {
//            layout_shadow.visibility =
//                if (it > 0f) {
//                    View.VISIBLE
//                } else {
//                    View.GONE
//                }
//        }
    }

    private fun ticketRequest(request:IntArray){
        var available = 10
        for(items in request){
            if (items>0){
                if (available>=items){
                    available -= items
                    println("Request for $items tickets approved; remaining $available")
                }else{
                    println("Request for $items tickets denied; remaining $available")
                }
            }else if(items<0){
                if ((10-available)>= abs(items)){
                    available+=abs(items)
                    println("Request for  cancelling ${abs(items)} tickets approved; remaining $available")
                }else{
                    println("Request for cancelling ${abs(items)} tickets denied; sold ${10-available}; remaining $available")
                }
            }
        }
    }

    private fun main() = runBlocking {
        val job= GlobalScope.launch {
            delay(1000L)
            println("$preTextSegment world is becoming slow every day")
        }
        println("$preTextSegment hope we can improve the speed little better because it matters")
        delay(2000L)
        job.join()
    }

    private fun mainAlternative() = runBlocking{
        launch {
            delay(2000L)
            println("$preTextSegment this process might be slow")
        }
        println("$preTextSegment outer layer of the function")
    }

    private fun studentRegistration() = runBlocking {
        launch {
            delay(2000L)
            println("$preTextSegment student started to fill the forms")
        }

        coroutineScope{
            launch {
                delay(2000L)
                println("$preTextSegment student submitted the form")
            }
            delay(2000L)
            println("$preTextSegment response came from the server and its parsing")
        }
        println("$preTextSegment parsed response printed on the screen")
    }

    private fun coroutineDestroyer() = runBlocking {
        repeat(100_000){i->
            launch {
                delay(1000L)
                println("$preTextSegment .$i")
            }
        }
    }

    private fun threadDestroyer() = runBlocking {
        repeat(100_000){i ->
            val simpleThread = Thread()
            simpleThread.start()
            println("$preTextSegment .$i")
        }
    }

    private fun cancellationDemo() = runBlocking {
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

    @ExperimentalCoroutinesApi
    fun CoroutineScope.produceNumbers() = produce {
        var x = 1
        while (true){
            send(x++)
            delay(100)
        }
    }

    private fun CoroutineScope.launchProcessor(Id:Int, channel:ReceiveChannel<Int>)= launch{
        for (msg in channel){
            println("$preTextSegment process $Id received a message $channel")
        }
    }

    @ExperimentalCoroutinesApi
    fun coRoutineSegment() = runBlocking {
        val producer:ReceiveChannel<Int> = produceNumbers()
        repeat(5){
            launchProcessor(it,producer)
        }
        delay(950)
        producer.cancel()
    }

    data class Ball(var hits:Int)

    private fun setUpLineChart(){
        val entries = ArrayList<Entry>()
        val chartData = ArrayList<ChartData>()

        chartData.add(ChartData("1","5"))
        chartData.add(ChartData("2","6"))
        chartData.add(ChartData("3","4"))
        chartData.add(ChartData("4","3"))
        chartData.add(ChartData("5","5"))
        chartData.add(ChartData("6","6"))
        for(items in chartData){
            entries.add(Entry(items.xaxis.toFloat(),items.yaxis.toFloat()))
        }
        val dataSet = LineDataSet(entries,"Label")
        dataSet.color = Color.BLACK
        dataSet.valueTextColor = Color.BLUE

        val lineData = LineData(dataSet)
        chart.data = lineData
        chart.invalidate()
    }

    private fun fibonacciSeries(){
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

    private fun debounceButtonInitializer(){
        var count = 0
        btBounce.onClickDebounced {
            count += 1
            println(count)
            Toast.makeText(/* left=   */this,"testing",Toast.LENGTH_LONG).show()
        }
    }

    private fun initateLongPrintMessage(){
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
}
