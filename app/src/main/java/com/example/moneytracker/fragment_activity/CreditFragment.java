package com.example.moneytracker.fragment_activity;


import android.Manifest;
import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.icu.util.Calendar;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.moneytracker.DB.CreditTable;
import com.example.moneytracker.ModelClass.CreditModel;
import com.example.moneytracker.R;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Locale;

import static android.app.Activity.RESULT_OK;


/**
 * A simple {@link Fragment} subclass.
 */
public class CreditFragment extends Fragment implements View.OnClickListener {

    EditText amount,note,nameOfCreditor;
    TextView currentDate,backDate;
    ImageView image;
    ImageButton calculator;
    CreditTable creditTable;
    Button saveBtn,cancleBtn;
    final Calendar myCalendar = Calendar.getInstance();
    String cDate;
    private  int GALLERY=1,CAMERA=2;
    Bitmap imageData;

    public CreditFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v=inflater.inflate(R.layout.fragment_credit, container, false);

        amount=v.findViewById(R.id.credit_fragment_amount_id);
        note=v.findViewById(R.id.credit_fragment_note_id);
        nameOfCreditor=v.findViewById(R.id.credit_fragment_nameOfCreditor_id);
        currentDate=v.findViewById(R.id.credit_fragment_current_date_id);
        backDate=v.findViewById(R.id.credit_fragment_back_date_id);
        image=v.findViewById(R.id.credit_fragment_imageview_id);
        calculator=v.findViewById(R.id.credit_fragment_calculator_id);
        saveBtn=v.findViewById(R.id.credit_fragment_save_id);
        cancleBtn=v.findViewById(R.id.credit_fragment_cancel_id);
        creditTable=new CreditTable(getContext());

        SQLiteDatabase db=creditTable.getWritableDatabase();

        currentDate.setOnClickListener(this);
        backDate.setOnClickListener(this);
        image.setOnClickListener(this);
        saveBtn.setOnClickListener(this);


