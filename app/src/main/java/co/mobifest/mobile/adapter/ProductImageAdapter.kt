package co.mobifest.mobile.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import co.mobifest.mobile.R
import co.mobifest.mobile.models.ProductImage
import co.mobifest.mobile.ui.shopping.ProductDetailsFragment

class ProductImageAdapter(private val myContext: Context, private val dataSet: ArrayList<ProductImage>)
    : RecyclerView.Adapter<ProductImageAdapter.ViewHolder>() {
    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(viewGroup.context).inflate(R.layout.custom_products_image_layout,
                viewGroup, false)
        val categoryParams = RecyclerView.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.WRAP_CONTENT)
        view.layoutParams = categoryParams
        return ViewHolder(view)
    }

    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {
        val productImageUrl = dataSet[position].productImageUrl
//        viewHolder.productNameTv.text = productImageUrl
//        viewHolder.productImageLayout.setOnClickListener(returnOnClickListener(viewHolder))
    }

    private fun returnOnClickListener(viewHolder: ViewHolder): View.OnClickListener? {
        return View.OnClickListener {
            when (it) {
//                viewHolder.productImageLayout -> loadProductDetailsView()


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
//        val productNameTv: TextView = view.findViewById(R.id.product_name_tv)
//        val cardView: CardView = view.findViewById(R.id.products_custom_card_view)
//        val productImageLayout: LinearLayout = view.findViewById(R.id.product_image_layout)
    }
}