package com.kovalevskyi.radiot.news;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.kovalevskyi.radiot.news.model.News;
import com.kovalevskyi.radiot.news.views.NewsView;

import java.util.Collections;
import java.util.List;

public class NewsCardsAdapter extends RecyclerView.Adapter<NewsCardsAdapter.ViewHolder> {

    private List<News> news = Collections.emptyList();

    public void setNews(List<News> news) {
        this.news = news;
        notifyDataSetChanged();
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        NewsView v = (NewsView) LayoutInflater.from(parent.getContext())
                .inflate(R.layout.news_card, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.newsView.setNews(news.get(position));
    }

    @Override
    public int getItemCount() {
        return news.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        private NewsView newsView;

        public ViewHolder(NewsView v) {
            super(v);
            newsView = v;
        }
    }

}
