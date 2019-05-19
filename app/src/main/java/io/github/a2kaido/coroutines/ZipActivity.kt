package io.github.a2kaido.coroutines

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_coroutines.*
import kotlinx.coroutines.*
import kotlin.coroutines.CoroutineContext

class ZipActivity : AppCompatActivity(), CoroutineScope {

    private val job = SupervisorJob()

    override val coroutineContext: CoroutineContext
        get() = job + Dispatchers.Main

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_coroutines)

        val parent = launch {
            try {
                coroutineScope {
                    val a = async(Dispatchers.IO) {
                        delay(3000)
                        1
                    }

                    val b = async(Dispatchers.IO) {
                        delay(3000)
                        2
                    }

                    parent_coroutine.text = (a.await() + b.await()).toString()
                }
            } catch (e: Exception) {
                child_coroutine.text = e.message
            }
        }

        stop_parent.setOnClickListener {
            parent.cancel()
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        job.cancelChildren()
    }
}