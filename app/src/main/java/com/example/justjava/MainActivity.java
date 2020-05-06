package com.example.justjava;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

/**
 * This app displays an order form to order coffee.
 */
public class MainActivity extends AppCompatActivity {

    int quantity=0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    /**
     * This method is called when the order button is clicked.
     */
    public void submitOrder(View view) {
        String orderSummary=createOrderSummary();
        EditText et =((EditText) findViewById(R.id.TextBar));
        String name=et.getText().toString();
        Intent i=new Intent(Intent.ACTION_SEND);
        i.setType("text/plain");
        i.putExtra(Intent.EXTRA_TEXT,orderSummary);
        i.putExtra(Intent.EXTRA_SUBJECT,"Just Java Order for "+name);
        if(i.resolveActivity(getPackageManager())!=null)
            startActivity(i);
    }

    private String createOrderSummary()
    {
        boolean isCheckedWhippedCream=((CheckBox) findViewById(R.id.checkBoxWhippedCream)).isChecked();
        boolean isCheckedChocolate=((CheckBox) findViewById(R.id.checkBoxChocolate)).isChecked();
        EditText et =((EditText) findViewById(R.id.TextBar));
        String name=et.getText().toString();
        return "Name: "+name+"\nAdd whipped cream? " +isCheckedWhippedCream+"\nAdd Chocolate? "+isCheckedChocolate+"\nQuantity: "+quantity+"\nTotal: "+calculatePrice(isCheckedChocolate,isCheckedWhippedCream)+"\nThank You!";
    }

    private int calculatePrice(boolean isCheckedChocolate, boolean isCheckedWhippedCream)
    {
        int price=0;
        if(isCheckedChocolate)
            price=price+2*quantity;
        if(isCheckedWhippedCream)
            price=price+1*quantity;
        price=price+quantity*5;
        return price;
    }

    /**
     * This method displays the given quantity value on the screen.
     */
    private void display(int number) {
        TextView quantityTextView = (TextView) findViewById(R.id.quantity_text_view);
        quantityTextView.setText("" + number);
    }
    public void increment(View view)
    {
        if(quantity>=100) {
            Toast t=Toast.makeText(this,"Too much Coffee!!",Toast.LENGTH_SHORT);
            t.show();
            return;
        }
        quantity++;
        display(quantity);
    }
    public void decrement(View view)
    {
        if(quantity<=1) {
            Toast t = Toast.makeText(this, "Order at least one!!", Toast.LENGTH_SHORT);
            t.show();
            return;
        }
        quantity--;
        display(quantity);
    }
}