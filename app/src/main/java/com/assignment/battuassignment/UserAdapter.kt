package com.assignment.battuassignment

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.assignment.battuassignment.databinding.ItemBinding
import com.assignment.battuassignment.ui.network.UserDetail


class UserAdapter() : RecyclerView.Adapter<UserAdapter.UserViewHolder>() {

    var data = listOf<UserDetail>()
        set(value) {
            field = value
            notifyDataSetChanged()
        }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserViewHolder {

        val binding = ItemBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return UserViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return data.size
    }

    override fun onBindViewHolder(holder: UserViewHolder, position: Int) {
        val userDetail = data[position]
        holder.id.text = userDetail.id.toString()
        holder.name.text = userDetail.name
        holder.email.text = userDetail.email
        holder.num.text = userDetail.phone

    }

    class UserViewHolder(itemView: ItemBinding) : RecyclerView.ViewHolder(itemView.root) {

        var id = itemView.userId
        var name = itemView.userName
        var email = itemView.userEmail
        var num = itemView.userNumber

    }

}