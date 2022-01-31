package co.mobifest.mobile.ui.shopping

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.OnBackPressedCallback
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.SnapHelper
import co.mobifest.mobile.R
import co.mobifest.mobile.adapter.ProductImageAdapter
import co.mobifest.mobile.models.ProductImage
import co.mobifest.mobile.utils.StartSnapHelper
import kotlin.collections.ArrayList


private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

class ProductDetailsFragment : Fragment() {
    private var param1: String? = null
    private var param2: String? = null

    private lateinit var productImagesRecyclerView: RecyclerView
    private lateinit var productImagesList: ArrayList<ProductImage>
    private lateinit var snapHelperStart: SnapHelper
    lateinit var detailsOrigin: String
    lateinit var shoppingActivity: ShoppingActivity

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
        if (this.requireArguments().getString("detailsOrigin") != null) {
            detailsOrigin = this.requireArguments().getString("detailsOrigin", null)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_product_details, container, false)
        productImagesRecyclerView = view.findViewById(R.id.product_images_recycler_view)
        shoppingActivity = context as ShoppingActivity
        shoppingActivity.checkDashboard()
        productImagesList = ArrayList()
        snapHelperStart = StartSnapHelper()
        productImagesList.add(ProductImage("testingone"))
        productImagesList.add(ProductImage("testingTwo"))
        productImagesList.add(ProductImage("testingone"))
        productImagesList.add(ProductImage("testingTwo"))
        initializeProductImagesRecyclerView()
        return view
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        handleOnBackArrowClicked()
    }


    private fun handleOnBackArrowClicked() {
        val callback = object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                controlKYCNavigation()
            }
        }
        shoppingActivity.onBackPressedDispatcher.addCallback(viewLifecycleOwner, callback)
    }

    private fun controlKYCNavigation() {
        if (detailsOrigin == "savedItems") {
            shoppingActivity.loadBottomNavigationView(MyItemsFragment())
        } else if (detailsOrigin == "dashboard") {
            shoppingActivity.loadBottomNavigationView(DashboardFragment())
        }
    }


    private fun initializeProductImagesRecyclerView() {
        val productAdapter = ProductImageAdapter(requireContext(), productImagesList)
        productImagesRecyclerView.adapter = productAdapter
        productImagesRecyclerView.layoutManager = LinearLayoutManager(
            requireContext(),
            RecyclerView.HORIZONTAL, false
        )
        productImagesRecyclerView.setHasFixedSize(true)
        snapHelperStart.attachToRecyclerView(productImagesRecyclerView)
//        hanldeAutoScroll(productImagesList, productImagesRecyclerView, productAdapter)


    }

    private fun hanldeAutoScroll(
        imageList: ArrayList<ProductImage>,
        recyclerView: RecyclerView,
        adapter: ProductImageAdapter
    ) {
        var position = 0
        val duration = 2000
        val mHandler = Handler(Looper.getMainLooper())
        val SCROLLING_RUNNABLE: Runnable = object : Runnable {
            override fun run() {
                position++
                if (position < imageList.size) {
                    recyclerView.scrollToPosition(position)
                    adapter.notifyDataSetChanged()
                } else if (position == imageList.size) {
                    position = -1
                }
                mHandler.postDelayed(this, duration.toLong())
            }
        }
        mHandler.postDelayed(SCROLLING_RUNNABLE, 2000)
    }


    companion object {
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            ProductDetailsFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}