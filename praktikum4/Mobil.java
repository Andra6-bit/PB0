/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package praktikum4;

/**
 *
 * @author Pongo
 */
public class Mobil extends Kendaraan {
    private int jumlahPintu;
    
    public Mobil(String nama, String jenisMesin, int kecepatanMaks, int jumlahPintu) {
        super(nama, jenisMesin, kecepatanMaks);
        this.jumlahPintu = jumlahPintu;
    }
    public void tampilkaninfoMobil() {
        System.out.println("Kecepatan Maksimum Mobil: " + kecepatanMaks + " km/h");
        System.out.println("Jumlah Pintu: " + jumlahPintu);
        
    }
}
