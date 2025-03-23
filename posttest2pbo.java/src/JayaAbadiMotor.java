import java.util.ArrayList;
import java.util.Scanner;

// Class Sparepart
class Sparepart {
    private int id; // Private: hanya dapat diakses di dalam kelas ini
    String nama; // Default (package-private): hanya dapat diakses di dalam package yang sama
    protected double harga; // Protected: dapat diakses di dalam package yang sama dan oleh subclass
    public int stok; // Public: dapat diakses dari mana saja

    public Sparepart(int id, String nama, double harga, int stok) {
        this.id = id;
        this.nama = nama;
        this.harga = harga;
        this.stok = stok;
    }

    // Getter untuk id (karena id bersifat private)
    public int getId() {
        return id;
    }

    // Setter untuk id (karena id bersifat private)
    public void setId(int id) {
        this.id = id;
    }

    public void display() {
        System.out.printf("ID: %d | Nama: %s | Harga: Rp%.2f | Stok: %d%n", id, nama, harga, stok);
    }
}

// Class Supplier
class Supplier {
    protected int id; // Protected: dapat diakses di dalam package yang sama dan oleh subclass
    String nama; // Default (package-private): hanya dapat diakses di dalam package yang sama
    private String kontak; // Private: hanya dapat diakses di dalam kelas ini

    public Supplier(int id, String nama, String kontak) {
        this.id = id;
        this.nama = nama;
        this.kontak = kontak;
    }

    // Getter untuk kontak (karena kontak bersifat private)
    public String getKontak() {
        return kontak;
    }

    // Setter untuk kontak (karena kontak bersifat private)
    public void setKontak(String kontak) {
        this.kontak = kontak;
    }

    public void display() {
        System.out.printf("ID: %d | Nama: %s | Kontak: %s%n", id, nama, kontak);
    }
}

// Main Class
public class JayaAbadiMotor {
    static Scanner scanner = new Scanner(System.in);
    static ArrayList<Sparepart> spareparts = new ArrayList<>();
    static ArrayList<Supplier> suppliers = new ArrayList<>();
    private static int idSparepart = 1; //ID Sparepart dibuat private
    protected static int idSupplier = 1; //ID Supplier dibuat protected

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

    // =================== CRUD Sparepart ===================
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

        spareparts.add(new Sparepart(getIdSparepart(), nama, harga, stok)); //Menggunakan Getter
        setIdSparepart(getIdSparepart()+1); //Menggunakan Setter
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
            if (s.getId() == id) { //Menggunakan Getter
                System.out.print("Nama Baru: ");
                s.nama = scanner.nextLine();
                System.out.print("Harga Baru: ");
                s.harga = inputDouble();
                System.out.print("Stok Baru: ");
                s.stok = inputInteger();
                System.out.println("✅ Sparepart berhasil diperbarui!");
                return;
            }
        }
        System.out.println("❌ ID Sparepart tidak ditemukan.");
    }

    static void hapusSparepart() {
        System.out.print("Masukkan ID Sparepart yang ingin dihapus: ");
        int id = inputInteger();
        
        for (Sparepart s : spareparts) {
            if (s.getId() == id) { //Menggunakan Getter
                spareparts.remove(s);
                System.out.println("✅ Sparepart berhasil dihapus!");
                return;
            }
        }
        System.out.println("❌ ID Sparepart tidak ditemukan.");
    }

    // =================== CRUD Supplier ===================
    static void menuSupplier() {
        while (true) {
            System.out.println("\n=== Kelola Supplier ===");
            System.out.println("1. Tambah Supplier");
            System.out.println("2. Lihat Supplier");
            System.out.println("3. Edit Supplier");
            System.out.println("4. Hapus Supplier");
            System.out.println("5. Kembali");
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
                    hapusSupplier();
                    break;
                case 5:
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

        suppliers.add(new Supplier(getIdSupplier(), nama, kontak)); //Menggunakan Getter
        setIdSupplier(getIdSupplier()+1); //Menggunakan Setter
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

    static void editSupplier() {
        System.out.print("Masukkan ID Supplier yang ingin diubah: ");
        int id = inputInteger();
        
        for (Supplier s : suppliers) {
            if (s.id == id) {
                System.out.print("Nama Baru: ");
                s.nama = scanner.nextLine();
                System.out.print("Kontak Baru: ");
                s.setKontak(scanner.nextLine()); //Menggunakan Setter
                System.out.println("✅ Supplier berhasil diperbarui!");
                return;
            }
        }
        System.out.println("❌ ID Supplier tidak ditemukan.");
    }

    static void hapusSupplier() {
        System.out.print("Masukkan ID Supplier yang ingin dihapus: ");
        int id = inputInteger();
        
        for (Supplier s : suppliers) {
            if (s.id == id) {
                suppliers.remove(s);
                System.out.println("✅ Supplier berhasil dihapus!");
                return;
            }
        }
        System.out.println("❌ ID Supplier tidak ditemukan.");
    }

    static int inputInteger() {
        while (!scanner.hasNextInt()) {
            System.out.println("❌ Harap masukkan angka yang valid.");
            scanner.next();
        }
        int angka = scanner.nextInt();
        scanner.nextLine(); // Untuk menghindari masalah input berikutnya
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

    //Getter dan Setter ID Sparepart
    public static int getIdSparepart() {
        return idSparepart;
    }

    public static void setIdSparepart(int idSparepart) {
        JayaAbadiMotor.idSparepart = idSparepart;
    }

    //Getter dan Setter ID Supplier
    public static int getIdSupplier() {
        return idSupplier;
    }

    public static void setIdSupplier(int idSupplier) {
        JayaAbadiMotor.idSupplier = idSupplier;
    }
}
