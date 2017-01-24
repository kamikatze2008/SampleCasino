package com.example.serhii.samplecasino.fragments.box_chooser;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.serhii.samplecasino.R;
import com.example.serhii.samplecasino.entities.Box;
import com.example.serhii.samplecasino.views.HalfCircleListView;

import java.util.ArrayList;
import java.util.List;

public class BoxChooserFragment extends Fragment {
    private HalfCircleListView halfCircleListView;
    private List<Box> boxList = new ArrayList<Box>() {{
        add(new Box(1));
        add(new Box(2));
        add(new Box(3));
        add(new Box(4));
        add(new Box(5));
        add(new Box(6));
        add(new Box(7));
//        add(new Box(8));
//        add(new Box(9));
    }};

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.box_chooser_layout, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        halfCircleListView = (HalfCircleListView) view.findViewById(R.id.half_circle_list_view);
        halfCircleListView.setBoxes(boxList);
        halfCircleListView.setActivity((AppCompatActivity) getActivity());
    }
}
