package com.tito.cyber.sistemsekolah.json.nilai.adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.util.Log;
import android.util.LruCache;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.ImageRequest;
import com.android.volley.toolbox.Volley;
import com.tito.cyber.sistemsekolah.R;
import com.tito.cyber.sistemsekolah.config.Config;
import com.tito.cyber.sistemsekolah.json.jadwal.adapter.JadwalAdapter;
import com.tito.cyber.sistemsekolah.json.jadwal.model.Jadwal;
import com.tito.cyber.sistemsekolah.json.nilai.model.Nilai;

import java.util.List;

/**
 * Created by cyber on 06/11/16.
 */

public class NilaiAdapter extends BaseAdapter{
    private Context context;
    private List<Nilai> nilaiList;
    private LayoutInflater inflater = null;
    private LruCache<Integer,Bitmap> imageCache;
    private RequestQueue queue;
    public NilaiAdapter(Context context, List<Nilai> list) {
        this.context = context;
        this.nilaiList = list;
        inflater = LayoutInflater.from(context);
        final int maxMemory = (int) (Runtime.getRuntime().maxMemory() / 1024);
        final int cacheSize = maxMemory / 8;
        imageCache = new LruCache<>(cacheSize);
        queue = Volley.newRequestQueue(context);
    }

    public class ViewHolder {

        TextView _mapel;
        TextView _nilai_tugas;
        TextView _nilai_uh;
        TextView _nilai_uts;
        TextView _nilai_uas;
        TextView _nilai_akhir;
        ImageView _nilai_Image;

    }

    @Override
    public int getCount() {
        return nilaiList.size();
    }

    @Override
    public Object getItem(int position) {

        return nilaiList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup viewGroup) {


        final Nilai nilai = nilaiList.get(position);
        final NilaiAdapter.ViewHolder holder;
        if(convertView == null) {

            convertView = inflater.inflate(R.layout.template_list,null);
            holder = new NilaiAdapter.ViewHolder();

            holder._mapel = (TextView) convertView.findViewById(R.id.tv1);
            holder._nilai_tugas = (TextView) convertView.findViewById(R.id.tv2);
            holder._nilai_uh = (TextView) convertView.findViewById(R.id.tv3);
            holder._nilai_uts = (TextView) convertView.findViewById(R.id.tv4);
            holder._nilai_uas=(TextView)convertView.findViewById(R.id.tv5);
            holder._nilai_akhir = (TextView) convertView.findViewById(R.id.tv6);


            convertView.setTag(holder);
        }
        else {

            holder = (NilaiAdapter.ViewHolder) convertView.getTag();
        }

        holder._mapel.setText(""+nilai.getMapel().toString());
        holder._nilai_tugas.setText("Nilai Tugas : "+nilai.getNilai_tugas().toString());
        holder._nilai_uh.setText("Nilai UH : "+nilai.getNilai_uh().toString());
        holder._nilai_uts.setText("Nilai UTS : "+nilai.getNilai_uts().toString());
        holder._nilai_uas.setText("Nilai UAS : "+nilai.getNilai_uas().toString());
        holder._nilai_akhir.setText("Nilai Akhir : "+nilai.getNilai_akhir().toString());
        Bitmap bitmap = imageCache.get(Integer.parseInt(nilai.getId_nilai()));
        holder._nilai_Image = (ImageView) convertView.findViewById(R.id.img);

        if (bitmap != null) {

            holder._nilai_Image.setImageBitmap(bitmap);

        }else {

            String imageURL = Config.IMAGE +nilai.getImage();
            ImageRequest request = new ImageRequest(imageURL,
                    new Response.Listener<Bitmap>() {
                        @Override
                        public void onResponse(Bitmap response) {

                            holder._nilai_Image.setImageBitmap(response);
                            imageCache.put(Integer.parseInt(nilai.getId_nilai()), response);

                        }
                    },
                    90, 90,
                    Bitmap.Config.ARGB_8888,
                    new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {

                            Log.d("error",error.getMessage().toString());
                        }
                    });
            queue.add(request);
        }
        return convertView;
    }


}
