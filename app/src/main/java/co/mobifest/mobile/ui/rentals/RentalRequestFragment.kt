package co.mobifest.mobile.ui.rentals

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.OnBackPressedCallback
import co.mobifest.mobile.R

private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

class RentalRequestFragment : Fragment() {
    private var param1: String? = null
    private var param2: String? = null

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
        val view = inflater.inflate(R.layout.fragment_rental_request, container, false)

        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        handleOnBackArrowClicked()
    }

    private fun handleOnBackArrowClicked() {
        val callback = object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                (requireContext() as RentalsActivity).loadFragment(RentalDetailFragment())
            }
        }
        requireActivity().onBackPressedDispatcher.addCallback(viewLifecycleOwner, callback)

    }


    companion object {
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            RentalRequestFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}