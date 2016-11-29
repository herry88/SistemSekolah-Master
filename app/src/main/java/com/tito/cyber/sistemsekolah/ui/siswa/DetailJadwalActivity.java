package com.tito.cyber.sistemsekolah.ui.siswa;

import android.content.Intent;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.tito.cyber.sistemsekolah.R;
import com.tito.cyber.sistemsekolah.config.Config;

/**
 * Created by cyber on 06/11/16.
 */

public class DetailJadwalActivity extends AppCompatActivity {

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
        tvDet1.setText("Mapel : "+intent.getStringExtra(Config.TAG_MAPEL));
        tvDet2.setText("Kelas : "+intent.getStringExtra(Config.TAG_KELAS));
        tvDet3.setText("Sub Kelas : "+intent.getStringExtra(Config.TAG_SUB_KELAS));
        tvDet4.setText("Hari : "+intent.getStringExtra(Config.TAG_HARI));
        tvDet5.setText("Guru : "+intent.getStringExtra(Config.TAG_NAMA_GURU));
        tvDet6.setText("Jam Mulai : "+intent.getStringExtra(Config.TAG_JAM_MULAI));
        tvDet7.setText("Jam Selesai : "+intent.getStringExtra(Config.TAG_JAM_SELESAI));

    }
}
