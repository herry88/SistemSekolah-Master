package com.tito.cyber.sistemsekolah.json.jadwalajar.adapter;

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
import com.tito.cyber.sistemsekolah.json.jadwalajar.model.JadwalAjar;

import java.util.List;

/**
 * Created by cyber on 07/11/16.
 */

public class JadwalAjarAdapter extends BaseAdapter{
    private Context context;
    private List<JadwalAjar> jadwalList;
    private LayoutInflater inflater = null;
    private LruCache<Integer,Bitmap> imageCache;
    private RequestQueue queue;
    public JadwalAjarAdapter(Context context, List<JadwalAjar> list) {
        this.context = context;
        this.jadwalList = list;
        inflater = LayoutInflater.from(context);
        final int maxMemory = (int) (Runtime.getRuntime().maxMemory() / 1024);
        final int cacheSize = maxMemory / 8;
        imageCache = new LruCache<>(cacheSize);
        queue = Volley.newRequestQueue(context);
    }

    public class ViewHolder {


        TextView _kelas;
        TextView _sub_kelas;
        TextView _mapel;
        TextView _hari;
        TextView _jam_mulai;
        TextView _jam_selesai;
        ImageView _jadwal_Image;

    }

    @Override
    public int getCount() {
        return jadwalList.size();
    }

    @Override
    public Object getItem(int position) {

        return jadwalList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup viewGroup) {


        final JadwalAjar jadwal = jadwalList.get(position);
        final JadwalAjarAdapter.ViewHolder holder;
        if(convertView == null) {

            convertView = inflater.inflate(R.layout.template_list,null);
            holder = new JadwalAjarAdapter.ViewHolder();

            holder._kelas = (TextView) convertView.findViewById(R.id.tv3);
            holder._sub_kelas = (TextView) convertView.findViewById(R.id.tv4);
            holder._mapel = (TextView) convertView.findViewById(R.id.tv1);
            holder._hari=(TextView)convertView.findViewById(R.id.tv2);
            holder._jam_mulai = (TextView) convertView.findViewById(R.id.tv5);
            holder._jam_selesai = (TextView) convertView.findViewById(R.id.tv6);

            convertView.setTag(holder);
        }
        else {

            holder = (JadwalAjarAdapter.ViewHolder) convertView.getTag();
        }

        holder._kelas.setText("Kelas :"+jadwal.getKelas().toString());
        holder._sub_kelas.setText("Sub Kelas :"+jadwal.getSub_kelas().toString());
        holder._mapel.setText(""+jadwal.getMapel().toString());
        holder._hari.setText("Hari :"+jadwal.getHari().toString());
        holder._jam_mulai.setText("Jam Mulai "+jadwal.getJam_mulai().toString());
        holder._jam_selesai.setText("Jam Selesai "+jadwal.getJam_selesai().toString());
        Bitmap bitmap = imageCache.get(Integer.parseInt(jadwal.getId_jadwal()));
        holder._jadwal_Image = (ImageView) convertView.findViewById(R.id.img);

        if (bitmap != null) {

            holder._jadwal_Image.setImageBitmap(bitmap);

        }else {

            String imageURL = Config.IMAGE +jadwal.getImage();
            ImageRequest request = new ImageRequest(imageURL,
                    new Response.Listener<Bitmap>() {
                        @Override
                        public void onResponse(Bitmap response) {

                            holder._jadwal_Image.setImageBitmap(response);
                            imageCache.put(Integer.parseInt(jadwal.getId_jadwal()), response);

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
