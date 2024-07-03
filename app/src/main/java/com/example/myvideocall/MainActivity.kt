package com.example.myvideocall

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.widget.EditText
import android.widget.TextView
import com.zegocloud.uikit.prebuilt.call.invite.widget.ZegoAcceptCallInvitationButton
import com.zegocloud.uikit.prebuilt.call.invite.widget.ZegoSendCallInvitationButton
import com.zegocloud.uikit.service.defines.ZegoUIKitUser

class MainActivity : AppCompatActivity() {
    private lateinit var targetUserId : EditText
    private lateinit var myUserIdText : TextView
    private lateinit var videoCallButton : ZegoSendCallInvitationButton
    private lateinit var voiceCallVutton : ZegoSendCallInvitationButton
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        targetUserId = findViewById(R.id.targetUserId)
        myUserIdText = findViewById(R.id.myUserIdText)
        videoCallButton  = findViewById(R.id.videoCallButton)
        voiceCallVutton = findViewById(R.id.voiceCallButton)

        val myUserId = intent.getStringExtra("userID")
        myUserIdText.text = "Hi $myUserId, \n Whom do you want to call?"

        targetUserId.addTextChangedListener(object :TextWatcher{
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                val targetUserID = targetUserId.text.toString()
                if (targetUserID.isNotEmpty()){
                    startVideoCall(targetUserID)
                    startVoiceCall(targetUserID)
                }
                            }

            override fun afterTextChanged(p0: Editable?) {

            }

        })

    }
    private fun startVideoCall(targetUserID : String){
        val targetUserName : String = targetUserID

        videoCallButton.setIsVideoCall(true)
        videoCallButton.resourceID = "zego_uikit_call"
        videoCallButton.setInvitees(listOf(ZegoUIKitUser(targetUserID,targetUserName)))
    }
    private fun startVoiceCall(targetUserID : String){
        val targetUserName : String = targetUserID

        voiceCallVutton.setIsVideoCall(true)
        voiceCallVutton.resourceID = "zego_uikit_call"
        voiceCallVutton.setInvitees(listOf(ZegoUIKitUser(targetUserID,targetUserName)))
    }
}