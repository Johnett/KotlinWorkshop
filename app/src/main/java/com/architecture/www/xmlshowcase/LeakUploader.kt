package com.architecture.www.xmlshowcase

import android.app.Application
import leakcanary.*

class LeakUploader:AnalysisResultListener {
        override fun invoke(
            application: Application,
            heapAnalysis: HeapAnalysis
        ) {
            // Delegate to default behavior (notification and saving result)
            DefaultAnalysisResultListener(application, heapAnalysis)
            println("print all heap file list ${heapAnalysis.heapDumpFile.list()}")
        }
}