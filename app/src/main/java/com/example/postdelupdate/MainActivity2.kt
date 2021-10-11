package com.example.postdelupdate
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.example.postdelupdate.RetrofitInstance.api
import com.example.postdelupdate.databinding.ActivityMain2Binding

import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity2 : AppCompatActivity() {
    lateinit var binding: ActivityMain2Binding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMain2Binding.inflate(layoutInflater)
        setContentView(binding.root)




        binding.btnView.setOnClickListener {
            val intent = Intent(this@MainActivity2,MainActivity::class.java )
            startActivity(intent)
        }



        binding.btnSend.setOnClickListener {

            postData()
            clear()
        }

    }

    private fun postData(){
        val name = binding.etUserName.text.toString()
        val location = binding.etUserLocation.text.toString()

        api.postData(Data("4",name, location)).enqueue(object : Callback<Data> {
            override fun onResponse(call: Call<Data>, response: Response<Data>){
                Log.d("Main-success", "Data Added")
            }
            override fun onFailure(call: Call<Data>, t: Throwable){
                Log.d("Main-Failure", "Data Not Added")
            }
        })

    }

    private fun clear(){
        binding.etUserLocation.text.clear()
        binding.etUserLocation.text.clear()
    }
}