package com.curtisnewbie.activities;

import android.app.Activity;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.curtisnewbie.services.App;
import com.curtisnewbie.services.HttpService;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.inject.Inject;

/**
 * ------------------------------------
 * <p>
 * Author: Yongjie Zhuang
 * <p>
 * ------------------------------------
 * <p>
 * Adapter of RecyclerView in MediaListActivity
 */
public class MediaListAdapter extends RecyclerView.Adapter {

    public static final String URL_TAG = "url";

    @Inject
    protected HttpService httpService;

    private Activity activity;

    private final List<String> list = Collections.synchronizedList(new ArrayList<String>());

    public MediaListAdapter(Activity activity) {
        App.getAppComponent().inject(this);
        this.activity = activity;
        new Thread(new Runnable() { // fetch list of media names asynchronously
            @Override
            public void run() {
                List<String> l = httpService.getMediaList();
                list.addAll(l);
                notifyItemRangeInserted(0, l.size() - 1);
            }
        }).start();
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_media_list_item, parent, false
        );
        return new ItemViewHolder(v);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        ((ItemViewHolder) holder).btn.setText(list.get(position));
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class ItemViewHolder extends RecyclerView.ViewHolder {

        public Button btn;

        public ItemViewHolder(View v) {
            super(v);
            this.btn = v.findViewById(R.id.mediaNameBtn);
            this.btn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(activity, String.format("Playing '%s'", btn.getText().toString()),
                            Toast.LENGTH_LONG).show();
                    playVideo();
                }
            });
        }

        /**
         * Create MediaPlayerActivity to plays the video
         */
        private void playVideo(){
            String url = httpService.buildMediaUrl(btn.getText().toString());
            Intent intent = new Intent(".MediaPlayerActivity");
            intent.putExtra(URL_TAG, url);
            activity.startActivity(intent);
        }
    }
}
