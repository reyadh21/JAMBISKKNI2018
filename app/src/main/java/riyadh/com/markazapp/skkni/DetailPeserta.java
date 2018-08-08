package riyadh.com.markazapp.skkni;

import android.app.ProgressDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;

import org.json.JSONException;
import org.json.JSONObject;

public class DetailPeserta extends AppCompatActivity {
    private TextView tvNama,tvNik,tvHp,tvEmail,tvSkema,tvTempat,tvRekomendasi,tvTerbit,tvTgl,tvOrganisasi;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_peserta);

        tvNama = (TextView)findViewById(R.id.detail_nama);
        tvNik = (TextView)findViewById(R.id.detail_nik);
        tvHp = (TextView)findViewById(R.id.detail_hp);
        tvEmail = (TextView)findViewById(R.id.detail_email);
        tvSkema = (TextView)findViewById(R.id.detail_skema);
        tvTempat = (TextView)findViewById(R.id.detail_tempat);
        tvRekomendasi = (TextView)findViewById(R.id.detail_rekomendasi);
        tvTgl = (TextView)findViewById(R.id.detail_tgl);
        tvOrganisasi = (TextView)findViewById(R.id.detail_organisasi);

        String ni = getIntent().getStringExtra("nik");

        ambilDetail(ni);
    }

    private void ambilDetail(String id){
        final ProgressDialog loading = ProgressDialog.show(this,"","Loading Data...",false,false);
        JsonObjectRequest objectRequest = new JsonObjectRequest(Request.Method.GET, Config.DETAIL_PESERTA+"?id="+id, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        loading.dismiss();
                        try {

                            String nama = response.getString("nama");
                            String nik = response.getString("nik");
                            String hp = response.getString("hp");
                            String email = response.getString("email");
                            String skema = response.getString("skema");
                            String tempat = response.getString("tempat");
                            String rekomendasi = response.getString("rekomendasi");
                            String tgl_lhr = response.getString("tgl_lhr");
                            String organisasi = response.getString("organisasi");

                            tvNama.setText("Nama Lengkap : "+nama);
                            tvNik.setText("Nik"+nik);
                            tvHp.setText("No. Handphone : "+hp);
                            tvEmail.setText("Email : "+email);
                            tvSkema.setText("Skema : "+skema);
                            tvTempat.setText("Tempat Ujian : "+tempat);
                            tvRekomendasi.setText("Rekomendasi : "+rekomendasi);
                            tvTgl.setText("Tanggal Lahir : ");
                            tvOrganisasi.setText("Organisasi : "+organisasi);



                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        loading.dismiss();
                        Toast.makeText(DetailPeserta.this, "Failed, check internet connection...", Toast.LENGTH_SHORT).show();
                    }
                });

        MySingeleton.getmInstance(DetailPeserta.this).addToRequestque(objectRequest);

    }
}
