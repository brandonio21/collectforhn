package com.brandonio21.collectforhackernews.model;

import java.io.Serializable;

/**
 * Created by brandon on 7/17/16.
 * Represents the model provided by the Hacker news API
 * for an item
 */
public class HackerNewsItem implements Serializable {
    private int id; // The unique id of the item
    private boolean deleted; // True if the item has been deleted
    private String type; // The type of the item
    private String by; // The username of the item's author
    private long time; // UNIX time of creation
    private String text; // The HTML content text
    private boolean dead; // True if the item is dead
    private int parent; // The ID of the parent item
    private int[] kids; // The IDs of the kids
    private String url; // The URL of the item
    private int score; // The score of the item
    private String title; // The title of the item
    private int[] parts; // A list of IDs correspoding to related pollopts
    private int descendants; // The number of comments

    public HackerNewsItem() {

    }

    public int getId() {
        return id;
    }

    public boolean isDeleted() {
        return deleted;
    }

    public String getType() {
        return type;
    }

    public String getBy() {
        return by;
    }

    public long getTime() {
        return time;
    }

    public String getText() {
        return text;
    }

    public boolean isDead() {
        return dead;
    }

    public int getParent() {
        return parent;
    }

    public int[] getKids() {
        return kids;
    }

    public String getUrl() {
        return url;
    }

    public int getScore() {
        return score;
    }

    public String getTitle() {
        return title;
    }

    public int[] getParts() {
        return parts;
    }

    public int getDescendants() {
        return descendants;
    }

    public static class HackerNewsItemType {
        public static final String JOB = "job";
        public static final String STORY = "story";
        public static final String COMMENT = "comment";
        public static final String POLL = "poll";
        public static final String POLLOPT = "pollopt";
    }
}


