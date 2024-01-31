package com.example.randomuser.presentation.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.paging.LoadState
import androidx.paging.LoadStateAdapter
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.example.randomuser.databinding.PartLoadStateBinding

typealias TryAgain = () -> Unit

class UserLoaderStateAdapter(private val tryAgainAction: TryAgain) :
    LoadStateAdapter<UserLoaderStateAdapter.HolderState>() {

    override fun onBindViewHolder(
        holder: UserLoaderStateAdapter.HolderState,
        loadState: LoadState
    ) {
        holder.bind(loadState)
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        loadState: LoadState
    ): UserLoaderStateAdapter.HolderState {
        val inflater = LayoutInflater.from(parent.context)
        val binding = PartLoadStateBinding.inflate(inflater, parent, false)
        return HolderState(binding, null, tryAgainAction)
    }

    inner class HolderState(
        private val binding: PartLoadStateBinding,
        private val swipeRefreshLayout: SwipeRefreshLayout?,
        private val tryAgainAction: TryAgain
    ) : RecyclerView.ViewHolder(binding.root) {

        init {
            binding.tryAgainButton.setOnClickListener {
                tryAgainAction()
            }
        }

        fun bind(loadState: LoadState) = with(binding) {
            messageTextView.isVisible = loadState is LoadState.Error
            tryAgainButton.isVisible = loadState is LoadState.Error
            if (swipeRefreshLayout != null) {
                swipeRefreshLayout.isRefreshing = loadState is LoadState.Loading
                progressBar.isVisible = false
            } else {
                progressBar.isVisible = loadState is LoadState.Loading
            }
        }
    }
}