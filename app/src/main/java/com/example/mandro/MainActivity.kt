package com.example.mandro

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import androidx.appcompat.app.AppCompatActivity
import com.example.mandro.databinding.ActivityMainBinding
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import android.util.Log
import android.widget.SeekBar
import android.widget.Toast

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var requestQueue: RequestQueue
    private var eyeDistance: String = "17"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.toolbar)

        requestQueue = Volley.newRequestQueue(this)

        binding.btnStart.setOnClickListener {
            val ip = binding.etIpAddress.text.toString().trim()
            val port = binding.etPort.text.toString().trim().ifEmpty { "8011" }

            if (ip.isEmpty()) {
                Toast.makeText(this, "IP를 입력하세요", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }
            val portNum = port.toIntOrNull()
            if (portNum == null || portNum !in 1..65535) {
                Toast.makeText(this, "포트가 올바르지 않습니다", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            val url = "http://$ip:$port/"
            startActivity(Intent(this, CameraActivity::class.java).putExtra("url", url))
        }

    }

    private fun sendValuesToRaspberryPi(eyeDistance: String, distorted: String, ipAddress: String, port: String) {
        val url = "http://${ipAddress}:${port}/config"
        val stringRequest = object : StringRequest(Request.Method.POST, url,
            { response -> Log.d("Response", response) },
            { error -> Log.e("Error", error.toString()) }) {
            override fun getParams(): MutableMap<String, String> {
                return mutableMapOf(
                    "left" to eyeDistance,
                    "right" to eyeDistance,
                    "distorted" to distorted
                )
            }
        }
        requestQueue.add(stringRequest)

    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        return super.onCreateOptionsMenu(menu)
    }
}
