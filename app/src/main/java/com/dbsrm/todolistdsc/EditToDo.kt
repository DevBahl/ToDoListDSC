package com.dbsrm.todolistdsc

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.google.firebase.database.*
import kotlinx.android.synthetic.main.activity_edit_to_do.*

class EditToDo : AppCompatActivity() {


    private lateinit var reference: DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_edit_to_do)

        titledoes.setText(Intent().getStringExtra("titledoes"))
        descdoes.setText(Intent().getStringExtra("descdoes"))
        datedoes.setText(Intent().getStringExtra("datedoes"))

        var keykeyDoes = Intent().getStringExtra("keydoes")

        btnupdate!!.setOnClickListener(object : View.OnClickListener {
            override fun onClick(v: View) {
                // insert data to database
                reference = FirebaseDatabase.getInstance().getReference().child("DoesApp")
                    .child("Does" + keykeyDoes)
                reference!!.addValueEventListener(object : ValueEventListener {
                    override fun onDataChange(dataSnapshot: DataSnapshot) {
                        dataSnapshot.getRef().child("titledoes")
                            .setValue(titledoes.getText().toString())
                        dataSnapshot.getRef().child("descdoes")
                            .setValue(descdoes.getText().toString())
                        dataSnapshot.getRef().child("datedoes")
                            .setValue(datedoes.getText().toString())
                        dataSnapshot.getRef().child("keydoes").setValue(keykeyDoes)
                        val a = Intent(this@EditToDo, TodoHomeActivity::class.java)
                        startActivity(a)
                    }

                    override fun onCancelled(databaseError: DatabaseError) {
                    }
                })
            }
        })
    }
}
