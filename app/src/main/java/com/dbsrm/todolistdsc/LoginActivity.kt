package com.dbsrm.todolistdsc

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.activity_login.*

class LoginActivity : AppCompatActivity() {
    var uemail:String ?=null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        noacc.setOnClickListener {
            startActivity(Intent(this,RegisterActivity::class.java))
        }

        signin_button.setOnClickListener {
            signinUser()
        }

        /*val user = FirebaseAuth.getInstance().currentUser
        if (user != null) {
            user?.let {
                val uemail = user.email.toString()
            }
            val slicedemail:String? = uemail!!.substring(0,uemail!!.indexOf('@'))
            val intent = Intent(this,TodoHomeActivity::class.java)
            intent.putExtra("username",slicedemail)
            startActivity(intent)
        }*/

    }

    private fun signinUser() {

        val email = login_email.text.toString()
        val password = login_password.text.toString()
        val slicedemail:String? = email.substring(0,email.indexOf('@'))

        if (email.isEmpty() || password.isEmpty()) {
            Toast.makeText(this, "Fill the details", Toast.LENGTH_SHORT).show()
            return
        }

        FirebaseAuth.getInstance().signInWithEmailAndPassword(email, password)
            .addOnCompleteListener {
                if(!it.isSuccessful) return@addOnCompleteListener

                val intent = Intent(this,TodoHomeActivity::class.java)
                intent.putExtra("username",slicedemail)
                startActivity(intent)
                finish()
            }
            .addOnFailureListener {
                Toast.makeText(this, "failed to SignIn user: ${it.message}", Toast.LENGTH_SHORT).show()
            }
    }
}
