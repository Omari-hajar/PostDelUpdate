package com.example.postdelupdate


import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log

import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.GridLayoutManager

import com.example.postdelupdate.databinding.ActivityMainBinding

import retrofit2.HttpException

import java.io.IOException

class MainActivity : AppCompatActivity() {

    lateinit var binding: ActivityMainBinding

    private lateinit var rvMain: RVAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupRV()

        binding.btnAdd.setOnClickListener {
            val intent = Intent(this@MainActivity,MainActivity2::class.java )
            startActivity(intent)
        }

        binding.btnDelUp.setOnClickListener {
            val intent = Intent(this@MainActivity,DelUpdateActivity::class.java )
            startActivity(intent)
        }




        lifecycleScope.launchWhenCreated {
            val response = try{
                RetrofitInstance.api.getData()
            }catch (e: IOException){
                Log.d("Main-Error", e.message.toString())
                return@launchWhenCreated

            }catch (e: HttpException){
                Log.d("Main-Error", e.message.toString())
                return@launchWhenCreated
            }
            if (response.isSuccessful && response.body() !=null){
                rvMain.items = response.body()!!
            } else{
                Log.d("Main-Error", "Response not successful")
            }
        }





    }


    private fun setupRV()= binding.rvMain.apply {
        rvMain = RVAdapter()
        adapter = rvMain
        layoutManager = GridLayoutManager(this@MainActivity, 2)
    }



}