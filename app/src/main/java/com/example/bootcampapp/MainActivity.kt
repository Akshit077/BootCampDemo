package com.example.bootcampapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import androidx.core.os.bundleOf


class MainActivity : AppCompatActivity() {
    private lateinit var b1:Button
    private lateinit var et1:EditText
    private lateinit var et2:EditText
    private lateinit var et3:EditText
    private lateinit var et4:EditText
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        b1=findViewById(R.id.button_1)
        et1=findViewById(R.id.editText1)
        et2=findViewById(R.id.editText2)
        et3=findViewById(R.id.editText3)
        et4=findViewById(R.id.editText4)

        fun checkUsername():Boolean
        {
            if(et1.text.toString().isEmpty())
            {
                et1.error = "This field is mandatory"
                return false
            }
            else
            {
                et1.error = null
                return true
            }
        }
        fun checkEmail():Boolean
        {
            val emailPattern:String="[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+"
            if(et2.text.toString().isEmpty())
            {
                et2.error="This field is mandatory"
                return false
            }
            else if(!Regex(emailPattern).matches(et2.text.toString()))
            {
                et2.error="Invalid email address"
                return false
            }
            else
            {
                et2.error=null
                return true
            }
        }
        fun checkPhoneNumber():Boolean
        {
            val samplePhoneNumber:String="[0-9]"
            if(et3.text.toString().isEmpty())
            {
                et3.error="This field is mandatory"
                return false
            }
            else if(!Regex(samplePhoneNumber).matches(et3.text.toString()))
            {
                if(et3.text.toString().length!=10) {
                    et3.error = "Invalid phone number"
                    return false
                }
                else
                {
                    et3.error=null
                    return true
                }
            }
            else
            {
                et3.error=null
                return true
            }
        }
        fun checkPassword():Boolean
        {
            if(et4.text.toString().isEmpty())
            {
                et4.error="This field is mandatory"
                return false
            }
            else if(et4.text.toString().length<10)
            {
                et4.error="Password is too short"
                return false
            }
            else
            {
                et4.error=null
                return true
            }
        }
        fun validateUser():Boolean
        {
            return !(!checkUsername()||!checkEmail()||!checkPassword()||!checkPhoneNumber())
        }
        b1.setOnClickListener(View.OnClickListener {

            if(validateUser()) {
                intent = Intent(applicationContext, UserActivity::class.java)
                intent.putExtra("Username", et1.text.toString())
                intent.putExtra("Email", et2.text.toString())
                intent.putExtra("PhoneNumber", et3.text.toString())
                intent.putExtra("Password", et4.text.toString())
                startActivity(intent)
            }
        })
    }
}