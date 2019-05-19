package io.github.a2kaido.coroutines

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_coroutines.*
import kotlinx.coroutines.*
import kotlin.coroutines.CoroutineContext

class CoroutinesActivity : AppCompatActivity(), CoroutineScope {

    private val job = SupervisorJob()

    override val coroutineContext: CoroutineContext
        get() = job + Dispatchers.Main

    lateinit var parent: Job
    lateinit var child: Job

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_coroutines)

        parent = launch {
            var count = 0

            child = launch {
                var count2 = 0

                while (isActive) {
                    child_coroutine.text = count2++.toString()
                    delay(100)
                }
            }

            while (isActive) {
                parent_coroutine.text = count++.toString()
                delay(100)
            }
        }

        stop_parent.setOnClickListener {
            parent.cancel()
        }

        stop_child.setOnClickListener {
            child.cancel()
        }

        stop_job.setOnClickListener {
            job.cancel()
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        job.cancelChildren()
    }
}
