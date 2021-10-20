package co.mobifest.mobile.ui.shopping

import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.fragment.app.FragmentTransaction
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import co.mobifest.mobile.*
import com.google.android.material.bottomnavigation.BottomNavigationView
import android.content.Intent
import co.mobifest.mobile.ui.home.UserHomeActivity

class ShoppingActivity : AppCompatActivity() {
    lateinit var fragmentManager: FragmentManager
    private lateinit var bottomNavigationView: BottomNavigationView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_shopping)
        val toolbar = findViewById<Toolbar>(R.id.user_home_toolbar)
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setDisplayShowTitleEnabled(false)
        supportActionBar?.setDisplayShowHomeEnabled(true)
        bottomNavigationView = findViewById(R.id.shopping_activity_bottom_navigation_view)
        fragmentManager = supportFragmentManager
        loadBottomNavigationView(DashboardFragment())
        bottomNavigationView.setOnItemSelectedListener {
            setOnNavigationItemSelectedListener(it)
            return@setOnItemSelectedListener true
        }
    }

    private fun setOnNavigationItemSelectedListener(menuItem: MenuItem) {
        when (menuItem.itemId) {
            R.id.shopping_dashboard_bottom_nav -> loadBottomNavigationView(
                DashboardFragment()
            )
            R.id.shopping_saved_items_bottom_nav -> loadBottomNavigationView(MyItemsFragment())
            R.id.shopping_orders_bottom_nav -> loadBottomNavigationView(ShoppingOrdersFragment())
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            android.R.id.home -> {
                when {
                    getVisibleFragment() is ProductDetailsFragment -> {
                        loadBottomNavigationView(DashboardFragment())
                    }
                    getVisibleFragment() is ShoppingOrdersFragment -> {
                        loadBottomNavigationView(DashboardFragment())
                    }
                    getVisibleFragment() is MyItemsFragment -> {
                        loadBottomNavigationView(DashboardFragment())
                    }
                    else -> {
                        val intent = Intent(this, UserHomeActivity::class.java)
                        startActivity(intent)
                        finish()
                    }
                }

                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    private fun getVisibleFragment(): Fragment? {
        val fragments = fragmentManager.fragments
        for (fragment in fragments) {
            if (fragment != null && fragment.isVisible) return fragment
        }
        return null
    }

    fun loadBottomNavigationView(fragment: Fragment) {
        if (fragment is DashboardFragment) {
            checkDashboard()
        }else if (fragment is MyItemsFragment) {
            checkMyItems()
        }

        val fragmentTransaction: FragmentTransaction = fragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.shopping_activity_container_frame_layout, fragment)
        fragmentTransaction.commit()
    }

    fun checkDashboard() {
        bottomNavigationView.menu.findItem(R.id.shopping_dashboard_bottom_nav).isCheckable =
            true
        bottomNavigationView.menu.findItem(R.id.shopping_dashboard_bottom_nav).isChecked =
            true
    }

    fun checkMyItems() {
        bottomNavigationView.menu.findItem(R.id.shopping_saved_items_bottom_nav).isCheckable =
            true
        bottomNavigationView.menu.findItem(R.id.shopping_saved_items_bottom_nav).isChecked =
            true
    }

}