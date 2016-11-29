package com.tito.cyber.sistemsekolah.json.ujian.parsing;

import com.tito.cyber.sistemsekolah.json.jadwal.model.Jadwal;
import com.tito.cyber.sistemsekolah.json.ujian.model.Ujian;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by cyber on 06/11/16.
 */

public class UjianJSONParser {
    static List<Ujian> ujianList;

    public static List<Ujian> parseData(String content) {

        JSONArray ujian_arry = null;
        Ujian ujian = null;
        try {

            ujian_arry = new JSONArray(content);
            ujianList = new ArrayList<>();

            for (int i = 0; i < ujian_arry.length(); i++) {

                JSONObject obj = ujian_arry.getJSONObject(i);
                ujian = new Ujian();
                ujian.setId_ujian(obj.getString("id_ujian"));
                ujian.setKelas(obj.getString("kelas"));
                ujian.setSub_kelas(obj.getString("sub_kelas"));
                ujian.setMapel(obj.getString("mapel"));
                ujian.setTgl_ujian(obj.getString("tgl_ujian"));
                ujian.setJam_mulai(obj.getString("jam_mulai"));
                ujian.setJam_selesai(obj.getString("jam_selesai"));
                ujian.setKet(obj.getString("ket"));

                ujian.setImage(obj.getString("img_ujian"));

                ujianList.add(ujian);
            }
            return ujianList;

        }
        catch (JSONException ex) {
            ex.printStackTrace();
            return null;
        }
    }

}
