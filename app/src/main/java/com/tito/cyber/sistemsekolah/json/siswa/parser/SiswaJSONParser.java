package com.tito.cyber.sistemsekolah.json.siswa.parser;

import com.tito.cyber.sistemsekolah.json.nilai.model.Nilai;
import com.tito.cyber.sistemsekolah.json.siswa.model.Siswa;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by cyber on 07/11/16.
 */

public class SiswaJSONParser {
    static List<Siswa> siswaList;

    public static List<Siswa> parseData(String content) {

        JSONArray siswa_arry = null;
        Siswa siswa = null;
        try {

            siswa_arry = new JSONArray(content);
            siswaList = new ArrayList<>();

            for (int i = 0; i < siswa_arry.length(); i++) {

                JSONObject obj = siswa_arry.getJSONObject(i);
                siswa = new Siswa();
                siswa.setId_jadwal(obj.getString("id_jadwal"));
                siswa.setNis(obj.getString("nis"));
                siswa.setNama_siswa(obj.getString("nama_siswa"));
                siswa.setKode_mapel(obj.getString("kode_mapel"));
                siswa.setMapel(obj.getString("mapel"));
                siswa.setKelas(obj.getString("kelas"));
                siswa.setSub_kelas(obj.getString("sub_kelas"));
                siswa.setImage(obj.getString("img_siswa"));

                siswaList.add(siswa);
            }
            return siswaList;

        } catch (JSONException ex) {
            ex.printStackTrace();
            return null;

        }
    }
}