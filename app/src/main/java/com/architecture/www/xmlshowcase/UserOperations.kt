package com.architecture.www.xmlshowcase

interface UserOperations {
    fun deleteUser(id:String)
    fun loadingStatus(loadstatus:Boolean,notifier:RecyclerViewOperation)
    fun navigateToUpdate(id:String)
}