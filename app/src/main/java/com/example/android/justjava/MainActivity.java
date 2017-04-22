package com.example.android.justjava;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.text.NumberFormat;

/**
 * This app displays an order form to order coffee.
 */
public class MainActivity extends AppCompatActivity {

    int quantity = 2;
    int priceCoffee = 5;

    int priceCream = 1;
    int priceChocolate = 1;

    boolean addCream = false;
    boolean addChocolate = false;

    TextView summaryTextView;
    TextView quantityTextView;
    CheckBox addCreamBox;
    CheckBox addChocolateBox;

    String orderName;
    EditText orderNameField;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        summaryTextView = (TextView) findViewById(R.id.summary_text_view);
        quantityTextView = (TextView) findViewById(R.id.quantity_text_view);
        addCreamBox = (CheckBox) findViewById(R.id.add_cream_box);
        addChocolateBox = (CheckBox) findViewById(R.id.add_chocolate_box);
        orderNameField = (EditText) findViewById(R.id.order_name_field);
    }

    public void increment(View view)
    {
        if (quantity == 10)
        {
            quantity = 10;
            displayQuantity(quantity);
            Toast toast = Toast.makeText(this, getString(R.string.error_maximum_ten), Toast.LENGTH_SHORT);
            toast.show();
        }
        else
        {
            quantity=quantity+1;
            displayQuantity(quantity);
        }
    }

    public void decrement(View view)
    {
        if (quantity==1)
        {
            quantity = 1;
            displayQuantity(quantity);
        }
        else
        {
            quantity=quantity-1;
            displayQuantity(quantity);
        }
    }

    public void submitOrder(View view) {

        if (quantity == 0)
        {
            summaryTextView.setText(getString(R.string.error_cannot_order_zero));
            return;
        }

        if (addCreamBox.isChecked())
            addCream = true;
        else
            addCream = false;

        if (addChocolateBox.isChecked())
            addChocolate = true;
        else
            addChocolate = false;

        orderName = orderNameField.getText().toString();

        int price = calculatePrice();
        createOrderSummary(price);
    }

    public void resetOrder(View view)
    {
        quantity = 1;
        displayQuantity(quantity);

        addCream = false;
        addCreamBox.setChecked(false);

        addChocolate = false;
        addChocolateBox.setChecked(false);

        orderNameField.setText(getString(R.string.order_name));

        resetSummary();
    }

    private int calculatePrice()
    {
        int TotalCoffeePrice = quantity * priceCoffee;

        if (addCream)
        {
            TotalCoffeePrice = TotalCoffeePrice + ( priceCream * quantity );
        }

        if (addChocolate)
        {
            TotalCoffeePrice = TotalCoffeePrice + ( priceChocolate * quantity );
        }

        return TotalCoffeePrice;
    }

    private void createOrderSummary(int price) {

        String order;

        summaryTextView.setText("Name: " + orderName + "\n" +
                "Quantity: " + quantity + "\n" +
                "Total: " + NumberFormat.getCurrencyInstance().format(price) + "\n" +
                "Add Whipped cream? " + addCream + "\n" +
                "Add Chocolate? " + addChocolate + "\n" +
                "Thank you!");
        order = summaryTextView.getText().toString();

        Intent intent = new Intent(Intent.ACTION_SENDTO);
        intent.setData(Uri.parse("mailto:"));
        intent.putExtra(Intent.EXTRA_EMAIL, "justjava@justjava.com");
        intent.putExtra(Intent.EXTRA_SUBJECT, "My JustJava Order");
        intent.putExtra(Intent.EXTRA_TEXT, order);
        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivity(intent);
        }
    }

    private void resetSummary() {
        summaryTextView.setText("");
        Toast toast = Toast.makeText(this, getString(R.string.orders_reset), Toast.LENGTH_SHORT);
        toast.show();
    }

    private void displayQuantity(int numberOfCoffees) {
        quantityTextView.setText("" + numberOfCoffees);
    }
}