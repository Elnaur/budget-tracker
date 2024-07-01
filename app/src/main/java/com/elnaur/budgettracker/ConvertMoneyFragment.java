package com.elnaur.budgettracker;

import static java.lang.Double.parseDouble;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import java.util.Locale;

public class ConvertMoneyFragment extends Fragment implements View.OnClickListener {
    public Button btnConvertToHKD, btnConvertToR;
    public EditText editConvertToR, editConvertToHKD;

    public TextView textToR, textToHKD;

    public View view;

    final double EXCHANGE_RATE = 2.37;

    public ConvertMoneyFragment() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view =  inflater.inflate(R.layout.fragment_convert, container, false);

        btnConvertToHKD = (Button) view.findViewById(R.id.btnConvertToHKD);
        btnConvertToHKD.setOnClickListener(this);

        btnConvertToR = (Button) view.findViewById(R.id.btnConvertToR);
        btnConvertToR.setOnClickListener(this);

        editConvertToHKD = (EditText) view.findViewById(R.id.inputR);
        editConvertToR = (EditText) view.findViewById(R.id.inputHKD);

        textToHKD = (TextView) view.findViewById(R.id.textToHKD);
        textToR = (TextView) view.findViewById(R.id.textToR);

        return view;
    }

    @Override
    public void onClick(View view) {
        convertValues(view);
    }

    private void convertValues(View view) {
        double initial;
        double converted;

        int id = view.getId();

        if (id == R.id.btnConvertToHKD) {
            initial = parseDouble(editConvertToHKD.getText().toString());
            converted = initial / EXCHANGE_RATE;
            textToHKD.setText(String.format(Locale.CHINA, "$ %.2f", converted));

        } else if (id == R.id.btnConvertToR) {
            initial = parseDouble(editConvertToR.getText().toString());
            converted = initial * EXCHANGE_RATE;
            textToR.setText(String.format(Locale.ENGLISH, "R %.2f", converted));
        }
    }
}
