package perpustakaan;

import java.io.Serializable;

public abstract class Koleksi implements Serializable {
    protected String id;
    protected String judul;

    public Koleksi(String id, String judul) {
        this.id = id;
        this.judul = judul;
    }

    public String getJudul() {
        return judul;
    }

    // method abstract
    public abstract String getInfo();
}
