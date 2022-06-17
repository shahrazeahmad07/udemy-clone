package com.example.udemyclone

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

class LoginActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
    }
}


/*
class LoginActivity : AppCompatActivity() {
    //VIEW BINDING
    private lateinit var binding: ActivityLoginBinding
    //action bar

    //progressDialog h
    private lateinit var progressDialog: ProgressDialog
    //firebase auth
    private lateinit var firebaseAuth: FirebaseAuth
    private var email=""
    private var password =""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //configure progress dialog
        progressDialog = ProgressDialog(this)
        progressDialog.setTitle("please wait")
        progressDialog.setMessage("Logging in---")
        progressDialog.setCanceledOnTouchOutside(false)
        // init firebase
        firebaseAuth = FirebaseAuth.getInstance()
        checkUser()
//handle register activity,open signup
        binding.noAccountTv.setOnClickListener{
            startActivity(Intent(this, SignupActivity::class.java))
        }
//handle click, begin login
        binding.loginBtn.setOnClickListener{
            validateData()
        }

    }
    private fun validateData(){

        email=binding.emailEt.text.toString().trim()
        password=binding.passwordEt.text.toString().trim()
        //validate data
        if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()){
            binding.emailEt.error="invalid email format"

        }
        else if (TextUtils.isEmpty(password)){
            binding.passwordEt.error="please enter password"
        }
        else
        {
            firebaseLogin()
        }
    }
    private fun firebaseLogin(){
        //show progress
        progressDialog.show()
        firebaseAuth.signInWithEmailAndPassword(email, password)
            .addOnSuccessListener {
                //login success
                progressDialog.dismiss()
                val firebaseUser= firebaseAuth.currentUser
                val email = firebaseUser!!.email
                Toast.makeText(this, "LOGIN  Successfully as $email", Toast.LENGTH_SHORT).show()
                //open profile

                startActivity(Intent(this,ProfileActivity::class.java))
                finish()
            }
            .addOnFailureListener { e->
                //login failed
                progressDialog.dismiss()
                Toast.makeText(this, "LOGIN FAILED DUE TO ${e.message}", Toast.LENGTH_SHORT).show()

            }
    }

    private fun checkUser() {
        // if user is already login to activity .get current user
        val firebaseUser= firebaseAuth.currentUser
        if(firebaseUser != null)
        {
            startActivity(Intent(this, ProfileActivity::class.java) )
            finish()

        }
    }


 */