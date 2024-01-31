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
import androidx.navigation.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.randomuser.databinding.FragmentMainBinding
import com.example.randomuser.presentation.adapter.TryAgain
import com.example.randomuser.presentation.adapter.UserLoaderStateAdapter
import com.example.randomuser.presentation.adapter.UsersAdapter
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@AndroidEntryPoint
class MainFragment : Fragment() {

    private var _binding: FragmentMainBinding? = null
    private val binding get() = _binding!!

    private val viewModel by viewModels<MainViewModel>()
    private lateinit var usersAdapter: UsersAdapter
    private lateinit var tryAgain: TryAgain
    private lateinit var userLoaderStateAdapter: UserLoaderStateAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentMainBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initAdapter()

        usersAdapter.setOnItemClickListener {
            val args = MainFragmentDirections.actionMainFragmentToDetailsFragment(it)
            view.findNavController().navigate(args)
        }

        viewLifecycleOwner.lifecycleScope.launch {
            lifecycle.repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.user.collectLatest {

                    usersAdapter.submitData(it)
                }
            }
        }
    }

    private fun initAdapter() {
        usersAdapter = UsersAdapter()
        tryAgain = { usersAdapter.retry()}
        userLoaderStateAdapter = UserLoaderStateAdapter(tryAgain)
        val adapterWithLoadState = usersAdapter.withLoadStateFooter(userLoaderStateAdapter)
        binding.userAdapter.apply {
            adapter = adapterWithLoadState
            layoutManager = LinearLayoutManager(activity)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}