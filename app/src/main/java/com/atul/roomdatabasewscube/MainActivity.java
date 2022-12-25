package com.atul.roomdatabasewscube;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    EditText et_item, et_amount;
    Button btn_submit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        et_item = findViewById(R.id.et_item);
        et_amount = findViewById(R.id.et_amount);
        btn_submit = findViewById(R.id.btn_submit);

        DatabaseHelper databaseHelper = DatabaseHelper.getDB(this);


        btn_submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String str_title = et_item.getText().toString().trim();
                String str_amount = et_amount.getText().toString().trim();

                databaseHelper.expenseDao().addTx(new Expense(str_title, str_amount));

                ArrayList<Expense> arrExpenses = (ArrayList<Expense>) databaseHelper.expenseDao().getAllExpense();

                for (int i = 0; i<arrExpenses.size(); i++){

                    Log.e("Response", "title: " +arrExpenses.get(i).getTitle() +" Amount: " +arrExpenses.get(i).getAmount());
                }

            }
        });

    }
}