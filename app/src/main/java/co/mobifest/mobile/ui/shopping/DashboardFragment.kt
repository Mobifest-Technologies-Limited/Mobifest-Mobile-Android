package co.mobifest.mobile.ui.shopping

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.Spinner
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import co.mobifest.mobile.R
import co.mobifest.mobile.adapter.CategorySpinnerAdapter
import co.mobifest.mobile.adapter.ProductAdapter
import co.mobifest.mobile.models.ProductCategory
import co.mobifest.mobile.models.Product
import com.mancj.materialsearchbar.MaterialSearchBar


private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

class DashboardFragment : Fragment() {
    private var param1: String? = null
    private var param2: String? = null

    private lateinit var productsList: ArrayList<Product>
    private lateinit var productsRecyclerView: RecyclerView
    private lateinit var categorySpinner: Spinner
    private lateinit var categoriesList: ArrayList<ProductCategory>
    private lateinit var selectedCategory: ProductCategory
    private lateinit var searchBar: MaterialSearchBar
    private lateinit var productAdapter: ProductAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_dashboard, container, false)
        productsRecyclerView = view.findViewById(R.id.products_recycler_view)
        categorySpinner = view.findViewById(R.id.category__spinner)
        searchBar = view.findViewById(R.id.shopping_user_search_bar)
        productsList = ArrayList()
        categoriesList = ArrayList()
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        addSearchTextChangedListener()
        loadCategories()
    }

    private fun addSearchTextChangedListener() {
        searchBar.addTextChangeListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(newText: CharSequence, start: Int, before: Int, count: Int) {
                if (productAdapter == null) {
                    return
                } else {
                    productAdapter.filter.filter(newText)
                }
            }

            override fun afterTextChanged(s: Editable) {}
        })
    }

    private fun loadCategories() {
        categoriesList.add(ProductCategory(1, "ALL"))
        categoriesList.add(ProductCategory(2, "Phones"))
        categoriesList.add(ProductCategory(3, "Home & Office"))
        categoriesList.add(ProductCategory(4, "Electronics"))
        categoriesList.add(ProductCategory(5, "Health & Beauty"))
        categoriesList.add(ProductCategory(6, "Fashion Women"))
        categoriesList.add(ProductCategory(7, "Fashion Men"))
        categoriesList.add(ProductCategory(8, "Computing"))
        categoriesList.add(ProductCategory(9, "Sports"))
        categoriesList.add(ProductCategory(10, "Garden & Outdoor"))
        categoriesList.add(ProductCategory(11, "Gaming"))
        populateCategorySpinner()
    }

    private fun populateCategorySpinner() {
        val categorySpinnerAdapter = CategorySpinnerAdapter(
            requireContext(),
            R.layout.categories_list_spinner,
            categoriesList
        )
        categorySpinner.adapter = categorySpinnerAdapter
        categorySpinner.setPadding(4, 4, 4, 4)
        categorySpinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
                selectedCategory = parent?.getItemAtPosition(position) as ProductCategory
                if (selectedCategory.catName == "ALL") {
                    loadProducts()
                } else {
                    productsRecyclerView.adapter = null
                }
            }

            override fun onNothingSelected(p0: AdapterView<*>?) {
                Log.d("NOTHINGSELECTED", "THIS")
            }
        }
    }

    private fun loadProducts() {
        productsList.add(Product(2, "American Shoes", "Local Store", 56100.87, "M"))
        productsList.add(Product(3, "German Brand Shoes", "Local Store", 5400000.00, "L"))
        productsList.add(Product(4, "Shoes3", "Shipped From Abroad", 7800.59, "S"))
        productsList.add(Product(5, "Shoes4", "Local Store", 5700.56, "M"))
        productsList.add(Product(6, "Shoes4", "Shipped From Abroad", 56700.46, "L"))
        productsList.add(Product(7, "Shoes5", "Local Store", 52200.10, "S"))
        initializeProductsRecyclerView()
    }

    private fun initializeProductsRecyclerView() {
//        productsRecyclerView.layoutManager = GridLayoutManager(context, 1)
        productAdapter = ProductAdapter(requireContext(), productsList)
        productsRecyclerView.adapter = productAdapter
        productsRecyclerView.layoutManager =
            LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
        productsRecyclerView.setHasFixedSize(true)
    }
}