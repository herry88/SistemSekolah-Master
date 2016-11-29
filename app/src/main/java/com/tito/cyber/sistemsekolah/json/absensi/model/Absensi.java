package com.tito.cyber.sistemsekolah.json.absensi.model;

import android.graphics.Bitmap;

/**
 * Created by cyber on 07/11/16.
 */

public class Absensi {
    private String id_absensi;
    private String nis;

    public String getId_absensi() {
        return id_absensi;
    }

    public void setId_absensi(String id_absensi) {
        this.id_absensi = id_absensi;
    }

    public String getNis() {
        return nis;
    }

    public void setNis(String nis) {
        this.nis = nis;
    }

    public String getNama_siswa() {
        return nama_siswa;
    }

    public void setNama_siswa(String nama_siswa) {
        this.nama_siswa = nama_siswa;
    }

    public String getMapel() {
        return mapel;
    }

    public void setMapel(String mapel) {
        this.mapel = mapel;
    }

    public String getTgl_absensi() {
        return tgl_absensi;
    }

    public void setTgl_absensi(String tgl_absensi) {
        this.tgl_absensi = tgl_absensi;
    }

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

    private String nama_siswa;
    private String mapel;
    private String tgl_absensi;
    private String image;
    private Bitmap bitmap;

}
