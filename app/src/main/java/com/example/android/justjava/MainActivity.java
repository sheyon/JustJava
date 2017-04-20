package com.example.android.justjava;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import java.text.NumberFormat;

/**
 * This app displays an order form to order coffee.
 */
public class MainActivity extends AppCompatActivity {

    int quantity = 2;
    int priceCoffee = 5;

    TextView summaryTextView;
    TextView quantityTextView;
    TextView priceTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        summaryTextView = (TextView) findViewById(R.id.summary_text_view);
        quantityTextView = (TextView) findViewById(R.id.quantity_text_view);
        priceTextView = (TextView) findViewById(R.id.price_text_view);
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
        displayPrice(price);
        createOrderSummary(price);
    }

    public void resetOrder(View view)
    {
        quantity = 0;
        displayQuantity(quantity);
        resetSummary("Orders cleared!");
    }

    private int calculatePrice()
    {
        return quantity * priceCoffee;
    }

    private void createOrderSummary(int price)
    {
        summaryTextView.setText("Name: Person \nQuantity: " + quantity + "\nTotal: " + price + "\nThank you!");
    }

    private void displayQuantity(int numberOfCoffees) {
        quantityTextView.setText("" + numberOfCoffees);
    }

    private void displayPrice(int number) {
        priceTextView.setText(NumberFormat.getCurrencyInstance().format(number));
    }

    private void resetSummary(String message) {
        summaryTextView.setText(message);
    }
}