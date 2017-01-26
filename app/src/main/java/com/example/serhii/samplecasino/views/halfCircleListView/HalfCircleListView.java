package com.example.serhii.samplecasino.views.halfCircleListView;

import android.content.Context;
import android.graphics.Color;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.design.widget.FloatingActionButton;
import android.util.AttributeSet;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Space;

import com.example.serhii.samplecasino.entities.Box;

import java.util.List;

public class HalfCircleListView extends RelativeLayout {
    private List<Box> boxes;

    private OnHalfCircleItemClick onHalfCircleItemClick;

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

    public void setUpView() {
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
                marginTop += height * 0.35;
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
                        weightCoefficient *= 2 * Math.pow(3, 1 / (1.7d * i));
                        space.setLayoutParams(spaceLayoutParams);
                        childLinearLayout.addView(space);
                    }
                    FloatingActionButton floatingActionButton = new FloatingActionButton(context);
                    if (boxes.get(position).getBet() > 0) {
                        floatingActionButton.setImageResource(android.R.drawable.ic_menu_camera);
                        floatingActionButton.setRippleColor(Color.YELLOW);
                    }
                    floatingActionButton.setTag(position);
                    position++;
                    floatingActionButton.setOnLongClickListener(v -> {
                        Box box = boxes.get((Integer) v.getTag());
                        if (onHalfCircleItemClick != null) {
                            onHalfCircleItemClick.onClick(box);
                        }
                        return true;
                    });
                    LinearLayout.LayoutParams fabLayoutParams =
                            new LinearLayout.LayoutParams(150, 150);
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

    public void setOnHalfCircleItemClick(OnHalfCircleItemClick onHalfCircleItemClick) {
        this.onHalfCircleItemClick = onHalfCircleItemClick;
    }
}
