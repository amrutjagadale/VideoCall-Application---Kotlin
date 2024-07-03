package com.example.myvideocall

import android.app.Application
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import com.example.myvideocall.databinding.ActivityLoginBinding
import com.zegocloud.uikit.prebuilt.call.ZegoUIKitPrebuiltCallService
import com.zegocloud.uikit.prebuilt.call.invite.ZegoUIKitPrebuiltCallInvitationConfig

class LoginActivity : AppCompatActivity() {

    private lateinit var myUserId : EditText
    private lateinit var loginButton : Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        myUserId = findViewById(R.id.myUserId)
        loginButton  = findViewById(R.id.loginButton)

        loginButton.setOnClickListener {
            val myUserId = myUserId.text.toString()
            if (myUserId.isNotEmpty()){
                val intent = Intent(this@LoginActivity,MainActivity::class.java)
                intent.putExtra("userID",myUserId)
                startActivity(intent)

                setupZegoUIKit(myUserId)
            }
        }
    }
    private fun setupZegoUIKit(userID : String){
        val application : Application = application
        val appID : Long = 1836853415
        val appSign : String = "fd4bd6d13208e4048887b10afb0756bd3323a5e08aee718948f19e46519b2f3a"
        val userName : String = userID
        val callInvitationConfig =  ZegoUIKitPrebuiltCallInvitationConfig()
        ZegoUIKitPrebuiltCallService.init(application,appID,appSign,userID,userName,callInvitationConfig)
    }

    fun OnDestroy(){
        super.onDestroy()
        ZegoUIKitPrebuiltCallService.unInit()
    }
}