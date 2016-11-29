package com.tito.cyber.sistemsekolah.config;

/**
 * Created by cyber on 01/11/16.
 */

public class Config {
//project0.pe.hu
    public static final String SERVER_API = "http://project0.pe.hu/api-sms/";
    public static final String IMAGE = "http://project0.pe.hu/api-sms/img/";
    public static final String LOGIN_URL_WALIMURID = Config.SERVER_API +"login_ortu.php";
    public static final String LOGIN_URL_GURU = Config.SERVER_API +"login_guru.php";
    public static final String LOGIN_URL_SISWA = Config.SERVER_API +"login_siswa.php";
    public static final String AMBIL_DATA_GURU = Config.SERVER_API +"ambilDataguru.php?nip=";
    public static final String AMBIL_DATA_WALIMURID = Config.SERVER_API +"ambilDataortu.php?nio=";
    public static final String AMBIL_DATA_SISWA = Config.SERVER_API +"ambilDatasiswa.php?nis=";
    public static final String PENGUMUMAN = Config.SERVER_API +"list_pengumuman.php";
    public static final String INSERT_NILAI = Config.SERVER_API +"insert_nilai_siswa.php";
    public static final String JADWAL_SISWA = Config.SERVER_API +"list_jadwal_siswa.php?nis=";
    public static final String ABSENSI_LIST = Config.SERVER_API +"list_absensi_siswa.php?nis=";
    public static final String JADWAL_GURU = Config.SERVER_API +"list_jadwal_guru.php?nip=";
    public static final String PILIH_SISWA = Config.SERVER_API +"list_siswa.php?nip=";
    public static final String UJIAN_SISWA = Config.SERVER_API +"list_jadwal_ujian.php";
    public static final String NILAI_SISWA = Config.SERVER_API +"list_nilai_siswa.php?nis=";
    public static final String INSERT_ABSENSI = Config.SERVER_API +"insert_absensi.php";
    public static final String NIO = "nio";
    public static final String NIP = "nip";
    public static final String NIS = "nis";
    public static final String KEY_PASS = "password";
    public static final String LOGIN_SUCCESS = "success";
    public static final String SHARED_PREF_NAME = "myloginapp";
    public static final String USERNAME_SHARED_PREF_WALIMURID = "nio";
    public static final String USERNAME_SHARED_PREF_GURU = "nip";
    public static final String USERNAME_SHARED_PREF_SISWA = "nis";
    public static final String LOGGEDIN_SHARED_PREF = "loggedin";
    public static final String TAG_NAMA_GURU="nama_guru";
    public static final String TAG_KELAS="kelas";
    public static final String TAG_SUB_KELAS="sub_kelas";
    public static final String TAG_MAPEL="mapel";
    public static final String TAG_NIS="nis";
    public static final String TAG_NAMA_SISWA="nama_siswa";
    public static final String TAG_KODE_MAPEL="kode_mapel";
    public static final String TAG_HARI="hari";
    public static final String TAG_JAM_MULAI="jam_mulai";
    public static final String TAG_JAM_SELESAI="jam_selesai";
    public static final String TAG_TGL="tgl_ujian";
    public static final String TAG_KET="ket";
    public static final String TAG_N_TUGAS="nilai_tugas";
    public static final String TAG_N_UH="nilai_uh";
    public static final String TAG_N_UTS="nilai_uts";
    public static final String TAG_N_UAS="nilai_uas";
    public static final String TAG_N_AKHIR="nilai_akhir";

}
