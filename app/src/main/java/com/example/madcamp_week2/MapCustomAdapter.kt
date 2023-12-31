package com.example.madcamp_week2

import androidx.recyclerview.widget.RecyclerView

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.RatingBar
import android.widget.TextView
import androidx.compose.ui.geometry.Offset

class MapCustomAdapter(
    private val gymIdDataSet: ArrayList<Int>,
    private val nameDataSet: ArrayList<String>,
    private val locationDataSet: ArrayList<String>,
    private val ratingDataSet: ArrayList<String>,
    private val imageDataSet: ArrayList<String>,
    private val latitudeDataSet: ArrayList<Double>,
    private val longitudeDataSet: ArrayList<Double>
) : RecyclerView.Adapter<MapCustomAdapter.ViewHolder>() {

    // click event implementation
    // OnItemClickListener 인터페이스 선언
    interface OnItemClickListener {
        fun onItemClicked(position: Int, data: String)

    }

    // OnItemClickListener 참조 변수 선언
    private var itemClickListener: OnItemClickListener? = null

    // OnItemClickListener 전달 메소드
    fun setOnItemClickListener(listener: OnItemClickListener) {
        itemClickListener = listener
    }

    inner class ViewHolder(itemView: View, private val adapter: MapCustomAdapter) : RecyclerView.ViewHolder(itemView) {

        val imageView: ImageView = itemView.findViewById(R.id.fragfour_imageview)
        val gymname: TextView = itemView.findViewById(R.id.fragfour_gymname)
        val location: TextView = itemView.findViewById(R.id.fragfour_location)
        val rating: TextView = itemView.findViewById(R.id.rating)
        val rating_: RatingBar = itemView.findViewById(R.id.ratingBar)
        val contactRecyclerView: LinearLayout = itemView.findViewById(R.id.fragfour_recyclerview)

        fun bind(ratingValue: String) {
            rating_.rating = ratingValue.toFloatOrNull() ?:0f

            rating_.onRatingBarChangeListener = RatingBar.OnRatingBarChangeListener { _, newRating, _ ->
                //변경된 ratingbar 값 처리
            }
        }
        init {
            contactRecyclerView.setOnClickListener {
                val position = adapterPosition
                if(position!=RecyclerView.NO_POSITION) {
                    val name =nameDataSet[position]
                    itemClickListener?.onItemClicked(position, name)
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.fragment_four_item, parent, false)
        return ViewHolder(view, this)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val name = nameDataSet[position]
        holder.gymname.text = name

        val location = locationDataSet[position]
        holder.location.text = location

        val gym_id = gymIdDataSet[position]
        val latitude = latitudeDataSet[position]
        val longitude = longitudeDataSet[position]

        val rating = ratingDataSet[position]
        holder.rating.text = rating

        val ratingValue = ratingDataSet[position]
        holder.bind(ratingValue)

        val image = imageDataSet[position]

        val context = holder.itemView.context
        val id = context.resources.getIdentifier(image, "drawable", context.packageName)
        holder.imageView.setImageResource(id)

        holder.imageView.clipToOutline = true

        holder.contactRecyclerView.setOnClickListener {
            val context = it.context

            val infoActivity = Intent(context, GymInfoActivity::class.java)

            infoActivity.putExtra("gym_id", gym_id)
            infoActivity.putExtra("name", name)
            infoActivity.putExtra("location", location)
            infoActivity.putExtra("rating", ratingValue)
            infoActivity.putExtra("image", image)
            infoActivity.putExtra("latitude", latitude)
            infoActivity.putExtra("longitude",longitude)

            context.startActivity(infoActivity)
        }
    }

    override fun getItemCount(): Int {
        return nameDataSet.size
    }
}
