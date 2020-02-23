package com.dbsrm.todolistdsc

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.*
import kotlinx.android.synthetic.main.activity_todo_home.*
import kotlinx.android.synthetic.main.activity_todo_home.btnAddNew as btnAddNew1


class TodoHomeActivity : AppCompatActivity() {


    var ourdoes: RecyclerView? = null
    private var reference: DatabaseReference? = null
    private var list: ArrayList<ToDoes>? = null
    var doesAdapter: ToDoAdapter? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_todo_home)


        signout_button.setOnClickListener {
            FirebaseAuth.getInstance().signOut()
            startActivity(Intent(this, LoginActivity::class.java))
            finish()
        }


            btnAddNew1.setOnClickListener(object : View.OnClickListener {
                override fun onClick(v: View) {
                    val a = Intent(this@TodoHomeActivity, NewTask::class.java)
                    startActivity(a)
                }
            })

        ourdoes = findViewById(R.id.ourdoes)
        ourdoes!!.setLayoutManager(LinearLayoutManager(this))
        list = ArrayList<ToDoes>()

            reference = FirebaseDatabase.getInstance().getReference().child("DoesApp")
            reference!!.addValueEventListener(object : ValueEventListener {
                override fun onDataChange(dataSnapshot: DataSnapshot) {

                    for (dataSnapshot1 in dataSnapshot.getChildren()) {
                        val p = dataSnapshot1.getValue(ToDoes::class.java)
                        list!!.add(p!!)
                    }
                   doesAdapter = ToDoAdapter(this@TodoHomeActivity, list!!)
                    ourdoes!!.setAdapter(doesAdapter)
                    doesAdapter!!.notifyDataSetChanged()

                }

                override fun onCancelled(databaseError: DatabaseError) {

                    Toast.makeText(getApplicationContext(), "No Data", Toast.LENGTH_SHORT).show()
                }

            })
    }
}