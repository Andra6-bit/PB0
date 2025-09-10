
package praktikum4;


public class Kendaraan {
    private String nama;
    public String jenisMesin;
    protected int kecepatanMaks;

    public Kendaraan(String nama,String jenisMesin, int kecepatanMaks){
        this.nama = nama;
        this.jenisMesin = jenisMesin;
        this.kecepatanMaks = kecepatanMaks;
    }
    public String getNama(){
        return nama;
    }
    public void setNama(String nama) {
        this.nama = nama;
    }
    public void tampilkaninfoKendaraan() {
        System.out.println("Nama Kendaraan: " + nama);
        System.out.println("Kecepatan Maksimum: " + kecepatanMaks + " km/h");
        System.out.println("Jenis Mesin: " + jenisMesin);
    }
}
