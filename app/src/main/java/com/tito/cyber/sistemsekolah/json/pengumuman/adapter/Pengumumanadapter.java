package com.tito.cyber.sistemsekolah.json.pengumuman.adapter;

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
import com.tito.cyber.sistemsekolah.json.pengumuman.model.Pengumuman;

import java.util.List;

/**
 * Created by cyber on 07/11/16.
 */

public class Pengumumanadapter extends BaseAdapter {
    private Context context;
    private List<Pengumuman> pList;
    private LayoutInflater inflater = null;
    private LruCache<Integer,Bitmap> imageCache;
    private RequestQueue queue;
    public Pengumumanadapter(Context context, List<Pengumuman> list) {
        this.context = context;
        this.pList = list;
        inflater = LayoutInflater.from(context);
        final int maxMemory = (int) (Runtime.getRuntime().maxMemory() / 1024);
        final int cacheSize = maxMemory / 8;
        imageCache = new LruCache<>(cacheSize);
        queue = Volley.newRequestQueue(context);
    }

    public class ViewHolder {

        TextView _judul;
        TextView _detail;
        TextView _tgl;
        ImageView _P_Image;

    }

    @Override
    public int getCount() {
        return pList.size();
    }

    @Override
    public Object getItem(int position) {

        return pList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup viewGroup) {


        final Pengumuman p = pList.get(position);
        final Pengumumanadapter.ViewHolder holder;
        if(convertView == null) {

            convertView = inflater.inflate(R.layout.template_list,null);
            holder = new Pengumumanadapter.ViewHolder();

            holder._judul = (TextView) convertView.findViewById(R.id.tv1);
            holder._detail = (TextView) convertView.findViewById(R.id.tv2);
            holder._tgl = (TextView) convertView.findViewById(R.id.tv3);


            convertView.setTag(holder);
        }
        else {

            holder = (Pengumumanadapter.ViewHolder) convertView.getTag();
        }

        holder._judul.setText(""+p.getJudul().toString());
        holder._detail.setText(""+p.getDetail().toString());
        holder._tgl.setText(""+p.getTgl().toString());
        Bitmap bitmap = imageCache.get(Integer.parseInt(p.getId_()));
        holder._P_Image = (ImageView) convertView.findViewById(R.id.img);

        if (bitmap != null) {

            holder._P_Image.setImageBitmap(bitmap);

        }else {

            String imageURL = Config.IMAGE +p.getImage();
            ImageRequest request = new ImageRequest(imageURL,
                    new Response.Listener<Bitmap>() {
                        @Override
                        public void onResponse(Bitmap response) {

                            holder._P_Image.setImageBitmap(response);
                            imageCache.put(Integer.parseInt(p.getId_()), response);

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
