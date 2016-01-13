package com.kovalevskyi.radiot.news.model;

public class News {

    private final Boolean active;

    private final String title;

    private final String snippet;

    private final int votes;

    private final String link;

    private final String id;

    public News(boolean active, String title, String snippet, int votes, String link, String id) {
        this.title = title;
        this.snippet = snippet;
        this.votes = votes;
        this.link = link;
        this.id = id;
        this.active = active;
    }

    public String getTitle() {
        return title;
    }

    public String getSnippet() {
        return snippet;
    }

    public int getVotes() {
        return votes;
    }

    public String getLink() {
        return link;
    }

    public String getId() {
        return id;
    }

    public Boolean getActive() {
        return active;
    }

    public static class Builder {
        private String title;
        private String snippet;
        private int votes;
        private String link;
        private String id;
        private Boolean active;

        public Builder setActive(boolean active) {
            this.active = active;
            return this;
        }

        public Builder setTitle(String title) {
            this.title = title;
            return this;
        }

        public Builder setSnippet(String snippet) {
            this.snippet = snippet;
            return this;
        }

        public Builder setVotes(int votes) {
            this.votes = votes;
            return this;
        }

        public Builder setLink(String link) {
            this.link = link;
            return this;
        }

        public Builder setId(String id) {
            this.id = id;
            return this;
        }

        public News createNews() {
            return new News(active, title, snippet, votes, link, id);
        }
    }
}
