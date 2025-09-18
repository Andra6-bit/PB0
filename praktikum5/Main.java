
package praktikum5;
public class Main {

    public static void main(String[] args) {
       Mobil mobil = new Mobil();
       mobil.nama = "Toyota";
       mobil.kecepatan = 180;
       mobil.jumlahPintu = 4;
       mobil.jumlahRoda = 16;
  
       mobil.tampilkanInfo();
       
       SepedaMotor motor = new SepedaMotor();
       motor.nama = "Yamaha";
       motor.kecepatan = 120;
       motor.jenisMesin = "2-tak";
       motor.jumlahRoda = 4;
       motor.tampilkanInfo();
       
       Kucing kucing1 = new Kucing();
       kucing1.nama = "Angela";
       kucing1.jenis = "Persia";
       kucing1.infoHewan();
       
       Anjing anjing1 = new Anjing() ;
       anjing1.jenis = "Bulldog";
       anjing1.nama = "kotac";
       anjing1.infoHewan();
    
    } 
}
