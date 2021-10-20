package co.mobifest.mobile.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import co.mobifest.mobile.R
import co.mobifest.mobile.models.Rental
import co.mobifest.mobile.ui.rentals.RentalDetailFragment
import co.mobifest.mobile.ui.rentals.RentalsActivity


class RentalsAdapter(
    private val context: Context, private val dataSet: ArrayList<Rental>
) : RecyclerView.Adapter<RentalsAdapter.ViewHolder>() {
    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(viewGroup.context).inflate(
            R.layout.custom_all_rentals_items,
            viewGroup, false
        )
        val rentalParams = RecyclerView.LayoutParams(
            ViewGroup.LayoutParams.MATCH_PARENT,
            ViewGroup.LayoutParams.WRAP_CONTENT
        )
        view.layoutParams = rentalParams
        return ViewHolder(view)
    }

    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {
        val rentalPrice = dataSet[position].price
        val currency = dataSet[position].currency
        val description = dataSet[position].description
        val rentalStatus = dataSet[position].status
        val location = dataSet[position].location
        viewHolder.rentalPriceTv.text = "$currency: $rentalPrice"
        viewHolder.rentalDescriptionTv.text = description
        viewHolder.rentalStatusTv.text = rentalStatus
        viewHolder.rentalLocationTv.text = location
        viewHolder.rentalImageView.setOnClickListener(returnOnClickListener(viewHolder))

    }

    private fun returnOnClickListener(viewHolder: ViewHolder): View.OnClickListener? {
        return View.OnClickListener{
            when(it) {
                viewHolder.rentalImageView -> loadRentalDetailsFragment()


            }
        }
    }

    private fun loadRentalDetailsFragment() {
        val destinationFragment = RentalDetailFragment()
        (context as RentalsActivity).supportFragmentManager.beginTransaction()
            .replace(R.id.rentals_activity_container_frame_layout, destinationFragment)
            .commit()
    }

    override fun getItemCount() = dataSet.size

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val rentalPriceTv: TextView = view.findViewById(R.id.rental_price_tv)
        val rentalDescriptionTv: TextView = view.findViewById(R.id.rental_description_tv)
        val rentalStatusTv: TextView = view.findViewById(R.id.rental_status_tv)
        val rentalLocationTv: TextView = view.findViewById(R.id.rental_location_tv)
        val rentalImageView: ImageView = view.findViewById(R.id.rental_image_view)

    }

}
