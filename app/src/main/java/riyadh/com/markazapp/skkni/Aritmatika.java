package riyadh.com.markazapp.skkni;

import android.content.Intent;
import android.os.Build;
import android.speech.tts.TextToSpeech;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.Locale;

public class Aritmatika extends AppCompatActivity {

    Button button0 , button1 , button2 , button3 , button4 , button5 , button6 ,
            button7 , button8 , button9 , buttonAdd , buttonSub , buttonDivision ,
            buttonMul , button10 , buttonC , buttonEqual ;


    EditText edt1 ;

    float mValueOne , mValueTwo ;

    boolean mAddition , mSubtract ,mMultiplication ,mDivision ;
    TextToSpeech textToSpeech;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_aritmatika);

        Toolbar toolbar = (Toolbar)findViewById(R.id.toolbar);
        toolbar.setTitle("Kalkulator");

        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        button0 = (Button) findViewById(R.id.button0);
        button1 = (Button) findViewById(R.id.button1);
        button2 = (Button) findViewById(R.id.button2);
        button3 = (Button) findViewById(R.id.button3);
        button4 = (Button) findViewById(R.id.button4);
        button5 = (Button) findViewById(R.id.button5);
        button6 = (Button) findViewById(R.id.button6);
        button7 = (Button) findViewById(R.id.button7);
        button8 = (Button) findViewById(R.id.button8);
        button9 = (Button) findViewById(R.id.button9);
        button10 = (Button) findViewById(R.id.button10);
        buttonAdd = (Button) findViewById(R.id.buttonadd);
        buttonSub = (Button) findViewById(R.id.buttonsub);
        buttonMul = (Button) findViewById(R.id.buttonmul);
        buttonDivision = (Button) findViewById(R.id.buttondiv);
        buttonC = (Button) findViewById(R.id.buttonC);
        buttonEqual = (Button) findViewById(R.id.buttoneql);
        edt1 = (EditText) findViewById(R.id.edt1);

        textToSpeech = new TextToSpeech(this, new TextToSpeech.OnInitListener() {
            @Override
            public void onInit(int status) {

                //method untuk mendeteksi suara dari text

                if(status != TextToSpeech.ERROR) {
                    textToSpeech.setLanguage(Locale.US);

                    //menggunakan bahasa US (amerika)

                }
            }
        });


        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (edt1.getText().equals(" ")){
                    edt1.setText(edt1.getText()+"1");
                }else {
                    edt1.setText(edt1.getText()+"1");
                }
                //edt1.setText(edt1.getText()+"1");
            }
        });

        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                edt1.setText(edt1.getText()+"2");
            }
        });

        button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                edt1.setText(edt1.getText()+"3");
            }
        });

        button4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                edt1.setText(edt1.getText()+"4");
            }
        });

        button5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                edt1.setText(edt1.getText()+"5");
            }
        });

        button6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                edt1.setText(edt1.getText()+"6");
            }
        });

        button7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                edt1.setText(edt1.getText()+"7");
            }
        });

        button8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                edt1.setText(edt1.getText()+"8");
            }
        });

        button9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                edt1.setText(edt1.getText()+"9");
            }
        });

        button0.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                edt1.setText(edt1.getText()+"0");
            }
        });

        buttonAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (edt1 == null){
                    edt1.setText("");
                }else {
                    mValueOne = Float.parseFloat(edt1.getText() + "");
                    mAddition = true;
                    edt1.setText(null);
                }
            }
        });

        buttonSub.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mValueOne = Float.parseFloat(edt1.getText() + "");
                mSubtract = true ;
                edt1.setText(null);
            }
        });

        buttonMul.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mValueOne = Float.parseFloat(edt1.getText() + "");
                mMultiplication = true ;
                edt1.setText(null);
            }
        });

        buttonDivision.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mValueOne = Float.parseFloat(edt1.getText()+"");
                mDivision = true ;
                edt1.setText(null);
            }
        });

        buttonEqual.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mValueTwo = Float.parseFloat(edt1.getText() + "");

                if (mAddition == true){
                    edt1.setText(mValueOne + mValueTwo +"");
                    mAddition=false;
                    int inputan = Integer.parseInt(edt1.getText().toString());
                    String hasil = ""+info_terbilang(inputan);

                    String text = edt1.getText().toString();
                    Snackbar.make(findViewById(R.id.buttoneql),""+hasil, Snackbar.LENGTH_LONG)
                            .setAction("Action", null).show();
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                        textToSpeech.speak(hasil, TextToSpeech.QUEUE_FLUSH,null,null);

                    } else {
                        textToSpeech.speak(hasil, TextToSpeech.QUEUE_FLUSH, null);
                    }

                    edt1.setText(mValueOne +" + "+mValueTwo);

                }


                if (mSubtract == true){
                    edt1.setText(mValueOne - mValueTwo+"");
                    mSubtract=false;
                    int inputan = Integer.parseInt(edt1.getText().toString());
                    String hasil = ""+info_terbilang(inputan);

                    String text = edt1.getText().toString();
                    Snackbar.make(findViewById(R.id.buttoneql),""+hasil, Snackbar.LENGTH_LONG)
                            .setAction("Action", null).show();
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                        textToSpeech.speak(hasil, TextToSpeech.QUEUE_FLUSH,null,null);


                    } else {
                        textToSpeech.speak(hasil, TextToSpeech.QUEUE_FLUSH, null);
                    }

                    edt1.setText(mValueOne +" - "+mValueTwo);
                }

                if (mMultiplication == true){
                    edt1.setText(mValueOne * mValueTwo+"");
                    mMultiplication=false;
                    int inputan = Integer.parseInt(edt1.getText().toString());
                    String hasil = ""+info_terbilang(inputan);

                    String text = edt1.getText().toString();
                    Snackbar.make(findViewById(R.id.buttoneql),""+hasil, Snackbar.LENGTH_LONG)
                            .setAction("Action", null).show();
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                        textToSpeech.speak(hasil, TextToSpeech.QUEUE_FLUSH,null,null);


                    } else {
                        textToSpeech.speak(hasil, TextToSpeech.QUEUE_FLUSH, null);
                    }
                    edt1.setText(mValueOne +" x "+mValueTwo);
                }

                if (mDivision == true){
                    edt1.setText(mValueOne / mValueTwo+"");
                    mDivision=false;
                    int inputan = Integer.parseInt(edt1.getText().toString());
                    String hasil = ""+info_terbilang(inputan);

                    String text = edt1.getText().toString();
                    Snackbar.make(findViewById(R.id.buttoneql),""+hasil, Snackbar.LENGTH_LONG)
                            .setAction("Action", null).show();
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                        textToSpeech.speak(hasil, TextToSpeech.QUEUE_FLUSH,null,null);


                    } else {
                        textToSpeech.speak(hasil, TextToSpeech.QUEUE_FLUSH, null);
                    }
                    edt1.setText(mValueOne +" / "+mValueTwo);
                }
            }
        });

        buttonC.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                edt1.setText("");
            }
        });

        button10.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                edt1.setText(edt1.getText()+".");
            }
        });
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        switch (item.getItemId()){
            case android.R.id.home:
                goToBack();
                break;

        }

        return super.onOptionsItemSelected(item);
    }

    private void goToBack(){
        Intent in = new Intent(getApplicationContext(), MainActivity.class);
        in.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(in);
    }

    public String info_terbilang(int value)
    {
        String [] bilangan ={"","satu","dua","tiga","empat","lima","enam","tujuh","delapan","sembilan","sepuluh","sebelas"};
        String temp=" ";
        if (value<12){
            temp = " " + bilangan[value];
        }
        else if(value<20){
            temp = info_terbilang(value-10) + " belas";
        }
        else if(value<100){
            temp = info_terbilang(value/10) + " puluh" + info_terbilang(value%10);
        }
        else if(value<200){
            temp = " seratus" + info_terbilang(value-100);
        }
        else if(value<1000){
            temp = info_terbilang(value/100) + " ratus" + info_terbilang(value%100);
        }
        else if(value<2000){
            temp = "seribu"+ info_terbilang(value-1000);
        }
        else if(value<1000000){
            temp = info_terbilang(value/1000) + " ribu" + info_terbilang (value%1000);

        }else if(value >= 1000000 && value <=999999999){
            temp = info_terbilang(value/1000000) + " juta" + info_terbilang (value%1000);
        }
        return temp;
    }

}
