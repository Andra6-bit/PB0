
package Tugas_PBO_3;

public class Mobil {
    private String merk;
    private String model;
    private  int tahun;
    private String warna;
    
    public Mobil(String merk, String model, int tahun, String warna) {
        this.merk = merk;
        this.model = model;
        this.tahun = tahun;
        this.warna = warna;
    }
    public String getMerk() {
        return merk;
    }
    public void setMerk(String merk) {
        this.merk = merk;
    }
    public String getModel() {
        return model;
    }
    public void setModel(String model) {
        this.model = model;
    }
    public int getTahun() {
        return tahun;
    }
    public void setTahun(int tahun) {
        this.tahun = tahun;
    }
    public String getWarna() {
        return warna;
    }
    public void setWarna(String warna) {
        this.warna = warna;
    }
    void DisplayInfo() {
        System.out.println("Merk : " + getMerk() + "\nModel : " + getModel() + "\nTahun : " + getTahun() + "\nWarna :" + getWarna());
    }
    void startEngine() {
        System.out.println("Mesin mobil " + getMerk() + " menyala");
    }
}
