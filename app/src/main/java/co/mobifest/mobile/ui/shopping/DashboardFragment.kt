package co.mobifest.mobile.ui.shopping

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.Spinner
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import co.mobifest.mobile.R
import co.mobifest.mobile.adapter.CategorySpinnerAdapter
import co.mobifest.mobile.adapter.ProductAdapter
import co.mobifest.mobile.models.Category
import co.mobifest.mobile.models.Product


private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

class DashboardFragment : Fragment() {
    private var param1: String? = null
    private var param2: String? = null

    private lateinit var categoryProductsList: ArrayList<Product>
    private lateinit var categoryProductsRecyclerView: RecyclerView
    private lateinit var categorySpinner: Spinner
    private lateinit var categoriesList: ArrayList<Category>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_dashboard, container, false)
        categoryProductsRecyclerView = view.findViewById(R.id.products_recycler_view)
        categorySpinner = view.findViewById(R.id.category__spinner)
        categoryProductsList = ArrayList()
        categoriesList = ArrayList()

        categoriesList.add(Category(1, "ALL"))
        categoriesList.add(Category(2, "Phones"))
        categoriesList.add(Category(3, "Home & Office"))
        categoriesList.add(Category(4, "Electronics"))
        categoriesList.add(Category(5, "Health & Beauty"))
        categoriesList.add(Category(6, "Fashion Women"))
        categoriesList.add(Category(7, "Fashion Men"))
        categoriesList.add(Category(8, "Computing"))
        categoriesList.add(Category(9, "Sports"))
        categoriesList.add(Category(10, "Garden & Outdoor"))
        categoriesList.add(Category(11, "Gaming"))

        categoryProductsList.add(Product(2, "American Shoes"))
        categoryProductsList.add(Product(3, "Shoes2"))
        categoryProductsList.add(Product(4, "Shoes3"))
        categoryProductsList.add(Product(5, "Shoes4"))
        categoryProductsList.add(Product(6, "Shoes4"))
        categoryProductsList.add(Product(7, "Shoes5"))
        populateCategorySpinner()
        initializeProductsRecyclerView()

        return view
    }

    private fun initializeProductsRecyclerView() {
        val productAdapter = ProductAdapter(requireContext(), categoryProductsList)
        categoryProductsRecyclerView.adapter = productAdapter
        categoryProductsRecyclerView.layoutManager = GridLayoutManager(context, 2)
        categoryProductsRecyclerView.setHasFixedSize(true)
    }

    private fun populateCategorySpinner() {
        val categorySpinnerAdapter = CategorySpinnerAdapter(requireContext(),
                android.R.layout.simple_spinner_item, categoriesList)
        categorySpinner.adapter = categorySpinnerAdapter
        categorySpinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                val selectedCategory = parent?.getItemAtPosition(position) as Category
                Log.d("SELECTED_CAT_NAME", selectedCategory.catName)
                Log.d("SELECTED_CAT_ID", selectedCategory.catId.toString())
            }

            override fun onNothingSelected(p0: AdapterView<*>?) {
                Log.d("NOTHINGSELECTED", "THIS")
            }
        }
    }
}