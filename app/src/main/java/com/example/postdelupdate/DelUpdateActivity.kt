package com.example.postdelupdate

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.postdelupdate.RetrofitInstance.api
import com.example.postdelupdate.databinding.ActivityDelUpdateBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class DelUpdateActivity : AppCompatActivity() {
     var userID = ""
     var userName = ""
     var userLocation = ""
    lateinit var binding: ActivityDelUpdateBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDelUpdateBinding.inflate(layoutInflater)
        setContentView(binding.root)



        binding.btnUpdate.setOnClickListener {
            update()
            clear()

        }

        binding.btnDel.setOnClickListener {
            delete()
            clear()

        }
        binding.btnBack.setOnClickListener {
            val intent = Intent(this@DelUpdateActivity,MainActivity::class.java )
            startActivity(intent)
        }

    }


    private fun update(){
        userID= binding.etPk.text.toString()
        userName= binding.etUserName.text.toString()
        userLocation= binding.etUserLocation.text.toString()
        api.update(
            userID,
            Data(userID, userName, userLocation)
        ).enqueue(object : Callback<Data?> {
            override fun onResponse(call: Call<Data?>, response: Response<Data?>) {
                Toast.makeText(applicationContext, "User was updated Successfully", Toast.LENGTH_LONG).show()
            }

            override fun onFailure(call: Call<Data?>, t: Throwable) {
                Toast.makeText(applicationContext, "User was not updated Successfully", Toast.LENGTH_LONG).show()
            }
        })
    }

    private fun delete(){
        userID= binding.etPk.text.toString()
        userName= binding.etUserName.text.toString()
        userLocation= binding.etUserLocation.text.toString()

        api?.deleteUser(
            userID
        )?.enqueue(object : Callback<Void?> {
            override fun onResponse(call: Call<Void?>, response: Response<Void?>) {
                Toast.makeText(applicationContext, "User was deleted Successfully", Toast.LENGTH_LONG).show()
            }

            override fun onFailure(call: Call<Void?>, t: Throwable) {
                Toast.makeText(applicationContext, "User was not deleted Successfully", Toast.LENGTH_LONG).show()
            }
        })

    }


    private fun clear(){
         binding.etPk.text.clear()
        binding.etUserName.text.clear()
         binding.etUserLocation.text.clear()
    }
}