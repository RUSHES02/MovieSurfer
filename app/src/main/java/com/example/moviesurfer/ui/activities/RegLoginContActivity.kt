package com.example.moviesurfer.ui.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import com.example.moviesurfer.utils.LogReg
import com.example.moviesurfer.R
import com.example.moviesurfer.databinding.ActivityRegLoginContBinding
import com.example.moviesurfer.ui.fragments.LoginFragment
import com.example.moviesurfer.ui.fragments.RegistrationFragment

class RegLoginContActivity : AppCompatActivity() {

    var pageMode = LogReg.REGISTERATION
    private lateinit var binding: ActivityRegLoginContBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityRegLoginContBinding.inflate(layoutInflater)
        setContentView(binding.root)

        window.statusBarColor = ContextCompat.getColor(this, R.color.black)

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
