package com.tito.cyber.sistemsekolah.json.siswa.model;

import android.graphics.Bitmap;

/**
 * Created by cyber on 07/11/16.
 */

public class Siswa {
    private String id_jadwal;
    private String nis;
    private String nama_siswa;
    private String kode_mapel;
    private String mapel;
    private String kelas;
    private String sub_kelas;

    public String getId_jadwal() {
        return id_jadwal;
    }

    public void setId_jadwal(String id_jadwal) {
        this.id_jadwal = id_jadwal;
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

    public String getKode_mapel() {
        return kode_mapel;
    }

    public void setKode_mapel(String kode_mapel) {
        this.kode_mapel = kode_mapel;
    }

    public String getMapel() {
        return mapel;
    }

    public void setMapel(String mapel) {
        this.mapel = mapel;
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

    private String image;
    private Bitmap bitmap;
}
