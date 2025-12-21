package perpustakaan;

import java.io.Serializable;

public class Anggota implements Serializable {
    private String nama;

    public Anggota(String nama) {
        this.nama = nama;
    }

    public String getNama() {
        return nama;
    }
}
