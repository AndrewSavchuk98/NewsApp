package com.example.savchuk.newsappmvvm.ui.fragments

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebChromeClient
import android.webkit.WebViewClient
import android.widget.Toast
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavArgs
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.savchuk.newsappmvvm.R
import com.example.savchuk.newsappmvvm.adapters.NewsAdapter
import com.example.savchuk.newsappmvvm.databinding.FragmentArticleBinding
import com.example.savchuk.newsappmvvm.databinding.FragmentSearchNewsBinding
import com.example.savchuk.newsappmvvm.ui.viewmodel.NewsViewModel
import com.example.savchuk.newsappmvvm.utils.Constantes
import com.example.savchuk.newsappmvvm.utils.Resource
import com.google.android.material.snackbar.Snackbar
import kotlinx.coroutines.Job
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class ArticleFragment : Fragment() {

    private var _binding: FragmentArticleBinding? = null
    private val binding get() = _binding!!

    private val viewModel: NewsViewModel by lazy{
        ViewModelProvider(this).get(NewsViewModel::class.java)
    }
    val args: ArticleFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentArticleBinding.inflate(inflater, container, false)
        val view = binding.root
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val article = args.article
        binding.wvArticle.apply {
            webViewClient = WebViewClient()
            loadUrl(article.url)
        }

        binding.fab.setOnClickListener {
            viewModel.saveArticle(article)
            //Snackbar.make(view, "Article saved successfully", Snackbar.LENGTH_SHORT).show()
            Toast.makeText(context, "Article saved successfully", Toast.LENGTH_SHORT).show()
        }

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}