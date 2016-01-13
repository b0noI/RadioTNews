package com.kovalevskyi.radiot.news;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.kovalevskyi.radiot.news.helpers.NewsSorter;
import com.kovalevskyi.radiot.news.model.News;
import com.kovalevskyi.radiot.news.model.NewsService;
import com.kovalevskyi.radiot.news.network.RetrofitServiceHelper;
import com.kovalevskyi.radiot.news.views.NewsView;

import java.util.Collections;
import java.util.List;

import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class MainActivity extends AppCompatActivity {

    private NewsView currentNews;

    private RecyclerView newsRecyclerView;

    private NewsCardsAdapter newsCardsAdapter = new NewsCardsAdapter();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        initViews();
        subscribeOnMainTopic();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void initViews() {
        currentNews = (NewsView) findViewById(R.id.current_news);
        newsRecyclerView = (RecyclerView) findViewById(R.id.news_recycler_view);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        newsRecyclerView.setHasFixedSize(true);
        newsRecyclerView.setLayoutManager(layoutManager);
        newsRecyclerView.setAdapter(newsCardsAdapter);
    }

    private void subscribeOnMainTopic() {
        NewsService service = RetrofitServiceHelper.createRetrofitService(NewsService.class, NewsService.NEWS_ENDPOINT);
        service
                .getActiveNews()
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<News>() {
                    @Override
                    public void onCompleted() {
                    }

                    @Override
                    public void onError(Throwable e) {
                        e.printStackTrace();
                    }

                    @Override
                    public void onNext(News news) {
                        currentNews.setNews(news);
                    }
                });
        service
                .getAllNews()
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<List<News>>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onNext(List<News> newses) {
                        Collections.sort(newses, new NewsSorter());
                        newsCardsAdapter.setNews(newses);
                    }
                });

    }
}
