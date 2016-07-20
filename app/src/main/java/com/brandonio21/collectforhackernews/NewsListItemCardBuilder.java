package com.brandonio21.collectforhackernews;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.widget.TextView;

import com.brandonio21.collectforhackernews.model.HackerNewsItem;

/**
 * Created by brandon on 7/18/16.
 */
public class NewsListItemCardBuilder extends NewsItemCardBuilder {
    private Context parentContext;

    public NewsListItemCardBuilder(Context parentContext) {
        super();
        this.parentContext = parentContext;

        this.addBuildStep(new AddClickabilityToTitleStep());
    }

    class AddClickabilityToTitleStep implements ItemCardPopulatorBuildStep {

        @Override
        public void PerformStep(final HackerNewsItem item, View view) {
            TextView cardTitle = (TextView) view.findViewById(R.id.news_item_title);
            /* Set the click to go to the comments section */
            cardTitle.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent goToCommentsIntent = new Intent(parentContext, CommentViewerActivity.class);
                    goToCommentsIntent.putExtra("item", item);
                    parentContext.startActivity(goToCommentsIntent);
            }
        });
        }
    }
}
