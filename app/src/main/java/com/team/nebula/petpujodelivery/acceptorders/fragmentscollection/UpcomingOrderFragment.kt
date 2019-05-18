package com.team.nebula.petpujodelivery.acceptorders.fragmentscollection

import android.content.Context
import android.net.Uri
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.ListView
import androidx.appcompat.app.AlertDialog
import com.google.firebase.firestore.*

import com.team.nebula.petpujodelivery.R
import org.jetbrains.anko.support.v4.toast
import org.jetbrains.anko.toast

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Activities that contain this fragment must implement the
 * [UpcomingOrderFragment.OnFragmentInteractionListener] interface
 * to handle interaction events.
 * Use the [UpcomingOrderFragment.newInstance] factory method to
 * create an instance of this fragment.
 *
 */
class UpcomingOrderFragment : Fragment() {

    lateinit var foodNames:ArrayList<String>
    val db = FirebaseFirestore.getInstance()
    var dc: ListenerRegistration? = null


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val rootView = inflater.inflate(R.layout.fragment_upcoming_order, container, false)
        val docRef = db.collection("publish-cafeteria")
        foodNames= ArrayList()

        val listView = rootView.findViewById<ListView>(R.id.lView)
        var adapter = ArrayAdapter(activity!!.applicationContext,R.layout.food_layout,R.id.plfoodView,foodNames)
        listView.adapter=adapter

        dc = docRef.orderBy("foodname").addSnapshotListener(EventListener<QuerySnapshot>{snapshots ,e ->
            if (e != null) {
                Log.w("Errr", "listen:error", e)
                return@EventListener
            }



            for (dc in snapshots!!.documentChanges) {
                when (dc.type) {
                    DocumentChange.Type.ADDED -> {
                        val foods = dc.document.get("foodname")
                        foodNames.add(foods.toString())
                        adapter.notifyDataSetChanged()
                    }
                    DocumentChange.Type.REMOVED ->{
                        foodNames.remove(dc.document.get("foodname"))
                        adapter.notifyDataSetChanged()
                    }
                }

            }
        })

        listView.setOnItemLongClickListener {parent, view, position, id ->

            val alert = AlertDialog.Builder(activity!!)
            alert.setTitle("Warning")
                .setMessage("Are you sure ?")
                .setPositiveButton("Yes") { dialog, which ->
                docRef.whereEqualTo("foodname",foodNames.get(position)).get().addOnCompleteListener {task->

                        if (task.isSuccessful){
                            for (doc in task.result!!.iterator()){
                                docRef.document(doc.id).delete()
                            }
                        } else{
                            toast(position.toString())
                        }
                    }
                }.setNegativeButton("No"){ dialog, which ->
                    dialog.dismiss()
                }.create().show()




            true
        }

        return rootView
    }

    override fun onPause() {
        if (dc!=null){
            dc!!.remove()
        }
        super.onPause()
    }
}
