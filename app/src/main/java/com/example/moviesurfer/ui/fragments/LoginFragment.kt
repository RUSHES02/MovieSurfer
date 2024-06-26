package com.example.moviesurfer.ui.fragments

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.CollectionReference
import com.google.firebase.firestore.FirebaseFirestore
import androidx.lifecycle.ViewModelProvider
import com.example.moviesurfer.databinding.FragmentLoginBinding
import com.example.moviesurfer.modals.User
import com.example.moviesurfer.ui.activities.main.MainActivity
import com.example.moviesurfer.utils.CheckNetwork
import com.example.moviesurfer.utils.StoreUser
import com.example.moviesurfer.viewModel.UserViewModel

class LoginFragment : Fragment() {

    private lateinit var binding: FragmentLoginBinding
    private lateinit var firebaseAuth: FirebaseAuth
    private lateinit var dbRef : CollectionReference
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentLoginBinding.inflate(inflater, container, false)
        return binding.root
    }

    private lateinit var user : User
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        firebaseAuth = FirebaseAuth.getInstance()
        dbRef = FirebaseFirestore.getInstance().collection("User")
        binding.btnSignIn.setOnClickListener{
            if(CheckNetwork.isInternetAvailable(requireActivity())){
                user = User(
                    email = binding.editTextEmail.text.toString().trim(),
                    password = binding.editTextPass.text.toString(),
                    name = null,
                    picture = null
                )
                loginUser(user)
            }else{
                Toast.makeText(context, "No Internet", Toast.LENGTH_SHORT).show()
            }

        }

    }


    private fun loginUser(user: User){
        firebaseAuth.signInWithEmailAndPassword(user.email, user.password)
            .addOnSuccessListener {
                Toast.makeText(context, "Login Successfully", Toast.LENGTH_SHORT).show()
                binding.editTextEmail.setText("")
                binding.editTextPass.setText("")
                getUserDetails(user.email)

            }.addOnFailureListener {
                Toast.makeText(context, "Login Failed", Toast.LENGTH_SHORT).show()
            }
    }

    private fun getUserDetails(email : String){
        dbRef.document(email).get()
            .addOnSuccessListener{document ->
                if (document.exists()) {
                    Log.d("fetched user", document.toString())
                    Toast.makeText(context, "Document Successful", Toast.LENGTH_SHORT).show()
                    val userViewModel = ViewModelProvider(this)[UserViewModel::class.java]
                    val userModel = userViewModel.convertToUserModel(document)
                    StoreUser.saveData(userModel, requireActivity())
                    val intent = Intent(requireActivity(), MainActivity::class.java)
                    startActivity(intent)
                } else {
                    Toast.makeText(context, "Document Failed", Toast.LENGTH_SHORT).show()
                    Log.d("fetched user", "failed")
                }
            }
    }

}