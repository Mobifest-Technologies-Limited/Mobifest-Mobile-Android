package co.mobifest.mobile.ui.shopping

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.navigation.Navigation
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.NavigationUI
import co.mobifest.mobile.*
import co.mobifest.mobile.ui.UserHomeActivity
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class ShoppingUserHomeActivity : AppCompatActivity() {
    private lateinit var clothing: Button
    private lateinit var electronics: Button
    private lateinit var books: Button
    private lateinit var otherItems: Button
    private var mAppBarConfiguration: AppBarConfiguration? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_shopping_user_home)
        val toolbar = findViewById<Toolbar>(R.id.user_home_toolbar)
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayShowTitleEnabled(false)
        clothing = findViewById<Button>(R.id.clothing)
        electronics = findViewById(R.id.electronics)
        books = findViewById(R.id.books)
        otherItems = findViewById(R.id.otherItems)


        val sna = intent.getStringExtra("NAME")
        val sph = intent.getStringExtra("PHONE")
        val spa = intent.getStringExtra("PASSWORD")
        val ca = intent.getStringExtra("CALLINGACTIVITY")
        if (ca == "LoginPage") Toast.makeText(this, "Hello ,$sna!",
                Toast.LENGTH_SHORT).show() else if (ca == "PlaceOrder")
            Toast.makeText(this, "Order Placed Successfully!",
                    Toast.LENGTH_LONG).show()
        clothing.setOnClickListener {
            val intent = Intent(this@ShoppingUserHomeActivity, Clothing::class.java)
            intent.putExtra("NAME", sna)
            intent.putExtra("PHONE", sph)
            intent.putExtra("PASSWORD", spa)
            startActivity(intent)
        }
        electronics.setOnClickListener {
            val intent = Intent(this@ShoppingUserHomeActivity, Electronics::class.java)
            intent.putExtra("NAME", sna)
            intent.putExtra("PHONE", sph)
            intent.putExtra("PASSWORD", spa)
            startActivity(intent)
        }
        books.setOnClickListener {
            val intent = Intent(this@ShoppingUserHomeActivity, Books::class.java)
            intent.putExtra("NAME", sna)
            intent.putExtra("PHONE", sph)
            intent.putExtra("PASSWORD", spa)
            startActivity(intent)
        }
        otherItems.setOnClickListener {
            val intent = Intent(this@ShoppingUserHomeActivity, OtherItems::class.java)
            intent.putExtra("NAME", sna)
            intent.putExtra("PHONE", sph)
            intent.putExtra("PASSWORD", spa)
            startActivity(intent)
        }
        databaseOrders = FirebaseDatabase.getInstance().getReference("orders")
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.home_page, menu)
        return true
    }

    override fun onSupportNavigateUp(): Boolean {
        val navController = Navigation.findNavController(this, R.id.nav_host_fragment)
        return (NavigationUI.navigateUp(navController, mAppBarConfiguration!!)
                || super.onSupportNavigateUp())
    }

    override fun onBackPressed() {
        startActivity(Intent(this@ShoppingUserHomeActivity, UserHomeActivity::class.java))
    }

    companion object {
        var databaseOrders: DatabaseReference? = null
        val order: Unit
            get() {
                databaseOrders = FirebaseDatabase.getInstance().getReference("orders")
            }
    }
}