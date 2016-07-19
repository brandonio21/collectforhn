package com.brandonio21.collectforhackernews;

import android.content.ClipData;
import android.view.View;
import android.widget.TextView;

import com.brandonio21.collectforhackernews.model.HackerNewsItem;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by brandon on 7/18/16.
 */
public class StandardItemCardBuilder {
    protected View view;
    private List<ItemCardPopulatorBuildStep> buildSteps;

    public StandardItemCardBuilder(View view) {
        if (view == null) {
            throw new IllegalArgumentException("view Argument cannot be null");
        }

        this.view = view;
        this.buildSteps = new ArrayList<ItemCardPopulatorBuildStep>();

        this.addBuildStep(new TitleBuildStep());
    }

    public void addBuildStep(ItemCardPopulatorBuildStep step) {
        this.buildSteps.add(step);
    }

    public void BuildItemCard(HackerNewsItem item) {
        if (item == null) {
            throw new IllegalArgumentException("item Argument cannot be null");
        }

        for (ItemCardPopulatorBuildStep buildStep : this.buildSteps) {
            buildStep.PerformStep(item);
        }
    }

    interface ItemCardPopulatorBuildStep {
        void PerformStep(HackerNewsItem item);
    }

    class TitleBuildStep implements ItemCardPopulatorBuildStep {
        @Override
        public void PerformStep(HackerNewsItem item) {
            TextView titleTextView = (TextView) view.findViewById(R.id.news_item_title);
            titleTextView.setText(item.getTitle());
        }
    }
}
