package co.mobifest.mobile.ui.shopping

import android.graphics.Color
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import co.mobifest.mobile.R
import co.mobifest.mobile.adapter.ProductOrdersAdapter
import co.mobifest.mobile.models.Product
import co.mobifest.mobile.models.ProductOrder
import co.mobifest.mobile.utils.ComponentNavigator

private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

class ShoppingOrdersFragment : Fragment() {
    private var param1: String? = null
    private var param2: String? = null

    private lateinit var productOrdersList: ArrayList<ProductOrder>
    private lateinit var openOrdersList: ArrayList<ProductOrder>
    private lateinit var closedOrdersList: ArrayList<ProductOrder>
    private lateinit var productsRecyclerView: RecyclerView
    lateinit var openOrdersTv: TextView
    lateinit var closedOrdersTv: TextView

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
        val view = inflater.inflate(R.layout.fragment_shopping_orders, container, false)
        productOrdersList = ArrayList()
        openOrdersList = ArrayList()
        closedOrdersList = ArrayList()
        productsRecyclerView = view.findViewById(R.id.shopping_orders_recycler_view)
        openOrdersTv = view.findViewById(R.id.open_orders_tv)
        closedOrdersTv = view.findViewById(R.id.closed_orders_tv)
        openOrdersTv.setOnClickListener(returnOnClickListener())
        closedOrdersTv.setOnClickListener(returnOnClickListener())

        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        ComponentNavigator.navigateToShoppingDashBoard(
            requireContext(), requireActivity(),
            viewLifecycleOwner
        )
        fetchOrders()
        loadOpenOrdersView()
    }

    private fun returnOnClickListener(): View.OnClickListener {
        return View.OnClickListener {
            when (it) {
                openOrdersTv -> loadOpenOrdersView()
                closedOrdersTv -> loadClosedOrdersView()
            }
        }
    }

    private fun loadClosedOrdersView() {
        closedOrdersTv.setTextColor(Color.parseColor("#FFA500"))
        openOrdersTv.setTextColor(Color.parseColor("#000000"))
        productsRecyclerView.adapter = null
        initializeOrderRecyclerView(closedOrdersList)
    }

    private fun loadOpenOrdersView() {
        openOrdersTv.setTextColor(Color.parseColor("#FFA500"))
        closedOrdersTv.setTextColor(Color.parseColor("#000000"))
        productsRecyclerView.adapter = null
        initializeOrderRecyclerView(openOrdersList)
    }

    private fun fetchOrders() {
        productOrdersList.add(
            ProductOrder(
                123743, "Delivered", "12-12-2022",
                Product(
                    2, "American Shoes", "Local Store", 56100.87,
                    "M"
                )
            )
        )
        productOrdersList.add(
            ProductOrder(
                233765, "Pending Confirmation", "03-06-2022", Product(
                    3,
                    "German Brand Shoes",
                    "Local Store", 5400000.00,
                    "L"
                )
            )
        )
        productOrdersList.add(
            ProductOrder(
                121715, "Canceled", "07-10-2022", Product(
                    4,
                    "Shoes3",
                    "Shipped From Abroad", 7800.59,
                    "M"
                )
            )
        )
        productOrdersList.add(
            ProductOrder(
                137439, "Delivered", "12-12-2022", Product(
                    5,
                    "Detergent",
                    "Local Store", 56100.87,
                    null
                )
            )
        )
        productOrdersList.add(
            ProductOrder(
                233777, "Confirmed", "03-06-2022", Product(
                    6,
                    "Scholarstics",
                    "Local Store", 5400000.00,
                    "S"
                )
            )
        )
        productOrdersList.add(
            ProductOrder(
                121700, "Canceled", "07-10-2022", Product(
                    7,
                    "Computer",
                    "Shipped From Abroad", 7800.59,
                    "M"
                )
            )

        )
        productOrdersList.add(
            ProductOrder(
                115743, "Out For Delivery", "12-12-2022", Product(
                    8,
                    "Mattress",
                    "Local Store", 56100.87,
                    "M"
                )
            )
        )
        productOrdersList.add(
            ProductOrder(
                20065, "Pending Confirmation", "03-06-2022", Product(
                    9,
                    "Ugandan Brand Shoes",
                    "Local Store", 5400000.00,
                    "L"
                )
            )
        )
        productOrdersList.add(
            ProductOrder(
                432715, "Canceled", "07-10-2022", Product(
                    10,
                    "Katwe Made Fridge",
                    "Shipped From Abroad", 7800.59,
                    null
                )
            )
        )

        for (order in productOrdersList) {
            if (order.productOrderStatus == "Canceled") {
                closedOrdersList.add(order)
            } else {
                openOrdersList.add(order)
            }
        }
    }

    private fun initializeOrderRecyclerView(list: ArrayList<ProductOrder>) {
        val productAdapter = ProductOrdersAdapter(list)
        productsRecyclerView.adapter = productAdapter
        productsRecyclerView.layoutManager =
            LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
        productsRecyclerView.setHasFixedSize(true)
    }

    companion object {
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            ShoppingOrdersFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}