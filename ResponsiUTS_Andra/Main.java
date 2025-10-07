
package ResponsiUTS_Andra;

public class Main {

    public static void main(String[] args) {
        System.out.println(" Output Produk ");
        Produk elektronik = new Elektronik("Laptop", 15000000, 2);
        elektronik.tampilkaninfo();
        
        System.out.println(" Output Pegawai ");
        Pegawai pegawaiTetap = new PegawaiTetap("Budi", 5000000, 1000000);
        pegawaiTetap.tampilkaninfo();
        
         System.out.println(" Output Polimorfisme ");
         Produk makanan = new Makanan("Snack", 15000, "2023-12-30");
         Pegawai pegawaiKontrak = new PegawaiKontrak("Andi", 3000000, 12);
         
         makanan.tampilkaninfo();
         System.out.println();
         pegawaiKontrak.tampilkaninfo();
    }
    
}
