package com.example.randomuser.presentation.main

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.randomuser.databinding.FragmentMainBinding
import com.example.randomuser.presentation.UsersAdapter
import com.example.randomuser.presentation.details.DetailsFragment
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@AndroidEntryPoint
class MainFragment : Fragment() {

    private var _binding: FragmentMainBinding? = null
    private val binding get() = _binding!!

    private val viewModel by viewModels<ManeViewModel>()
    private lateinit var usersAdapter: UsersAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentMainBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    //Todo в DetailsFragment.newInstance() передать объект типа модели
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initAdapter()

        viewLifecycleOwner.lifecycleScope.launch {
            lifecycle.repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.user.collectLatest {
                    usersAdapter.submitData(it)
                }
            }
        }

//            val detailsFragment = DetailsFragment.newInstance("ПЕРЕДАЙ ОБЪЕКТ МОДЕЛИ")
//            requireActivity().supportFragmentManager.beginTransaction()
//                .add(R.id.fragment_container, detailsFragment)
//                .addToBackStack(detailsFragment.javaClass.simpleName)
//                .commit()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun initAdapter() {
        usersAdapter = UsersAdapter()
        binding.userAdapter.apply {
            adapter = usersAdapter
            layoutManager = LinearLayoutManager(activity)
        }
    }
}