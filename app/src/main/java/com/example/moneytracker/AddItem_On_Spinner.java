package com.example.moneytracker;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.moneytracker.Fragment.DepositFragment;

public class AddItem_On_Spinner extends AppCompatActivity {
private EditText addItemEt;
private Button CancelBtn,SaveBtn;
private String ItemValue;
private Bundle bundle;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_item__on__spinner);

        addItemEt=findViewById(R.id.AddItemETId);
        CancelBtn=findViewById(R.id.CancelBtnId);
        SaveBtn=findViewById(R.id.ADDNewBtnId);
        ItemValue=addItemEt.getText().toString();

        CancelBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
SaveBtn.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {
        bundle=getIntent().getExtras();
        if(bundle!=null){
            String text=bundle.getString("name");
            previusActivity(text);
        }

    }
});

    }

    public void previusActivity(String text){
        if (text.equals("Deposit")){

            Intent intent=new Intent(getApplicationContext(), DepositFragment.class);
            intent.putExtra("newItem",ItemValue);
            startActivity(intent);

        }
    }
}
