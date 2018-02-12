package io.github.armcha.awesomeproject.presentation.adapter

import android.support.v7.widget.RecyclerView
import android.view.View
import android.view.ViewGroup
import io.github.armcha.awesomeproject.R
import io.github.armcha.awesomeproject.model.User
import io.github.armcha.awesomeproject.presentation.inflate
import kotlinx.android.synthetic.main.user_item_view.view.*

class UserAdapter(private val userList: MutableList<User>) : RecyclerView.Adapter<UserAdapter.UserHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserHolder {
        val itemView = parent inflate R.layout.user_item_view
        return UserHolder(itemView)
    }

    override fun getItemCount() = userList.size

    override fun onBindViewHolder(holder: UserHolder, position: Int) {
        holder.bind(userList[position])
    }

    fun addNewUser(user: User) {
        userList.add(user)
        notifyItemInserted(userList.size)
    }

    class UserHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun bind(user: User) {
            val (name, surName) = user
            itemView.userName.text = name.plus(", ")
            itemView.userSurName.text = surName
        }
    }
}