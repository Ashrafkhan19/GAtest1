package com.mapkin.gatest.adapter

import android.annotation.SuppressLint
import android.app.AlertDialog
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.mapkin.gatest.MapsActivity
import com.mapkin.gatest.R
import com.mapkin.gatest.UserInterface
import com.mapkin.gatest.api.ImageList
import com.mapkin.gatest.api.UserList
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class UserRecyclerAdapter(private val context: Context, private val userList: UserList) :
    RecyclerView.Adapter<UserRecyclerAdapter.UserViewHolder>() {

    class UserViewHolder(view: View) : RecyclerView.ViewHolder(view) {

        var imgUser: ImageView = view.findViewById(R.id.imgUser)
        var txtUserName: TextView = view.findViewById(R.id.txtUserName)
        var txtEmail: TextView = view.findViewById(R.id.txtEmail)
        var txtName: TextView = view.findViewById(R.id.txtName)
        var txtAddress: TextView = view.findViewById(R.id.txtAddress)
        var txtPhone: TextView = view.findViewById(R.id.txtPhone)
        var txtWebsite: TextView = view.findViewById(R.id.txtWebsite)
        var txtCName: TextView = view.findViewById(R.id.txtCName)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.recyler_user_list_item, parent, false)
        return UserViewHolder(view)
    }

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: UserViewHolder, position: Int) {
        val user = userList[position]
        holder.txtUserName.text = "@${user.username}"
        holder.txtEmail.text = user.email
        holder.txtName.text = user.name
        holder.txtAddress.text =
            "${user.address.city}, ${user.address.street}, (${user.address.zipcode})"
        holder.txtPhone.text = user.phone
        holder.txtWebsite.text = user.website
        holder.txtCName.text = user.company.name


        imageLoader(user.id, holder)

        /*Glide.with(context)
            .load("https://cdnb.artstation.com/p/assets/images/images/032/982/393/medium/animesh-kumar-rashmika-mandanna.jpg?1608054248")
            .into(holder.imgUser)*/

        holder.txtAddress.setOnClickListener {
            Toast.makeText(context, "${user.address.geo}", Toast.LENGTH_SHORT).show()

            val dialog = AlertDialog.Builder(context)
            dialog.setTitle("Address")
            dialog.setMessage("Open in....")
            dialog.setPositiveButton("Google Map"){ _, _ ->
                //Open mobile settings
                val gmmIntentUri = Uri.parse("geo:0,0?q=${user.address.geo.lat},${user.address.geo.lng}")

                val mapIntent = Intent(Intent.ACTION_VIEW, gmmIntentUri)
                mapIntent.setPackage("com.google.android.apps.maps")

                context.startActivity(mapIntent)

            }
            dialog.setNegativeButton("Local Map"){ _, _ ->
                //Open Local Map
                val intent = Intent(context, MapsActivity::class.java)
                intent.putExtra("Lat", user.address.geo.lat)
                intent.putExtra("Lng", user.address.geo.lng)
                intent.putExtra("add", user.address.toString())
                context.startActivity(intent)

            }
            dialog.create()
            dialog.show()



        }


    }

    private fun imageLoader(id: Int, holder: UserViewHolder) {
        UserInterface.create().getUserImg(id).enqueue(object : Callback<ImageList> {
            override fun onResponse(call: Call<ImageList>, response: Response<ImageList>) {
                val img = response.body()
                if(img != null){
                    Glide.with(context).load(img.url).into(holder.imgUser)
                }
            }

            override fun onFailure(call: Call<ImageList>, t: Throwable) {
                Log.d("Img", "Error in img", t)
            }

        })
    }

    override fun getItemCount(): Int {

        return userList.size
    }
}