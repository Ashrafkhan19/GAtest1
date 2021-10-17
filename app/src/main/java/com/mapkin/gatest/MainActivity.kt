package com.mapkin.gatest

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.util.Log
import android.view.View
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.mapkin.gatest.adapter.UserRecyclerAdapter
import com.mapkin.gatest.api.ImageList
import com.mapkin.gatest.api.UserList
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {

    lateinit var progress: LinearLayout
    lateinit var txtView: TextView
    lateinit var rView: RecyclerView
    lateinit var layoutManager: LinearLayoutManager
    lateinit var recyclerAdapter: UserRecyclerAdapter
    lateinit var imagList: MutableList<ImageList>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        rView = findViewById(R.id.rView)
        layoutManager = LinearLayoutManager(this)

        val userList = UserInterface.create().getUserList()


        userList.enqueue(object : Callback<UserList> {
            override fun onResponse(call: Call<UserList>, response: Response<UserList>) {
                val user: UserList? = response.body()


                if (user != null) {

                    recyclerAdapter = UserRecyclerAdapter(this@MainActivity, user) }

                rView.adapter = recyclerAdapter

                rView.layoutManager = layoutManager
//                    for (i in user) {
//                        val imglist = UserInterface.create().getUserImg(i.id)
//
//                        imglist.enqueue(object : Callback<List<ImageList>> {
//                            override fun onResponse(
//                                call: Call<List<ImageList>>,
//                                response: Response<List<ImageList>>
//                            ) {
//                                val imgs = response.body()
//                                if (imgs != null) {
//                                    Log.d("img", imgs.toString())
//                                    ImageList(imgs[i].albumId)
//                                    recyclerAdapter =
//                                        UserRecyclerAdapter(this@MainActivity, user, imgs)
//                                }
//
//                                rView.adapter = recyclerAdapter
//
//                                rView.layoutManager = layoutManager
//                            }
//
//
//                            override fun onFailure(call: Call<List<ImageList>>, t: Throwable) {
//                                Log.d("img", "Error in img", t)
//                            }
//
//
//                        })
//
//                    }

                }

            override fun onFailure(call: Call<UserList>, t: Throwable) {
                Log.d("user", "Error in img", t)
            }



        })


    }
}