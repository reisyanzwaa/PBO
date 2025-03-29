import java.util.ArrayList;
import java.util.Scanner;

// Parent Class Barang
class Barang {
    protected int id;
    protected String nama;
    protected double harga;
    
    public Barang(int id, String nama, double harga) {
        this.id = id;
        this.nama = nama;
        this.harga = harga;
    }

    public void display() {
        System.out.printf("ID: %d | Nama: %s | Harga: Rp%.2f%n", id, nama, harga);
    }

    public int getId() {
        return id;
    }
}

// Subclass Sparepart (inheritance dari Barang)
class Sparepart extends Barang {
    private int stok;

    public Sparepart(int id, String nama, double harga, int stok) {
        super(id, nama, harga);
        this.stok = stok;
    }

    public void setStok(int stok) {
        this.stok = stok;
    }

    public int getStok() {
        return stok;
    }

    @Override
    public void display() {
        System.out.printf("ID: %d | Nama: %s | Harga: Rp%.2f | Stok: %d%n", id, nama, harga, stok);
    }
}

// Parent Class Orang
class Orang {
    protected int id;
    protected String nama;

    public Orang(int id, String nama) {
        this.id = id;
        this.nama = nama;
    }

    public void display() {
        System.out.printf("ID: %d | Nama: %s%n", id, nama);
    }
}

// Subclass Supplier (inheritance dari Orang)
class Supplier extends Orang {
    private String kontak;

    public Supplier(int id, String nama, String kontak) {
        super(id, nama);
        this.kontak = kontak;
    }

    public String getKontak() {
        return kontak;
    }

    public void setKontak(String kontak) {
        this.kontak = kontak;
    }

    @Override
    public void display() {
        System.out.printf("ID: %d | Nama: %s | Kontak: %s%n", id, nama, kontak);
    }
}

// Main Class
public class JayaAbadiMotor {
    static Scanner scanner = new Scanner(System.in);
    static ArrayList<Sparepart> spareparts = new ArrayList<>();
    static ArrayList<Supplier> suppliers = new ArrayList<>();
    private static int idSparepart = 1;
    private static int idSupplier = 1;

    public static void main(String[] args) {
        while (true) {
            System.out.println("\n=== Sistem Penjualan Sparepart Jaya Abadi Motor ===");
            System.out.println("1. Kelola Sparepart");
            System.out.println("2. Kelola Supplier");
            System.out.println("3. Exit");
            System.out.print("Pilih menu: ");
            
            int pilihan = inputInteger();
            
            switch (pilihan) {
                case 1:
                    menuSparepart();
                    break;
                case 2:
                    menuSupplier();
                    break;
                case 3:
                    System.out.println("Terima kasih! Program selesai.");
                    System.exit(0);
                default:
                    System.out.println("❌ Pilihan tidak valid. Silakan pilih menu yang benar.");
            }
        }
    }

    // CRUD Sparepart
    static void menuSparepart() {
        while (true) {
            System.out.println("\n=== Kelola Sparepart ===");
            System.out.println("1. Tambah Sparepart");
            System.out.println("2. Lihat Sparepart");
            System.out.println("3. Edit Sparepart");
            System.out.println("4. Hapus Sparepart");
            System.out.println("5. Kembali");
            System.out.print("Pilih menu: ");
            
            int pilihan = inputInteger();

            switch (pilihan) {
                case 1:
                    tambahSparepart();
                    break;
                case 2:
                    lihatSparepart();
                    break;
                case 3:
                    editSparepart();
                    break;
                case 4:
                    hapusSparepart();
                    break;
                case 5:
                    return;
                default:
                    System.out.println("❌ Pilihan tidak valid.");
            }
        }
    }

    static void tambahSparepart() {
        System.out.print("Nama Sparepart: ");
        String nama = scanner.nextLine();
        System.out.print("Harga: ");
        double harga = inputDouble();
        System.out.print("Stok: ");
        int stok = inputInteger();

        spareparts.add(new Sparepart(idSparepart++, nama, harga, stok));
        System.out.println("✅ Sparepart berhasil ditambahkan!");
    }

    static void lihatSparepart() {
        if (spareparts.isEmpty()) {
            System.out.println("❌ Tidak ada data sparepart.");
        } else {
            System.out.println("\n=== Data Sparepart ===");
            for (Sparepart s : spareparts) {
                s.display();
            }
        }
    }

    static void editSparepart() {
        System.out.print("Masukkan ID Sparepart yang ingin diubah: ");
        int id = inputInteger();
        
        for (Sparepart s : spareparts) {
            if (s.getId() == id) {
                System.out.print("Nama Baru: ");
                s.nama = scanner.nextLine();
                System.out.print("Harga Baru: ");
                s.harga = inputDouble();
                System.out.print("Stok Baru: ");
                s.setStok(inputInteger());
                System.out.println("✅ Sparepart berhasil diperbarui!");
                return;
            }
        }
        System.out.println("❌ ID Sparepart tidak ditemukan.");
    }

    static void hapusSparepart() {
        System.out.print("Masukkan ID Sparepart yang ingin dihapus: ");
        int id = inputInteger();

        spareparts.removeIf(s -> s.getId() == id);
        System.out.println("✅ Sparepart berhasil dihapus!");
    }

    static void menuSupplier() {
        while (true) {
            System.out.println("\n=== Kelola Supplier ===");
            System.out.println("1. Tambah Supplier");
            System.out.println("2. Lihat Supplier");
            System.out.println("3. Kembali");
            System.out.print("Pilih menu: ");

            int pilihan = inputInteger();

            switch (pilihan) {
                case 1:
                    tambahSupplier();
                    break;
                case 2:
                    lihatSupplier();
                    break;
                case 3:
                    return;
                default:
                    System.out.println("❌ Pilihan tidak valid.");
            }
        }
    }

    static void tambahSupplier() {
        System.out.print("Nama Supplier: ");
        String nama = scanner.nextLine();
        System.out.print("Kontak: ");
        String kontak = scanner.nextLine();

        suppliers.add(new Supplier(idSupplier++, nama, kontak));
        System.out.println("✅ Supplier berhasil ditambahkan!");
    }

    static void lihatSupplier() {
        if (suppliers.isEmpty()) {
            System.out.println("❌ Tidak ada data supplier.");
        } else {
            System.out.println("\n=== Data Supplier ===");
            for (Supplier s : suppliers) {
                s.display();
            }
        }
    }

    static int inputInteger() {
        while (!scanner.hasNextInt()) {
            System.out.println("❌ Harap masukkan angka yang valid.");
            scanner.next();
        }
        int angka = scanner.nextInt();
        scanner.nextLine();
        return angka;
    }

    static double inputDouble() {
        while (!scanner.hasNextDouble()) {
            System.out.println("❌ Harap masukkan angka yang valid.");
            scanner.next();
        }
        double angka = scanner.nextDouble();
        scanner.nextLine();
        return angka;
    }
}
