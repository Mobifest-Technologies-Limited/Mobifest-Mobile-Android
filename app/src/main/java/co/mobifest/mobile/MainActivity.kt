package co.mobifest.mobile

import android.content.Intent
import android.os.Bundle
import android.os.Process
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.button.MaterialButton

class MainActivity : AppCompatActivity() {
    var log: MaterialButton? = null
    var admlog: TextView? = null
    var stflog: TextView? = null
    var about: TextView? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        log = findViewById(R.id.btnlog)
        admlog = findViewById(R.id.admlog)
        stflog = findViewById(R.id.stflog)
        about = findViewById(R.id.tvAbout)
        log!!.setOnClickListener {
            val intent = Intent(this@MainActivity, LoginPage::class.java)
            startActivity(intent)
        }
        admlog!!.setOnClickListener { startActivity(Intent(this@MainActivity, AdminLogin::class.java)) }
        stflog!!.setOnClickListener { startActivity(Intent(this@MainActivity, StaffLogin::class.java)) }
        about!!.setOnClickListener { startActivity(Intent(this@MainActivity, About::class.java)) }
    }

    override fun onBackPressed() {
        moveTaskToBack(true)
        Process.killProcess(Process.myPid())
        System.exit(1)
    }
}