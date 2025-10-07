
package ResponsiUTS_Andra;

public class Produk {
    private String namaProduk;
    private int harga;
    
    public Produk(String namaProduk, int harga) {
        this.namaProduk = namaProduk;
        this.harga = harga;
    }
    public String getNamaProduk() {
        return namaProduk;
    }
    public void setNamaProduk(String namaProduk) {
        this.namaProduk = namaProduk;
    }
    public int getHarga() {
        return harga;
    }
    public void setHarga(int harga) {
        this.harga = harga;
    }
    
    public void tampilkaninfo(){
        System.out.println("Nama Produk: " + namaProduk);
        System.out.println("Harga: " + harga);
    }
}

class Elektronik extends Produk {
    private int garansi;
    
    public Elektronik(String namaProduk, int harga, int garansi) {
        super(namaProduk, harga);
        this.garansi = garansi;
    }
    
    public int getGaransi() {
        return garansi;
    }
    public void setGaransi(int garansi) {
        this.garansi = garansi;
    }
    
    @Override
    public void tampilkaninfo() {
        super.tampilkaninfo();
        System.out.println("Garansi: " + garansi + "tahun");
    }
    
}

class Makanan extends Produk {
    private String TanggalKadaluarsa;
    
    public Makanan(String namaProduk, int harga, String TanggalKadaluarsa) {
        super(namaProduk, harga);
        this.TanggalKadaluarsa = TanggalKadaluarsa;
    }
    
    public String getTanggalKadaluarsa() {
        return TanggalKadaluarsa;
    }
    public void setTanggalKadaluarsa(String TanggalKadaluarsa) {
        this.TanggalKadaluarsa = TanggalKadaluarsa;
    }
    
    @Override
    public void tampilkaninfo() {
        super.tampilkaninfo();
        System.out.println("Tanggal Kadaluarsa: " + TanggalKadaluarsa);
    }
    
}