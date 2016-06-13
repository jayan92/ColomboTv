package com.xicigny.colombotv.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;
import com.xicigny.colombotv.R;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;

/**
 * Created by Moksham on 5/14/2016.
 */
public class YouTubePosterFragment extends Fragment {

    @InjectView(R.id.iv_thumbnail)
    ImageView thumbnailImageView;

    private String videoPosterThumbnail;
    private String posterTitle;

    @Override public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                       Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_youtube_poster, container, false);
        ButterKnife.inject(this, view);
        Picasso.with(getActivity())
                .load(videoPosterThumbnail)
                .placeholder(R.drawable.colombotv)
                .into(thumbnailImageView);
        return view;
    }

    public void setPoster(String videoPosterThumbnail) {
        this.videoPosterThumbnail = videoPosterThumbnail;
    }


    public void setPosterTitle(String posterTitle) {
        this.posterTitle = posterTitle;
    }


    @OnClick(R.id.iv_thumbnail) void onThubmnailClicked() {
        Toast.makeText(getActivity(), posterTitle, Toast.LENGTH_SHORT).show();
    }
}
