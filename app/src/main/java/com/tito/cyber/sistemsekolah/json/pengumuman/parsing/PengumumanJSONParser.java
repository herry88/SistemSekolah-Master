package com.tito.cyber.sistemsekolah.json.pengumuman.parsing;

import com.tito.cyber.sistemsekolah.json.pengumuman.model.Pengumuman;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by cyber on 07/11/16.
 */

public class PengumumanJSONParser {
    static List<Pengumuman> pList;

    public static List<Pengumuman> parseData(String content) {

        JSONArray p_arry = null;
        Pengumuman p = null;
        try {

            p_arry = new JSONArray(content);
            pList = new ArrayList<>();

            for (int i = 0; i < p_arry.length(); i++) {

                JSONObject obj = p_arry.getJSONObject(i);
                p = new Pengumuman();
                p.setId_(obj.getString("id_"));
                p.setJudul(obj.getString("judul"));
                p.setDetail(obj.getString("detail"));
                p.setTgl(obj.getString("tgl"));
                p.setImage(obj.getString("img_p"));

                pList.add(p);
            }
            return pList;

        }
        catch (JSONException ex) {
            ex.printStackTrace();
            return null;
        }
    }
}
