package com.example.rebook

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import com.example.rebook.databinding.ActivityHomeAdminBinding
import com.example.rebook.fragmentAdmin.ManagerAccountFragment

class HomeAdminActivity : AppCompatActivity() {
    private lateinit var binding: ActivityHomeAdminBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home_admin)
        binding = ActivityHomeAdminBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val subManagerAccountFragment = ManagerAccountFragment()

//        binding.btnUsers.setOnClickListener {
//            supportFragmentManager.beginTransaction().apply {
//                replace(R.id.ll,subManagerAccountFragment)
//            }
//        }

        val bundle = intent.extras
        binding.btnUsers.setOnClickListener {
            val intent = Intent(this,AccountManagenmentActivity::class.java)
            startActivity(intent)
        }
        binding.btnPosts.setOnClickListener {
            if(bundle != null){
                val intent = Intent(this, BaiVietActivity::class.java)
                intent.putExtras(bundle)
                startActivity(intent)
            }
        }

        binding.btnSquare.setOnClickListener {
            val intent = Intent(this, ThongKeActivity::class.java)
            startActivity(intent)
        }
    }
}