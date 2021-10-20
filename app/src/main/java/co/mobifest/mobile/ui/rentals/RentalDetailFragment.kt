package co.mobifest.mobile.ui.rentals

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.activity.OnBackPressedCallback
import co.mobifest.mobile.R
import co.mobifest.mobile.models.Rental
import com.google.android.material.button.MaterialButton

private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

class RentalDetailFragment : Fragment() {
    private var param1: String? = null
    private var param2: String? = null

    private lateinit var rentalRequestButton: MaterialButton
    private lateinit var rentalDescriptionTv: TextView
    private lateinit var rentalLocationTv: TextView
    private lateinit var rentalDistrictTv: TextView
    private lateinit var rentalTypeTv: TextView
    private lateinit var rentalSizeTv: TextView
    private lateinit var rentalCodeTv: TextView
    private lateinit var rentalInspectionTv: TextView
    private lateinit var rentalAvailabilityTv: TextView
    private lateinit var rentalCurrencyTv: TextView
    private lateinit var rentalPriceTv: TextView
    private lateinit var parkingCountTv: TextView
    private lateinit var bathroomCountTv: TextView
    private lateinit var bedroomCountTv: TextView

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
        val view = inflater.inflate(R.layout.fragment_rental_detail, container, false)
        rentalRequestButton = view.findViewById(R.id.rental_detail_request_button)
        rentalDescriptionTv = view.findViewById(R.id.rental_details_description_tv)
        rentalLocationTv = view.findViewById(R.id.rental_detail_location)
        rentalDistrictTv = view.findViewById(R.id.rental_detail_district)
        rentalTypeTv = view.findViewById(R.id.rental_detail_type)
        rentalSizeTv = view.findViewById(R.id.rental_detail_size)
        rentalCodeTv = view.findViewById(R.id.rental_detail_code)
        rentalInspectionTv = view.findViewById(R.id.rental_detail_inspection)
        rentalAvailabilityTv = view.findViewById(R.id.rental_detail_availability)
        rentalCurrencyTv = view.findViewById(R.id.rental_details_currency_tv)
        rentalPriceTv = view.findViewById(R.id.rental_detail_price)
        parkingCountTv = view.findViewById(R.id.parking_count_tv)
        bathroomCountTv = view.findViewById(R.id.bath_room_count_tv)
        bedroomCountTv = view.findViewById(R.id.bed_room_count_tv)

        rentalRequestButton.setOnClickListener(returnOnClickListener())
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        handleOnBackArrowClicked()
        fetchRentalDetails()
    }

    private fun fetchRentalDetails() {

        val rental = Rental(
            1, "Apartment", "Kampala, Uganda", "Brand New",
            true, true, 56700F, "UGX", "12m",
            "This is a new house located in kampala, Uganda", "Two BedRooms",
            "12543", "Kampala", true, 3, 1, 2
        )
        rentalDescriptionTv.text = rental.description
        rentalLocationTv.text = rental.location
        rentalDistrictTv.text = rental.district
        rentalTypeTv.text = rental.rentalType
        rentalSizeTv.text = rental.size
        rentalCodeTv.text = rental.code
        if (rental.isInspectable) {
            rentalInspectionTv.text = "YES"
        }else {
            rentalInspectionTv.text = "NO"
        }
        if (rental.isAvailable) {
            rentalAvailabilityTv.text = "AVAILABLE"
        }else {
            rentalAvailabilityTv.text = "TAKEN"
        }
        rentalCurrencyTv.text = rental.currency
        rentalPriceTv.text = rental.price.toString()
        parkingCountTv.text = rental.parkingCount.toString()
        bathroomCountTv.text = rental.bathRoomCount.toString()
        bedroomCountTv.text = rental.bedRoomCount.toString()
    }

    private fun returnOnClickListener(): View.OnClickListener {
        return View.OnClickListener {
            when(it) {
                rentalRequestButton -> submitRequest()
            }

        }

    }

    private fun submitRequest() {
//        val destinationFragment = RentalRequestFragment()
//        (context as RentalsActivity).supportFragmentManager.beginTransaction()
//            .replace(R.id.rentals_activity_container_frame_layout, destinationFragment)
//            .commit()
    }

    private fun handleOnBackArrowClicked() {
        val callback = object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                (requireContext() as RentalsActivity).loadFragment(RentalsHomeFragment())
            }
        }
        requireActivity().onBackPressedDispatcher.addCallback(viewLifecycleOwner, callback)
    }

    companion object {
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            RentalDetailFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}