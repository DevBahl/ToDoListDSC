package com.dbsrm.todolistdsc

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.google.firebase.database.*
import kotlinx.android.synthetic.main.activity_edit_to_do.*

class EditToDo : AppCompatActivity() {


    private lateinit var reference: DatabaseReference
    var name:String?=""
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_edit_to_do)
       // val sharedprefrance: SharedPreferences = this.getSharedPreferences("uname", Context.MODE_PRIVATE)
        //name=sharedprefrance.getString("name","default")
        //val editor: SharedPreferences.Editor=sharedprefrance.edit()
        titledoes.setText(Intent().getStringExtra("titledoes"))
        descdoes.setText(Intent().getStringExtra("descdoes"))
        datedoes.setText(Intent().getStringExtra("datedoes"))
        var keykeyDoes = Intent().getStringExtra("keydoes")
        reference = FirebaseDatabase.getInstance().getReference().child("DoesApp")
            .child("Does" + keykeyDoes)


        btndelete!!.setOnClickListener {
                reference!!.removeValue().addOnCompleteListener {
                    if(it.isSuccessful) return@addOnCompleteListener

                    else{
                        Toast.makeText(this,"Failed to delete",Toast.LENGTH_SHORT).show()
                    }
                }
            }

       /* btnupdate!!.setOnClickListener(object : View.OnClickListener {
            override fun onClick(v: View) {
                reference!!.addValueEventListener(object : ValueEventListener {
                    override fun onDataChange(dataSnapshot: DataSnapshot) {
                        dataSnapshot.getRef().child("DoesApp").child(name.toString()).child("titledoes")
                            .setValue(titledoes.getText().toString())
                        dataSnapshot.getRef().child("DoesApp").child(name.toString()).child("descdoes")
                            .setValue(descdoes.getText().toString())
                        dataSnapshot.getRef().child("DoesApp").child(name.toString()).child("datedoes")
                            .setValue(datedoes.getText().toString())
                        //dataSnapshot.getRef().child("keydoes").setValue(keykeyDoes)
                        val a = Intent(this@EditToDo, TodoHomeActivity::class.java)
                        startActivity(a)
                    }

                    override fun onCancelled(databaseError: DatabaseError) {
                    }
                })
            }
        })*/

        btnupdate!!.setOnClickListener {
            Toast.makeText(this,"Still Working on it",Toast.LENGTH_LONG).show()
        }
    }
}
