package co.mobifest.mobile.utils

import android.os.Bundle
import android.widget.TextView
import androidx.fragment.app.Fragment
import co.mobifest.mobile.R
import co.mobifest.mobile.ui.shopping.MyItemsFragment
import co.mobifest.mobile.ui.shopping.ProductDetailsFragment
import co.mobifest.mobile.ui.shopping.ShoppingActivity
import java.util.*

class ShoppingUtil {
    companion object {
        fun getDecimalFormattedString(value: String): String? {
            val lst = StringTokenizer(value, ".")
            var str1 = value
            var str2 = ""
            if (lst.countTokens() > 1) {
                str1 = lst.nextToken()
                str2 = lst.nextToken()
            }
            var str3 = ""
            var i = 0
            var j = -1 + str1.length
            if (str1[-1 + str1.length] == '.') {
                j--
                str3 = "."
            }
            var k = j
            while (true) {
                if (k < 0) {
                    if (str2.isNotEmpty()) str3 = "$str3.$str2"
                    return str3
                }
                if (i == 3) {
                    str3 = ",$str3"
                    i = 0
                }
                str3 = str1[k].toString() + str3
                i++
                k--
            }
        }

        fun setProductOrderStatusBackground(orderStatus: String, textView: TextView) {
            when (orderStatus) {
                "Canceled" -> {
                    textView.setBackgroundResource(R.drawable.rounded_corner_cancel)
                }
                "Delivered" -> {
                    textView.setBackgroundResource(R.drawable.rounded_corner_delivered)
                }
                "Out For Delivery" -> {
                    textView.setBackgroundResource(R.drawable.rounded_corner_out)
                }
                "Pending Confirmation" -> {
                    textView.setBackgroundResource(R.drawable.rounded_corner_pending)
                }
            }
        }

        fun loadProductDetails(parentFragment: Fragment, activity: ShoppingActivity) {
            val bundle = Bundle()
            if (parentFragment is MyItemsFragment) {
                bundle.putString("detailsOrigin", "savedItems")
            } else {
                bundle.putString("detailsOrigin", "dashboard")
            }
            val destinationFragment = ProductDetailsFragment()
            destinationFragment.arguments = bundle
            activity.supportFragmentManager.beginTransaction()
                .replace(R.id.shopping_activity_container_frame_layout, destinationFragment)
                .commit()
        }


    }

}