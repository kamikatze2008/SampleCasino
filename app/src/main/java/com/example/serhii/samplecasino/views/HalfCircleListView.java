package com.example.serhii.samplecasino.views;

import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.util.AttributeSet;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Space;

import com.example.serhii.samplecasino.R;
import com.example.serhii.samplecasino.entities.Box;
import com.example.serhii.samplecasino.fragments.set_bet.SetBetFragment;

import java.util.List;

public class HalfCircleListView extends RelativeLayout {
    private List<Box> boxes;
    private AppCompatActivity activity;

    public HalfCircleListView(Context context) {
        super(context);
    }

    public HalfCircleListView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public HalfCircleListView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public HalfCircleListView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    @Override
    public void onWindowFocusChanged(boolean hasWindowFocus) {
        super.onWindowFocusChanged(hasWindowFocus);
        setUpView();
    }

    private void setUpView() {
        if (boxes != null) {
            int boxesSize = boxes.size();
            int rows = boxesSize / 2 + boxesSize % 2;
            int height = getHeight() / rows;
            double marginTop = 0;
            int weightCoefficient = 1;
            Context context = getContext();
            int position = 0;
            for (int i = 0; i < rows; i++) {
                LinearLayout childLinearLayout = new LinearLayout(context);
                childLinearLayout.setOrientation(LinearLayout.HORIZONTAL);
                RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, height);
                layoutParams.topMargin = (int) marginTop;
                childLinearLayout.setLayoutParams(layoutParams);
                marginTop = marginTop + height * 0.95;
                addView(childLinearLayout);
                int columns = 2;
                if (i == 0 && boxesSize % 2 == 1) {
                    columns = 1;
                }
                Space space = new Space(context);
                LinearLayout.LayoutParams spaceLayoutParams =
                        new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.MATCH_PARENT);
                spaceLayoutParams.weight = 1;
                space.setLayoutParams(spaceLayoutParams);
                childLinearLayout.addView(space);
                for (int j = 0; j < columns; j++) {
                    if (j != 0) {
                        space = new Space(context);
                        spaceLayoutParams =
                                new LinearLayout.LayoutParams(0, ViewGroup.LayoutParams.MATCH_PARENT);
                        spaceLayoutParams.weight = weightCoefficient;
                        weightCoefficient *= 3;
                        space.setLayoutParams(spaceLayoutParams);
                        childLinearLayout.addView(space);
                    }
                    FloatingActionButton floatingActionButton = new FloatingActionButton(context);
                    floatingActionButton.setTag(position);
                    position++;
                    floatingActionButton.setOnLongClickListener(view -> {
                        Box box = boxes.get((Integer) view.getTag());
                        Bundle bundle = new Bundle();
                        bundle.putInt("boxNumber", box.getBoxNumber());
                        Fragment fragment = new SetBetFragment();
                        fragment.setArguments(bundle);
                        activity.getSupportFragmentManager()
                                .beginTransaction()
                                .add(R.id.container, fragment)
                                .addToBackStack(SetBetFragment.class.getSimpleName())
                                .commit();
//                        Toast.makeText(context, box.toString(), Toast.LENGTH_SHORT).show();
                        return true;
                    });
                    LinearLayout.LayoutParams fabLayoutParams =
                            new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
                    floatingActionButton.setLayoutParams(fabLayoutParams);
                    childLinearLayout.addView(floatingActionButton);
                }
                space = new Space(context);
                spaceLayoutParams =
                        new LinearLayout.LayoutParams(0, ViewGroup.LayoutParams.MATCH_PARENT);
                spaceLayoutParams.weight = 1;
                space.setLayoutParams(spaceLayoutParams);
                childLinearLayout.addView(space);
            }
        }
    }

    public void setBoxes(List<Box> boxes) {
        this.boxes = boxes;
    }

    public void setActivity(AppCompatActivity activity) {
        this.activity = activity;
    }
}
