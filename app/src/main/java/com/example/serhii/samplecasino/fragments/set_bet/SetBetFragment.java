package com.example.serhii.samplecasino.fragments.set_bet;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.serhii.samplecasino.R;

public class SetBetFragment extends Fragment {
    private TextView betTextView;
    private Button confirmButton;
    private GridLayout gridLayout;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.set_bet_layout, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        betTextView = (TextView) view.findViewById(R.id.bet_text_view);
        confirmButton = (Button) view.findViewById(R.id.confirm_button);

        confirmButton.setOnClickListener(confirmButtonView -> {
            Double betValue = Double.parseDouble(betTextView.getText().toString().replace("$", ""));
            Toast.makeText(getActivity(), "Bet is " + betValue, Toast.LENGTH_SHORT).show();
            getActivity().getSupportFragmentManager().popBackStack();
        });
        setupGridLayout(view);
    }

    private void setBet(TextView numberTextView) {
        String betTextViewText = betTextView.getText().toString();
        betTextViewText = betTextViewText.substring(0, betTextViewText.length() - 1);
        betTextView.setText(betTextViewText + numberTextView.getText().toString() + "$");
    }

    private void setupGridLayout(View view) {
        view.findViewById(R.id.one).setOnClickListener(numberTextView -> setBet((TextView) numberTextView));
        view.findViewById(R.id.two).setOnClickListener(numberTextView -> setBet((TextView) numberTextView));
        view.findViewById(R.id.three).setOnClickListener(numberTextView -> setBet((TextView) numberTextView));
        view.findViewById(R.id.four).setOnClickListener(numberTextView -> setBet((TextView) numberTextView));
        view.findViewById(R.id.five).setOnClickListener(numberTextView -> setBet((TextView) numberTextView));
        view.findViewById(R.id.six).setOnClickListener(numberTextView -> setBet((TextView) numberTextView));
        view.findViewById(R.id.seven).setOnClickListener(numberTextView -> setBet((TextView) numberTextView));
        view.findViewById(R.id.eight).setOnClickListener(numberTextView -> setBet((TextView) numberTextView));
        view.findViewById(R.id.nine).setOnClickListener(numberTextView -> setBet((TextView) numberTextView));
        view.findViewById(R.id.zero).setOnClickListener(numberTextView -> setBet((TextView) numberTextView));
        view.findViewById(R.id.delete).setOnClickListener(deleteView -> {
            String betTextViewText = betTextView.getText().toString();
            if (betTextViewText.length() >= 2) {
                betTextViewText = betTextViewText.substring(0, betTextViewText.length() - 2);
                betTextView.setText(betTextViewText + "$");
            }
        });
    }
}
