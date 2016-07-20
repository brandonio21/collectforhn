package com.brandonio21.collectforhackernews;

import android.view.View;
import android.widget.TextView;

import com.brandonio21.collectforhackernews.model.HackerNewsItem;

/**
 * Created by brandon on 7/18/16.
 */
public class CommentListItemCardBuilder extends HackerNewsCardBuilder {
    public CommentListItemCardBuilder() {
        super();

        this.addBuildStep(new AddCommentTextBuildStep());
    }

    class AddCommentTextBuildStep implements ItemCardPopulatorBuildStep {

        @Override
        public void PerformStep(HackerNewsItem item, View v) {
            TextView commentText = (TextView) v.findViewById(R.id.comment_text);
            commentText.setText(item.getText());
        }
    }
}
