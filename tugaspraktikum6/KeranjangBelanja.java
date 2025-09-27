/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package tugaspraktikum6;

import java.util.ArrayList;
import java.util.List;

public class KeranjangBelanja {
    private List<Produk> produkList = new ArrayList<>();
    private List<Integer> hargaList = new ArrayList<>();
    
    public void tambahProduk(Produk p, int harga) {
        produkList.add(p);
        hargaList.add(harga);
        p.infoharga(p.getClass().getSimpleName(), harga);
        p.labelbarang();
    }
    
    public double hitungTotal() {
        double total = 0;
        for (int i = 0; i < produkList.size(); i++) {
            Produk p = produkList.get(i);
            int harga = hargaList.get(i);
            double hargaDiskon = p.hitungDiskon(harga);
            System.out.println("Harga setelah diskon " + p.getClass().getSimpleName() + " : Rp " + hargaDiskon);
            total += hargaDiskon;
        }
        return total;
    }
}
