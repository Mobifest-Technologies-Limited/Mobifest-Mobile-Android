package co.mobifest.mobile.ui.shopping

import android.content.Intent
import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.fragment.app.FragmentTransaction
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import co.mobifest.mobile.*
import co.mobifest.mobile.ui.UserHomeActivity
import com.google.android.material.bottomnavigation.BottomNavigationView

class ShoppingUserHomeActivity : AppCompatActivity(),
        BottomNavigationView.OnNavigationItemSelectedListener {
    lateinit var fragmentManager: FragmentManager
    private lateinit var bottomNavigationView: BottomNavigationView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_shopping_user_home)
        val toolbar = findViewById<Toolbar>(R.id.user_home_toolbar)
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayShowTitleEnabled(false)
        bottomNavigationView = findViewById(R.id.shopping_user_home_bottom_navigation_view)
        fragmentManager = supportFragmentManager
        bottomNavigationView.setOnNavigationItemSelectedListener(this)
        loadBottomNavigationView(DashboardFragment())

    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.shopping_user_home_dashboard_bottom_nav -> loadBottomNavigationView(DashboardFragment())
        }
        return true
    }

    private fun loadBottomNavigationView(fragment: Fragment) {
        val fragmentTransaction: FragmentTransaction = fragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.shopping_user_home_container_frame_layout, fragment)
        fragmentTransaction.commit()
    }

    override fun onBackPressed() {
        startActivity(Intent(this@ShoppingUserHomeActivity, UserHomeActivity::class.java))
    }
}