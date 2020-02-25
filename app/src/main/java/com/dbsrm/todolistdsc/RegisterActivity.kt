package com.dbsrm.todolistdsc

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.firestore.auth.User
import kotlinx.android.synthetic.main.activity_register.*

class RegisterActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        signup_button.setOnClickListener {
            registerUser()
        }

        alreadyacc_button.setOnClickListener {
            startActivity(Intent(this,LoginActivity::class.java))
        }
    }

    private fun registerUser() {

        val email = email.text.toString()
        val password = password.text.toString()
       // val name = name.text.toString()

        val slicedemail:String? = email.substring(0,email.indexOf('@'))

        if (email.isEmpty() || password.isEmpty()) {
            Toast.makeText(this, "Fill the details", Toast.LENGTH_SHORT).show()
            return
        }

        FirebaseAuth.getInstance().createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener {
                if (!it.isSuccessful) return@addOnCompleteListener
                FirebaseDatabase.getInstance().getReference().child("DoesApp").child(slicedemail.toString()).setValue("")
                val intent = Intent(this,TodoHomeActivity::class.java)
                intent.putExtra("username",slicedemail)
                startActivity(intent)
                finish()
            }
            .addOnFailureListener {
                Toast.makeText(this, "failed to create user: ${it.message}", Toast.LENGTH_SHORT).show()
            }
    }
}

