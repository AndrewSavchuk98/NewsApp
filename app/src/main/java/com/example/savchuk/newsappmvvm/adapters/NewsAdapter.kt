package com.example.savchuk.newsappmvvm.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import coil.load
import coil.size.ViewSizeResolver
import com.example.savchuk.newsappmvvm.R
import com.example.savchuk.newsappmvvm.databinding.ItemArticlePreviewBinding
import com.example.savchuk.newsappmvvm.data.models.Article

class NewsAdapter : RecyclerView.Adapter<NewsAdapter.NewsViewHolder>() {


    inner class NewsViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val binding = ItemArticlePreviewBinding.bind(itemView)
    }

    private val differCallBack = object : DiffUtil.ItemCallback<Article>() {
        override fun areItemsTheSame(oldItem: Article, newItem: Article): Boolean {
            return oldItem.url == newItem.url
        }

        override fun areContentsTheSame(oldItem: Article, newItem: Article): Boolean {
            return oldItem == newItem
        }

    }

    val differ = AsyncListDiffer(this, differCallBack)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewsViewHolder {
        return NewsViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.item_article_preview, parent, false)
        )
    }

    override fun onBindViewHolder(holder: NewsViewHolder, position: Int) {
        val article = differ.currentList[position]
        holder.binding.apply {
            ivArticleImage.load(article.urlToImage) {
                crossfade(true)
                placeholder(R.drawable.ic_launcher_foreground)
                size(ViewSizeResolver(ivArticleImage))
            }
            tvSource.text = article.source.name
            tvTitle.text = article.title
            tvDescription.text = article.description
            tvPublishedAt.text = article.publishedAt
        }

        holder.itemView.setOnClickListener {
            onItemClickListener?.let {
                it(article)
            }
        }
    }

    override fun getItemCount(): Int {
        return differ.currentList.size
    }

    private var onItemClickListener: ((Article) -> Unit)? = null

    fun setOnItemClickListener(listener: ((Article) -> Unit)) {
        onItemClickListener = listener
    }
}