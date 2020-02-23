package com.dbsrm.todolistdsc

import android.content.Intent
import android.graphics.Typeface
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.*
import kotlinx.android.synthetic.main.activity_todo_home.*
import kotlinx.android.synthetic.main.activity_todo_home.btnAddNew as btnAddNew1
import kotlinx.android.synthetic.main.activity_todo_home.endpage as endpage1
import kotlinx.android.synthetic.main.activity_todo_home.ourdoes as ourdoes1
import kotlinx.android.synthetic.main.activity_todo_home.subtitlepage as subtitlepage1
import kotlinx.android.synthetic.main.activity_todo_home.titlepage as titlepage1

class TodoHomeActivity : AppCompatActivity() {


    private lateinit var reference: DatabaseReference
    private lateinit var list: ArrayList<ToDoes>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_todo_home)

        signout_button.setOnClickListener {
            FirebaseAuth.getInstance().signOut()
            startActivity(Intent(this, LoginActivity::class.java))
            finish()
        }

            // import font
            val MLight = Typeface.createFromAsset(getAssets(), "fonts/ML.ttf")
            val MMedium = Typeface.createFromAsset(getAssets(), "fonts/MM.ttf")
            // customize font
            titlepage1.setTypeface(MMedium)
            subtitlepage1.setTypeface(MLight)
            endpage1.setTypeface(MLight)
            btnAddNew1.setTypeface(MLight)
            btnAddNew1.setOnClickListener(object : View.OnClickListener {
                override fun onClick(v: View) {
                    val a = Intent(this@TodoHomeActivity, NewTask::class.java)
                    startActivity(a)
                }
            })
            // working with data
            ourdoes1.setLayoutManager(LinearLayoutManager(this))
            list = ArrayList<ToDoes>()
            // get data from firebase
            reference = FirebaseDatabase.getInstance().getReference().child("DoesApp")
            reference.addValueEventListener(object : ValueEventListener {
                override fun onDataChange(dataSnapshot: DataSnapshot) {
                    // set code to retrive data and replace layout
                    for (dataSnapshot1 in dataSnapshot.getChildren()) {
                        val p = dataSnapshot1.getValue(ToDoes::class.java)
                        list.add(p!!)
                    }
                    ToDoAdapter = ToDoAdapter(this@TodoHomeActivity, list)
                    ourdoes1.setAdapter(ToDoAdapter)
                    ToDoAdapter.notifyDataSetChanged()
                }

                override fun onCancelled(databaseError: DatabaseError) {
                    // set code to show an error
                    Toast.makeText(getApplicationContext(), "No Data", Toast.LENGTH_SHORT).show()
                }

            })
    }

}