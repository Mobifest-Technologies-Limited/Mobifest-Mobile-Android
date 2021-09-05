package co.mobifest.mobile

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.button.MaterialButton
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener

class LoginPage : AppCompatActivity() {
    var phoneno: EditText? = null
    var pass: EditText? = null
    var login: MaterialButton? = null
    lateinit var registerLink: TextView
    var status: TextView? = null
    var ph: String? = null
    var pa: String? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login_page)
        phoneno = findViewById<View>(R.id.logphone) as EditText
        pass = findViewById<View>(R.id.logpass) as EditText
        login = findViewById(R.id.btnlogin)
        registerLink = findViewById(R.id.tvreg)
        status = findViewById<View>(R.id.tvstatus) as TextView
        status!!.text = ""
        login!!.setOnClickListener {
            RegisterPage.getuser()
            ph = phoneno!!.text.toString()
            pa = pass!!.text.toString()
            RegisterPage.databaseUsers.addValueEventListener(object : ValueEventListener {
                override fun onDataChange(dataSnapshot: DataSnapshot) {
                    var x = 0
                    for (userSnapshot in dataSnapshot.children) {
                        val memberReg = userSnapshot.getValue(MemberReg::class.java)!!
                        val dpn = memberReg.getUsername()
                        val dph = memberReg.getPhone()
                        val dpa = memberReg.getPassword()
                        if (dph == ph && dpa == pa) {
                            val i = Intent(this@LoginPage, HomePageActivity::class.java)
                            i.putExtra("NAME", dpn)
                            i.putExtra("PHONE", dph)
                            i.putExtra("PASSWORD", dpa)
                            i.putExtra("CALLINGACTIVITY", "LoginPage")
                            startActivity(i)
                            x = 1
                        }
                    }
                    if (x == 0) status!!.text = "Invalid Credentials"
                }

                override fun onCancelled(databaseError: DatabaseError) {}
            })
        }
        registerLink.setOnClickListener{
            val intent = Intent(this@LoginPage, RegisterPage::class.java)
            startActivity(intent)
        }
    }

    override fun onBackPressed() {
        startActivity(Intent(this@LoginPage, MainActivity::class.java))
    }

    companion object {
        var tmpname: String? = null
    }
}