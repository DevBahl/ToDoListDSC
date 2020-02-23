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
import kotlinx.android.synthetic.main.item_do.*
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
        btnSaveTask!!.setOnClickListener(object: View.OnClickListener {
            override fun onClick(v:View) {
                // insert data to database
                reference = FirebaseDatabase.getInstance().getReference().child("DoesApp").child("Does" + doesNum)
                reference!!.addValueEventListener(object: ValueEventListener {
                    override fun onDataChange(dataSnapshot: DataSnapshot) {
                        dataSnapshot.getRef().child("titledoes").setValue(titledo.getText().toString())
                        dataSnapshot.getRef().child("descdoes").setValue(descdo.getText().toString())
                        dataSnapshot.getRef().child("datedoes").setValue(datedo.getText().toString())
                        dataSnapshot.getRef().child("keydoes").setValue(keydoes)
                        val a = Intent(this@NewTask, TodoHomeActivity::class.java)
                        startActivity(a)
                    }
                    override fun onCancelled(databaseError: DatabaseError) {
                    }
                })
            }
        })
        // import font
        val MLight = Typeface.createFromAsset(getAssets(), "fonts/ML.ttf")
        val MMedium = Typeface.createFromAsset(getAssets(), "fonts/MM.ttf")
        // customize font
        titlepage!!.setTypeface(MMedium)
        addtitle!!.setTypeface(MLight)
        titledoes!!.setTypeface(MMedium)
        adddesc!!.setTypeface(MLight)
        descdoes!!.setTypeface(MMedium)
        adddate!!.setTypeface(MLight)
        datedoes!!.setTypeface(MMedium)
        btnSaveTask!!.setTypeface(MMedium)
        btnCancel!!.setTypeface(MLight)
    }


}