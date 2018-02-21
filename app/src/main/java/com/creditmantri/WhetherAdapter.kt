package com.creditmantri

import android.content.Context
import android.content.Intent
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.creditmantri.apiResponse.WhetherModel
import java.text.SimpleDateFormat




/**
 * Created by Mani on 20-02-2018.
 */
class WhetherAdapter() : RecyclerView.Adapter<WhetherAdapter.ViewHolder>() {
    lateinit var favList: List<WhetherModel>
    lateinit var inflater: LayoutInflater
    lateinit var context: Context

    constructor(mcontext: Context, favList: ArrayList<WhetherModel>) : this() {
        this.context = mcontext
        this.favList = favList

    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.adapter_layout, parent, false)
        return ViewHolder(view)
    }


    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        var forecast =favList.get(position)
        var spf = SimpleDateFormat("yyyy-MM-dd HH:mm:ss")
        val newDate = spf.parse(forecast.dt_txt)
        spf = SimpleDateFormat("EEE dd MMM")
        val newDateString = spf.format(newDate)
              holder.tvFavName!!.setText(newDateString)
              holder.tvCel1!!.setText(forecast.main.temp_max)
              holder.tvCel2!!.setText(forecast.main.temp_min)
              holder.tvClouds!!.setText(forecast.weather.get(0).description)
              holder.tvmill!!.setText(forecast.wind.speed.toString())
              holder.tvcloud!!.setText("Clouds: "+forecast.clouds.all.toString()+" %,  "+forecast.main.pressure+" hpa")

              holder.share!!.setOnClickListener(object : View.OnClickListener{
                  override fun onClick(v: View?) {
                      val sendIntent = Intent()
                      sendIntent.action = Intent.ACTION_SEND
                      sendIntent.putExtra(Intent.EXTRA_TEXT, forecast.weather.get(0).description)
                      sendIntent.type = "text/plain"
                      context.startActivity(Intent.createChooser(sendIntent, "send_to"))
                  }
              })



    }

    override fun getItemCount() = favList.size

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {


        internal var tvFavName: TextView? = view.findViewById<TextView>(R.id.tvName)
        internal var tvCel1: TextView? = view.findViewById<TextView>(R.id.tvCel1)
        internal var tvCel2: TextView? = view.findViewById<TextView>(R.id.tvCel2)

        internal var tvClouds: TextView? = view.findViewById<TextView>(R.id.tvClouds)
        internal var tvmill: TextView? = view.findViewById<TextView>(R.id.tvmill)
        internal var tvcloud: TextView? = view.findViewById<TextView>(R.id.tvcloud)
        internal var share: ImageView? = view.findViewById<ImageView>(R.id.share)


    }

}