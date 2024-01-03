package com.example.rebook

import android.annotation.SuppressLint
import android.content.Intent
import android.database.Cursor
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.widget.SearchView.OnQueryTextListener
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.rebook.adapterAdmin.AdapterManagerAccount
import com.example.rebook.databinding.ActivityAccountManagenmentBinding
import com.example.rebook.helper.DatabaseHelper
import com.example.rebook.model.Users
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import androidx.appcompat.widget.SearchView
import java.util.Locale

@Suppress("DEPRECATION")
class AccountManagenmentActivity : AppCompatActivity() {
    private lateinit var adapter: AdapterManagerAccount
    private lateinit var helper: DatabaseHelper
    private lateinit var binding: ActivityAccountManagenmentBinding
    private lateinit var rs: Cursor

    @SuppressLint("NotifyDataSetChanged")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_account_managenment)
        binding = ActivityAccountManagenmentBinding.inflate(layoutInflater)
        setContentView(binding.root)


        helper = DatabaseHelper(this)
        val userList = loadUserDatabase()
        adapter = AdapterManagerAccount(userList, this)
        adapter.notifyDataSetChanged()
        Toast.makeText(this, "${loadUserDatabase()}", Toast.LENGTH_SHORT).show()

        binding.rvUser.adapter = adapter
        binding.rvUser.layoutManager = LinearLayoutManager(
            this@AccountManagenmentActivity,
            LinearLayoutManager.VERTICAL,
            false
        )

        binding.btnSearchView.queryHint = "Tìm kiếm"
        binding.btnSearchView.setOnQueryTextListener(object : OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                if(
                    newText != null
                ){
                    var list = mutableListOf<Users>()

                    for (i in userList){
                        if (i.email.contains(newText, ignoreCase = true) || i.fullname.contains(newText, ignoreCase = true)){
                            list.add(i)
                        }
                    }
                    if(list.isEmpty()){
                        Toast.makeText(this@AccountManagenmentActivity,"No data", Toast.LENGTH_SHORT).show()
                    }
                    else{
                        Toast.makeText(this@AccountManagenmentActivity,"$list", Toast.LENGTH_SHORT).show()
                        adapter.setUserList(list)
                    }
                }
                return true
            }

        })

        binding.btnExit.setOnClickListener {
            onBackPressed()
        }
    }

    private fun loadUserDatabase(): List<Users> {
        CoroutineScope(Dispatchers.IO).launch {
            val userList = helper.getAllUser()
            withContext(Dispatchers.Main) {
                adapter.setUserList(userList)
            }
        }
        return helper.getAllUser()
    }
}