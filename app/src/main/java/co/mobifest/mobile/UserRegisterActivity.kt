package co.mobifest.mobile

import android.os.Bundle
import android.text.TextUtils
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.button.MaterialButton
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class UserRegisterActivity : AppCompatActivity() {
    lateinit var etname: EditText
    lateinit var etphone: EditText
    lateinit var etpass: EditText
    lateinit var registerButton: MaterialButton
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_user_register)
        databaseUsers = FirebaseDatabase.getInstance().getReference("memberReg")
        registerButton = findViewById(R.id.btnregister)
        etname = findViewById(R.id.etName)
        etphone = findViewById(R.id.etPhone)
        etpass = findViewById(R.id.etPassword)
        registerButton.setOnClickListener { reg() }
    }

    fun reg() {
        val name = etname.text.toString()
        val phone = etphone.text.toString()
        val password = etpass.text.toString()
        if (TextUtils.isEmpty(name)) {
            Toast.makeText(this, "Please write your name", Toast.LENGTH_SHORT).show()
        } else if (TextUtils.isEmpty(phone)) {
            Toast.makeText(this, "Please write your phone no", Toast.LENGTH_SHORT).show()
        } else if (TextUtils.isEmpty(password)) {
            Toast.makeText(this, "Please write your password", Toast.LENGTH_SHORT).show()
        } else {
            val id = databaseUsers!!.push().key
            val memberReg = MemberReg(id, name, password, phone)
            databaseUsers!!.child(id!!).setValue(memberReg)
            Toast.makeText(this, "User registered", Toast.LENGTH_SHORT).show()
        }
    }

    companion object {
        @JvmField
        var databaseUsers: DatabaseReference? = null

        @JvmStatic
        fun getuser() {
            databaseUsers = FirebaseDatabase.getInstance().getReference("memberReg")
        }
    }
}