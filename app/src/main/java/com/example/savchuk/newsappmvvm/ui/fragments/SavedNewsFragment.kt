package com.example.savchuk.newsappmvvm.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.savchuk.newsappmvvm.R
import com.example.savchuk.newsappmvvm.adapters.NewsAdapter
import com.example.savchuk.newsappmvvm.databinding.FragmentSavedNewsBinding
import com.example.savchuk.newsappmvvm.ui.fragments.BreakingNewsFragment.Companion.ARTICLE_KEY
import com.example.savchuk.newsappmvvm.ui.viewmodel.NewsViewModel
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SavedNewsFragment : Fragment() {

    private var _binding: FragmentSavedNewsBinding? = null
    private val binding get() = _binding!!

    private val viewModel: NewsViewModel by viewModels()
    lateinit var newsAdapter: NewsAdapter


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentSavedNewsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupRecyclerView()

        newsAdapter.setOnItemClickListener { article ->
            findNavController().navigate(
                R.id.action_savedNewsFragment_to_articleFragment,
                bundleOf(ARTICLE_KEY to article)
            )
        }

        val itemTouchHelperCallBack = object : ItemTouchHelper.SimpleCallback(
            ItemTouchHelper.UP or ItemTouchHelper.DOWN,
            ItemTouchHelper.LEFT or ItemTouchHelper.RIGHT
        ) {
            override fun onMove(
                recyclerView: RecyclerView,
                viewHolder: RecyclerView.ViewHolder,
                target: RecyclerView.ViewHolder
            ): Boolean {
                return true
            }

            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                val pos = viewHolder.adapterPosition
                val article = newsAdapter.differ.currentList[pos]
                viewModel.deleteArticle(article)
                Snackbar.make(view, getString(R.string.success_delete), Snackbar.LENGTH_SHORT)
                    .apply {
                        setAction("Undo") {
                            viewModel.saveArticle(article)
                        }
                        show()
                    }
            }
        }

        ItemTouchHelper(itemTouchHelperCallBack).apply {
            attachToRecyclerView(binding.rvSavedNews)
        }

        viewModel.getSavedNews().observe(viewLifecycleOwner) {
            newsAdapter.differ.submitList(it)
        }
    }

    private fun setupRecyclerView() {
        newsAdapter = NewsAdapter()

        binding.rvSavedNews.apply {
            layoutManager = LinearLayoutManager(activity)
            adapter = newsAdapter
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}