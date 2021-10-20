package co.mobifest.mobile.utils

import android.content.Context
import android.view.inputmethod.InputMethodManager
import androidx.activity.OnBackPressedCallback
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.LifecycleOwner
import co.mobifest.mobile.ui.shopping.DashboardFragment
import co.mobifest.mobile.ui.shopping.ShoppingActivity

class ComponentNavigator {
    companion object {

        fun navigateToShoppingDashBoard(
            context: Context,
            activity: FragmentActivity,
            owner: LifecycleOwner
        ) {
            val callback = object : OnBackPressedCallback(true) {
                override fun handleOnBackPressed() {
                    val shoppingHomeActivity = context as ShoppingActivity
                    shoppingHomeActivity.loadBottomNavigationView(DashboardFragment())
                }
            }
            activity.onBackPressedDispatcher.addCallback(owner, callback)
        }

        fun hideSoftKeyBoard(activity: FragmentActivity) {
            activity.currentFocus?.let { view ->
                val imm =
                    activity.getSystemService(Context.INPUT_METHOD_SERVICE) as? InputMethodManager
                imm?.hideSoftInputFromWindow(view.windowToken, 0)
            }
        }

//        fun navigateToDashBoardFromRetake(myActivity: AppCompatActivity, owner: LifecycleOwner) {
//            val callback = object : OnBackPressedCallback(true) {
//                override fun handleOnBackPressed() {
//                    if (myActivity is GetWizardStartedRetakeActivity) {
//                        myActivity.startActivity(Intent(myActivity, NdovuHomeActivity::class.java))
//                    } else {
//                        myActivity.startActivity(Intent(myActivity, MainActivity::class.java))
//                    }
//                }
//            }
//            myActivity.onBackPressedDispatcher.addCallback(owner, callback)
//        }
//
//        fun exitApp(context: Context, activity: FragmentActivity, owner: LifecycleOwner) {
//            val callback = object : OnBackPressedCallback(true) {
//                override fun handleOnBackPressed() {
//                    val ndovuHomeActivity = context as NdovuHomeActivity
//                    val dialog = Dialog(ndovuHomeActivity)
//                    dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
//                    dialog.setCancelable(false)
//                    dialog.setContentView(R.layout.log_out_custom_layout)
//                    val yesBtn = dialog.findViewById(R.id.yesBtn) as MaterialButton
//                    val noBtn = dialog.findViewById(R.id.noBtn) as MaterialButton
//                    yesBtn.setOnClickListener {
//                        val sharedPreferences = activity.applicationContext
//                            .getSharedPreferences("myPrefs", 0)
//                        val sharedPrefEditor: SharedPreferences.Editor = sharedPreferences.edit()
//                        sharedPrefEditor.clear()
//                        sharedPrefEditor.apply()
//                        ndovuHomeActivity.moveTaskToBack(true)
//                        exitProcess(-1)
//                    }
//                    noBtn.setOnClickListener { dialog.dismiss() }
//                    dialog.show()
//                }
//            }
//            activity.onBackPressedDispatcher.addCallback(owner, callback)
//        }
//
//        fun closeApp(activity: FragmentActivity, owner: LifecycleOwner) {
//            val callback = object : OnBackPressedCallback(true) {
//                override fun handleOnBackPressed() {
//                    activity.finishAffinity()
//                }
//            }
//            activity.onBackPressedDispatcher.addCallback(owner, callback)
//        }
//
//        fun navigateToSecurity(
//            context: Context,
//            activity: FragmentActivity,
//            owner: LifecycleOwner
//        ) {
//            val callback = object : OnBackPressedCallback(true) {
//                override fun handleOnBackPressed() {
//                    val ndovuHomeActivity = context as NdovuHomeActivity
//                    ndovuHomeActivity.loadFragmentWithNoBackStack(SecurityFragment())
//                }
//            }
//            activity.onBackPressedDispatcher.addCallback(owner, callback)
//        }
//
//        fun navigateToInvest(context: Context, activity: FragmentActivity, owner: LifecycleOwner) {
//            val callback = object : OnBackPressedCallback(true) {
//                override fun handleOnBackPressed() {
//                    val ndovuHomeActivity = context as NdovuHomeActivity
//                    ndovuHomeActivity.loadFragmentWithNoBackStack(InvestFragment())
//                }
//            }
//            activity.onBackPressedDispatcher.addCallback(owner, callback)
//        }
//
//        fun navigateToAllPlansFromPlanDetails(
//            context: Context,
//            activity: FragmentActivity,
//            owner: LifecycleOwner
//        ) {
//            val callback = object : OnBackPressedCallback(true) {
//                override fun handleOnBackPressed() {
//                    val ndovuHomeActivity = context as NdovuHomeActivity
//                    ndovuHomeActivity.loadFragmentWithNoBackStack(AllPlansFragment())
//                }
//            }
//            activity.onBackPressedDispatcher.addCallback(owner, callback)
//        }
//
//        fun navigateToPortfolioFromPortfolioDetails(
//            context: Context,
//            activity: FragmentActivity,
//            owner: LifecycleOwner
//        ) {
//            val callback = object : OnBackPressedCallback(true) {
//                override fun handleOnBackPressed() {
//                    val ndovuHomeActivity = context as NdovuHomeActivity
//                    ndovuHomeActivity.loadFragmentWithNoBackStack(PortfolioFragment())
//                }
//            }
//            activity.onBackPressedDispatcher.addCallback(owner, callback)
//        }
//
//        fun navigateToSignIn(context: Context, activity: FragmentActivity, owner: LifecycleOwner) {
//            val callback = object : OnBackPressedCallback(true) {
//                override fun handleOnBackPressed() {
//                    val mainActivity = context as MainActivity
//                    mainActivity.loadDefaultFragment()
//                }
//            }
//            activity.onBackPressedDispatcher.addCallback(owner, callback)
//        }
//
//        fun loadDashBoard(context: Context) {
//            val ndovuHomeActivity = context as NdovuHomeActivity
//            ndovuHomeActivity.loadBottomNavigationViewSelectedMenuItem(DashboardFragment())
//        }
//
//        fun checkBottomNavigationView(context: Context, resId: Int) {
//            val activity = context as NdovuHomeActivity
//            val item = activity.bottomNavigationView.menu.findItem(resId)
//            item.isChecked = true
//        }
//
//        fun unCheckDashBoard(context: Context) {
//            val activity = context as NdovuHomeActivity
//            val item = activity.bottomNavigationView.menu.findItem(R.id.dashboard_bottom_nav)
//            item.isCheckable = false
//            item.isChecked = false
//        }
    }

}