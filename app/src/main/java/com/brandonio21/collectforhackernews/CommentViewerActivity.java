package com.brandonio21.collectforhackernews;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.brandonio21.collectforhackernews.model.HackerNewsItem;

public class CommentViewerActivity extends AppCompatActivity {
    private HackerNewsItem item;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_comment_viewer);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        Intent senderIntent = getIntent();

        //TODO: Put "item" in a resources file or something
        //TODO: Do something if serialization is not HackerNewsItem before casting or if it is null.
        this.item = (HackerNewsItem) senderIntent.getSerializableExtra("item");
    }

}
