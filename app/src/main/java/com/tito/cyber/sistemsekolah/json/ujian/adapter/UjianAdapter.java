package com.tito.cyber.sistemsekolah.json.ujian.adapter;

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
import com.tito.cyber.sistemsekolah.json.ujian.model.Ujian;

import java.util.List;

/**
 * Created by cyber on 06/11/16.
 */

public class UjianAdapter extends BaseAdapter {
    private Context context;
    private List<Ujian> ujianList;
    private LayoutInflater inflater = null;
    private LruCache<Integer,Bitmap> imageCache;
    private RequestQueue queue;
    public UjianAdapter(Context context, List<Ujian> list) {
        this.context = context;
        this.ujianList = list;
        inflater = LayoutInflater.from(context);
        final int maxMemory = (int) (Runtime.getRuntime().maxMemory() / 1024);
        final int cacheSize = maxMemory / 8;
        imageCache = new LruCache<>(cacheSize);
        queue = Volley.newRequestQueue(context);
    }

    public class ViewHolder {

        TextView _mapel;
        TextView _kelas;
        TextView _sub_kelas;
        TextView _tgl_ujian;
        TextView _hari;
        TextView _jam_mulai;
        TextView _jam_selesai;
        TextView _ket;
        ImageView _ujian_Image;

    }

    @Override
    public int getCount() {
        return ujianList.size();
    }

    @Override
    public Object getItem(int position) {

        return ujianList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup viewGroup) {


        final Ujian ujian = ujianList.get(position);
        final UjianAdapter.ViewHolder holder;
        if(convertView == null) {

            convertView = inflater.inflate(R.layout.template_list,null);
            holder = new UjianAdapter.ViewHolder();

            holder._mapel = (TextView) convertView.findViewById(R.id.tv1);
            holder._kelas = (TextView) convertView.findViewById(R.id.tv2);
            holder._sub_kelas = (TextView) convertView.findViewById(R.id.tv3);
            holder._tgl_ujian = (TextView) convertView.findViewById(R.id.tv4);
            holder._jam_mulai = (TextView) convertView.findViewById(R.id.tv5);
            holder._jam_selesai = (TextView) convertView.findViewById(R.id.tv6);
            holder._ket=(TextView)convertView.findViewById(R.id.tv7);

            convertView.setTag(holder);
        }
        else {

            holder = (UjianAdapter.ViewHolder) convertView.getTag();
        }

        holder._mapel.setText(""+ujian.getMapel().toString());
        holder._kelas.setText("Kelas :"+ujian.getKelas().toString());
        holder._sub_kelas.setText("Sub Kelas :"+ujian.getSub_kelas().toString());
        holder._tgl_ujian.setText("Tanggal :"+ujian.getTgl_ujian().toString());
        holder._jam_mulai.setText("Jam Mulai "+ujian.getJam_mulai().toString());
        holder._jam_selesai.setText("Jam Selesai "+ujian.getJam_selesai().toString());
        holder._ket.setText("Ket :"+ujian.getKet().toString());

        Bitmap bitmap = imageCache.get(Integer.parseInt(ujian.getId_ujian()));
        holder._ujian_Image = (ImageView) convertView.findViewById(R.id.img);

        if (bitmap != null) {

            holder._ujian_Image.setImageBitmap(bitmap);

        }else {

            String imageURL = Config.IMAGE +ujian.getImage();
            ImageRequest request = new ImageRequest(imageURL,
                    new Response.Listener<Bitmap>() {
                        @Override
                        public void onResponse(Bitmap response) {

                            holder._ujian_Image.setImageBitmap(response);
                            imageCache.put(Integer.parseInt(ujian.getId_ujian()), response);

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
