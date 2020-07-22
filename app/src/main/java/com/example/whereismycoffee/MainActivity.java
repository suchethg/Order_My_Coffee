package com.example.whereismycoffee;

import androidx.appcompat.app.AppCompatActivity;


import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;



public class MainActivity extends AppCompatActivity {
String customerName = "";
boolean checkChocolate = false;
TextView quantity_textView;
int quantity  = 1;
boolean checkWhippedCream  =  false;
EditText nameOfCustomer;
int total =0;
boolean haveOrdered = false;

@Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        quantity_textView  = (TextView) findViewById(R.id.quantity);
        nameOfCustomer = (EditText)findViewById(R.id.name);


}


    public void increment(View view)
    {
        if(quantity>=1 && quantity<100) {
            ++quantity;
            quantity_textView.setText(Integer.toString(quantity));

        }
        else
        {
            Toast.makeText(this, "Cannot order more than 100 coffee", Toast.LENGTH_SHORT).show();
        }
    }
    public void decrement(View view) {
        if (quantity >1) {
            quantity--;
            quantity_textView.setText(Integer.toString(quantity));
        }
        else{
            Toast.makeText(this, "Must order minimum 1 coffee", Toast.LENGTH_SHORT).show();
        }
    }

public void order(View view)
{
    customerName = nameOfCustomer.getText().toString();
    CheckBox chocolate = (CheckBox)findViewById(R.id.chocolate);
    CheckBox whippedCream = (CheckBox)findViewById(R.id.whippedCream);
    checkWhippedCream = whippedCream.isChecked();
    checkChocolate = chocolate.isChecked();
    total = quantity*5;
    if(checkChocolate)
    {
        total =(2+5)*quantity;

    }
    if (checkWhippedCream)
    {
        total=(1+5)*quantity;
    }
    if(checkChocolate && checkWhippedCream)
    {
        total=(2+1+5)*quantity;
    }

    TextView order_summary = (TextView) findViewById(R.id.order_summary);
    order_summary.setText("Name : "+customerName+"\n"+"Add whipped cream?"+checkWhippedCream+"\n"+"Add chocolate?"+checkChocolate+"\n"+"Quantity:"+Integer.toString(quantity)+"\n"+"Total : "+"$"+Integer.toString(total)+"\n"+"Thank you!");
    haveOrdered = true;
}

public void bill(View view)
{
    customerName = nameOfCustomer.getText().toString();
    if (haveOrdered) {
        Intent intent = new Intent(Intent.ACTION_SENDTO);
        intent.setData(Uri.parse("mailto:"));
        intent.putExtra(Intent.EXTRA_SUBJECT,"Coffee Bill for "+customerName);
        intent.putExtra(Intent.EXTRA_TEXT, "Name : " + customerName + "\n" + "Add whipped cream?" + checkWhippedCream + "\n" + "Add chocolate?" + checkChocolate + "\n" + "Quantity:" + Integer.toString(quantity) + "\n" + "Total : " + "$" + Integer.toString(total) + "\n" + "Thank you!");
        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivity(intent);
        }
    }
    else
    {
        Toast.makeText(this, "Please order first in order to generate the bill", Toast.LENGTH_SHORT).show();
    }
}




    }




