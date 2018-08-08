package riyadh.com.markazapp.skkni;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.android.volley.AuthFailureError;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;

import java.util.Hashtable;
import java.util.Map;

public class FormPendaftaran extends AppCompatActivity {
    private AlertDialog dialog;
    private EditText etNama,etNik, etHP,etEmail,etSkema,etTempat,etRekomendasi,etTglTerbit,etTglLhr,Organisasi;
    private Button btnBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form_pendaftaran);

        Toolbar toolbar = (Toolbar)findViewById(R.id.toolbar);
        toolbar.setTitle("Form Pendaftaran");

        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        etNama  = (EditText)findViewById(R.id.nama_lengkap);
        etNik   = (EditText)findViewById(R.id.nik);
        etHP    = (EditText)findViewById(R.id.hp);
        etEmail  = (EditText)findViewById(R.id.email);
        etSkema = (EditText)findViewById(R.id.skema);
        etTempat = (EditText)findViewById(R.id.tempat);
        etRekomendasi = (EditText)findViewById(R.id.rekomendasi);
        etTglTerbit = (EditText)findViewById(R.id.tgl_terbit);
        etTglLhr = (EditText)findViewById(R.id.tgl_lhir);
        Organisasi = (EditText)findViewById(R.id.organisasi);
        btnBtn = (Button)findViewById(R.id.btn_save);

        btnBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                prosesSimpan();
            }
        });

    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        //getMenuInflater().inflate(R.menu.menu_filter, menu);
        return true;
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
    private void prosesSimpan(){
        final ProgressDialog dialoga = new ProgressDialog(FormPendaftaran.this);
        dialoga.setMessage("Sedang Diproses...");
        dialoga.setCancelable(false);
        dialoga.show();
        final StringRequest request = new StringRequest(com.android.volley.Request.Method.POST, Config.SIMPAN_PESERTA,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        dialoga.dismiss();
                        if (response.equals("sukses")){
                            AlertDialog.Builder builder = new AlertDialog.Builder(FormPendaftaran.this);
                            builder.setMessage("Terima Kasih peserta berhasil disimpan...");
                            builder.setTitle("Konfirmasi");
                            builder.setCancelable(false);
                            builder.setPositiveButton("IYA", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    Intent n = new Intent(FormPendaftaran.this,Pendaftaran.class);
                                    startActivity(n);
                                }
                            });
                            builder.show();
                        }else {
                            AlertDialog.Builder builder = new AlertDialog.Builder(FormPendaftaran.this);
                            builder.setMessage("Terima Kasih peserta gagal disimpan...");
                            builder.setTitle("Gagal");
                            builder.setCancelable(false);
                            builder.setPositiveButton("IYA", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    Intent n = new Intent(FormPendaftaran.this,Pendaftaran.class);
                                    startActivity(n);
                                }
                            });
                            builder.show();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        dialoga.dismiss();
                        AlertDialog.Builder builder = new AlertDialog.Builder(FormPendaftaran.this);
                        builder.setMessage("Periksa jaringan internet...");
                        builder.setCancelable(true);
                        builder.show();
                    }
                })
        {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                final String nama = etNama.getText().toString();
                final String nik = etNik.getText().toString();
                final String hp = etHP.getText().toString();
                final String email = etEmail.getText().toString();
                final String skema = etSkema.getText().toString();
                final String tempat = etTempat.getText().toString();
                final String rekomendasi = etRekomendasi.getText().toString();
                final String terbit = etTglTerbit.getText().toString();
                final String tgl = etTglLhr.getText().toString();
                final String organisasi = Organisasi.getText().toString();

                Map<String,String> params = new Hashtable<>();
                params.put("nama",nama);
                params.put("nik",nik);
                params.put("hp",hp);
                params.put("email",email);
                params.put("skema",skema);
                params.put("tempat",tempat);
                params.put("rekomendasi",rekomendasi);
                params.put("terbit",terbit);
                params.put("tgl",tgl);
                params.put("organisasi",organisasi);
                return params;
            }
        };

        MySingeleton.getmInstance(FormPendaftaran.this).addToRequestque(request);
    }
}
