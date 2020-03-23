package com.example.open_fsmvu

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.activity_sign_up.*

class SignUp : AppCompatActivity() {
    private lateinit var mAuth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_up)
        mAuth=FirebaseAuth.getInstance()
    }

    fun signUp(v: View){
        if (!txtControl()) return
        mAuth.createUserWithEmailAndPassword(txtSMail.text.toString(),txtSPass.text.toString()).addOnCompleteListener(this){
            task ->
            if (task.isSuccessful){
                Toast.makeText(baseContext, "başarılı",Toast.LENGTH_LONG)
            }else{
                Toast.makeText(baseContext, task.exception.toString(),Toast.LENGTH_LONG)
            }

        }

    }
    fun txtControl(): Boolean{
        if(!txtSMail.text.toString().contains("fsm.edu.tr")){
            txtSMail.setError("Okul maili ile kayıt olunuz.")
            return false
        }
        if (!txtSPass.text.toString().equals(txtSPass2.text.toString())){
            txtSPass.setError("Şifreler uyuşmuyor.")
            txtSPass2.setError("Şifreler uyuşmuyor.")
            return false
        }
        return true
    }
}
