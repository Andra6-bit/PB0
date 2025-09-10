
package PraktikumPBO_4Tugas;

public class Manusia {
    private String nama;
    protected int usia;
    public String pekerjaan;
    
    //Cosntructor
    public Manusia(String nama, int usia, String pekerjaan) {
        this.nama = nama;
        this.usia = usia;
        this.pekerjaan = pekerjaan;
    }
    public String getNama() {
        return nama;
    }
    public void setNama(String nama) {
        this.nama = nama;
    }
    
}
