package com.example.moviesurfer.ui.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import com.example.moviesurfer.utils.LogReg
import com.example.moviesurfer.R
import com.example.moviesurfer.databinding.ActivityRegLoginContBinding
import com.example.moviesurfer.ui.activities.main.MainActivity
import com.example.moviesurfer.ui.fragments.LoginFragment
import com.example.moviesurfer.ui.fragments.RegistrationFragment
import com.google.firebase.auth.FirebaseAuth

class RegLoginContActivity : AppCompatActivity() {

    var pageMode = LogReg.REGISTERATION
    private lateinit var binding: ActivityRegLoginContBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityRegLoginContBinding.inflate(layoutInflater)
        setContentView(binding.root)

        window.statusBarColor = ContextCompat.getColor(this, R.color.black)

        val auth = FirebaseAuth.getInstance().currentUser

        if(auth != null){

            Intent(this, MainActivity::class.java)
        }
        binding.textSwitchRegLog.text = getString(R.string.concat, getString(R.string.don_t_have_an_account), getString(R.string.sign_up))

        binding.textSwitchRegLog.setOnClickListener {
            if (pageMode == LogReg.REGISTERATION) {
                binding.textSwitchRegLog.text = getString(R.string.concat, getString(R.string.don_t_have_an_account), getString(R.string.sign_up))
                changeFragment(LoginFragment())
                pageMode = LogReg.LOGIN
            } else {
                binding.textSwitchRegLog.text = getString(R.string.concat, getString(R.string.already_have_an_account), getString(R.string.sign_in))
                changeFragment(RegistrationFragment())
                pageMode = LogReg.REGISTERATION
            }
        }

    }

    private fun changeFragment(fragment: Fragment) {
        val fragmentManager = supportFragmentManager
        fragmentManager.beginTransaction()
            .replace(R.id.fragContRegLog, fragment)
            .commit()
    }
}
