package com.example.randomuser.presentation

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.example.randomuser.R
import com.example.randomuser.databinding.ItemCardViewBinding
import com.example.randomuser.presentation.model.UserUI

class UsersAdapter : PagingDataAdapter<UserUI, UsersAdapter.Holder>(UsersDiffCallback()) {

    inner class Holder(val binding: ItemCardViewBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onBindViewHolder(holder: Holder, position: Int) {
        val user = getItem(position) ?: return
        with(holder.binding) {
            textName.text = user.getUserName()
            user.getColorTextName()?.let { textName.setTextColor(it) }
            textAddress.text = user.getLocation()
            textPhoneNumber.text = user.phone
            imagePhoto.load(user.picture?.medium)

            root.setOnClickListener {
                onItemClickListener?.let {
                    it(user)
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemCardViewBinding.inflate(inflater, parent, false)
        return Holder(binding)
    }

    private var onItemClickListener: ((UserUI) -> Unit)? = null

    fun setOnItemClickListener(listener: (UserUI) -> Unit) {
        onItemClickListener = listener
    }

}

class UsersDiffCallback : DiffUtil.ItemCallback<UserUI>() {
    override fun areItemsTheSame(oldItem: UserUI, newItem: UserUI): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: UserUI, newItem: UserUI): Boolean {
        return oldItem.id == newItem.id
    }
}
