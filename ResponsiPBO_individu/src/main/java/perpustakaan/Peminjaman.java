package perpustakaan;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Peminjaman implements LayananPinjam, Serializable {

    // Komposisi: Peminjaman memiliki daftar Buku
    private List<Buku> daftarBuku = new ArrayList<>();

    public void tambahBuku(Buku buku) {
        daftarBuku.add(buku);
    }

    @Override
    public void pinjam() {
        System.out.println("Buku dipinjam:");
        for (Buku b : daftarBuku) {
            System.out.println("- " + b.getInfo());
        }
    }

    @Override
    public void kembali() {
        System.out.println("Buku dikembalikan.");
        daftarBuku.clear();
    }
}
