package com.hasna_19104045.modul11

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.api.ApiException
import com.google.android.material.snackbar.Snackbar
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.GoogleAuthProvider
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class SignInActivity : AppCompatActivity(), View.OnClickListener  {
    override fun onCreate(savedInstanceState: Bundle?) {
        private lateinit var auth: FirebaseAuth
        private lateinit var binding: ActivitySignInBinding
        private lateinit var googleSignInClient: GoogleSignInClient
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_in)

        binding = ActivitySignInBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnSign.setOnClickListener(this)
        binding.tvSignUp.setOnClickListener(this)
        binding.btnEmail.setOnClickListener(this)
        binding.btnPhone.setOnClickListener(this)

        auth = Firebase.auth

        val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestIdToken(getString(R.string.default_web_client_id))
            .requestEmail()
            .build()
        googleSignInClient = GoogleSignIn.getClient(this, gso)
    }

    override fun onClick(v: View) {
        TODO("Not yet implemented")
        when (v.id) {
            R.id.btnSign -> {
                signIn(binding.inputEmail.text.toString(),
                    binding.inputPassword.text.toString())
            } R
            .id.tvSignUp -> {
            val intent = Intent(this@SignInActivity, SignUpActivity::class.java)
            startActivity(intent)
            }
        }

        R.id.btnEmail -> {
            signIn()
        }
    }

    private fun signIn(email: String, password: String) {
        if (!validateForm()) {
            return
        }
        auth.signInWithEmailAndPassword(email, password)
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    finish()
                    val intent = Intent(this@SignInActivity,
                        MainActivity::class.java)
                    startActivity(intent)
                } else {
                    Toast.makeText(baseContext, "Authentication failed.",
                        Toast.LENGTH_SHORT).show()
                }
            }

        val signInIntent = googleSignInClient.signInIntent
        startActivityForResult(signInIntent, 9001)

    }

    public override fun onStart() {
        super.onStart()
        val currentUser = auth.currentUser
        if (currentUser != null) {
            val intent = Intent(this@SignInActivity, MainActivity::class.java)
            startActivityForResult(intent, 1)
            finish()
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == 9001) {
            val task = GoogleSignIn.getSignedInAccountFromIntent(data)
            try {
                val account = task.getResult(ApiException::class.java)!!
                firebaseAuthWithGoogle(account.idToken!!)
            } catch (e: ApiException) {
                val view = binding.mainLayout
                Snackbar.make(view, "Google sign in failed.",
                    Snackbar.LENGTH_SHORT).show()
            }
        }
    }

    private fun firebaseAuthWithGoogle(idToken: String) {
        val credential = GoogleAuthProvider.getCredential(idToken, null)
        auth.signInWithCredential(credential)
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    val user = auth.currentUser
                    val view = binding.mainLayout
                    Snackbar.make(view, "Authentication Success.",
                        Snackbar.LENGTH_SHORT).show()
                    val intent = Intent(this@SignInActivity, MainActivity::class.java)
                    startActivity(intent)
                    finish()
                } else {
                    val view = binding.mainLayout
                    Snackbar.make(view, "Authentication Failed.",
                        Snackbar.LENGTH_SHORT).show()
                }
            }
    }
}