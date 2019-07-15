package com.architecture.www.xmlshowcase

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.user_details_recycler_view.view.*

class UserAdpater(private var items : MutableList<UserModel>, private val context: Context, private val userOperations: UserOperations) : RecyclerView.Adapter<ViewHolder>(),RecyclerViewOperation {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
       return ViewHolder(LayoutInflater.from(context).inflate(R.layout.user_details_recycler_view, parent, false))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.tvUserName!!.text = items[position].name
        holder.tvUserAge!!.text = items[position].age
        holder.tvUserAddress!!.text = items[position].address
        holder.tvUserDescription!!.text = items[position].description
        holder.btDelete!!.setOnClickListener {
            userOperations.deleteUser(items[position].id)
            println("idprinting ${items[position].id}")
            userOperations.loadingStatus(true,this)
            items.removeAt(position)
            notifyItemRemoved(position)
            notifyItemRangeChanged(position,items.size)
            userOperations.loadingStatus(false,this)
        }
        holder.btUpdate!!.setOnClickListener {
            userOperations.navigateToUpdate(items[position].id)
        }
    }

    // Gets the number of animals in the list
    override fun getItemCount(): Int {

        return items.size
    }

    override fun dataSetChangeAlertI() {
    }
}

class ViewHolder (view: View) : RecyclerView.ViewHolder(view) {
    // Holds the TextView that will add each animal to
    val tvUserName: TextView? = view.user_name
    val tvUserAge: TextView? = view.user_age
    val tvUserAddress: TextView? = view.user_address
    val tvUserDescription: TextView? = view.user_description
    val btDelete: Button? = view.delete
    val btUpdate: Button? = view.update
}