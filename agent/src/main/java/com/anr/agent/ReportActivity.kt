package com.anr.agent

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.anr.agent.AnrAgentConstants.EXTRA_MESSAGE
import com.anr.agent.AnrAgentConstants.EXTRA_TITLE
import kotlin.system.exitProcess

class ReportActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_report)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        loadViews(intent)
    }

    fun loadViews(intent: Intent){
        val title = intent.getStringExtra(EXTRA_TITLE)
        val msg = intent.getStringExtra(EXTRA_MESSAGE)
        findViewById<TextView>(R.id.txtTitle)
            .text = title
        findViewById<TextView>(R.id.txtMessage)
            .text = msg

        findViewById<Button>(R.id.btnClose)
            .setOnClickListener{
                finishAndRemoveTask()
                exitProcess(0)
            }
    }
}