/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package praktikum9;

/**
 *
 * @author Pongo
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Kendaraan mobil = new Mobil();
        Kendaraan sepeda = new Sepeda();
        mobil.berjalan();
        mobil.info();
        sepeda.berjalan();
        sepeda.info();
        
        Hewan anjing = new Anjing();
        Hewan kucing = new Kucing();
        anjing.bersuara();
        anjing.info();
        kucing.bersuara();
        kucing.info();
        
       
    }
    
}
