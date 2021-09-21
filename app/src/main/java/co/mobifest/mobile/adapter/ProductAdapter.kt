package co.mobifest.mobile.adapter

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import co.mobifest.mobile.R
import co.mobifest.mobile.models.Product
import co.mobifest.mobile.ui.shopping.ProductDetailsFragment

class ProductAdapter(private val myContext: Context, private val dataSet: ArrayList<Product>)
    : RecyclerView.Adapter<ProductAdapter.ViewHolder>() {
    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(viewGroup.context).inflate(R.layout.custom_products_layout,
                viewGroup, false)
        val categoryParams = RecyclerView.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.WRAP_CONTENT)
        view.layoutParams = categoryParams
        return ViewHolder(view)
    }

    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {
        val productName = dataSet[position].productName
        viewHolder.productNameTv.text = productName
        viewHolder.productImageLayout.setOnClickListener(returnOnClickListener(viewHolder))
    }

    private fun returnOnClickListener(viewHolder: ViewHolder): View.OnClickListener? {
        return View.OnClickListener {
            when (it) {
                viewHolder.productImageLayout -> loadProductDetailsView()


                else -> {

                }
            }
        }
    }

    private fun loadProductDetailsView() {
        val activity = myContext as AppCompatActivity
        activity.supportFragmentManager.beginTransaction()
                .replace(R.id.shopping_user_home_container_frame_layout, ProductDetailsFragment())
                .commit()

    }

    override fun getItemCount() = dataSet.size

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val productNameTv: TextView = view.findViewById(R.id.product_name_tv)
        val cardView: CardView = view.findViewById(R.id.products_custom_card_view)
        val productImageLayout: LinearLayout = view.findViewById(R.id.product_image_layout)
    }
}