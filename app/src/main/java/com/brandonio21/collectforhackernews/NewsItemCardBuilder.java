package com.brandonio21.collectforhackernews;

import android.view.View;
import android.widget.TextView;

import com.brandonio21.collectforhackernews.model.HackerNewsItem;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.IOException;

/**
 * Created by brandon on 7/19/16.
 */
public class NewsItemCardBuilder extends HackerNewsCardBuilder {

    public NewsItemCardBuilder() {
        super();

        this.addBuildStep(new TitleBuildStep());
        //this.addBuildStep(new URLPreviewBuildStep());
    }

     class TitleBuildStep implements ItemCardPopulatorBuildStep {
        @Override
        public void PerformStep(HackerNewsItem item, View view) {
            TextView titleTextView = (TextView) view.findViewById(R.id.news_item_title);
            titleTextView.setText(item.getTitle());
        }
    }

    class URLPreviewBuildStep implements ItemCardPopulatorBuildStep {

        @Override
        public void PerformStep(HackerNewsItem item, View v) {
            // Only populate the URL preview if a URL exists
            if (item.getUrl() == null || item.getUrl().isEmpty())
                return;

            TextView urlPreviewTextView = (TextView) v.findViewById(R.id.news_item_url_title);
            try {
                Document doc = Jsoup.connect(item.getUrl()).get();
                urlPreviewTextView.setText(doc.title());
            }
            catch (IOException downloadFailedException) {
                urlPreviewTextView.setText(item.getUrl());
            }

            urlPreviewTextView.setText(item.getUrl());
        }
    }
}
