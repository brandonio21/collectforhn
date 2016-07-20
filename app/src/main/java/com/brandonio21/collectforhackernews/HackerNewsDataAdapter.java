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
import java.util.HashMap;
import java.util.Map;

/**
 * Created by brandon on 7/17/16.
 */
public class HackerNewsDataAdapter extends RecyclerView.Adapter<HackerNewsDataAdapter.ViewHolder> {
    private ArrayList<HackerNewsItem> dataset;
    private Map<Integer, Integer> idToIndexMap;
    private ValueEventListener dataChangeListener;
    private Firebase rootFirebase;
    private HackerNewsCardBuilder cardBuilder;
    private Context parentContext;
    private int itemLayoutId;

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public CardView viewHolderCardView;

        public ViewHolder(CardView v) {
            super(v);
            this.viewHolderCardView = v;
        }
    }

    public HackerNewsDataAdapter(Firebase rootFirebaseConnection,
                                 Context parentContext,
                                 HackerNewsCardBuilder cardBuilder,
                                 int itemLayoutId) {

        this.dataset = new ArrayList<HackerNewsItem>();
        this.idToIndexMap = new HashMap<Integer, Integer>();
        this.rootFirebase = rootFirebaseConnection;
        this.parentContext = parentContext;
        this.cardBuilder = cardBuilder;
        this.itemLayoutId = itemLayoutId;

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

    public void handleDataChange(int[] dataItemIds) {
        for (int dataItemId : dataItemIds) {
            this.rootFirebase.child("item").child(Integer.toString(dataItemId)).addValueEventListener(
                    new ValueEventListener() {
                        @Override
                        public void onDataChange(DataSnapshot dataSnapshot) {
                            HackerNewsItem newsItem = dataSnapshot.getValue(HackerNewsItem.class);

                            dataset.add(newsItem);
                            idToIndexMap.put(newsItem.getId(), dataset.size()-1);
                            notifyItemInserted(dataset.size()-1);
                        }

                        @Override
                        public void onCancelled(FirebaseError firebaseError) {

                        }
                    }
            );
        }
    }

    public void handleTopStoriesDataSnapshotChange(DataSnapshot newShapshot) {
        int[] dataItemIds = newShapshot.getValue(int[].class);
        this.handleDataChange(dataItemIds);
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        CardView v = (CardView) LayoutInflater.from(parent.getContext())
                .inflate(itemLayoutId, parent, false);


        ViewHolder viewHolder = new ViewHolder(v);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        /* Build the actual item */
        // TODO: Design a reusable NewsListItemCardBuilder so that a new one doesnt have to constantly be constructed
        final HackerNewsItem itemOfInterest = dataset.get(position);
        this.cardBuilder.BuildItemCard(itemOfInterest, holder.viewHolderCardView);
    }

    @Override
    public int getItemCount() {
        return this.dataset.size();
    }
}
