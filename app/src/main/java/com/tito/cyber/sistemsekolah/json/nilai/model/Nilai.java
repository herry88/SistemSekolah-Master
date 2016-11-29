package com.tito.cyber.sistemsekolah.json.nilai.model;

import android.graphics.Bitmap;

/**
 * Created by cyber on 06/11/16.
 */

public class Nilai {
    private String id_nilai;
    private String mapel;

    public String getId_nilai() {
        return id_nilai;
    }

    public void setId_nilai(String id_nilai) {
        this.id_nilai = id_nilai;
    }

    public String getMapel() {
        return mapel;
    }

    public void setMapel(String mapel) {
        this.mapel = mapel;
    }

    public String getNilai_tugas() {
        return nilai_tugas;
    }

    public void setNilai_tugas(String nilai_tugas) {
        this.nilai_tugas = nilai_tugas;
    }

    public String getNilai_uh() {
        return nilai_uh;
    }

    public void setNilai_uh(String nilai_uh) {
        this.nilai_uh = nilai_uh;
    }

    public String getNilai_uts() {
        return nilai_uts;
    }

    public void setNilai_uts(String nilai_uts) {
        this.nilai_uts = nilai_uts;
    }

    public String getNilai_uas() {
        return nilai_uas;
    }

    public void setNilai_uas(String nilai_uas) {
        this.nilai_uas = nilai_uas;
    }

    public String getNilai_akhir() {
        return nilai_akhir;
    }

    public void setNilai_akhir(String nilai_akhir) {
        this.nilai_akhir = nilai_akhir;
    }

    private String nilai_tugas;
    private String nilai_uh;
    private String nilai_uts;
    private String nilai_uas;
    private String nilai_akhir;
    private String image;
    private Bitmap bitmap;

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public Bitmap getBitmap() {
        return bitmap;
    }

    public void setBitmap(Bitmap bitmap) {
        this.bitmap = bitmap;
    }
}
