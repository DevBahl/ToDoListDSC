package com.dbsrm.todolistdsc

import android.content.Intent
import android.graphics.Typeface
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.database.*
import java.util.*

class NewTask : AppCompatActivity() {

    var titlepage:TextView? = null
    var addtitle:TextView? = null
    var adddesc: TextView? = null
    var adddate:TextView? = null
    var titledoes:EditText? = null
    var descdoes: EditText? = null
    var datedoes:EditText? = null
    var btnSaveTask:Button? = null
    var btnCancel: Button? = null
    var reference: DatabaseReference? = null
    var doesNum = Random().nextInt()
    var keydoes = Integer.toString(doesNum)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.new_task)
        titlepage = findViewById(R.id.titlepage)
        addtitle = findViewById(R.id.addtitle)
        adddesc = findViewById(R.id.adddesc)
        adddate = findViewById(R.id.adddate)
        titledoes = findViewById(R.id.titledoes)
        descdoes = findViewById(R.id.descdoes)
        datedoes = findViewById(R.id.datedoes)
        btnSaveTask = findViewById(R.id.btnSaveTask)
        btnCancel = findViewById(R.id.btnCancel)

        btnCancel!!.setOnClickListener {
            val intent = Intent(this,TodoHomeActivity::class.java)
            startActivity(intent)
        }
        val intent = getIntent()
        val name = intent.getStringExtra("username")
        btnSaveTask!!.setOnClickListener(object: View.OnClickListener {
            override fun onClick(v:View) {
                reference = FirebaseDatabase.getInstance().getReference().child("DoesApp").child(name).child("Does" + doesNum)
                reference!!.addValueEventListener(object: ValueEventListener {
                    override fun onDataChange(dataSnapshot: DataSnapshot) {
                        dataSnapshot.getRef().child("titledoes").setValue(titledoes!!.getText().toString())
                        dataSnapshot.getRef().child("descdoes").setValue(descdoes!!.getText().toString())
                        dataSnapshot.getRef().child("datedoes").setValue(datedoes!!.getText().toString())
                        dataSnapshot.getRef().child("keydoes").setValue(keydoes)

                    }
                    override fun onCancelled(databaseError: DatabaseError) {
                    }

                })
                val a = Intent(this@NewTask, TodoHomeActivity::class.java)
                startActivity(a)
            }
        })
    }


}