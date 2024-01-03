package com.example.rebook.adapterAdmin

import android.annotation.SuppressLint
import android.content.Context
import android.database.Cursor
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.rebook.AccountManagenmentActivity
import com.example.rebook.databinding.UserItemBinding
import com.example.rebook.helper.DatabaseHelper
import com.example.rebook.model.Users

class AdapterManagerAccount(
    private var ds: List<Users> = emptyList(),
    private val context: Context
) : RecyclerView.Adapter<AdapterManagerAccount.AccountHolder>() {
    private lateinit var binding: UserItemBinding
    private lateinit var helper: DatabaseHelper
    private lateinit var rs:Cursor

    inner class AccountHolder(binding: UserItemBinding) : RecyclerView.ViewHolder(binding.root) {

        fun bind(user: Users) {
            helper = DatabaseHelper(context)
            val db = helper.readableDatabase
            val query = "select * from users where email = '${user.email}'"
            rs = db.rawQuery(query,null)
            rs.moveToFirst()
            binding.txtID.text = rs.getInt(0).toString()
            binding.txtEmail.text = user.email
            binding.txtName.text = user.fullname
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AccountHolder {
        val inflater = LayoutInflater.from(parent.context)
        binding = UserItemBinding.inflate(inflater)
        return AccountHolder(binding)
    }

    override fun getItemCount(): Int {
        return ds.size
    }

    override fun onBindViewHolder(holder: AccountHolder, position: Int) {
        val currentUser = ds[position]
        return holder.bind(currentUser)
    }
    @SuppressLint("NotifyDataSetChanged")
    fun setUserList(users: List<Users>){
        ds = users
        notifyDataSetChanged()
    }
}