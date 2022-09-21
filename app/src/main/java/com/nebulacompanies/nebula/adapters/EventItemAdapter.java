package com.nebulacompanies.nebula.adapters;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.nebulacompanies.nebula.Model.Guest.SubEventList;
import com.nebulacompanies.nebula.R;
import com.nebulacompanies.nebula.gui.ShowFullScreenEvents;


import java.util.ArrayList;
import java.util.List;

public class EventItemAdapter extends RecyclerView.Adapter<EventItemAdapter.ItemViewHolder> {

    private List<SubEventList> Items = new ArrayList<>();
    OnItemClickListener mItemClickListener;
    Context mContext;
    String event;
    final String PREFS_WALKTHROUGH_EVENT_SUP = "eventsuptwalkthrough";
    ArrayList<String> imagepic = new ArrayList<String>();
    ArrayList<String> date = new ArrayList<String>();
    ArrayList<Integer> count = new ArrayList<Integer>();
    private boolean isLoadingAdded = false;
    boolean first_time;


    public EventItemAdapter(Context context, String event, ArrayList<String> imagepic, ArrayList<String> date, ArrayList<Integer> count, boolean first_time) {
        Items = new ArrayList<>();
        mContext = context;
        this.event = event;
        this.imagepic = imagepic;
        this.date = date;
        this.count = count;
        this.first_time=first_time;

    }

    /*public EventItemAdapter(List<SubEventList> items) {
        this.Items = items;
    }*/

    public class ItemViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        ImageView imgUserAvatar;

        public ItemViewHolder(View view) {
            super(view);
            imgUserAvatar = (ImageView) view.findViewById(R.id.picture1);
            view.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            if (mItemClickListener != null) {
                mItemClickListener.onItemClick(v, getPosition());
            }
        }
    }

    @Override
    public ItemViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.grid_item_events, parent, false);
        return new ItemViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ItemViewHolder holder, @SuppressLint("RecyclerView") int position) {
        SubEventList model = Items.get(position);

        if (position == Items.size() - 1) {
            holder.imgUserAvatar.setVisibility(View.GONE);
        } else {
            holder.imgUserAvatar.setVisibility(View.VISIBLE);
        }

        Glide.with(mContext).load(model.getEventThumbnail())
                .crossFade()
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .placeholder(R.drawable.nebula_placeholder)
                .into(holder.imgUserAvatar);


        //Picasso.with(mContext).load(model.getEventThumbnail()).placeholder(R.drawable.nebula_placeholder).into(holder.imgUserAvatar);
        RecyclerView.ViewHolder finalHolder1 = holder;

        holder.imgUserAvatar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(mContext, ShowFullScreenEvents.class);
              //  holder.imgUserAvatar.setClickable(false);
               // holder.imgUserAvatar.setEnabled(true);
                intent.putExtra("id", position);
                intent.putExtra("date", date);
                intent.putExtra("imagepic", imagepic);
                intent.putExtra("count", count);
                intent.putExtra("first_time_event_sup", false);
                intent.putExtra("eventName", event);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
                mContext.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return Items.size();
    }

    public interface OnItemClickListener {
        public void onItemClick(View view, int position);
    }

    public void setOnItemClicklListener(final OnItemClickListener mItemClickListener) {
        this.mItemClickListener = mItemClickListener;
    }

    public void setItems(List<SubEventList> items) {
        Items = items;
        notifyDataSetChanged();
    }

    public void clearData() {
        // clear the data
        Items.clear();
    }

    public void add(SubEventList r) {
        Items.add(r);
        notifyItemInserted(Items.size() - 1);
        // notifyItemInserted(Items.size());
    }

    public void addAll(List<SubEventList> moveResults) {
        for (SubEventList result : moveResults) {
            if (!moveResults.contains(result.getEventPic())) {
                add(result);
            }
            // add(result);
        }
    }

    public void remove(SubEventList r) {
        int position = Items.indexOf(r);
        if (position > -1) {
            Items.remove(position);
            notifyItemRemoved(position);
        }
    }

    public void clear() {
        isLoadingAdded = false;
        Items.clear();
        /*while (getItemCount() > 0) {
            remove(getItem(0));
        }*/
    }

    public boolean isEmpty() {
        return getItemCount() == 0;
    }


    public void addLoadingFooter() {
        isLoadingAdded = true;
        add(new SubEventList());
    }

    public void removeLoadingFooter() {
        isLoadingAdded = false;

        int position = Items.size() - 1;
        SubEventList result = getItem(position);

        if (result != null) {
            Items.remove(position);
            notifyItemRemoved(position);
        }
    }

    public SubEventList getItem(int position) {
        return Items.get(position);
    }
}
