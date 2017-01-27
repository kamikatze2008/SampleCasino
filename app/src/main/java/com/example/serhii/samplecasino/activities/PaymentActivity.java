package com.example.serhii.samplecasino.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.serhii.samplecasino.R;
import com.example.serhii.samplecasino.entities.Box;
import com.example.serhii.samplecasino.views.halfCircleListView.HalfCircleListView;

import java.util.List;

public class PaymentActivity extends AppCompatActivity {
    Button noGameButton;
    Button noMoreGameButton;
    Button cancelButton;
    HalfCircleListView halfCircleListView;
    List<Box> boxList;
    private static final int OPEN_COMBINATION_ACTIVITY = 1;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.payment_layout);
        noGameButton = (Button) findViewById(R.id.no_game_button);
        noMoreGameButton = (Button) findViewById(R.id.no_more_game_button);
        cancelButton = (Button) findViewById(R.id.cancel_button);
        halfCircleListView = (HalfCircleListView) findViewById(R.id.half_circle_list_view);
        Intent receivedIntent = getIntent();
        if (receivedIntent != null) {
            boxList = (List<Box>) receivedIntent.getSerializableExtra("box_list");
            halfCircleListView.setBoxes(boxList);
            halfCircleListView.setOnHalfCircleItemClick(box -> {
                Intent intent = new Intent(this, CombinationActivity.class);
                intent.putExtra("bet", box.getBet());
                intent.putExtra("box_number", box.getBoxNumber());
                startActivityForResult(intent, OPEN_COMBINATION_ACTIVITY);
            });
        }
        noGameButton.setOnClickListener(view -> showStartAgainAlertDialog());
        noMoreGameButton.setOnClickListener(view -> showStartAgainAlertDialog());
        cancelButton.setOnClickListener(view -> {
            for (Box box : boxList) {
                box.setWinAmount(0);
            }
            halfCircleListView.removeAllViews();
            halfCircleListView.setBoxes(boxList);
            halfCircleListView.setUpView();

            noGameButton.setVisibility(View.VISIBLE);
            noMoreGameButton.setVisibility(View.GONE);
            cancelButton.setVisibility(View.GONE);
        });
    }

    private void showStartAgainAlertDialog() {
        new AlertDialog.Builder(this)
                .setTitle("New game")
                .setMessage("Are you sure?")
                .setPositiveButton("Yes", (dialog, which) -> {
                    long timestamp = System.currentTimeMillis();
                    StringBuilder stringBuilder = new StringBuilder();
                    for (Box box : boxList) {
                        if (box.getWinAmount() > 0) {
                            stringBuilder.append("bet_id|")
                                    .append(box.getWinAmount())
                                    .append("|")
                                    .append(timestamp)
                                    .append(";");

                        }
                    }
                    Toast.makeText(this, stringBuilder.toString(), Toast.LENGTH_LONG).show();
                    Intent intent = new Intent(this, MainActivity.class);
                    startActivity(intent);
                    finish();
                })
                .setNegativeButton("No", (dialog, which) -> dialog.cancel())
                .show();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == OPEN_COMBINATION_ACTIVITY && resultCode == RESULT_OK && data != null) {
            int boxNumber = data.getIntExtra("box_number", 0);
            int combination = data.getIntExtra("combination", 1);
            Box box = boxList.get(boxNumber - 1);
            double winAmount = box.getBet() * combination;
            box.setWinAmount(winAmount);
            halfCircleListView.setBoxes(boxList);
            int winAmountCounter = 0;
            for (Box box1 : boxList) {
                if (box1.getWinAmount() > 0) {
                    winAmountCounter++;
                }
            }
            if (winAmountCounter != 0) {
                noMoreGameButton.setVisibility(View.VISIBLE);
                if (winAmountCounter == boxList.size()) {
                    noMoreGameButton.setText(R.string.ok);
                }
                noGameButton.setVisibility(View.GONE);
                cancelButton.setVisibility(View.VISIBLE);
            } else {
                noGameButton.setVisibility(View.VISIBLE);
                noMoreGameButton.setVisibility(View.GONE);
                cancelButton.setVisibility(View.GONE);
            }
        }
    }
}
