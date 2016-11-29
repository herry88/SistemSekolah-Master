package com.tito.cyber.sistemsekolah.json.nilai.parsing;

import com.tito.cyber.sistemsekolah.json.jadwal.model.Jadwal;
import com.tito.cyber.sistemsekolah.json.nilai.model.Nilai;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by cyber on 06/11/16.
 */

public class NilaiJSONParser {
    static List<Nilai> nilaiList;

    public static List<Nilai> parseData(String content) {

        JSONArray nilai_arry = null;
        Nilai nilai = null;
        try {

            nilai_arry = new JSONArray(content);
            nilaiList = new ArrayList<>();

            for (int i = 0; i < nilai_arry.length(); i++) {

                JSONObject obj = nilai_arry.getJSONObject(i);
                nilai = new Nilai();
                nilai.setId_nilai(obj.getString("id_nilai"));
                nilai.setMapel(obj.getString("mapel"));
                nilai.setNilai_tugas(obj.getString("nilai_tugas"));
                nilai.setNilai_uh(obj.getString("nilai_uh"));
                nilai.setNilai_uts(obj.getString("nilai_uts"));
                nilai.setNilai_uas(obj.getString("nilai_uas"));
                nilai.setNilai_akhir(obj.getString("nilai_akhir"));
                nilai.setImage(obj.getString("img_nilai"));

                nilaiList.add(nilai);
            }
            return nilaiList;

        }
        catch (JSONException ex) {
            ex.printStackTrace();
            return null;
        }
    }
}
