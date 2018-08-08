package riyadh.com.markazapp.skkni;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class Pendaftaran extends AppCompatActivity {

    PesertaAdapater pesertaAdapater;
    private RecyclerView recyclerView;
    private ArrayList<Peserta> pesertas;
    private RecyclerView.LayoutManager layoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pendaftaran);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle("Peserta");
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        pesertas = new ArrayList<>();
        recyclerView = (RecyclerView)findViewById(R.id.recycler_peserta);
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setHasFixedSize(true);
        pesertas = getPeserta();
        pesertaAdapater = new PesertaAdapater(this,getPeserta());
        recyclerView.setAdapter(pesertaAdapater);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent n = new Intent(Pendaftaran.this,FormPendaftaran.class);
                startActivity(n);
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
    private ArrayList<Peserta> getPeserta() {

        final ProgressDialog loading = ProgressDialog.show(this,"","Loading Data...",false,false);
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(com.android.volley.Request.Method.POST, Config.LIST_PESERTA,null,
                new Response.Listener<JSONArray>() {

                    @Override
                    public void onResponse(JSONArray response) {
                        loading.dismiss();
                        int count = 0;
                        pesertas.clear();
                        while (count < response.length()){
                            try{
                                JSONObject object =  response.getJSONObject(count);
                                Peserta peserta = new Peserta();
                                peserta.setNik(object.getString("nik"));
                                peserta.setNama(object.getString("nama"));

                                pesertas.add(peserta);

                                count++;
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }
                        pesertaAdapater.notifyDataSetChanged();
                        //Log.e("","hasilnya"+arrayList+tgl);
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        AlertDialog.Builder builder = new AlertDialog.Builder(Pendaftaran.this);
                        builder.setMessage("Tidak ada jaringan internet...");
                        builder.setCancelable(true);
                        builder.show();
                    }
                });
        MySingeleton.getmInstance(this).addToRequestque(jsonArrayRequest);
        return pesertas;
    }

    class PesertaAdapater extends RecyclerView.Adapter<PesertaAdapater.TcashViewHolder> {

        private Context mContext;
        private ArrayList<Peserta> arrayList;


        public PesertaAdapater(Context mContext,ArrayList<Peserta> arrayList) {
            this.mContext = mContext;
            this.arrayList = arrayList;
        }

        public class  TcashViewHolder extends RecyclerView.ViewHolder{
            public TextView tvNama,tvNik;
            public TcashViewHolder(View itemView) {
                super(itemView);
                tvNama = (TextView)itemView.findViewById(R.id.item_nm_lengkap);
                tvNik = (TextView)itemView.findViewById(R.id.item_tgl);
                itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent a = new Intent(Pendaftaran.this,DetailPeserta.class);
                        a.putExtra("nik",tvNik.getText().toString());
                        startActivity(a);
                    }
                });
            }
        }

        @Override
        public PesertaAdapater.TcashViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View itemView = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.item_peserta, parent, false);
            return new PesertaAdapater.TcashViewHolder(itemView);
        }

        @Override
        public void onBindViewHolder(PesertaAdapater.TcashViewHolder holder, int position) {

            Peserta peserta = arrayList.get(position);

            holder.tvNama.setText(peserta.getNama());
            holder.tvNik.setText(peserta.getNik());

        }

        @Override
        public int getItemCount() {
            return arrayList.size();
        }
    }

}
