package com.example.serhii.samplecasino.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;

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
            halfCircleListView.setOnHalfCircleItemClick(box -> new AlertDialog.Builder(this)
                    .setTitle("Game results")
                    .setPositiveButton(R.string.game, (dialog, which) -> {
                        Intent intent = new Intent(this, CombinationActivity.class);
                        intent.putExtra("bet", box.getBet());
                        startActivityForResult(intent, OPEN_COMBINATION_ACTIVITY);
                    })
                    .setNegativeButton(R.string.no_game, (dialog, which) -> dialog.cancel())
                    .show());
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
        });
    }

    private void showStartAgainAlertDialog() {
        new AlertDialog.Builder(this)
                .setTitle("New game")
                .setMessage("Are you sure?")
                .setPositiveButton("Yes", (dialog, which) -> {
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

        }
    }
}
