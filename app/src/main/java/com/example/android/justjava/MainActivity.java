package com.example.android.justjava;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.CheckBox;
import android.widget.TextView;

import java.text.NumberFormat;

/**
 * This app displays an order form to order coffee.
 */
public class MainActivity extends AppCompatActivity {

    int quantity = 2;
    int priceCoffee = 5;
    boolean addCream;

    TextView summaryTextView;
    TextView quantityTextView;
    CheckBox addCreamBox;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        summaryTextView = (TextView) findViewById(R.id.summary_text_view);
        quantityTextView = (TextView) findViewById(R.id.quantity_text_view);
        addCreamBox = (CheckBox) findViewById(R.id.add_cream_box);
    }

    public void increment(View view)
    {
        quantity=quantity+1;
        displayQuantity(quantity);
    }

    public void decrement(View view)
    {
        if (quantity==0)
        {
            quantity = 0;
            displayQuantity(quantity);
        }
        else
        {
            quantity=quantity-1;
            displayQuantity(quantity);
        }
    }

    public void submitOrder(View view) {
        int price = calculatePrice();

        if (addCreamBox.isChecked())
            addCream = true;
        else
            addCream = false;

        createOrderSummary(price);
    }

    public void resetOrder(View view)
    {
        quantity = 0;
        displayQuantity(quantity);

        addCream = false;
        addCreamBox.setChecked(false);

        resetSummary();
    }

    private int calculatePrice()
    {
        return quantity * priceCoffee;
    }

    private void createOrderSummary(int price)
    {
        summaryTextView.setText("Name: Person \n" +
                "Quantity: " + quantity + "\n" +
                "Total: " + NumberFormat.getCurrencyInstance().format(price) + "\n" +
                "Add Whipped cream? " + addCream + "\n" +
                "Thank you!");
    }

    private void resetSummary() {
        summaryTextView.setText("Orders reset!");
    }

    private void displayQuantity(int numberOfCoffees) {
        quantityTextView.setText("" + numberOfCoffees);
    }
}