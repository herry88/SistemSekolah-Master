package com.tito.cyber.sistemsekolah.json.siswa.adapter;

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
import com.tito.cyber.sistemsekolah.json.nilai.adapter.NilaiAdapter;
import com.tito.cyber.sistemsekolah.json.nilai.model.Nilai;
import com.tito.cyber.sistemsekolah.json.siswa.model.Siswa;

import java.util.List;

/**
 * Created by cyber on 07/11/16.
 */

public class SiswaAdapter extends BaseAdapter{
    private Context context;
    private List<Siswa> siswaList;
    private LayoutInflater inflater = null;
    private LruCache<Integer,Bitmap> imageCache;
    private RequestQueue queue;
    public SiswaAdapter(Context context, List<Siswa> list) {
        this.context = context;
        this.siswaList = list;
        inflater = LayoutInflater.from(context);
        final int maxMemory = (int) (Runtime.getRuntime().maxMemory() / 1024);
        final int cacheSize = maxMemory / 8;
        imageCache = new LruCache<>(cacheSize);
        queue = Volley.newRequestQueue(context);
    }

    public class ViewHolder {

        TextView _nis;
        TextView _nama_siswa;
        TextView _kode_mapel;
        TextView _mapel;
        TextView _kelas;
        TextView _sub_kelas;
        ImageView _nilai_Image;

    }

    @Override
    public int getCount() {
        return siswaList.size();
    }

    @Override
    public Object getItem(int position) {

        return siswaList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup viewGroup) {


        final Siswa siswa = siswaList.get(position);
        final SiswaAdapter.ViewHolder holder;
        if(convertView == null) {

            convertView = inflater.inflate(R.layout.template_list,null);
            holder = new SiswaAdapter.ViewHolder();

            holder._nis = (TextView) convertView.findViewById(R.id.tv1);
            holder._nama_siswa = (TextView) convertView.findViewById(R.id.tv2);
            holder._kode_mapel = (TextView) convertView.findViewById(R.id.tv3);
            holder._mapel = (TextView) convertView.findViewById(R.id.tv4);
            holder._kelas=(TextView)convertView.findViewById(R.id.tv5);
            holder._sub_kelas = (TextView) convertView.findViewById(R.id.tv6);


            convertView.setTag(holder);
        }
        else {

            holder = (SiswaAdapter.ViewHolder) convertView.getTag();
        }

        holder._nis.setText("Nis : "+siswa.getNis().toString());
        holder._nama_siswa.setText("Nama : "+siswa.getNama_siswa().toString());
        holder._kode_mapel.setText("Kode : "+siswa.getKode_mapel().toString());
        holder._mapel.setText("Mapel : "+siswa.getMapel().toString());
        holder._kelas.setText("Kelas : "+siswa.getKelas().toString());
        holder._sub_kelas.setText("Sub Kelas : "+siswa.getSub_kelas().toString());
        Bitmap bitmap = imageCache.get(Integer.parseInt(siswa.getId_jadwal()));
        holder._nilai_Image = (ImageView) convertView.findViewById(R.id.img);

        if (bitmap != null) {

            holder._nilai_Image.setImageBitmap(bitmap);

        }else {

            String imageURL = Config.IMAGE +siswa.getImage();
            ImageRequest request = new ImageRequest(imageURL,
                    new Response.Listener<Bitmap>() {
                        @Override
                        public void onResponse(Bitmap response) {

                            holder._nilai_Image.setImageBitmap(response);
                            imageCache.put(Integer.parseInt(siswa.getId_jadwal()), response);

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
