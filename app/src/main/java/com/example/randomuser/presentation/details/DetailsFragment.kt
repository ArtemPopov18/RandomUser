package com.example.randomuser.presentation.details

import android.os.Build
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import coil.load
import com.example.randomuser.databinding.FragmentDetailsBinding
import com.example.randomuser.presentation.model.UserUI

class DetailsFragment : Fragment() {

    private var _binding: FragmentDetailsBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentDetailsBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val userUI = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            requireArguments().getSerializable(KEY, UserUI::class.java)
        } else {
            TODO("VERSION.SDK_INT < TIRAMISU")
        }

        with(binding) {
            imagePhoto.load(userUI?.picture?.large)
            textName.text = userUI?.getUserName() ?: ""
            textEmail.text = userUI?.email ?: ""
            textBirthday.text = userUI?.dob?.date ?: ""
            textAddress.text = userUI?.getLocation() ?: ""
            textPhoneNumber.text = userUI?.phone
        }
    }

    //Todo задать value типо модели
    companion object {
        private const val KEY = "DETAILS"

        fun newInstance(userUI: UserUI) = DetailsFragment().apply {
            arguments = Bundle().apply {
                putSerializable(KEY, userUI)
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}