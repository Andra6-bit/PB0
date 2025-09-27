/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package tugaspraktikum6;

import java.util.ArrayList;
import java.util.List;

abstract public class Produk {
    public void labelbarang() {
        System.out.println("Barang yang akan di beli");
    }
    public void infoharga(String nama){
        System.out.println("Nama : " + nama);
        
    }
    public void infoharga(String nama, int harga) {
        System.out.println("Nama : " + nama + "\nHarga " + harga);
    }
    
    public abstract double hitungDiskon(int harga);
    
}

class Buku extends Produk {
    @Override
    public void labelbarang() {
        System.out.println("Harry Potter Novel");
    }
    @Override
    public double hitungDiskon(int harga) {
        return harga * 0.90;
    }
}

class Elektronik extends Produk {
    @Override
    public void labelbarang() {
        System.out.println("Sharp TV");
    }
   
    @Override
    public double hitungDiskon(int harga) {
        return harga *0.95;
    }
}

class Pakaian extends Produk {
    @Override
    public void labelbarang() {
        System.out.println("Pollo");
    }
    
    @Override
    public double hitungDiskon(int harga) {
        return harga *0.95;
    }
}

