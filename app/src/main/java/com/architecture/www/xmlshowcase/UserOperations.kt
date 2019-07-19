package com.architecture.www.xmlshowcase

/**
 * User operations interface
 */
interface UserOperations {
    /**
     * @param id
     * Delete function
     * */
    fun deleteUser(id:String)
    /**
     * @param loadstatus
     * @param notifier
     * loadingStatus function
     * */
    fun loadingStatus(loadstatus:Boolean,notifier:RecyclerViewOperation)
    /**
     * @param id
     * navigateToUpdate function
     * */
    fun navigateToUpdate(id:String)
}