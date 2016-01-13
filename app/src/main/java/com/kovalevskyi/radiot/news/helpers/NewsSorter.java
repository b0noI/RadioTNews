package com.kovalevskyi.radiot.news.helpers;

import com.kovalevskyi.radiot.news.model.News;

import java.util.Comparator;

public class NewsSorter implements Comparator<News> {

    @Override
    public int compare(News lhs, News rhs) {
        if (lhs.getActive()) return -1;
        if (rhs.getActive()) return 1;
        if (lhs.getVotes() > rhs.getVotes()) return -1;
        if (rhs.getVotes() > lhs.getVotes()) return 1;
        return 0;
    }

}
