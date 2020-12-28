package com.example.ttt

import android.annotation.SuppressLint
import android.app.Activity
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Trace
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.TextView
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import kotlinx.android.synthetic.main.activity_trace.*
import java.io.BufferedReader
import java.io.InputStreamReader
import java.lang.StringBuilder
import java.net.HttpURLConnection
import java.net.URL
import kotlin.concurrent.thread

class TraceActivity : AppCompatActivity() {
    //val url = "http://route.showapi.com/64-19?showapi_appid=492225&com=auto&nu=770001827197549&phone=9235&showapi_sign=ba1fa17cc9a047d18faaf31302ab7407"
    val baseurl1="http://route.showapi.com/64-19?showapi_appid=492225&com=auto&nu="
    val baseurl2="&phone="
    val baseurl3="&showapi_sign=ba1fa17cc9a047d18faaf31302ab7407"
    val TAG="shagoutll"
    private var traecs = ArrayList<Data>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_trace)
        val url=baseurl1+intent.getStringExtra("id")+baseurl2+intent.getStringExtra("phone_num")+baseurl3
        val queue=Volley.newRequestQueue(this)
        val stringRequest = StringRequest(url,{
            val gson = Gson()
            val TraceType = object :TypeToken<trace>() {}.type
            val trace = gson.fromJson<trace>(it,TraceType)
            val len=trace.showapi_res_body.data.size-1

            for (i in 0 .. len){
                traecs.add(trace.showapi_res_body.data[i])
            }
            val adapter =TraceAdapter(this,R.layout.trace_item,traecs)
            listview.adapter=adapter


            Log.d(TAG,"${trace.showapi_res_body.data}")
            Log.d(TAG,"${traecs.toString()}")

        },{
            Log.d(TAG,"$it")
        })
        queue.add(stringRequest)

    }
}
/**
 * 定义adapter
 */
class TraceAdapter(activity: Activity, val resourceId:Int, data:ArrayList<Data>):
    ArrayAdapter<Data>(activity,resourceId,data){

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        val view= LayoutInflater.from(context).inflate(resourceId,parent,false)
        val time:TextView=view.findViewById(R.id.item1)
        val loca: TextView =view.findViewById(R.id.item2)
        val trace1=getItem(position)
        if (trace1!=null){
            time.text=trace1.time
            loca.text=trace1.context.trim()
        }
        return view
    }
}