package com.example.bootcampapp

import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat

class UserActivity : AppCompatActivity() {
    private lateinit var textView1: TextView
    private lateinit var textView2: TextView
    private lateinit var textView3: TextView
    private lateinit var textView4: TextView
    private lateinit var eText: EditText
    private lateinit var btt1: Button
    private lateinit var btt2: Button

    @RequiresApi(Build.VERSION_CODES.M)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_user)
        textView1 = findViewById(R.id.textView6)
        textView2 = findViewById(R.id.textView7)
        textView3 = findViewById(R.id.textView9)
        textView4 = findViewById(R.id.textView8)
        eText = findViewById(R.id.editText)
        btt1 = findViewById(R.id.Button_Browse)
        btt2 = findViewById(R.id.Button_CheckPermission)

        val bundle: Bundle? = intent.extras
        val uName = bundle?.get("Username")
        val eMail = bundle?.get("Email")
        val phone = bundle?.get("PhoneNumber")
        val pass = bundle?.get("Password")
        textView1.text = uName.toString()
        textView2.text = eMail.toString()
        textView3.text = phone.toString()
        textView4.text = pass.toString()

        btt1.setOnClickListener(View.OnClickListener {
            val webPage: Intent = Intent(Intent.ACTION_VIEW,
                    Uri.parse(eText.text.toString())
            )
            startActivity(webPage)
        })
        btt2.setOnClickListener(View.OnClickListener {
            val value = ContextCompat.checkSelfPermission(this, "android.permission.CAMERA")
            when (value) {
                PackageManager.PERMISSION_GRANTED -> {
                    Log.e("USER_PERMISSION", "PERMISSION_GRANTED")
                    Toast.makeText(applicationContext,"Permission Granted",Toast.LENGTH_SHORT).show()
                }
                PackageManager.PERMISSION_DENIED -> {
                    Log.e("USER_PERMISSION", "PERMISSION_DENIED")
                    Toast.makeText(applicationContext,"Permission denied",Toast.LENGTH_SHORT).show()
                    val value =
                            shouldShowRequestPermissionRationale("android.permission.CAMERA")
                    if (value) {
                        // show proper reason to user for why you need this permission
                        askForPermission()
                    } else {
                        // user denied for permission, ask user to give permission from setting menu
                        Toast.makeText(applicationContext,"Give permission from setting menu",Toast.LENGTH_SHORT).show()
                        Log.e("USER_PERMISSION", "ask user to give permission from setting menu")
                    }
                }
            }
        })
    }
        @RequiresApi(Build.VERSION_CODES.M)
        private fun askForPermission() {
            val permissions = arrayOf("android.permission.READ_EXTERNAL_STORAGE")
            requestPermissions(permissions, 1001)
        }
        override fun onRequestPermissionsResult(
                requestCode: Int,
                permissions: Array<out String>,
                grantResults: IntArray
        ) {
            super.onRequestPermissionsResult(requestCode, permissions, grantResults)
            if (requestCode == 1001) {
                if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    Log.e("USER_PERMISSION", "PERMISSION_GRANTED ${permissions[0]}")
                } else {
                    Log.e("USER_PERMISSION", "PERMISSION_DENIED ${permissions[0]}")
                }
            }
        }

    }
