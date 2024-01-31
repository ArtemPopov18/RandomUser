package com.example.randomuser.presentation.details

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Build
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.core.app.ActivityCompat
import androidx.navigation.fragment.navArgs
import coil.load
import com.example.randomuser.databinding.FragmentDetailsBinding

class DetailsFragment : Fragment() {

    private var _binding: FragmentDetailsBinding? = null
    private val binding get() = _binding!!
    val argsUserUI: DetailsFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentDetailsBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    @RequiresApi(Build.VERSION_CODES.TIRAMISU)
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val userUI = argsUserUI.userUI

        with(binding) {
            imagePhoto.load(userUI.picture?.large)
            textName.text = userUI.getUserName()
            textEmail.text = userUI.email
            textBirthday.text = userUI.dob?.date
            textAddress.text = userUI.getLocation()
            textPhoneNumber.text = userUI.phone

            textEmail.setOnClickListener {
                composeEmail(arrayOf(textEmail.text.toString()))
            }

            textPhoneNumber.setOnClickListener {
                checkPermissions(textPhoneNumber.text.toString())
            }

            textAddress.setOnClickListener {
                showMap(userUI?.geoLocationUri())
            }
        }
    }

    fun composeEmail(addresses: Array<String>) {
        val intent = Intent(Intent.ACTION_SENDTO).apply {
            data = Uri.parse("mailto:")
            putExtra(Intent.EXTRA_EMAIL, addresses)
        }
        if (intent.resolveActivity(requireActivity().packageManager) != null) {
            startActivity(intent)
        }
    }

    fun showMap(geoLocation: Uri?) {
        val intent = Intent(Intent.ACTION_VIEW).apply {
            data = geoLocation
        }
        if (intent.resolveActivity(requireActivity().packageManager) != null) {
            startActivity(intent)
        }
    }

    fun dialPhoneNumber(phoneNumber: String) {
        val intent = Intent(Intent.ACTION_DIAL).apply {
            data = Uri.parse("tel:$phoneNumber")
        }
        if (intent.resolveActivity(requireActivity().packageManager) == null) {
            startActivity(intent)
        }
    }

    private fun checkPermissions(phoneNumber: String) {
        if (ActivityCompat.checkSelfPermission(
                requireContext(),
                Manifest.permission.CALL_PHONE
            ) == PackageManager.PERMISSION_GRANTED
        ) {
            dialPhoneNumber(phoneNumber)
        } else {
            ActivityCompat.requestPermissions(
                requireActivity(),
                arrayOf(Manifest.permission.CALL_PHONE),
                PHONE_CALL
            )
        }
    }

    companion object {
        private const val PHONE_CALL = 1
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}