package com.kovalevskyi.radiot.news.views;

import android.content.Context;
import android.support.v7.widget.CardView;
import android.util.AttributeSet;
import android.view.View;
import android.widget.TextView;

import com.kovalevskyi.radiot.news.R;
import com.kovalevskyi.radiot.news.model.News;

public class NewsView extends CardView {

    private TextView title;

    private TextView snippet;

    private TextView votes;

    private String id;

    public NewsView(Context context) {
        super(context);
    }

    public NewsView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public NewsView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public void setNews(final News news) {
        initView();
        title.setText(news.getTitle());
        snippet.setText(news.getSnippet());
        votes.setText(String.valueOf(news.getVotes()));
        id = news.getId();
    }

    private void initView() {
        if (title != null) return;
        title = (TextView) findViewById(R.id.title);
        snippet = (TextView) findViewById(R.id.snippet);
        votes = (TextView) findViewById(R.id.votes);
    }

}
