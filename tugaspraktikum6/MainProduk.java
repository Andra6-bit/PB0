/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package tugaspraktikum6;

/**
 *
 * @author Pongo
 */
public class MainProduk {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
       KeranjangBelanja keranjang = new KeranjangBelanja();

        Buku buku = new Buku();
        Elektronik tv = new Elektronik();
        Pakaian baju = new Pakaian();

        keranjang.tambahProduk(buku, 100000);
        keranjang.tambahProduk(tv, 2000000);
        keranjang.tambahProduk(baju, 250000);

        System.out.println("Total belanja setelah diskon: Rp " + keranjang.hitungTotal());
    }
    
}
