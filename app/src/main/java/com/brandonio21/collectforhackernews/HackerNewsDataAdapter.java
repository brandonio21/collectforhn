package com.brandonio21.collectforhackernews;

import android.content.Context;
import android.content.Intent;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.brandonio21.collectforhackernews.model.HackerNewsItem;
import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.ValueEventListener;

import java.util.ArrayList;

/**
 * Created by brandon on 7/17/16.
 */
public class HackerNewsDataAdapter extends RecyclerView.Adapter<HackerNewsDataAdapter.ViewHolder> {
    private ArrayList<HackerNewsItem> dataset;
    private ValueEventListener dataChangeListener;
    private Firebase rootFirebase;
    private Context parentContext;

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public CardView viewHolderCardView;

        public ViewHolder(CardView v) {
            super(v);
            this.viewHolderCardView = v;
        }
    }

    public HackerNewsDataAdapter(Firebase rootFirebaseConnection,
                                 Context parentContext) {
        this.dataset = new ArrayList<HackerNewsItem>();
        this.rootFirebase = rootFirebaseConnection;
        this.parentContext = parentContext;

        this.dataChangeListener = new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                handleTopStoriesDataSnapshotChange(dataSnapshot);
            }

            @Override
            public void onCancelled(FirebaseError firebaseError) {

            }
        };
    }

    public ValueEventListener getDataChangeListener() {
        return this.dataChangeListener;
    }


    public void handleTopStoriesDataSnapshotChange(DataSnapshot newShapshot) {
        int[] topStoryIds = newShapshot.getValue(int[].class);
        for (int topStoryId : topStoryIds) {
            this.rootFirebase.child("item").child(Integer.toString(topStoryId)).addValueEventListener(
                    new ValueEventListener() {
                        @Override
                        public void onDataChange(DataSnapshot dataSnapshot) {
                            HackerNewsItem newsItem = dataSnapshot.getValue(HackerNewsItem.class);

                            dataset.add(newsItem);
                        }

                        @Override
                        public void onCancelled(FirebaseError firebaseError) {

                        }
                    }
            );
        }
        notifyDataSetChanged();
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        CardView v = (CardView) LayoutInflater.from(parent.getContext())
                .inflate(R.layout.activity_news_item, parent, false);


        ViewHolder viewHolder = new ViewHolder(v);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        /* Build the actual item */
        final HackerNewsItem itemOfInterest = dataset.get(position);
        TextView cardTitle = (TextView) holder.viewHolderCardView.findViewById(R.id.news_item_title);
        cardTitle.setText(itemOfInterest.getTitle());

        /* Set the click to go to the comments section */
        cardTitle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent goToCommentsIntent = new Intent(parentContext, CommentViewerActivity.class);
                goToCommentsIntent.putExtra("item", itemOfInterest);
                parentContext.startActivity(goToCommentsIntent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return this.dataset.size();
    }
}
