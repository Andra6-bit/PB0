
package TugasPraktikum10;

public class Makanan implements Pembayaran {
    @Override
    public double hitungPajak(double harga){
        return harga * 0.05;
    }
    
    
}
