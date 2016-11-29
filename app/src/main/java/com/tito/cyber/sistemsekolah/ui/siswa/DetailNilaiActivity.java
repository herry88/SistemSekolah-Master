package com.tito.cyber.sistemsekolah.ui.siswa;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.tito.cyber.sistemsekolah.R;
import com.tito.cyber.sistemsekolah.config.Config;

/**
 * Created by cyber on 06/11/16.
 */

public class DetailNilaiActivity extends AppCompatActivity {

    TextView tvDet1,tvDet2,tvDet3,tvDet4,tvDet5,tvDet6,tvDet7;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        tvDet1=(TextView)findViewById(R.id.tvDet1);
        tvDet2=(TextView)findViewById(R.id.tvDet2);
        tvDet3=(TextView)findViewById(R.id.tvDet3);
        tvDet4=(TextView)findViewById(R.id.tvDet4);
        tvDet5=(TextView)findViewById(R.id.tvDet5);
        tvDet6=(TextView)findViewById(R.id.tvDet6);
        tvDet7=(TextView)findViewById(R.id.tvDet7);
        Intent intent = getIntent();
        tvDet1.setText(intent.getStringExtra(Config.TAG_MAPEL));
        tvDet2.setText("Nilai Tugas : "+intent.getStringExtra(Config.TAG_N_TUGAS));
        tvDet3.setText("Nilai UH : "+intent.getStringExtra(Config.TAG_N_UH));
        tvDet4.setText("Nilai UTS : "+intent.getStringExtra(Config.TAG_N_UTS));
        tvDet5.setText("Nilai UAS : "+intent.getStringExtra(Config.TAG_N_UAS));
        tvDet6.setText("Nilai Akhir : "+intent.getStringExtra(Config.TAG_N_AKHIR));


    }
}
