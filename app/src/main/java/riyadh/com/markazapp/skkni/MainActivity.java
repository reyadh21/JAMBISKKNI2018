package riyadh.com.markazapp.skkni;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    private Button btnMenuSatu,btnMenuDua;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle("SKNNI - Jambi ");
        setSupportActionBar(toolbar);

        btnMenuSatu = (Button) findViewById(R.id.menu_1);
        btnMenuDua = (Button) findViewById(R.id.menu_2);

        btnMenuSatu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent n = new Intent(MainActivity.this,Aritmatika.class);
                startActivity(n);
            }
        });

        btnMenuDua.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent n = new Intent(MainActivity.this,Pendaftaran.class);
                startActivity(n);
            }
        });
    }
}
