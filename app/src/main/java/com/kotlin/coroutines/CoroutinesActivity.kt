package com.kotlin.coroutines

import android.os.Bundle
import android.util.Log
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import kotlinx.coroutines.*

class CoroutinesActivity : AppCompatActivity() {
    private lateinit var sequentialCoroutine: TextView
    companion object {
        private const val TAG = "CoroutinesActivity--"
    }

    @OptIn(DelicateCoroutinesApi::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_coroutines)

        sequentialCoroutine = findViewById(R.id.sequentialCoroutine)
        sequentialCoroutine.setOnClickListener {
            sequentialCoroutineApi()
        }
        Log.d(TAG, "main started ${Thread.currentThread().name}")
        GlobalScope.launch {
            Log.d(TAG, "GlobalScope started ${Thread.currentThread().name}")
            delay(2000)
            Log.d(TAG, "GlobalScope ended ${Thread.currentThread().name}")
        }
        GlobalScope.launch(Dispatchers.Main) {
            delay(1000)
            Log.d(TAG, "main ended ${Thread.currentThread().name}")
            delay(3000)
            Toast.makeText(
                this@CoroutinesActivity,
                "main ended ${Thread.currentThread().name}",
                Toast.LENGTH_SHORT
            ).show()
        }
    }

    @OptIn(DelicateCoroutinesApi::class)
    private fun sequentialCoroutineApi() {
        GlobalScope.launch {
            coroutineScope {
                val firstCoroutine = launch {
                    delay(5000L)
                    Log.d("Sequential Coroutine", "First Start")
                }
                firstCoroutine.join()   // Wait for this job to finish
                Log.d("Sequential Coroutine", "Second Start")
            }
        }
    }
}