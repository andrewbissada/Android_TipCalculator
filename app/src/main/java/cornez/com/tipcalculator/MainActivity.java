package cornez.com.tipcalculator;

import android.content.Context;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.WindowManager;
import android.view.inputmethod.EditorInfo;
import android.view.Window;
import android.view.ViewGroup.LayoutParams;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;
import java.text.NumberFormat;
import java.util.Locale;

import org.w3c.dom.Text;

import static cornez.com.tipcalculator.R.id.tenPercent;
import static cornez.com.tipcalculator.R.id.tip;
import static cornez.com.tipcalculator.R.id.tipAmount;

public class MainActivity extends AppCompatActivity {

    //RadioGroup radioGroup;
    //RadioButton tenPercent;
    //RadioButton fifteenPercent;
    //RadioButton eighteenPercent;
    //RadioButton twentyPercent;
    EditText editText;
    String tipAmountString;
    String totalAmountString;
    TextView tipAmountTextView;
    TextView totalAmountTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        RadioButton tenPercent = (RadioButton) findViewById(R.id.tenPercent);
        RadioButton fifteenPercent = (RadioButton) findViewById(R.id.fifteenPercent);
        RadioButton eighteenPercent = (RadioButton) findViewById(R.id.eighteenPercent);
        RadioButton twentyPercent = (RadioButton) findViewById(R.id.twentyPercent);

        tipAmountTextView = (TextView) findViewById(R.id.tipAmount);
        totalAmountTextView = (TextView) findViewById(R.id.totalAmount);





        tenPercent.setOnClickListener(radioButtonListener);
        fifteenPercent.setOnClickListener(radioButtonListener);
        eighteenPercent.setOnClickListener(radioButtonListener);
        twentyPercent.setOnClickListener(radioButtonListener);


        editText = (EditText) findViewById(R.id.editTextAmount);
        editText.setOnEditorActionListener(new TextView.OnEditorActionListener()
        {
            @Override
            public boolean onEditorAction(TextView v, int actionId,
                                          KeyEvent event)
            {
                if (actionId == EditorInfo.IME_ACTION_DONE)
                {
                    // actions when "Done" key is pressed

                    InputMethodManager imm = (InputMethodManager)v.getContext().getSystemService(Context.INPUT_METHOD_SERVICE);
                    imm.hideSoftInputFromWindow(v.getWindowToken(), 0);
                    return true;

                }
                return false;
            }
        });

        editText.requestFocus();
        getWindow().setSoftInputMode(WindowManager.LayoutParams.
                SOFT_INPUT_STATE_VISIBLE);


    }

    private View.OnClickListener radioButtonListener =
            new View.OnClickListener()
            {
                public void onClick(View v)
                {
                    //tipAmount = (TextView) findViewById(R.id.tipAmount);
                    //totalAmount = (TextView) findViewById(R.id.totalAmount);
                    // Perform action on clicks
                    if(editText.getText().toString().equals(""))
                    {
                        Toast.makeText(MainActivity.this, "You have not entered a bill amount, please enter one above", Toast.LENGTH_LONG).show();
                    }
                    else {
                        RadioButton rb = (RadioButton) v;
                        Toast toast = Toast.makeText(MainActivity.this,
                                rb.getText() + " was clicked", Toast.LENGTH_SHORT);
                        toast.show();
                        double billAmount = Double.parseDouble(editText.getText().toString());
                        String tenPercentString = "10%";
                        String fifteenPercentString = "15%";
                        String eighteenPercentString = "18%";
                        String twentyPercentString = "20%";

                        double tipPercent;

                        if(rb.getText().toString().equals(tenPercentString)) {
                            tipPercent = 0.10;
                        }

                        else if(rb.getText().toString().equals(fifteenPercentString)) {
                            tipPercent = 0.15;
                        }

                        else if(rb.getText().toString().equals(eighteenPercentString)) {
                            tipPercent = 0.18;
                        }

                        else {
                            tipPercent = 0.20;
                        }


                        double newTipAmount = billAmount * tipPercent;
                        //String stringTipAmount = Double.toString(newTipAmount);

                        NumberFormat defaultFormatTip = NumberFormat.getCurrencyInstance();
                        tipAmountString = defaultFormatTip.format(newTipAmount);

                        tipAmountTextView.setText(tipAmountString);

                        double totalAmountDouble = billAmount + newTipAmount;
                        //String totalAmountString = Double.toString(totalAmountDouble);

                        NumberFormat defaultFormatTotalAmount = NumberFormat.getCurrencyInstance();
                        totalAmountString = defaultFormatTotalAmount.format(totalAmountDouble);

                        totalAmountTextView.setText(totalAmountString);


                    }
                }
            };

    //RadioGroup radioGroup = (RadioGroup) findViewById(R.id.radioGroup);
    /*radioGroup.setOnCheckedChangeListener(
            new RadioGroup.OnCheckedChangeListener()
    {
        public void onCheckedChanged(RadioGroup arg0, int checkedId)
        {
            switch (checkedId)
            {
                case R.id.tenPercent:

                    break;
                case R.id.fifteenPercent:

                    break;
                case R.id.eighteenPercent:

                    break;
                case R.id.twentyPercent:

                    break;
            }
        }
    }); */

    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        //Save state for: [EditTExt]editText, radioButton selection? stringTipAmount, totalAmountString\
        //outState.putString("editText", editText);
        outState.putString("stringTipAmount", tipAmountString);
        outState.putString("totalAmountString", totalAmountString);
    }

    public void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        tipAmountString = savedInstanceState.getString("stringTipAmount");
        tipAmountTextView.setText(tipAmountString);

        totalAmountString = savedInstanceState.getString("totalAmountString");
        totalAmountTextView.setText(totalAmountString);
    }


}
