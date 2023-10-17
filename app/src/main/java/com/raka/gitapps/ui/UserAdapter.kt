package com.raka.gitapps.ui

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.raka.gitapps.data.response.ItemsItem
import com.raka.gitapps.databinding.ItemUserBinding

class UserAdapter : ListAdapter<ItemsItem,UserAdapter.MyViewHolder>(DIFF_CALLBACK) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val binding = ItemUserBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return MyViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val user = getItem(position)
        holder.bind(user)

        holder.itemView.setOnClickListener{
            val getDetailUser = Intent(holder.itemView.context, DetailActivity::class.java)
            getDetailUser.putExtra(DetailActivity.EXTRA_DETAIL,user.login)
            holder.itemView.context.startActivity(getDetailUser)
        }
    }
    companion object{
        val DIFF_CALLBACK = object : DiffUtil.ItemCallback<ItemsItem>(){
            override fun areContentsTheSame(oldItem: ItemsItem, newItem: ItemsItem): Boolean {
                return oldItem == newItem
            }

            override fun areItemsTheSame(oldItem: ItemsItem, newItem: ItemsItem): Boolean {
                return oldItem == newItem
            }
        }
    }

    inner class MyViewHolder(private val binding: ItemUserBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(user : ItemsItem){
            Glide.with(binding.root.context)
                .load(user.avatarUrl)
                .into(binding.profileImage)
            binding.tvNamaUser.text = user.login
        }
    }
}