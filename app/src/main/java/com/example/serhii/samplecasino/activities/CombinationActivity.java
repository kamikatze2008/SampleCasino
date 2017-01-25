package com.example.serhii.samplecasino.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.example.serhii.samplecasino.R;

public class CombinationActivity extends AppCompatActivity {

    TextView betTextView;
    TextView winTextView;
    ListView listView;
    Button confirmButton;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.combination_activity);
        setResult(RESULT_CANCELED);
        Intent intent = getIntent();
        double bet = intent.getDoubleExtra("bet", 0);
        betTextView = (TextView) findViewById(R.id.bet_text_view);
        betTextView.setText(bet + "$");
        winTextView = (TextView) findViewById(R.id.win_text_view);
        confirmButton = (Button) findViewById(R.id.confirm_button);
        confirmButton.setOnClickListener(v -> {
            Intent returnIntent = new Intent();
            intent.putExtra("bet", bet);
            setResult(RESULT_OK, returnIntent);
            finish();
        });
        listView = (ListView) findViewById(R.id.combination_list_view);
        listView.setAdapter(new ArrayAdapter<>(this, android.R.layout.simple_expandable_list_item_1, new Integer[]{1, 2, 3, 4, 5, 6, 7}));
        listView.setOnItemClickListener((parent, view, position, id) -> {
            Integer value = Integer.parseInt(((TextView) view).getText().toString());
            winTextView.setText("" + (bet * value) + "$");
        });
    }
}
