package com.elnaur.budgettracker;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;

public class AddItemFragment extends Fragment implements AdapterView.OnItemSelectedListener {

    View view;

    Spinner spnCategory;

    HashSet<String> categorySet;

    ArrayList<String> categoryList;

    ArrayAdapter<String> arrayAdapter;

    boolean startup = true;

    public AddItemFragment() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_add_item, container, false);

        categorySet = new HashSet<>();
        spnCategory = (Spinner) view.findViewById(R.id.spnCategory);

        fillData();
        arrayAdapter = new ArrayAdapter<>(getActivity(), android.R.layout.simple_spinner_item, categoryList);
        arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spnCategory.setAdapter(arrayAdapter);
        spnCategory.setOnItemSelectedListener(this);

        return view;
    }

    private void fillData() {
        categorySet.add("Gifts");
        categorySet.add("Food");
        categorySet.add("Transport");
        categorySet.add("Laundry");
        categorySet.add("Utensils");
        categorySet.add("Entertainment");

        updateData();
    }

    private void updateData() {
        categoryList = new ArrayList<>(categorySet);
        categoryList.add("Create new category...");
    }

    private void addData(String category) {
        if (categorySet.add(category)) {
            updateData();
            arrayAdapter.clear();
            arrayAdapter.addAll(categoryList);
            arrayAdapter.notifyDataSetChanged();
        } else {
            CharSequence text = "Category already exists";
            Toast toast = Toast.makeText(getContext(), text, Toast.LENGTH_SHORT);
            toast.show();
        }
    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
        if (!startup) {
            if (i == categorySet.size()) { // Last index is create new category

                // Set up AlertDialog builder
                AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
                builder.setTitle("New category name");
                View viewInflated = LayoutInflater.from(getContext()).inflate(R.layout.fragment_new_category, (ViewGroup) getView(), false);
                final EditText input = viewInflated.findViewById(R.id.editNewCategory);
                builder.setView(viewInflated);

                builder.setPositiveButton(android.R.string.ok, (dialog, which) -> {
                    dialog.dismiss();
                    String newCategoryName = input.getText().toString();
                    addData(newCategoryName);
                });

                builder.setNegativeButton(android.R.string.cancel, (dialog, which) -> dialog.cancel());
                builder.show();

            } else {

            }
        } else {
            
            startup = false;
        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }
}
