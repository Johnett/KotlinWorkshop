package com.architecture.www.xmlshowcase

/**
 * Sole purpose of this interface is to notify the recycler view adapter when an item changes it's state
 * */
interface RecyclerViewOperation {
   /**
    * Notify if DataSet is changed
    */
   fun dataSetChangeAlertI()
}