
package TugasPraktikum10;


public class Main {

   
    public static void main(String[] args) {
        Pembayaran laptop = new Elektronik();
        Pembayaran roti = new Makanan();
        
        double hargaLaptop = 5000000;
        double hargaRoti = 10000;
        
        double pajakLaptop = laptop.hitungPajak(hargaLaptop);
        double pajakRoti = roti.hitungPajak(hargaRoti);
        
        System.out.println("   Sistem Pembayaran   ");
        System.out.println("Harga Laptop : RP" + hargaLaptop);
        System.out.println("Pajak Laptop (10%) : RP" + pajakLaptop);
        System.out.println("Total Bayar Laptop : RP" + (hargaLaptop + pajakLaptop));
        System.out.println("\nHarga Roti : RP" + hargaRoti);
        
        System.out.println("Pajak Roti (5%) : RP" + hargaRoti);
        System.out.println("Total Bayar Roti : RP" + (hargaRoti + pajakRoti));
        
        
    }
    
}
