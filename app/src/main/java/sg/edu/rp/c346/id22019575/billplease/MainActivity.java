package sg.edu.rp.c346.id22019575.billplease;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.ToggleButton;

public class MainActivity extends AppCompatActivity {

    Button btnSplit, btnReset;
    TextView txtBill, txtEachPays;
    EditText editAmount, editPeople, editDiscount;
    ToggleButton togSvs, togGst;
    RadioGroup rgPay;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnSplit = findViewById(R.id.btn_split);
        editAmount = findViewById(R.id.amount);
        editPeople = findViewById(R.id.people);
        togSvs = findViewById(R.id.svs);
        togGst = findViewById(R.id.gst);
        editDiscount = findViewById(R.id.discount);
        rgPay = findViewById(R.id.pay);
        btnReset = findViewById(R.id.reset);
        txtBill = findViewById(R.id.bill);
        txtEachPays = findViewById(R.id.eachPays);

        btnSplit.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View V){

                double inputAmount = Double.parseDouble(editAmount.getText().toString());
                int inputPeople = Integer.parseInt(editPeople.getText().toString());
                double inputDiscount = Double.parseDouble(editDiscount.getText().toString());

                double totalAmount = inputAmount;



                if (togSvs.isChecked()){
                    totalAmount += inputAmount * 0.1;
                }
                if (togGst.isChecked()){
                    totalAmount += inputAmount * 0.07;
                }
                totalAmount = totalAmount -(inputAmount * (inputDiscount / 100));

                double eachPay = totalAmount/inputPeople;
                int checkedRadioId = rgPay.getCheckedRadioButtonId();
                String payment;
                if (checkedRadioId == R.id.cash){
                    payment = " in cash";
                }else{
                    payment = " via PayNow to  912345678";
                }

                txtBill.setText(String.format("Total Bill: $%.2f", totalAmount));
                txtEachPays.setText("Each pays: "+String.valueOf(eachPay)+payment);


            }
        });

        btnReset.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View V){

                editAmount.setText("");
                editPeople.setText("");
                editDiscount.setText("");

                txtBill.setText("Total Bill: ");
                txtEachPays.setText("Each pays: ");
                rgPay.clearCheck();
            }
        });






    }
}