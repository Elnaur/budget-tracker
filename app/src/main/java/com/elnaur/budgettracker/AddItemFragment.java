package com.elnaur.budgettracker;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import androidx.fragment.app.Fragment;

import java.util.ArrayList;

public class AddItemFragment extends Fragment implements AdapterView.OnItemSelectedListener {

    View view;

    Spinner spnCatagories;

    ArrayList<String> catagoryList;

    public AddItemFragment() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_add_item, container, false);

        catagoryList = new ArrayList<>();
        fillData();

        spnCatagories = (Spinner) view.findViewById(R.id.spnCatagory);

        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<>(getActivity(), android.R.layout.simple_spinner_item, catagoryList);
        arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spnCatagories.setAdapter(arrayAdapter);
        spnCatagories.setOnItemSelectedListener(this);

        return view;
    }

    private ArrayList<String> fillData() {
        catagoryList.add("Gift");
        catagoryList.add("Food");
        catagoryList.add("Transport");
        catagoryList.add("Laundry");
        catagoryList.add("Utensils");
        return catagoryList;
    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }
}
