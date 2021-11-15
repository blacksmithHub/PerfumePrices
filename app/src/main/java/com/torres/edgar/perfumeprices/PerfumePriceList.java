package com.torres.edgar.perfumeprices;

import android.support.annotation.IdRes;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ToggleButton;

public class PerfumePriceList extends AppCompatActivity {

    public Double perfumePrice  [][]={{450.00,2000.00,3900.00},{500.00,2100.00,4000.00},
            {600.00,2500.00,4800.00},{650.00,2600.00,4900.00},{625.00,2700.00,5000.00}};
    public String perfume []={"Lacoste Booster","Bvlgari Man","Bvlgari Extreme","Polo Black","Polo Blue"};
    public String size[]={"10 ML","50 ML","100 ML"};
    public String discount[]={"10 % discount ","No Discount"};

    SeekBar skBarSize,skBarPerfume;
    TextView txtPrice, txtMembershipType, txtTprice,txtOption;

    RadioGroup rdoMembership;
    RadioButton rdoMember,rdoNonmember;
    EditText txtQty;

    ToggleButton btnOption;

    public int perfumeCntr=0,sizeCntr=0,discountcntr=1;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_perfume_price_list);
        skBarPerfume=(SeekBar)findViewById(R.id.seekBarPerfume);
        skBarSize=(SeekBar)findViewById(R.id.seekBarSize);
        txtPrice =(TextView)findViewById(R.id.textPerfume);
        txtTprice =(TextView) findViewById(R.id.textTPrice);
        txtQty=(EditText) findViewById(R.id.textQty);
        rdoMembership =(RadioGroup) findViewById(R.id.RadioMembership);
        txtMembershipType =(TextView)findViewById(R.id.textMembershipType);
        rdoMember=(RadioButton) findViewById(R.id.radioMember);
        rdoNonmember=(RadioButton) findViewById(R.id.radioNonMember);
        txtOption=(TextView) findViewById(R.id.textOption);
        btnOption=(ToggleButton) findViewById(R.id.buttonOption);
        rdoNonmember.performClick();


        displayTprice(0,perfumeCntr,sizeCntr);
        display(perfumeCntr,sizeCntr);
        txtMembershipType.setText("You Selected Non Member " + discount[discountcntr]);



        skBarPerfume.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                perfumeCntr=i;
                display(perfumeCntr,sizeCntr);
                try {
                    displayTprice( Integer.parseInt(txtQty.getText().toString()),perfumeCntr,sizeCntr);
                } catch (NumberFormatException e) {
                    txtTprice.setText("P 0.00");
                }
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        skBarSize.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                sizeCntr=i;
                display(perfumeCntr,sizeCntr);
                try {
                    displayTprice( Integer.parseInt(txtQty.getText().toString()),perfumeCntr,sizeCntr);
                } catch (NumberFormatException e) {
                    txtTprice.setText("P 0.00");
                }
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });


//        rdoMembership.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
//            @Override
//            public void onCheckedChanged(RadioGroup radioGroup, @IdRes int i) {
//
//
//                if (i == R.id.radioMember) {
//                    Toast.makeText(getApplicationContext(),"Member Selected",Toast.LENGTH_SHORT).show();
//                } else if (i == R.id.radioNonMember) {
//                    Toast.makeText(getApplicationContext(),"Non-Member Selected",Toast.LENGTH_SHORT).show();
//                }
//            }
//        });


        rdoMembership.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, @IdRes int i) {
                switch(i){

                    case R.id.radioMember:
                        Toast.makeText(getApplicationContext(),"Member Selected"+i,Toast.LENGTH_SHORT).show();
                        discountcntr=0;
                        txtMembershipType.setText("You Selected Member " + discount[discountcntr]);
                        break;
                    case R.id.radioNonMember:
                        Toast.makeText(getApplicationContext(),"Non-Member Selected",Toast.LENGTH_SHORT).show();
                        discountcntr=1;
                        txtMembershipType.setText("You Selected Non Member " + discount[discountcntr]);
                        break;


                }


            }
        });

        txtQty.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                try {
                    displayTprice( Integer.parseInt(txtQty.getText().toString()),perfumeCntr,sizeCntr);
                } catch (NumberFormatException e) {
                    txtTprice.setText("P 0.00");
                }
            }
        });

      btnOption.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
          @Override
          public void onCheckedChanged(CompoundButton compoundButton, boolean b) {

              if (btnOption.isChecked()) {
                  txtOption.setText("Current Method: Pick UP");
              }


              else {

                  txtOption.setText("Current Method: Delivery");

              }



          }
      });

    }

    private void displayTprice(int i, int perfumeCntr, int sizeCntr) {
        try {
            txtTprice.setText("P "+String.format("%,.2f",perfumePrice[perfumeCntr][sizeCntr]*i));
        } catch (Exception e) {
            txtTprice.setText("P 0.00");
        }

    }


    private void display(int perfumeCntr, int sizeCntr) {

        txtPrice.setText(""+perfume[perfumeCntr]+"("+size[sizeCntr]+") " +
                "Priced at P "+ String.format("%,.2f",perfumePrice[perfumeCntr][sizeCntr]));

    }
}
