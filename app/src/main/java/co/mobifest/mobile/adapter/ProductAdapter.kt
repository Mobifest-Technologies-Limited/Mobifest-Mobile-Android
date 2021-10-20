package co.mobifest.mobile.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Filter
import android.widget.Filterable
import android.widget.LinearLayout
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import co.mobifest.mobile.R
import co.mobifest.mobile.models.Product
import co.mobifest.mobile.ui.shopping.DashboardFragment
import co.mobifest.mobile.ui.shopping.ShoppingActivity
import co.mobifest.mobile.utils.ShoppingUtil
import com.google.android.material.button.MaterialButton
import java.util.*
import kotlin.collections.ArrayList

class ProductAdapter(
    private val context: Context, private val dataSet: ArrayList<Product>,
    private val searchableList: ArrayList<Product> = ArrayList(dataSet)
) :
    RecyclerView.Adapter<ProductAdapter.ViewHolder>(), Filterable {
    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(viewGroup.context).inflate(
            R.layout.custom_products_layout,
            viewGroup, false
        )
        val categoryParams = RecyclerView.LayoutParams(
            ViewGroup.LayoutParams.MATCH_PARENT,
            ViewGroup.LayoutParams.WRAP_CONTENT
        )
        view.layoutParams = categoryParams
        return ViewHolder(view)
    }

    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {
        val productName = dataSet[position].productName
        val shippingStatus = dataSet[position].shipment
        val price = dataSet[position].productPrice
        viewHolder.productNameTv.text = productName
        viewHolder.shippingStatusTv.text = shippingStatus
        viewHolder.productPriceTv.text = ShoppingUtil.getDecimalFormattedString(price.toString())
        viewHolder.viewProductDetailsButton.setOnClickListener(returnOnClickListener(viewHolder))
    }

    private fun returnOnClickListener(viewHolder: ViewHolder): View.OnClickListener {
        return View.OnClickListener {
            when (it) {
                viewHolder.viewProductDetailsButton -> ShoppingUtil.loadProductDetails(
                    DashboardFragment(),
                    context as ShoppingActivity
                )
                else -> {
                }
            }
        }
    }

    override fun getItemCount() = dataSet.size

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val productNameTv: TextView = view.findViewById(R.id.product_name_tv)
        val cardView: CardView = view.findViewById(R.id.products_custom_card_view)
        val productImageLayout: LinearLayout = view.findViewById(R.id.product_image_layout)
        val viewProductDetailsButton: MaterialButton =
            view.findViewById(R.id.view_product_details_button)
        val shippingStatusTv: TextView = view.findViewById(R.id.shipment_status_tv)
        val productPriceTv: TextView = view.findViewById(R.id.product_price_tv)
    }

    override fun getFilter(): Filter {
        return object : Filter() {
            override fun performFiltering(constraint: CharSequence): FilterResults {
                val filteredList: MutableList<Product> = ArrayList()
                if (constraint.isEmpty()) {
                    filteredList.addAll(searchableList)
                } else {
                    val filterPattern =
                        constraint.toString().toLowerCase(Locale.ROOT).trim { it <= ' ' }
                    for (product in searchableList) {
                        if (product.productName.toLowerCase(Locale.ROOT).contains(filterPattern)) {
                            filteredList.add(product)
                        }
                    }
                }
                val filterResults = FilterResults()
                filterResults.values = filteredList
                return filterResults
            }

            override fun publishResults(constraint: CharSequence, results: FilterResults) {
                dataSet.clear()
                dataSet.addAll(results.values as ArrayList<Product>)
                notifyDataSetChanged()
            }
        }
    }

}