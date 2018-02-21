package com.creditmantri

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.AdapterView
import android.widget.LinearLayout
import android.widget.Spinner
import android.widget.Toast
import com.creditmantri.apiResponse.WheatherResp
import com.creditmantri.webservice.RestClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class MainActivity : AppCompatActivity() {
    private var spnCity: Spinner? = null
    private var rclWhether: RecyclerView? = null
    private var strCity = ""
    private var unit=""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initView()
        spnCity!!.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>, view: View, position: Int, id: Long) {

                strCity = parent.getItemAtPosition(position).toString()
                callwebservice()

            }

            override fun onNothingSelected(parent: AdapterView<*>) {

            }
        }

    }

    private fun initView() {
        spnCity = findViewById<Spinner>(R.id.spincity)
        rclWhether = findViewById<RecyclerView>(R.id.rclWhether)
        rclWhether!!.layoutManager = LinearLayoutManager(this@MainActivity, LinearLayout.VERTICAL, false)
        rclWhether!!.setHasFixedSize(true)
    }

    private fun whetherApiWebservice() {
        //Progressdialog.showDialog(this, "")

        val apiInterface = RestClient.getapiclient()
        val getNowShowingMoviesResponseCall = apiInterface.getWhetherResp(strCity,unit)
        getNowShowingMoviesResponseCall.enqueue(object : Callback<WheatherResp> {
            override fun onResponse(call: Call<WheatherResp>, response: Response<WheatherResp>) {
                try {
                    val getWhether = response.body()

                    val whetherAdapter = WhetherAdapter(this@MainActivity, getWhether!!.getList())
                    rclWhether!!.adapter = whetherAdapter

                } catch (e: Exception) {
                    e.printStackTrace()
                }

            }

            override fun onFailure(call: Call<WheatherResp>, t: Throwable) {
                t.printStackTrace()
                // Progressdialog.dismissDialog()
                Toast.makeText(applicationContext, "Please try again..", Toast.LENGTH_SHORT).show()

            }
        })
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.settings_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.create_new -> {
                unit="Metric"
                callwebservice()
                Toast.makeText(this, "Celcius selected", Toast.LENGTH_SHORT).show()
                return true
            }
            R.id.open -> {
                unit=""
                callwebservice()
                Toast.makeText(this, "Kelvin selected", Toast.LENGTH_SHORT).show()
                return true
            }
            R.id.fahrenheit -> {
                unit="Imperial"
                callwebservice()
                Toast.makeText(this, "Fahrenheit selected", Toast.LENGTH_SHORT).show()
                return true
            }

            else -> return super.onOptionsItemSelected(item)
        }
    }

    private fun callwebservice() {
        if (DialogBox.isNetworkStatusAvialable(this@MainActivity))
            whetherApiWebservice()
    }
}
