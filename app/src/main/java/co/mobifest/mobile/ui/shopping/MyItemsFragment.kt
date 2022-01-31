package co.mobifest.mobile.ui.shopping

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import co.mobifest.mobile.R
import co.mobifest.mobile.adapter.SavedItemsAdapter
import co.mobifest.mobile.models.Product
import co.mobifest.mobile.utils.ComponentNavigator

private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

class MyItemsFragment : Fragment() {
    private var param1: String? = null
    private var param2: String? = null

    private lateinit var productsList: ArrayList<Product>
    private lateinit var productsRecyclerView: RecyclerView

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
        val view = inflater.inflate(R.layout.fragment_my_items, container, false)
        productsList = ArrayList()
        productsRecyclerView = view.findViewById(R.id.shopping_items_recycler_view)

        return view

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        ComponentNavigator.navigateToShoppingDashBoard(
            requireContext(), requireActivity(),
            viewLifecycleOwner
        )
        loadProducts()
    }


    private fun loadProducts() {
        productsList.add(Product(2, "American Shoes", "Local Store", 56100.87, "M"))
        productsList.add(Product(3, "German Brand Shoes", "Local Store", 5400000.00, "L"))
        productsList.add(Product(4, "Shoes3", "Shipped From Abroad", 7800.59, "M"))
        productsList.add(Product(5, "Shoes4", "Local Store", 5700.56, "S"))
        productsList.add(Product(6, "Shoes4", "Shipped From Abroad", 56700.46, "S"))
        productsList.add(Product(7, "Shoes5", "Local Store", 52200.10, "L"))
        initializeProductsRecyclerView()
    }

    private fun initializeProductsRecyclerView() {
        val productAdapter = SavedItemsAdapter(requireContext(), productsList)
        productsRecyclerView.adapter = productAdapter
        productsRecyclerView.layoutManager =
            LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
        productsRecyclerView.setHasFixedSize(true)
    }

    companion object {
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            MyItemsFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}