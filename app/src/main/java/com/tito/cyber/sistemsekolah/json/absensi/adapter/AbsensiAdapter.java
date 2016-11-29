package com.tito.cyber.sistemsekolah.json.absensi.adapter;

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
import com.tito.cyber.sistemsekolah.json.absensi.model.Absensi;

import java.util.List;

/**
 * Created by cyber on 07/11/16.
 */

public class AbsensiAdapter extends BaseAdapter{
    private Context context;
    private List<Absensi> absensiList;
    private LayoutInflater inflater = null;
    private LruCache<Integer,Bitmap> imageCache;
    private RequestQueue queue;
    public AbsensiAdapter(Context context, List<Absensi> list) {
        this.context = context;
        this.absensiList = list;
        inflater = LayoutInflater.from(context);
        final int maxMemory = (int) (Runtime.getRuntime().maxMemory() / 1024);
        final int cacheSize = maxMemory / 8;
        imageCache = new LruCache<>(cacheSize);
        queue = Volley.newRequestQueue(context);
    }

    public class ViewHolder {


        TextView _nis;
        TextView _nama_siswa;
        TextView _mapel;
        TextView _tgl_absensi;
        ImageView _siswa_Image;

    }

    @Override
    public int getCount() {
        return absensiList.size();
    }

    @Override
    public Object getItem(int position) {

        return absensiList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup viewGroup) {


        final Absensi absensi = absensiList.get(position);
        final AbsensiAdapter.ViewHolder holder;
        if(convertView == null) {

            convertView = inflater.inflate(R.layout.template_list,null);
            holder = new AbsensiAdapter.ViewHolder();

            holder._nis = (TextView) convertView.findViewById(R.id.tv2);
            holder._nama_siswa = (TextView) convertView.findViewById(R.id.tv1);
            holder._mapel = (TextView) convertView.findViewById(R.id.tv3);
            holder._tgl_absensi=(TextView)convertView.findViewById(R.id.tv4);

            convertView.setTag(holder);
        }
        else {

            holder = (AbsensiAdapter.ViewHolder) convertView.getTag();
        }

        holder._nis.setText(""+absensi.getNis().toString());
        holder._nama_siswa.setText(""+absensi.getNama_siswa().toString());
        holder._mapel.setText(""+absensi.getMapel().toString());
        holder._tgl_absensi.setText(""+absensi.getTgl_absensi().toString());
        Bitmap bitmap = imageCache.get(Integer.parseInt(absensi.getId_absensi()));
        holder._siswa_Image = (ImageView) convertView.findViewById(R.id.img);

        if (bitmap != null) {

            holder._siswa_Image.setImageBitmap(bitmap);

        }else {

            String imageURL = Config.IMAGE +absensi.getImage();
            ImageRequest request = new ImageRequest(imageURL,
                    new Response.Listener<Bitmap>() {
                        @Override
                        public void onResponse(Bitmap response) {

                            holder._siswa_Image.setImageBitmap(response);
                            imageCache.put(Integer.parseInt(absensi.getId_absensi()), response);

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
