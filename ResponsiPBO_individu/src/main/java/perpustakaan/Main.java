package perpustakaan;

public class Main {
    public static void main(String[] args) {

        // Buku
        Buku b1 = new Buku("B01", "Pemrograman Java", "Andra");
        Buku b2 = new Buku("B02", "Struktur Data", "Firdaus");

        // Peminjaman (komposisi)
        Peminjaman p = new Peminjaman();
        p.tambahBuku(b1);
        p.tambahBuku(b2);
        p.pinjam();

        // Perpustakaan & Anggota (agregasi)
        Perpustakaan perpus = new Perpustakaan();
        perpus.tambahAnggota(new Anggota("Budi"));
        perpus.tambahAnggota(new Anggota("Siti"));
        perpus.tampilAnggota();

        // Simpan data
        DataManager.simpan(perpus, "perpustakaan.ser");

        // Baca data
        Perpustakaan hasil =
                (Perpustakaan) DataManager.baca("perpustakaan.ser");

        System.out.println("=== Data setelah dibaca ===");
        if (hasil != null) {
            hasil.tampilAnggota();
        }
    }
}
