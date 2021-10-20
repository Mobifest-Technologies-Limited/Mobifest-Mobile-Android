package co.mobifest.mobile.adapter

import android.content.Context
import android.graphics.Color
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.TextView
import co.mobifest.mobile.models.ProductCategory


class CategorySpinnerAdapter(
    context: Context,
    resource: Int,
    categories: ArrayList<ProductCategory>
) :
    ArrayAdapter<ProductCategory>(context, resource, categories) {
    private val categorySpinnerItems: ArrayList<ProductCategory> = categories


    override fun getCount(): Int {
        return categorySpinnerItems.size
    }

    override fun getItem(position: Int): ProductCategory {
        return categorySpinnerItems[position]
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        val label = TextView(context)
        label.textSize = 16f
        label.setTextColor(Color.BLACK)
        label.text = categorySpinnerItems[position].catName
        return label
    }

    override fun getDropDownView(position: Int, convertView: View?, parent: ViewGroup): View {
        val label = TextView(context)
        label.setTextColor(Color.BLACK)
        label.textSize = 16f
        label.setPadding(16,8,16,8)
        label.text = categorySpinnerItems[position].catName
        return label
    }

}