package com.xicigny.colombotv.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.gc.materialdesign.views.ButtonRectangle;
import com.squareup.picasso.Picasso;
import com.xicigny.colombotv.PlayerActivity;
import com.xicigny.colombotv.R;
import com.xicigny.colombotv.VideoItem;
import com.xicigny.colombotv.YoutubeConnectorPlaylist;
import com.xicigny.colombotv.activity.ActivityYoutubeDrag;

import java.util.List;

/**
 * Created by Moksham on 4/13/2016.
 */
public class EntertainmentFragment extends Fragment {
    private ListView videosFound;

    private Handler handler;

    private List<VideoItem> searchResults;
    private ButtonRectangle share;
    public EntertainmentFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_entertainment, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        videosFound = (ListView) view.findViewById(R.id.videos_found);
        handler = new Handler();
        addClickListener();
        searchOnYoutube("PLjaZTqEnXF56bjtrrPOd1Np2tWtjhOUNR");
    }
    private void addClickListener(){
        videosFound.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> av, View v, int pos,
                                    long id) {
                Intent intent = new Intent(getActivity(), ActivityYoutubeDrag.class);
                intent.putExtra("VIDEO_ID", searchResults.get(pos).getId());
                intent.putExtra("THUMB_URL", searchResults.get(pos).getId());
                intent.putExtra("TITLE", searchResults.get(pos).getTitle());
                intent.putExtra("FRAGMENT", "entertainment");
                startActivity(intent);
            }

        });
    }

    private void searchOnYoutube(final String keywords){
        new Thread(){
            public void run(){
                YoutubeConnectorPlaylist yc = new YoutubeConnectorPlaylist(getContext());
                searchResults = yc.search(keywords);
                handler.post(new Runnable(){
                    public void run(){
                        updateVideosFound();
                    }
                });
            }
        }.start();
    }

    private void updateVideosFound(){
        ArrayAdapter<VideoItem> adapter = new ArrayAdapter<VideoItem>(getActivity(), R.layout.video_item, searchResults){
            @Override
            public View getView(final int position, View convertView, ViewGroup parent) {
                if(convertView == null){
                    convertView
                            = getActivity().getLayoutInflater().inflate(R.layout.video_item, parent, false);
                }
                ImageView thumbnail = (ImageView)convertView.findViewById(R.id.video_thumbnail);
                TextView title = (TextView)convertView.findViewById(R.id.video_title);
                TextView description = (TextView)convertView.findViewById(R.id.video_description);
                share = (ButtonRectangle) convertView.findViewById(R.id.btnShare);
                share.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent i = new Intent(Intent.ACTION_SEND);
                        i.setType("text/plain");
                        i.putExtra(Intent.EXTRA_SUBJECT, "Sharing URL");
                        i.putExtra(Intent.EXTRA_TEXT, "https://www.youtube.com/watch?v="+searchResults.get(position).getId());
                        startActivity(Intent.createChooser(i, "Share URL"));
                    }
                });
                VideoItem searchResult = searchResults.get(position);

                Picasso.with(getActivity()).load(searchResult.getThumbnailURL()).into(thumbnail);
                title.setText(searchResult.getTitle());
                description.setText(searchResult.getDescription());
                return convertView;
            }
        };

        videosFound.setAdapter(adapter);
    }
}
