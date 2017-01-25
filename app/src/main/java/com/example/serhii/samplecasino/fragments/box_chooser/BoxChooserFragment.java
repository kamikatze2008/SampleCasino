package com.example.serhii.samplecasino.fragments.box_chooser;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.example.serhii.samplecasino.R;
import com.example.serhii.samplecasino.activities.SetBetActivity;
import com.example.serhii.samplecasino.entities.Box;
import com.example.serhii.samplecasino.views.halfCircleListView.HalfCircleListView;

import java.util.ArrayList;
import java.util.List;

import static android.app.Activity.RESULT_OK;

public class BoxChooserFragment extends Fragment {
    private HalfCircleListView halfCircleListView;

    private Button betButton;
    private Button noMoreBetButton;
    private Button cancelAllButton;

    public static final int OPEN_SET_BET_ACTIVITY = 0;
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
        halfCircleListView.setOnHalfCircleItemClick(box -> {
            Intent intent = new Intent(getActivity(), SetBetActivity.class);
            intent.putExtra("box_number", box.getBoxNumber());
            startActivityForResult(intent, OPEN_SET_BET_ACTIVITY);
//
        });
//        halfCircleListView.setFragment(this);
        betButton = (Button) view.findViewById(R.id.bet_button);
        betButton.setOnClickListener(buttonView -> new AlertDialog.Builder(getActivity())
                .setTitle("Bet information")
                .setMessage("Please make any bet to continue")
                .setCancelable(false)
                .setPositiveButton(R.string.ok, (dialog, which) -> dialog.cancel())
                .show());
        noMoreBetButton = (Button) view.findViewById(R.id.no_more_bet_button);
        noMoreBetButton.setOnClickListener(buttonView -> {
            sendMessageString();
            //TODO open next screen
        });
        cancelAllButton = (Button) view.findViewById(R.id.cancel_button);
        cancelAllButton.setOnClickListener(buttonView -> {
            for (Box box : boxList) {
                box.setBet(0);
            }
            betButton.setVisibility(View.VISIBLE);
            noMoreBetButton.setVisibility(View.GONE);
            cancelAllButton.setVisibility(View.GONE);
            halfCircleListView.removeAllViews();
            halfCircleListView.setBoxes(boxList);
            halfCircleListView.setUpView();
            halfCircleListView.invalidate();
        });
//        halfCircleListView.setActivity((AppCompatActivity) getActivity());
    }

    private void sendMessageString() {
        StringBuilder stringBuilder = new StringBuilder();
        long timestamp = System.currentTimeMillis();
        for (Box box : boxList) {
            stringBuilder.append("tableNumber|")
                    .append(box.getBoxNumber())
                    .append("|")
                    .append(box.getBet())
                    .append("|")
                    .append(timestamp)
                    .append("\n");
        }
        Toast.makeText(getActivity(), stringBuilder.toString(), Toast.LENGTH_LONG).show();
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == OPEN_SET_BET_ACTIVITY && resultCode == RESULT_OK && data != null) {
            int boxNumber = data.getIntExtra("box_number", -1);
            double bet = data.getDoubleExtra("bet", 0);
            boolean anyBetWasMade = false;
            for (Box box : boxList) {
                if (box.getBoxNumber() == boxNumber) {
                    box.setBet(bet);
                    anyBetWasMade = true;
                }
            }
            if (anyBetWasMade) {
                noMoreBetButton.setVisibility(View.VISIBLE);
                betButton.setVisibility(View.GONE);
                cancelAllButton.setVisibility(View.VISIBLE);
            }
            halfCircleListView.setBoxes(boxList);
            halfCircleListView.removeAllViews();
            halfCircleListView.setUpView();
            halfCircleListView.invalidate();
        }
    }
}
