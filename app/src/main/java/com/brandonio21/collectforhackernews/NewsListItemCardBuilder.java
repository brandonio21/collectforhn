package com.brandonio21.collectforhackernews;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.widget.TextView;

import com.brandonio21.collectforhackernews.model.HackerNewsItem;

/**
 * Created by brandon on 7/18/16.
 */
public class NewsListItemCardBuilder extends StandardItemCardBuilder {
    private Context parentContext;

    public NewsListItemCardBuilder(View view, Context parentContext) {
        super(view);
        this.parentContext = parentContext;

        this.addBuildStep(new AddClickabilityToTitleStep());
    }

    class AddClickabilityToTitleStep implements ItemCardPopulatorBuildStep {

        @Override
        public void PerformStep(final HackerNewsItem item) {
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
