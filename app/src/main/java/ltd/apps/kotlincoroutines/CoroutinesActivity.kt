package ltd.apps.kotlincoroutines

import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import kotlinx.coroutines.*

class CoroutinesActivity : AppCompatActivity() {

    companion object {
        /* type logt then enter and get Tag template */
        private const val TAG = "CoroutinesActivity--"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_coroutines)

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
}