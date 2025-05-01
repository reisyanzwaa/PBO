import java.util.ArrayList;
import java.util.Scanner;

// Interface DataManagement
interface DataManagement {
    void tambahData(); // Menambahkan data
    void hapusData(); // Menghapus data
}

// Parent Class Barang menjadi Kelas Abstrak
abstract class Barang {
    protected final int id;
    protected String nama;
    protected double harga;

    public Barang(int id, String nama, double harga) {
        this.id = id;
        this.nama = nama;
        this.harga = harga;
    }

    // Abstract Method
    public abstract void display(); // method abstrak untuk diturunkan oleh subclass
    public int getId() {
        return id;
    }
}

// Subclass Sparepart
class Sparepart extends Barang implements DataManagement {
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

    // Method Overriding
    @Override
    public void display() {
        System.out.printf("ID: %d | Nama: %s | Harga: Rp%.2f | Stok: %d%n", id, nama, harga, stok);
    }

    // Method Overloading (STATIC POLYMORPHISM)
    public void updateData(String nama, double harga) {
        this.nama = nama;
        this.harga = harga;
    }

    public void updateData(String nama, double harga, int stok) {
        this.nama = nama;
        this.harga = harga;
        this.stok = stok;
    }

    // Implementasi dari method tambahData dan hapusData dari interface
    @Override
    public void tambahData() {
        System.out.println("Tambah data sparepart ke dalam sistem.");
    }

    @Override
    public void hapusData() {
        System.out.println("Hapus data sparepart dari sistem.");
    }
}

// Parent Class Orang menjadi Kelas Abstrak
abstract class Orang {
    protected final int id;
    protected String nama;

    public Orang(int id, String nama) {
        this.id = id;
        this.nama = nama;
    }

    // Abstract Method
    public abstract void display(); // method abstrak untuk diturunkan oleh subclass
}

// Subclass Supplier
class Supplier extends Orang implements DataManagement {
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

    // Method Overriding
    @Override
    public void display() {
        System.out.printf("ID: %d | Nama: %s | Kontak: %s%n", id, nama, kontak);
    }

    // Method Overloading (STATIC POLYMORPHISM)
    public void updateKontak(String kontak) {
        this.kontak = kontak;
    }

    public void updateKontak(String nama, String kontak) {
        this.nama = nama;
        this.kontak = kontak;
    }

    // Implementasi dari method tambahData dan hapusData dari interface
    @Override
    public void tambahData() {
        System.out.println("Tambah data supplier ke dalam sistem.");
    }

    @Override
    public void hapusData() {
        System.out.println("Hapus data supplier dari sistem.");
    }
}

// Main Class
public class JayaAbadiMotor {
    static Scanner scanner = new Scanner(System.in);
    static ArrayList<Sparepart> spareparts = new ArrayList<>();
    static ArrayList<Supplier> suppliers = new ArrayList<>();
    private static int idSparepart = 1;
    private static int idSupplier = 1;

    // Static Method to count total spareparts
    static int totalSpareparts() {
        return spareparts.size();
    }

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
        try {
            System.out.print("Nama Sparepart: ");
            String nama = scanner.nextLine();
            System.out.print("Harga: ");
            double harga = inputDouble();
            System.out.print("Stok: ");
            int stok = inputInteger();

            spareparts.add(new Sparepart(idSparepart++, nama, harga, stok));
            System.out.println("✅ Sparepart berhasil ditambahkan!");
        } catch (Exception e) {
            System.out.println("❌ Terjadi kesalahan saat menambahkan sparepart: " + e.getMessage());
        }
    }

    static void lihatSparepart() {
        if (spareparts.isEmpty()) {
            System.out.println("❌ Tidak ada data sparepart.");
        } else {
            System.out.println("\n=== Data Sparepart ===");
            for (Sparepart s : spareparts) {
                s.display(); // polymorphism
            }
        }
    }

    static void editSparepart() {
        try {
            System.out.print("Masukkan ID Sparepart yang ingin diubah: ");
            int id = inputInteger();

            for (Sparepart s : spareparts) {
                if (s.getId() == id) {
                    System.out.print("Nama Baru: ");
                    String nama = scanner.nextLine();
                    System.out.print("Harga Baru: ");
                    double harga = inputDouble();
                    System.out.print("Stok Baru: ");
                    int stok = inputInteger();
                    s.updateData(nama, harga, stok); // method overloading
                    System.out.println("✅ Sparepart berhasil diperbarui!");
                    return;
                }
            }
            System.out.println("❌ ID Sparepart tidak ditemukan.");
        } catch (Exception e) {
            System.out.println("❌ Terjadi kesalahan saat mengedit sparepart: " + e.getMessage());
        }
    }

    static void hapusSparepart() {
        try {
            System.out.print("Masukkan ID Sparepart yang ingin dihapus: ");
            int id = inputInteger();

            boolean removed = spareparts.removeIf(s -> s.getId() == id);
            if (removed) {
                System.out.println("✅ Sparepart berhasil dihapus!");
            } else {
                System.out.println("❌ ID tidak ditemukan.");
            }
        } catch (Exception e) {
            System.out.println("❌ Terjadi kesalahan saat menghapus sparepart: " + e.getMessage());
        }
    }

    static void menuSupplier() {
        while (true) {
            System.out.println("\n=== Kelola Supplier ===");
            System.out.println("1. Tambah Supplier");
            System.out.println("2. Lihat Supplier");
            System.out.println("3. Edit Supplier");
            System.out.println("4. Kembali");
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
                    editSupplier();
                    break;
                case 4:
                    return;
                default:
                    System.out.println("❌ Pilihan tidak valid.");
            }
        }
    }

    static void tambahSupplier() {
        try {
            System.out.print("Nama Supplier: ");
            String nama = scanner.nextLine();
            System.out.print("Kontak: ");
            String kontak = scanner.nextLine();

            suppliers.add(new Supplier(idSupplier++, nama, kontak));
            System.out.println("✅ Supplier berhasil ditambahkan!");
        } catch (Exception e) {
            System.out.println("❌ Terjadi kesalahan saat menambahkan supplier: " + e.getMessage());
        }
    }

    static void lihatSupplier() {
        if (suppliers.isEmpty()) {
            System.out.println("❌ Tidak ada data supplier.");
        } else {
            System.out.println("\n=== Data Supplier ===");
            for (Supplier s : suppliers) {
                s.display(); // polymorphism
            }
        }
    }

    static void editSupplier() {
        try {
            System.out.print("Masukkan ID Supplier yang ingin diubah: ");
            int id = inputInteger();

            for (Supplier s : suppliers) {
                if (s.id == id) {
                    System.out.print("Nama Baru: ");
                    String nama = scanner.nextLine();
                    System.out.print("Kontak Baru: ");
                    String kontak = scanner.nextLine();
                    s.updateKontak(nama, kontak); // method overloading
                    System.out.println("✅ Supplier berhasil diperbarui!");
                    return;
                }
            }
            System.out.println("❌ ID Supplier tidak ditemukan.");
        } catch (Exception e) {
            System.out.println("❌ Terjadi kesalahan saat mengedit supplier: " + e.getMessage());
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
