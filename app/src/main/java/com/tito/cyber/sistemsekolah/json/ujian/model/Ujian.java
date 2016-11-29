package com.tito.cyber.sistemsekolah.json.ujian.model;

import android.graphics.Bitmap;

/**
 * Created by cyber on 06/11/16.
 */

public class Ujian {

    private String id_ujian;
    private String kelas;
    private String sub_kelas;
    private String mapel;
    private String jam_mulai;
    private String jam_selesai;
    private String tgl_ujian;

    public String getKet() {
        return ket;
    }

    public void setKet(String ket) {
        this.ket = ket;
    }

    private String ket;
    private String image;
    private Bitmap bitmap;

    public String getId_ujian() {
        return id_ujian;
    }

    public void setId_ujian(String id_ujian) {
        this.id_ujian = id_ujian;
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

    public String getTgl_ujian() {
        return tgl_ujian;
    }

    public void setTgl_ujian(String tgl_ujian) {
        this.tgl_ujian = tgl_ujian;
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
}
