package perpustakaan;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Perpustakaan implements Serializable {

    // Agregasi: Perpustakaan memiliki Anggota
    private List<Anggota> daftarAnggota = new ArrayList<>();

    public void tambahAnggota(Anggota a) {
        daftarAnggota.add(a);
    }

    public void tampilAnggota() {
        for (Anggota a : daftarAnggota) {
            System.out.println("Anggota: " + a.getNama());
        }
    }
}
