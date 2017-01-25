package com.example.serhii.samplecasino.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.serhii.samplecasino.R;

public class SetBetActivity extends AppCompatActivity {
    private TextView betTextView;
    private Button confirmButton;
    private GridLayout gridLayout;
    int boxNumber = -1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setResult(RESULT_CANCELED);
        setContentView(R.layout.set_bet_layout);
        betTextView = (TextView) findViewById(R.id.bet_text_view);
        confirmButton = (Button) findViewById(R.id.confirm_button);
        Intent intent = getIntent();
        if (intent != null) {
            boxNumber = intent.getIntExtra("box_number", -1);
        }
        if (boxNumber != -1) {
            confirmButton.setOnClickListener(confirmButtonView -> {
                setResult(RESULT_OK);
                Double betValue = Double.parseDouble(betTextView.getText().toString().replace("$", ""));
                Toast.makeText(this, "Bet is " + betValue, Toast.LENGTH_SHORT).show();
                Intent result = new Intent();
                result.putExtra("bet", betValue);
                result.putExtra("box_number", boxNumber);
                setResult(RESULT_OK, result);
                finish();
            });

            setupGridLayout();
        } else {
            finish();
        }
    }

    private void setBet(TextView numberTextView) {
        String betTextViewText = betTextView.getText().toString();
        betTextViewText = betTextViewText.substring(0, betTextViewText.length() - 1);
        betTextView.setText(betTextViewText + numberTextView.getText().toString() + "$");
    }

    private void setupGridLayout() {
        findViewById(R.id.one).setOnClickListener(numberTextView -> setBet((TextView) numberTextView));
        findViewById(R.id.two).setOnClickListener(numberTextView -> setBet((TextView) numberTextView));
        findViewById(R.id.three).setOnClickListener(numberTextView -> setBet((TextView) numberTextView));
        findViewById(R.id.four).setOnClickListener(numberTextView -> setBet((TextView) numberTextView));
        findViewById(R.id.five).setOnClickListener(numberTextView -> setBet((TextView) numberTextView));
        findViewById(R.id.six).setOnClickListener(numberTextView -> setBet((TextView) numberTextView));
        findViewById(R.id.seven).setOnClickListener(numberTextView -> setBet((TextView) numberTextView));
        findViewById(R.id.eight).setOnClickListener(numberTextView -> setBet((TextView) numberTextView));
        findViewById(R.id.nine).setOnClickListener(numberTextView -> setBet((TextView) numberTextView));
        findViewById(R.id.zero).setOnClickListener(numberTextView -> setBet((TextView) numberTextView));
        findViewById(R.id.delete).setOnClickListener(deleteView -> {
            String betTextViewText = betTextView.getText().toString();
            if (betTextViewText.length() >= 2) {
                betTextViewText = betTextViewText.substring(0, betTextViewText.length() - 2);
                betTextView.setText(betTextViewText + "$");
            }
        });
    }
}
