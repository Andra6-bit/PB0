package praktikum12;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class BookManagementSystem {

    private static final String TEXT_FILE = "buku.txt";
    private static final String SERIAL_FILE = "buku.ser";

    private static List<Buku> bukuList = new ArrayList<>();

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("\n=== MENU SISTEM MANAJEMEN BUKU ===");
            System.out.println("1. Tambah Buku");
            System.out.println("2. Simpan ke File Teks (buku.txt)");
            System.out.println("3. Simpan Objek ke File Serial (buku.ser)");
            System.out.println("4. Tampilkan Data Buku dari File");
            System.out.println("5. Keluar");
            System.out.print("Pilihan: ");

            int pilihan = scanner.nextInt();
            scanner.nextLine(); 

            switch (pilihan) {
                case 1 -> tambahBuku(scanner);
                case 2 -> simpanKeFileTeks();
                case 3 -> simpanKeFileSerial();
                case 4 -> tampilkanSemuaData();
                case 5 -> {
                    System.out.println("Keluar dari program.");
                    scanner.close();
                    return;
                }
                default -> System.out.println("Pilihan tidak valid!");
            }
        }
    }

    private static void tambahBuku(Scanner scanner) {
        System.out.print("Masukkan Judul Buku: ");
        String judul = scanner.nextLine();

        System.out.print("Masukkan Nama Pengarang: ");
        String pengarang = scanner.nextLine();

        System.out.print("Masukkan Tahun Terbit: ");
        int tahun = scanner.nextInt();

        bukuList.add(new Buku(judul, pengarang, tahun));
        System.out.println("Buku berhasil ditambahkan!");
    }

    private static void simpanKeFileTeks() {
        try (FileWriter writer = new FileWriter(TEXT_FILE)) {

            for (Buku b : bukuList) {
                writer.write(b.toString() + "\n");
            }

            System.out.println("Data buku berhasil disimpan ke buku.txt");

        } catch (IOException e) {
            System.out.println("Terjadi kesalahan saat menulis file teks.");
        }
    }

    private static void simpanKeFileSerial() {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(SERIAL_FILE))) {

            oos.writeObject(bukuList);
            System.out.println("Objek Buku berhasil disimpan ke buku.ser");

        } catch (IOException e) {
            System.out.println("Terjadi kesalahan saat serialisasi.");
        }
    }

    private static void tampilkanSemuaData() {
        System.out.println("\n=== DATA DARI FILE TEKS (buku.txt) ===");
        bacaFileTeks();

        System.out.println("\n=== DATA DARI FILE SERIAL (buku.ser) ===");
        bacaFileSerial();
    }

    private static void bacaFileTeks() {
        try (BufferedReader reader = new BufferedReader(new FileReader(TEXT_FILE))) {
            String line;
            while ((line = reader.readLine()) != null) {
                System.out.println(line);
            }
        } catch (IOException e) {
            System.out.println("Tidak bisa membaca file teks.");
        }
    }

    private static void bacaFileSerial() {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(SERIAL_FILE))) {

            List<Buku> list = (List<Buku>) ois.readObject();
            for (Buku b : list) {
                System.out.println(b);
            }

        } catch (IOException | ClassNotFoundException e) {
            System.out.println("Tidak bisa membaca file serial.");
        }
    }
}
