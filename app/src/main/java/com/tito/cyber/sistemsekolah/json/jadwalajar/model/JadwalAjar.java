package com.tito.cyber.sistemsekolah.json.jadwalajar.model;

import android.graphics.Bitmap;

/**
 * Created by cyber on 07/11/16.
 */

public class JadwalAjar {
    private String id_jadwal;
    private String kelas;
    private String sub_kelas;
    private String mapel;
    private String hari;

    public String getHari() {
        return hari;
    }

    public void setHari(String hari) {
        this.hari = hari;
    }

    private String jam_mulai;
    private String jam_selesai;
    private String image;

    public String getId_jadwal() {
        return id_jadwal;
    }

    public void setId_jadwal(String id_jadwal) {
        this.id_jadwal = id_jadwal;
    }

    public String getKelas() {
        return kelas;
    }

    public void setKelas(String kelas) {
        this.kelas = kelas;
    }

    public String getSub_kelas() {
        return sub_kelas;
    }

    public void setSub_kelas(String sub_kelas) {
        this.sub_kelas = sub_kelas;
    }

    public String getMapel() {
        return mapel;
    }

    public void setMapel(String mapel) {
        this.mapel = mapel;
    }

    public String getJam_mulai() {
        return jam_mulai;
    }

    public void setJam_mulai(String jam_mulai) {
        this.jam_mulai = jam_mulai;
    }

    public String getJam_selesai() {
        return jam_selesai;
    }

    public void setJam_selesai(String jam_selesai) {
        this.jam_selesai = jam_selesai;
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

    private Bitmap bitmap;
}
