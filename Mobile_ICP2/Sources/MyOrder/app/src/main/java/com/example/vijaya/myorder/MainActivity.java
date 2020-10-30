package com.example.vijaya.myorder;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private static final String MAIN_ACTIVITY_TAG = "MainActivity";
    final int Pizza_Price = 10;
    final int Cheese_PRICE = 1;
    final int Tomato_PRICE = 2;
    final int PineApple_PRICE = 3;
    final int Onion_PRICE = 1;
    final int Meat_PRICE = 3;
    final int Shrimp_PRICE = 4;
    final int Medium_PRICE = 3;
    final int Large_PRICE = 6;
    int quantity = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button order_btn = (Button) findViewById(R.id.order_btn);
        order_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                submitOrder(view);
            }
        });

        Button summary_btn = (Button) findViewById(R.id.summary_btn);

        summary_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showSummary(view);

            }
        });
    }

    /**
     * This method is called when the order button is clicked.
     */

    public void showSummary(View view){
        // get user input
        EditText userInputNameView = (EditText) findViewById(R.id.user_input);
        String userInputName = userInputNameView.getText().toString();

        // checks for the toppings
        CheckBox Cheese = (CheckBox) findViewById(R.id.cheese_checked);
        boolean hasCheese = Cheese.isChecked();

        CheckBox Tomato = (CheckBox) findViewById(R.id.tomato_checked);
        boolean hasTomato = Tomato.isChecked();

        CheckBox PineApple = (CheckBox) findViewById(R.id.pineaplle_checked);
        boolean hasPineApple = PineApple.isChecked();

        CheckBox Onion = (CheckBox) findViewById(R.id.onion_checked);
        boolean hasOnion = Onion.isChecked();

        CheckBox Meat = (CheckBox) findViewById(R.id.meat_checked);
        boolean hasMeat = Meat.isChecked();

        CheckBox Shrimp = (CheckBox) findViewById(R.id.shrimp_checked);
        boolean hasShrimp = Shrimp.isChecked();

        //check the size
        RadioButton Medium = (RadioButton) findViewById(R.id.medium);
        boolean isMedium = Medium.isChecked();

        RadioButton Large = (RadioButton) findViewById(R.id.large);
        boolean isLarge = Large.isChecked();

        // calculate and store the total price
        float totalPrice = calculatePrice(hasCheese, hasTomato, hasPineApple, hasOnion, hasMeat, hasShrimp, isMedium, isLarge);
        String orderSummaryMessage = createOrderSummary(userInputName, hasCheese, hasTomato,  hasPineApple, hasOnion, hasMeat, hasShrimp,isMedium, isLarge, totalPrice);
        Intent intent = new Intent(MainActivity.this, Summary.class);
        //Send message to Summary class
        intent.putExtra("Name", userInputName);
        intent.putExtra("Summary", orderSummaryMessage);

        startActivity(intent);


    }

    public void submitOrder(View view) {

        // get user input
        EditText userInputNameView = (EditText) findViewById(R.id.user_input);
        String userInputName = userInputNameView.getText().toString();

        // checks for the toppings
        CheckBox Cheese = (CheckBox) findViewById(R.id.cheese_checked);
        boolean hasCheese = Cheese.isChecked();

        CheckBox Tomato = (CheckBox) findViewById(R.id.tomato_checked);
        boolean hasTomato = Tomato.isChecked();

        CheckBox PineApple = (CheckBox) findViewById(R.id.pineaplle_checked);
        boolean hasPineApple = PineApple.isChecked();

        CheckBox Onion = (CheckBox) findViewById(R.id.onion_checked);
        boolean hasOnion = Onion.isChecked();

        CheckBox Meat = (CheckBox) findViewById(R.id.meat_checked);
        boolean hasMeat = Meat.isChecked();

        CheckBox Shrimp = (CheckBox) findViewById(R.id.shrimp_checked);
        boolean hasShrimp = Shrimp.isChecked();

        //check the size
        RadioButton Medium = (RadioButton) findViewById(R.id.medium);
        boolean isMedium = Medium.isChecked();

        RadioButton Large = (RadioButton) findViewById(R.id.large);
        boolean isLarge = Large.isChecked();

        // calculate and store the total price
        float totalPrice = calculatePrice(hasCheese, hasTomato, hasPineApple, hasOnion, hasMeat, hasShrimp, isMedium, isLarge);

        // create and store the order summary
        String orderSummaryMessage = createOrderSummary(userInputName, hasCheese, hasTomato,  hasPineApple, hasOnion, hasMeat, hasShrimp,isMedium,isLarge, totalPrice);
        // Write the relevant code for making the buttons work(i.e implement the implicit and explicit intents
        Intent intent = new Intent(MainActivity.this, Order.class);
        //Send message to Order class
        intent.putExtra("Name", userInputName);
        intent.putExtra("Summary", orderSummaryMessage);

        startActivity(intent);
    }



    private String boolToString(boolean bool) {
        return bool ? (getString(R.string.yes)) : (getString(R.string.no));
    }

    private String createOrderSummary(String userInputName, boolean hasCheese, boolean hasTomato,boolean hasPineApple, boolean hasOnion, boolean hasMeat, boolean hasShrimp,boolean isMedium,boolean isLarge, float price) {
        String SizeOrder;
        if(isMedium){
            SizeOrder = getString(R.string.order_summary_size, "Medium");
        }else if(isLarge){
            SizeOrder = getString(R.string.order_summary_size, "Large");
        }else{
            SizeOrder = getString(R.string.order_summary_size, "Small");
        }

        String orderSummaryMessage = getString(R.string.order_summary_name, userInputName) + "\n" +
                getString(R.string.order_summary_Cheese, boolToString(hasCheese)) + "\n" +
                getString(R.string.order_summary_tomato, boolToString(hasTomato)) + "\n" +
                getString(R.string.order_summary_pineapple, boolToString(hasPineApple)) + "\n" +
                getString(R.string.order_summary_onion, boolToString(hasOnion)) + "\n" +
                getString(R.string.order_summary_meat, boolToString(hasMeat)) + "\n" +
                getString(R.string.order_summary_shrimp, boolToString(hasShrimp)) + "\n" +
                SizeOrder+ "\n" +
                getString(R.string.order_summary_quantity, quantity) + "\n" +
                getString(R.string.order_summary_total_price, price) + "\n" +
                getString(R.string.thank_you);

        return orderSummaryMessage;
    }

    /**
     * Method to calculate the total price
     *
     * @return total Price
     */
    private float calculatePrice(boolean hasCheese, boolean hasTomato, boolean hasPineApple, boolean hasOnion, boolean hasMeat, boolean hasShrimp,boolean isMedium,boolean isLarge) {
        int basePrice = Pizza_Price;
        if (hasCheese) {
            basePrice += Cheese_PRICE;
        }
        if (hasTomato) {
            basePrice += Tomato_PRICE;
        }
        if (hasPineApple) {
            basePrice += PineApple_PRICE;
        }
        if (hasOnion) {
            basePrice += Onion_PRICE;
        }
        if (hasMeat) {
            basePrice += Meat_PRICE;
        }
        if (hasShrimp) {
            basePrice += Shrimp_PRICE;
        }
        if (isMedium){
            basePrice += Medium_PRICE;
        }
        if (isLarge){
            basePrice += Large_PRICE;

        }
        return quantity * basePrice;
    }

    /**
     * This method displays the given quantity value on the screen.
     */
    private void display(int number) {
        TextView quantityTextView = (TextView) findViewById(R.id.quantity_text_view);
        quantityTextView.setText("" + number);
    }

    /**
     * This method increments the quantity of pizzas by one
     *
     * @param view on passes the view that we are working with to the method
     */

    public void increment(View view) {
        if (quantity < 30) {
            quantity = quantity + 1;
            display(quantity);
        } else {
            Log.i("MainActivity", "Please select less than thrity pizzas");
            Context context = getApplicationContext();
            String lowerLimitToast = getString(R.string.too_much_pizza);
            int duration = Toast.LENGTH_SHORT;
            Toast toast = Toast.makeText(context, lowerLimitToast, duration);
            toast.show();
            return;
        }
    }

    /**
     * This method decrements the quantity of pizza by one
     *
     * @param view passes on the view that we are working with to the method
     */
    public void decrement(View view) {
        if (quantity > 1) {
            quantity = quantity - 1;
            display(quantity);
        } else {
            Log.i("MainActivity", "Please select at least one pizza");
            Context context = getApplicationContext();
            String upperLimitToast = getString(R.string.too_little_pizza);
            int duration = Toast.LENGTH_SHORT;
            Toast toast = Toast.makeText(context, upperLimitToast, duration);
            toast.show();
            return;
        }
    }
}