package com.team.nebula.petpujodelivery.adapter

import android.content.Context
import android.os.VibrationEffect
import android.os.Vibrator
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.LinearLayout
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.firestore.CollectionReference
import com.google.firebase.firestore.FirebaseFirestore
import com.team.nebula.petpujodelivery.R
import com.team.nebula.petpujodelivery.model.ChefOrders
import com.team.nebula.petpujodelivery.model.OrderItems
import org.jetbrains.anko.support.v4.toast

class OrderAdapter(val chefItems:ArrayList<ChefOrders>, val context: Context,val docRef:CollectionReference):RecyclerView.Adapter<OrderAdapter.MyViewHolder>(){

    var orderItems:ArrayList<OrderItems>
    var vibrate:Vibrator

    init {
        orderItems = ArrayList()
        vibrate = context.getSystemService(Context.VIBRATOR_SERVICE) as Vibrator
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val view = layoutInflater.inflate(R.layout.upcomming_accept_reject,parent,false)

        return MyViewHolder(view)
    }

    override fun getItemCount(): Int {
        return chefItems.size
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.ordView.text = chefItems.get(position).transactionId.toString()
        holder.timeView.text = chefItems.get(position).time.toString()
        holder.foodQuantity.removeAllViews()
        holder.foodLabel.removeAllViews()
        orderItems = chefItems.get(position).orderItems
        for (i in orderItems){
            val rowfoodView = TextView(context)
            val rowQuantityView = TextView(context)
            // set some properties of rowTextView or something
            rowfoodView.text = i.foodname
            rowfoodView.textSize = 20f
            rowQuantityView.text ="x"+ i.quantity.toString()
            rowQuantityView.textSize = 20f
            holder.foodLabel.addView(rowfoodView)
            holder.foodQuantity.addView(rowQuantityView)
        }
        holder.buDone.setOnClickListener {

            vibrate.vibrate(100)

            val alert = AlertDialog.Builder(context)
            alert.setTitle("Warning")
                .setMessage("Are you sure ?")
                .setPositiveButton("Yes") { dialog, which ->
                    this.docRef.whereEqualTo("transactionId", chefItems.get(position).transactionId.toString()).get()
                        .addOnCompleteListener { task ->

                            if (task.isSuccessful) {
                                for (doc in task.result!!.iterator()) {
                                    docRef.document(doc.id).update("orderStatus", "complete")
                                    chefItems.removeAt(position)
                                    notifyDataSetChanged()
                                }
                            } else {
                                Log.d("fail", "***************************************FAil")
                            }
                        }

                }.setNegativeButton("No"){ dialog, which ->
                    dialog.dismiss()
                }.create().show()

        }

    }

    class MyViewHolder(view:View): RecyclerView.ViewHolder(view){

        var ordView:TextView
        var timeView: TextView
        var foodLabel:LinearLayout
        var foodQuantity:LinearLayout
        var buDone:Button

        init {
            ordView = view.findViewById(R.id.ordView)
            timeView = view.findViewById(R.id.timeView)
            foodLabel = view.findViewById(R.id.foodLabel)
            foodQuantity = view.findViewById(R.id.foodQuantity)
            buDone = view.findViewById(R.id.buDone)
        }
    }
}