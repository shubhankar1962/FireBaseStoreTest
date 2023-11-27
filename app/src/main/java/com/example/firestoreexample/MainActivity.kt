package com.example.firestoreexample

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.firestoreexample.databinding.ActivityMainBinding
import com.google.firebase.Firebase
import com.google.firebase.firestore.firestore

class MainActivity : AppCompatActivity() {

    lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val db  = Firebase.firestore


        binding.submitBtn.setOnClickListener{
            val name= binding.etName.text.toString()
            val password =binding.etPassword.text.toString()

            val data  = User(name,password)

            db.collection("users")
                .add(data)
                .addOnSuccessListener {
                    Toast.makeText(this,"successful",Toast.LENGTH_SHORT).show()
                }
                .addOnFailureListener{
                    Toast.makeText(this,"fail",Toast.LENGTH_SHORT).show()

                }

        }

        binding.readBtn.setOnClickListener{
            val data  = db.collection("users")
                .get()
                .addOnSuccessListener {result->
                    for(document in result)
                    {
                        Toast.makeText(this,"data is ${document.id}==${document.data}",Toast.LENGTH_LONG).show()
                    }

                }
        }
    }


}