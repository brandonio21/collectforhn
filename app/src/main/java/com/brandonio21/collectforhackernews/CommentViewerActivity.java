package com.brandonio21.collectforhackernews;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
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

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

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

        makePrimaryItem();
    }

    public void makePrimaryItem() {
        CardView primaryItem = (CardView) findViewById(R.id.comment_card_primary);
        StandardItemCardBuilder itemCardPopulator = new StandardItemCardBuilder(primaryItem);
        itemCardPopulator.BuildItemCard(this.item);
    }


}
