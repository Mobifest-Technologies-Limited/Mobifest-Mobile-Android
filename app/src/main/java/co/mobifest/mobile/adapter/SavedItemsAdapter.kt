package co.mobifest.mobile.adapter

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import co.mobifest.mobile.R
import co.mobifest.mobile.models.Product
import co.mobifest.mobile.ui.shopping.MyItemsFragment
import co.mobifest.mobile.ui.shopping.ShoppingActivity
import co.mobifest.mobile.utils.ShoppingUtil
import com.google.android.material.button.MaterialButton
import kotlin.collections.ArrayList

class SavedItemsAdapter(private val context: Context, private val dataSet: ArrayList<Product>) :
    RecyclerView.Adapter<SavedItemsAdapter.ViewHolder>() {
    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(viewGroup.context).inflate(
            R.layout.custom_saved_items_layout,
            viewGroup, false
        )
        val savedItemsParams = RecyclerView.LayoutParams(
            ViewGroup.LayoutParams.MATCH_PARENT,
            ViewGroup.LayoutParams.WRAP_CONTENT
        )
        view.layoutParams = savedItemsParams
        return ViewHolder(view)
    }

    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {
        val productName = dataSet[position].productName
        val price = dataSet[position].productPrice
        viewHolder.productNameTv.text = productName
        viewHolder.productPriceTv.text = ShoppingUtil.getDecimalFormattedString(price.toString())
        viewHolder.cancelSavedItemBtn.setOnClickListener(returnOnClickListener(viewHolder))
        viewHolder.buyNowBtn.setOnClickListener(returnOnClickListener(viewHolder))
    }

    private fun returnOnClickListener(viewHolder: ViewHolder): View.OnClickListener {
        return View.OnClickListener {
            when (it) {
                viewHolder.cancelSavedItemBtn -> removeFromSavedItems()
                viewHolder.buyNowBtn -> ShoppingUtil.loadProductDetails(
                    MyItemsFragment(),
                    context as ShoppingActivity
                )
                else -> {
                }
            }
        }
    }

    private fun removeFromSavedItems() {
        Log.d("YOUCLICKEDHERE", "TRUE")
    }

    override fun getItemCount() = dataSet.size

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val productNameTv: TextView = view.findViewById(R.id.saved_product_name_tv)
        val productPriceTv: TextView = view.findViewById(R.id.saved_product_price_tv)
        val cancelSavedItemBtn: ImageView = view.findViewById(R.id.cancel_saved_item)
        val buyNowBtn: MaterialButton = view.findViewById(R.id.saved_items_buy_now_btn)

    }
}