package com.brandonio21.collectforhackernews;

import android.content.ClipData;
import android.view.View;
import android.widget.TextView;

import com.brandonio21.collectforhackernews.model.HackerNewsItem;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by brandon on 7/18/16.
 */
public abstract class HackerNewsCardBuilder {
    protected View view;
    private List<ItemCardPopulatorBuildStep> buildSteps;

    public HackerNewsCardBuilder() {
        this.buildSteps = new ArrayList<ItemCardPopulatorBuildStep>();
    }

    public void addBuildStep(ItemCardPopulatorBuildStep step) {
        this.buildSteps.add(step);
    }

    public void BuildItemCard(HackerNewsItem item, View v) {
        if (item == null) {
            throw new IllegalArgumentException("item Argument cannot be null");
        }

        for (ItemCardPopulatorBuildStep buildStep : this.buildSteps) {
            buildStep.PerformStep(item, v);
        }
    }

    interface ItemCardPopulatorBuildStep {
        void PerformStep(HackerNewsItem item, View v);
    }
}
