package com.example.moneytracker;


import android.app.DatePickerDialog;
import android.icu.util.Calendar;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.DatePicker;
import android.widget.Spinner;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.Locale;


/**
 * A simple {@link Fragment} subclass.
 */
public class ExpensesFragment extends Fragment {

    TextView showDateTv;
    DatePicker datePicker;
    private Spinner spinner;
    final Calendar myCalendar = Calendar.getInstance();
    String date;


    public ExpensesFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View v= inflater.inflate(R.layout.fragment_expenses, container, false);


        datePicker=v.findViewById(R.id.DatePickerIdExpenses);
        spinner=v.findViewById(R.id.SpinnerExpensesId);
        showDateTv=v.findViewById(R.id.ShowDatetvIdExpenses);

        ArrayListClass arrayListClass=new ArrayListClass();

        ArrayAdapter<String> adapter=new ArrayAdapter<>(getActivity(),R.layout.showspinneritem_sample_lay,R.id.ShowSpinnerTxtId,arrayListClass.getArrayList());
        spinner.setAdapter(adapter);
        showDateTv.setText(updateLabel());


        final DatePickerDialog.OnDateSetListener date = new DatePickerDialog.OnDateSetListener() {

            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear,
                                  int dayOfMonth) {

                myCalendar.set(java.util.Calendar.YEAR, year);
                myCalendar.set(java.util.Calendar.MONTH, monthOfYear);
                myCalendar.set(java.util.Calendar.DAY_OF_MONTH, dayOfMonth);
                updateLabel();
            }

        };


        showDateTv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new DatePickerDialog(getContext(), date, myCalendar
                        .get(Calendar.YEAR), myCalendar.get(Calendar.MONTH),
                        myCalendar.get(Calendar.DAY_OF_MONTH)).show();
            }
        });

        return v;
    }

    private String updateLabel() {
        String myFormat = "dd/MM/yy"; //In which you need put here
        SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.US);
        date=sdf.format(myCalendar.getTime());
        showDateTv.setText(date);
        return date;
    }
}
