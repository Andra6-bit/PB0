/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Tugas11;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Pongo
 */
class Buku {
    private String judul;
    private List<Pengarang> pengarangList;
    public Buku(String judul){
        this.judul = judul;
        this.pengarangList = new ArrayList<>();
    }
    public void tambahPengarang(Pengarang pengarang) {
        pengarangList.add(pengarang);
    }
    public void infoBuku() {
        System.out.println("Judul Buku: " + judul);
        for (Pengarang pengarang : pengarangList) {
            pengarang.infoPengarang();
        } 
    }
}

class Perpustakaan {
    private List<Buku> bukuList;
    public Perpustakaan() {
        this.bukuList = new ArrayList<>();
    }
    public void tambahkanBuku(Buku buku) {
        bukuList.add(buku);
    }
    public void infoPerpustakaan() {
        for (Buku buku : bukuList) {
            buku.infoBuku();
        }
    }
}

class Pengarang {
    private String namaPengarang;
    public Pengarang(String namaPengarang){
        this.namaPengarang = namaPengarang;
    }
    public void infoPengarang() {
        System.out.println("Nama Pengarang: " + namaPengarang);
    }
}