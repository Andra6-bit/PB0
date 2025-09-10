
package PraktikumPBO_4Tugas;


public class Pekerja extends Manusia {
    private int gaji;
    
    //Constructor
    public Pekerja(String nama, int usia, String pekerjaan, int gaji) {
        super(nama, usia, pekerjaan);
        this.gaji = gaji;
    }
    public int getGaji() {
        return gaji;
    }
    public void setGaji(int gaji) {
        this.gaji = gaji;
    }
    public void tostring() {
            System.out.println("nama        :" + getNama());
            System.out.println("usia        :" + usia);
            System.out.println("pekerjaan   :" + pekerjaan);
            System.out.println("gaji        :" + gaji + "Rp");
    }
}
