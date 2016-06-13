package com.xicigny.colombotv.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.gc.materialdesign.views.ButtonRectangle;
import com.xicigny.colombotv.R;
import com.xicigny.colombotv.activity.ProgramActivity;

/**
 * Created by Moksham on 5/14/2016.
 */
public class DashboardFragment extends Fragment implements View.OnClickListener {

    ButtonRectangle mVideoList;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_dashboard, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        mVideoList = (ButtonRectangle) view.findViewById(R.id.btnList);
        mVideoList.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        Intent program;
        if(v.getId()==R.id.btnList){
            program = new Intent(getActivity(), ProgramActivity.class);
            startActivity(program);
        }
    }
}
