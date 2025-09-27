/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package praktikum6;

/**
 *
 * @author Pongo
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Hewan hewan = new Kucing();
        hewan.bersuara();
        
        Kucing kucing = new Kucing () ;
        kucing.makan("ikan");
        kucing.makan("ikan", 2);
        
        Anjing anjing = new Anjing();
        anjing.bersuara();
        anjing.makan("daging", 3);
        
        
    }
    
}
