
package Tugas_PBO_3;

public class Main {
    public static void main(String[] args) {
        Mobil mobil1 = new Mobil("BMW", "E60", 2022, "Hitam");
        mobil1.DisplayInfo();
      
        
        Mobil mobil2 = new Mobil("Tesla", "Model Y", 3020, "Putih");
        mobil2.DisplayInfo();
        mobil2.startEngine();
    }
    
}
