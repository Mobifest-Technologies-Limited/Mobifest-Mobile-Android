package co.mobifest.mobile.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RelativeLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import co.mobifest.mobile.R
import co.mobifest.mobile.models.ProductOrder
import co.mobifest.mobile.utils.ShoppingUtil
import kotlin.collections.ArrayList

class ProductOrdersAdapter(
    private val dataSet: ArrayList<ProductOrder>
) : RecyclerView.Adapter<ProductOrdersAdapter.ViewHolder>() {
    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(viewGroup.context).inflate(
            R.layout.custom_product_orders_layout,
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
        val productName = dataSet[position].product.productName
        val orderNumber = dataSet[position].productOrderNumber
        val orderStatus = dataSet[position].productOrderStatus
        val orderStatusDate = dataSet[position].productOrderStatusDate
        if (dataSet[position].product.productSize != null) {
            val productSize = dataSet[position].product.productSize
            viewHolder.productSizeTv.text = productSize
        } else {
            viewHolder.productSizeLayout.visibility = View.GONE
        }
        viewHolder.productNameTv.text = productName
        viewHolder.orderNumberTv.text = orderNumber.toString()
        viewHolder.orderStatusTv.text = orderStatus
        viewHolder.orderStatusDate.text = orderStatusDate
        ShoppingUtil.setProductOrderStatusBackground(orderStatus, viewHolder.orderStatusTv)
    }

    override fun getItemCount() = dataSet.size

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val productNameTv: TextView = view.findViewById(R.id.order_product_name_tv)
        val orderNumberTv: TextView = view.findViewById(R.id.product_order_number_tv)
        val orderStatusTv: TextView = view.findViewById(R.id.product_order_status_tv)
        val orderStatusDate: TextView = view.findViewById(R.id.product_order_date_tv_display)
        val productSizeTv: TextView = view.findViewById(R.id.product_size_display_tv)
        val productSizeLayout: RelativeLayout = view.findViewById(R.id.order_product_size_layout)

    }
}