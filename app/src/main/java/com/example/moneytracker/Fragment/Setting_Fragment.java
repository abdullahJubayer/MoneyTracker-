package com.example.moneytracker.Fragment;


import android.Manifest;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.moneytracker.Activity.ContainerList;
import com.example.moneytracker.Activity.MainActivity;
import com.example.moneytracker.DB.DBHelper;
import com.example.moneytracker.ModelClass.Model_UserInfo;
import com.example.moneytracker.R;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

import static android.app.Activity.RESULT_OK;

/**
 * A simple {@link Fragment} subclass.
 */
public class Setting_Fragment extends Fragment implements View.OnClickListener {

    private static final int CAMERA =1 ;
    private static final int GALLERY =2 ;
    ImageView imageView;
    EditText editText,pass;
    Button button;
    Bitmap imageData;
    DBHelper helper;

    public Setting_Fragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View v= inflater.inflate(R.layout.fragment_setting_, container, false);

        ((ContainerList)getActivity()).setTitle("Register");

        imageView=v.findViewById(R.id.edit_pro_image);
        editText=v.findViewById(R.id.edit_pro_name);
        button=v.findViewById(R.id.pro_update_btn);
        pass=v.findViewById(R.id.edit_pro_pass);

        helper=new DBHelper(getContext());

        imageView.setOnClickListener(this);
        button.setOnClickListener(this);

        return v;
    }

    @Override
    public void onClick(View v) {
        if (v==imageView){
            showPictureDialog();
        }
        if (v==button){
            if (validation()){

                String name=editText.getText().toString();
                String password=pass.getText().toString();

                Model_UserInfo model=new Model_UserInfo(name,password,getBitmapAsByteArray(imageData));
                long result=helper.updateImgPass(model);

                if (result==-1){
                    Toast.makeText(getContext(),"Profile Update Failed",Toast.LENGTH_SHORT).show();
                }else {
                    Toast.makeText(getContext(),"Profile Update Success",Toast.LENGTH_SHORT).show();
                    Intent intent=new Intent(getContext(), MainActivity.class);
                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                    startActivity(intent);
                }
            }
        }
    }

    private boolean validation() {
        boolean val=true;
        String name=editText.getText().toString();
        String password=pass.getText().toString();

        if (name.isEmpty() || name.startsWith(" ")){
            editText.setError("User Name Null");
            val=false;
        }
        if (password.isEmpty() || password.length()<6){
            pass.setError("Password not valid");
            val=false;
        }
        if (imageData ==null){
            Toast.makeText(getContext(),"Image Not Select",Toast.LENGTH_SHORT).show();
            val=false;
        }
        return val;
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
            imageView.setImageBitmap(thumbnail);
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
            imageView.setImageBitmap(mBitmap);
        }
    }

    public static byte[] getBitmapAsByteArray(Bitmap bitmap) {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG, 0, outputStream);
        return outputStream.toByteArray();
    }
}
