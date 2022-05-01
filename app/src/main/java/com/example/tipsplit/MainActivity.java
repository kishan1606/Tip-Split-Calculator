package com.example.tipsplit;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private EditText bill_total, numPeople;
    private TextView tipAmount,totalWithTip,totalPerPerson,ov;
    private RadioGroup rg;

    public double twt = 0;                          //saves total amount after tip

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        bill_total = findViewById(R.id.bill_total_with_tax);
        numPeople = findViewById(R.id.number_of_people);
        tipAmount = findViewById(R.id.tip_amount);
        totalWithTip = findViewById(R.id.total_with_tip);
        totalPerPerson = findViewById(R.id.total_per_person);
        ov = findViewById(R.id.overage);
        rg = findViewById(R.id.radioGroup);
    }
    public void doPercentage(View v){

        twt = 0;

        String s1 = bill_total.getText().toString();

        if(s1.trim().isEmpty()){                    //check that user enter bill amount
            doClear(null);
            Toast.makeText(this,"Enter Total Bill Amount",Toast.LENGTH_SHORT).show();
            return;
        }

        double d1 = Double.parseDouble(s1);
        double t = 0;

        if(v.getId() == R.id.rb12){
            t = (12 * d1) / 100;
            twt = d1 + t;
            tipAmount.setText("$"+String.format("%.2f",t));
            totalWithTip.setText("$"+String.format("%.2f",twt));
        }
        else if(v.getId() == R.id.rb15){
            t = (15 * d1) / 100;
            twt = d1 + t;
            tipAmount.setText("$"+String.format("%.2f",t));
            totalWithTip.setText("$"+String.format("%.2f",twt));
        }
        else if(v.getId() == R.id.rb18){
            t = (18 * d1) / 100;
            twt = d1 + t;
            tipAmount.setText("$"+String.format("%.2f",t));
            totalWithTip.setText("$"+String.format("%.2f",twt));
        }
        else if(v.getId() == R.id.rb20){
            t = (20 * d1) / 100;
            twt = d1 + t;
            tipAmount.setText("$"+String.format("%.2f",t));
            totalWithTip.setText("$"+String.format("%.2f",twt));
        }
    }

    public void doCalculate(View v){

        String s1 = bill_total.getText().toString();
        if(s1.trim().isEmpty()) {                   //check that user enter bill amount
            Toast.makeText(this,"Enter Total Bill Amount",Toast.LENGTH_SHORT).show();
            return;
        }

        if(rg.getCheckedRadioButtonId() == -1){    //check that user checked radio button
            Toast.makeText(this,"Select Tip Percentage",Toast.LENGTH_SHORT).show();
            return;
        }

        String s2 = numPeople.getText().toString();
        if(s2.trim().isEmpty()){                  //check that user enter number of people
            Toast.makeText(this,"Enter Number of people",Toast.LENGTH_SHORT).show();
            return;
        }


        double d1 = Double.parseDouble(s1);
        double d2 = Double.parseDouble(s2);

        if(d2 == 0){                             //if user enter 0 for number of people
            Toast.makeText(this,"Number of people cannot be 0",Toast.LENGTH_SHORT).show();
            totalPerPerson.setText("-----");
            ov.setText("-----");
        }

        else{
            double tpp = Math.ceil(twt/d2 * 100) / 100;
            double o = tpp * d2 - twt;

            totalPerPerson.setText("$"+String.format("%.2f",tpp));
            ov.setText("$"+String.format("%.2f",o));
        }
    }

    public void doClear(View v){

        bill_total.setText("");
        numPeople.setText("");
        tipAmount.setText("");
        totalWithTip.setText("");
        totalPerPerson.setText("");
        ov.setText("");
        rg.clearCheck();
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {

        outState.putString("BILLTOTAL",bill_total.getText().toString());
        outState.putString("NUMBEROFPEOPLE",numPeople.getText().toString());
        outState.putString("TIPAMOUNT",tipAmount.getText().toString());
        outState.putString("TOTALWITHTIP",totalWithTip.getText().toString());
        outState.putString("TOTALPERPERSON",totalPerPerson.getText().toString());
        outState.putString("OVERAGE",ov.getText().toString());

        super.onSaveInstanceState(outState);
    }

    protected void onRestoreInstanceState(Bundle savedInstanceState){

        super.onRestoreInstanceState(savedInstanceState);

        bill_total.setText(savedInstanceState.getString("BILLTOTAL"));
        numPeople.setText(savedInstanceState.getString("NUMBEROFPEOPLE"));
        tipAmount.setText(savedInstanceState.getString("TIPAMOUNT"));
        totalWithTip.setText(savedInstanceState.getString("TOTALWITHTIP"));
        totalPerPerson.setText(savedInstanceState.getString("TOTALPERPERSON"));
        ov.setText(savedInstanceState.getString("OVERAGE"));
    }
}