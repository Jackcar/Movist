package com.gft.listapp.Features.News

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.gft.listapp.Model.Post
import com.gft.listapp.R
import kotlinx.android.synthetic.main.news_item.view.*

/**
 * Created by Jackson on 29/09/17.
 */
class NewsListAdapter : RecyclerView.Adapter<NewsListAdapter.ViewHolder> {

    private lateinit var mPosts: List<Post>

    constructor(posts: List<Post>) {
        setData(posts)
    }

    fun setData(posts: List<Post>) {
        mPosts = posts
        notifyDataSetChanged()
    }

    override fun getItemCount() = mPosts.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) = holder.bind(mPosts[position])

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.news_item, parent, false)
        return ViewHolder(view)
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(post: Post) = with(itemView) {
            tv_post_id.text = post.id.toString()
            tv_post_title.text = post.title
            tv_post_message.text = post.body
        }
    }

}