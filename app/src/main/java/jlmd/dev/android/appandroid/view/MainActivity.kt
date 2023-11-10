package jlmd.dev.android.appandroid.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import jlmd.dev.android.appandroid.R
import jlmd.dev.android.appandroid.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        showPhotosFragment()
    }

    private fun showPhotosFragment() {
        supportFragmentManager.beginTransaction()
            .replace(R.id.fragment_container, PhotosFragment())
            .commit()
    }
}