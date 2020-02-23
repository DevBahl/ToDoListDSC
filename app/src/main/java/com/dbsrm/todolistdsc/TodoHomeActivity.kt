package com.dbsrm.todolistdsc

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.*
import kotlinx.android.synthetic.main.activity_register.*
import kotlinx.android.synthetic.main.activity_todo_home.*
import kotlinx.android.synthetic.main.activity_todo_home.btnAddNew as btnAddNew1


class TodoHomeActivity : AppCompatActivity() {


    var ourdoes: RecyclerView? = null
    private var reference: DatabaseReference? = null
    private var list: ArrayList<ToDoes>? = null
    var doesAdapter: ToDoAdapter? = null
    //private val sharedPefFile="uname"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_todo_home)


        signout_button.setOnClickListener {
            FirebaseAuth.getInstance().signOut()
            startActivity(Intent(this, LoginActivity::class.java))
            finish()
        }


        val intent = getIntent()
        val name = intent.getStringExtra("username")
        titlepage!!.setText("Hello "+name.toString())
       // val sharedprefrance:SharedPreferences= this.getSharedPreferences(sharedPefFile,Context.MODE_PRIVATE)
       // val editor:SharedPreferences.Editor=sharedprefrance.edit()
        //editor.putString("name",name.toString())
        //editor.apply()
        //editor.commit()
            btnAddNew1.setOnClickListener{
                    val a = Intent(this@TodoHomeActivity, NewTask::class.java)
                    a.putExtra("username",name)
                    startActivity(a)
                }


        ourdoes = findViewById(R.id.ourdoes)
        ourdoes!!.setLayoutManager(LinearLayoutManager(this))
        list = ArrayList<ToDoes>()

            reference = FirebaseDatabase.getInstance().getReference().child("DoesApp").child(name)

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