        return v;
    }

    final DatePickerDialog.OnDateSetListener datepicker1 = new DatePickerDialog.OnDateSetListener() {

        @Override
        public void onDateSet(DatePicker view, int year, int monthOfYear,
                              int dayOfMonth) {

            myCalendar.set(java.util.Calendar.YEAR, year);
            myCalendar.set(java.util.Calendar.MONTH, monthOfYear);
            myCalendar.set(java.util.Calendar.DAY_OF_MONTH, dayOfMonth);
            updateLabel();

        }

    };
    final DatePickerDialog.OnDateSetListener datepicker2 = new DatePickerDialog.OnDateSetListener() {

        @Override
        public void onDateSet(DatePicker view, int year, int monthOfYear,
                              int dayOfMonth) {

            myCalendar.set(java.util.Calendar.YEAR, year);
            myCalendar.set(java.util.Calendar.MONTH, monthOfYear);
            myCalendar.set(java.util.Calendar.DAY_OF_MONTH, dayOfMonth);
            updateLabel2();

        }

    };

    @Override
    public void onClick(View v) {
        if (v==currentDate){
            new DatePickerDialog(getContext(), datepicker1, myCalendar
                    .get(Calendar.YEAR), myCalendar.get(Calendar.MONTH),
                    myCalendar.get(Calendar.DAY_OF_MONTH)).show();
        }
        if (v==backDate){
            new DatePickerDialog(getContext(), datepicker2, myCalendar
                    .get(Calendar.YEAR), myCalendar.get(Calendar.MONTH),
                    myCalendar.get(Calendar.DAY_OF_MONTH)).show();
        }

        if (v==image){
            showPictureDialog();
        }
        if (v==saveBtn){
            if (validation()){

                Double Amount=Double.valueOf(amount.getText().toString());
                String nameofCreditor=nameOfCreditor.getText().toString();
                String CurrentDate=currentDate.getText().toString();
                String BackDate=backDate.getText().toString();
                String Note=note.getText().toString();
                byte[] image=getBitmapAsByteArray(imageData);

                CreditModel data=new CreditModel(String.valueOf(Amount),nameofCreditor,CurrentDate,BackDate,Note,image);
                long row=creditTable.insertCredtData(data);

                if (row == -1){
                    Toast.makeText(getContext(),"Data Inserted Failed",Toast.LENGTH_SHORT).show();
                }else {
                    Toast.makeText(getContext(),"Data Inserted Success",Toast.LENGTH_SHORT).show();
                }
            }
            else {
                Toast.makeText(getContext(),"Check All Data ",Toast.LENGTH_SHORT).show();
            }

        }
    }



    private void updateLabel() {
        String myFormat = "dd/MM/yy"; //In which you need put here
        SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.US);
        cDate=sdf.format(myCalendar.getTime());
        currentDate.setText(cDate);
    }
    private void updateLabel2() {
        String myFormat = "dd/MM/yy"; //In which you need put here
        SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.US);
        cDate=sdf.format(myCalendar.getTime());
        backDate.setText(cDate);
    }

    private void showPictureDialog(){
        AlertDialog.Builder pictureDialog = new AlertDialog.Builder(getActivity());
        pictureDialog.setTitle("Select Action");
        String[] pictureDialogItems = {"Select photo from gallery", "Capture photo from camera" };
        pictureDialog.setItems(pictureDialogItems,
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        switch (which) {
                            case 0:
                                choosePhotoFromGallary();
                                break;
                            case 1:
                                takePhotoFromCamera();
                                break;
                        }
                    }
                });
        pictureDialog.show();
    }

    public void choosePhotoFromGallary() {
        Intent galleryIntent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.INTERNAL_CONTENT_URI);
        startActivityForResult(galleryIntent, GALLERY);
    }

    private void takePhotoFromCamera() {

        if (ContextCompat.checkSelfPermission(getActivity(), Manifest.permission.CAMERA)
                != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(getActivity(),
                    new String[]{Manifest.permission.CAMERA},
                    0);
        } else {
            Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
            startActivityForResult(intent, CAMERA);
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == CAMERA && resultCode == RESULT_OK) {
            Bitmap thumbnail = data.getParcelableExtra("data");
            imageData=thumbnail;
            image.setImageBitmap(thumbnail);
        }
        if (requestCode == GALLERY && resultCode == RESULT_OK) {
            Uri thumbnail=data.getData();
            Bitmap  mBitmap = null;
            try {
                mBitmap = MediaStore.Images.Media.getBitmap(getContext().getContentResolver(), thumbnail);
                imageData=mBitmap;
            } catch (IOException e) {
                e.printStackTrace();
            }
            image.setImageBitmap(mBitmap);
        }
    }

    public static byte[] getBitmapAsByteArray(Bitmap bitmap) {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG, 0, outputStream);
        return outputStream.toByteArray();
    }

    public boolean validation(){

        String Amount=amount.getText().toString();
        String nameofCreditor=nameOfCreditor.getText().toString();
        String CurrentDate=currentDate.getText().toString();
        String BackDate=backDate.getText().toString();
        String Note=note.getText().toString();

        boolean val=true;
        if (Amount.isEmpty()){
            val=false;
            amount.setError("Enter Amount");
        }
        if (nameofCreditor.isEmpty() || nameofCreditor.startsWith(" ")){
            val=false;
            nameOfCreditor.setError("Enter Creditor Name");
        }
        if (CurrentDate.isEmpty() || CurrentDate.startsWith(" ")){
            val=false;
            currentDate.setError("Enter Current Date");
        }if (BackDate.isEmpty() || BackDate.startsWith(" ")){
            val=false;
            backDate.setError("Enter Back Date");
        }
        if (Note.isEmpty()){
            val=false;
            note.setError("Enter Note");
        }
        if (imageData==null){
            val=false;
            Toast.makeText(getContext(),"Image Not Selected ",Toast.LENGTH_LONG).show();
        }
        return val;
    }
}
