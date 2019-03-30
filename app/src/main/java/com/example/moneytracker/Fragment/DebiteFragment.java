package com.example.moneytracker.Fragment;


import android.Manifest;
import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
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
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import com.example.moneytracker.DB.DBHelper;
import com.example.moneytracker.ModelClass.Model;
import com.example.moneytracker.R;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import static android.app.Activity.RESULT_OK;


/**
 * A simple {@link Fragment} subclass.
 */
public class DebiteFragment extends Fragment implements View.OnClickListener {

    EditText amount,note,nameOfDebtor;
    TextView currentDate,backDate;
    ImageView image;
    ImageButton calculator;
    DBHelper helper;
    Button saveBtn,cancleBtn;
    final Calendar myCalendar = Calendar.getInstance();
    String cDate;
    private  int GALLERY=1,CAMERA=2;
    Bitmap imageData;
    private static final String Type="Debit";
    private Model accessModel;
    private String Month,Year;


    public DebiteFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View v=inflater.inflate(R.layout.fragment_debite, container, false);
        findId(v);

        if (getArguments() != null){
            saveBtn.setText("UPDATE");
            accessModel= (Model) getArguments().getSerializable("data");
            if (accessModel != null){
                amount.setText(accessModel.getAmount());
                nameOfDebtor.setText(accessModel.getColumn2());
                currentDate.setText(accessModel.getColumn3());
                backDate.setText(accessModel.getColumn4());
                image.setImageBitmap(getBitmap(accessModel.getImage()));
                imageData=getBitmap(accessModel.getImage());
                note.setText(accessModel.getNote());
            }
        }

        helper=new DBHelper(getContext());
        SQLiteDatabase db=helper.getWritableDatabase();

        currentDate.setOnClickListener(this);
        backDate.setOnClickListener(this);
        image.setOnClickListener(this);
        saveBtn.setOnClickListener(this);



        return v;
    }

    private void findId(View v) {
        amount=v.findViewById(R.id.debit_fragment_amount_id);
        note=v.findViewById(R.id.debit_fragment_note_id);
        nameOfDebtor=v.findViewById(R.id.debit_fragment_nameOfDebtor_id);
        currentDate=v.findViewById(R.id.debit_fragment_current_date_id);
        backDate=v.findViewById(R.id.debit_fragment_back_date_id);
        image=v.findViewById(R.id.debit_fragment_imageview_id);
        calculator=v.findViewById(R.id.debit_fragment_calculator_id);
        saveBtn=v.findViewById(R.id.debit_fragment_save_id);
        cancleBtn=v.findViewById(R.id.debit_fragment_cancel_id);
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
                String Amount=amount.getText().toString();
                String name=nameOfDebtor.getText().toString();
                String CurrentDate=currentDate.getText().toString();
                String BackDate=backDate.getText().toString();
                byte[] image=getBitmapAsByteArray(imageData);
                String Note=note.getText().toString();

                if (saveBtn.getText().equals(getString(R.string.save_txt))){

                    if (Month==null || Year==null){
                        SimpleDateFormat formaterMonth=new SimpleDateFormat("MM/yyy");
                        SimpleDateFormat formaterYear=new SimpleDateFormat("yyy");
                        Date date=new Date();
                        Month=formaterMonth.format(date);
                        Year=formaterYear.format(date);
                    }

                    Model data=new Model(0,Amount,name,CurrentDate,BackDate,Note,image,Type,Month,Year);
                    long row=helper.insertData(data);

                    if (row == -1){
                        Toast.makeText(getContext(),"Data Inserted Failed",Toast.LENGTH_SHORT).show();
                    }else {
                        Toast.makeText(getContext(),"Data Inserted Success",Toast.LENGTH_SHORT).show();
                    }
                }
                else if(saveBtn.getText().equals(getString(R.string.update_txt))){


                    if (Month==null || Year==null){
                        SimpleDateFormat formaterMonth=new SimpleDateFormat("MM/yyy");
                        SimpleDateFormat formaterYear=new SimpleDateFormat("yyy");
                        Date date=new Date();
                        Month=formaterMonth.format(date);
                        Year=formaterYear.format(date);
                    }

                    Model data=new Model(0,Amount,name,CurrentDate,BackDate,Note,image,Type,Month,Year);
                    long result=helper.updateData(accessModel.getID(),data);

                    if (result == -1){
                        Toast.makeText(getContext(),"Data Updated Failed",Toast.LENGTH_SHORT).show();
                    }else {
                        Toast.makeText(getContext(),"Data Updated Success",Toast.LENGTH_SHORT).show();
                    }
                }
            }
            else {
                Toast.makeText(getContext(),"Check All Data ",Toast.LENGTH_SHORT).show();
            }
        }
    }



    private void updateLabel() {
        String myFormat = "dd/MM/yyy";
        String m = "MM/yyy";
        String y = "yyy";
        SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.getDefault());
        SimpleDateFormat month = new SimpleDateFormat(m,Locale.getDefault());
        SimpleDateFormat year = new SimpleDateFormat(y,Locale.getDefault());
        cDate=sdf.format(myCalendar.getTime());
        Month=month.format(myCalendar.getTime());
        Year=year.format(myCalendar.getTime());
        currentDate.setText(cDate);
    }
    private void updateLabel2() {
        String myFormat = "dd/MM/yyy"; //In which you need put here
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
        String nameofCreditor=nameOfDebtor.getText().toString();
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
            nameOfDebtor.setError("Enter Creditor Name");
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
    private Bitmap getBitmap(byte[] image){
        Bitmap img=null;
        try {
            img= BitmapFactory.decodeByteArray(image,0,image.length);
            return img;
        }catch (Exception e){
            return null;
        }
    }
}
