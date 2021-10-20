package co.mobifest.mobile.ui.rentals

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.SnapHelper
import co.mobifest.mobile.R
import co.mobifest.mobile.adapter.RentalsAdapter
import co.mobifest.mobile.models.Rental
import co.mobifest.mobile.utils.StartSnapHelper

private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

class RentalsHomeFragment : Fragment() {
    private var param1: String? = null
    private var param2: String? = null
    private lateinit var apartmentRecyclerview: RecyclerView
    private lateinit var villasRecyclerView: RecyclerView
    private lateinit var duplexRecyclerview: RecyclerView
    private lateinit var studioRecyclerview: RecyclerView
    private lateinit var bunglowRecyclerView: RecyclerView
    private lateinit var rentalResponse: ArrayList<Rental>

    private lateinit var apartmentList: ArrayList<Rental>
    private lateinit var villasList: ArrayList<Rental>
    private lateinit var duplexList: ArrayList<Rental>
    private lateinit var studioList: ArrayList<Rental>
    private lateinit var bunglowList: ArrayList<Rental>

    private lateinit var apartmentTv: TextView
    private lateinit var villaTv: TextView
    private lateinit var duplexTv: TextView
    private lateinit var studioTv: TextView
    private lateinit var bunglowTv: TextView

    private lateinit var apartmentHelperStart: SnapHelper
    private lateinit var villasHelperStart: SnapHelper
    private lateinit var duplexHelperStart: SnapHelper
    private lateinit var studioHelperStart: SnapHelper
    private lateinit var bunglowHelperStart: SnapHelper


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
        val view = inflater.inflate(R.layout.fragment_rentals_home, container, false)
        apartmentRecyclerview = view.findViewById(R.id.apartment_recycler_view)
        villasRecyclerView = view.findViewById(R.id.villas_recycler_view)
        duplexRecyclerview = view.findViewById(R.id.duplex_recycler_view)
        studioRecyclerview = view.findViewById(R.id.studio_recycler_view)
        bunglowRecyclerView = view.findViewById(R.id.bungalow_recycler_view)
        studioTv = view.findViewById(R.id.studio_tv)
        duplexTv = view.findViewById(R.id.duplex_tv)
        apartmentTv = view.findViewById(R.id.apartment_tv)
        villaTv = view.findViewById(R.id.villa_tv)
        bunglowTv = view.findViewById(R.id.bungalow_tv)
        rentalResponse = ArrayList()
        apartmentList = ArrayList()
        villasList = ArrayList()
        duplexList = ArrayList()
        studioList = ArrayList()
        bunglowList = ArrayList()
        apartmentHelperStart = StartSnapHelper()
        villasHelperStart = StartSnapHelper()
        duplexHelperStart = StartSnapHelper()
        studioHelperStart = StartSnapHelper()
        bunglowHelperStart = StartSnapHelper()
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
//        ComponentNavigator.navigateToShoppingDashBoard(
//            requireContext(), requireActivity(),
//            viewLifecycleOwner
//        )
        fetchRentals()
    }

    private fun fetchRentals() {
        rentalResponse.add(
            Rental(
                1, "Apartment", "Kampala, Uganda", "Brand New",
                true, true, 56700F, "UGX", "12m",
                "This is a new house located in kampala, Uganda", "Two BedRooms",
                "12543", "Kampala", true, 1, 1, 1
            )
        )
        rentalResponse.add(
            Rental(
                2,
                "Apartment",
                "Namuwongo, Kampala, Uganda",
                "Renovated",
                true,
                true,
                56700F,
                "12m",
                "KES",
                "This is a new house located in Namuwongo, Kampala, Uganda",
                "Two BedRooms", "12543", "Kampala", false,
                1, 1, 1
            )
        )
        rentalResponse.add(
            Rental(
                3,
                "Studio",
                "Kisasi, Kampala, Uganda",
                "New",
                true,
                true,
                56700F,
                "UGX",
                "12m",
                "This is a new house located in Kisasi, Kampala, Uganda",
                "Two BedRooms", "12543", "Kampala", true,
                1, 1, 1
            )
        )
        rentalResponse.add(
            Rental(
                4, "Studio", "Kampala", "New",
                true, true, 56700F, "KES", "12m",
                "This is a new house located in kampala", "Two BedRooms",
                "12543", "Kampala", false, 1, 1, 1
            )
        )

        rentalResponse.add(
            Rental(
                5, "Villas", "Kampala", "New",
                true, true, 56700F, "UGX", "12m",
                "This is a new house located in kampala", "Two BedRooms",
                "12543", "Kampala", true, 1, 1, 1
            )
        )
        rentalResponse.add(
            Rental(
                6, "Villa", "Kampala", "New",
                true, true, 56700F, "UGX", "12m",
                "This is a new house located in kampala", "Two BedRooms",
                "12543", "Kampala", true, 1, 1, 1
            )

        )

        rentalResponse.add(
            Rental(
                7, "Duplex", "Kampala", "New",
                true, true, 56700F, "UGX", "12m",
                "This is a new house located in kampala", "Two BedRooms",
                "12543", "Kampala", false, 1, 1, 1
            )
        )
        rentalResponse.add(
            Rental(
                8, "Duplex", "Kampala", "New",
                true, true, 56700F, "UGX", "12m",
                "This is a new house located in kampala", "Two BedRooms", "12543",
                "Kampala", false, 1, 1, 1
            )
        )

        rentalResponse.add(
            Rental(
                9, "Apartment", "Kampala", "New",
                true, true, 56700F, "UGX", "12m",
                "This is a new house located in kampala", "Two BedRooms",
                "12543", "Kampala", true, 1, 1, 1
            )
        )

        for (rental in rentalResponse) {
            if (rental.rentalType == "Apartment") {
                apartmentTv.visibility = View.VISIBLE
                apartmentTv.text = "Apartments"
                apartmentList.add(rental)
            } else if (rental.rentalType == "Studio") {
                studioTv.visibility = View.VISIBLE
                studioTv.text = "Studio"
                studioList.add(rental)
            } else if (rental.rentalType == "Villa") {
                villaTv.visibility = View.VISIBLE
                villaTv.text = "Villas"
                villasList.add(rental)

            } else if (rental.rentalType == "Duplex") {
                duplexTv.visibility = View.VISIBLE
                duplexTv.text = "Duplex"
                duplexList.add(rental)

            } else {
                bunglowTv.visibility = View.VISIBLE
                bunglowTv.text = "Bunglow"
                bunglowList.add(rental)
            }
        }

        initializeApartmentRecyclerView(apartmentList)
        initializeStudioRecyclerView(studioList)
        initializeVillasRecyclerView(villasList)
        initializeDuplexRecyclerView(duplexList)
        initializeBunglowRecyclerView(bunglowList)


    }

    private fun initializeApartmentRecyclerView(list: ArrayList<Rental>) {
        val rentalAdapter = RentalsAdapter(requireContext(), list)
        apartmentRecyclerview.adapter = rentalAdapter
        apartmentRecyclerview.layoutManager =
            LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
        apartmentRecyclerview.setHasFixedSize(true)
        apartmentHelperStart.attachToRecyclerView(apartmentRecyclerview)
    }

    private fun initializeStudioRecyclerView(list: ArrayList<Rental>) {
        val rentalAdapter = RentalsAdapter(requireContext(), list)
        studioRecyclerview.adapter = rentalAdapter
        studioRecyclerview.layoutManager =
            LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
        studioRecyclerview.setHasFixedSize(true)
        studioHelperStart.attachToRecyclerView(studioRecyclerview)
    }

    private fun initializeVillasRecyclerView(list: ArrayList<Rental>) {
        val rentalAdapter = RentalsAdapter(requireContext(), list)
        villasRecyclerView.adapter = rentalAdapter
        villasRecyclerView.layoutManager =
            LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
        villasRecyclerView.setHasFixedSize(true)
        villasHelperStart.attachToRecyclerView(villasRecyclerView)
    }

    private fun initializeDuplexRecyclerView(list: ArrayList<Rental>) {
        val rentalAdapter = RentalsAdapter(requireContext(), list)
        duplexRecyclerview.adapter = rentalAdapter
        duplexRecyclerview.layoutManager =
            LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
        duplexRecyclerview.setHasFixedSize(true)
        duplexHelperStart.attachToRecyclerView(duplexRecyclerview)
    }

    private fun initializeBunglowRecyclerView(list: ArrayList<Rental>) {
        val rentalAdapter = RentalsAdapter(requireContext(), list)
        bunglowRecyclerView.adapter = rentalAdapter
        bunglowRecyclerView.layoutManager =
            LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
        bunglowRecyclerView.setHasFixedSize(true)
        bunglowHelperStart.attachToRecyclerView(bunglowRecyclerView)
    }


    companion object {
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            RentalsHomeFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}