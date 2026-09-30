/**
 * TUGAS LATIHAN PEMROGRAMAN KOTLIN Modul 4
 * Nama : Ahmad Dzaky Al Farras
 * NIM  : 24523280
 */

// ============================================================================
// DEKLARASI STRUKTUR DATA & FUNGSI PENDUKUNG (DI LUAR MAIN)
// ============================================================================

// Latihan 5: Singleton Object Konfigurasi Aplikasi
object AppConfig {
    const val MAX_COURSES = 5
}

// Latihan 1 & 2: Enum Class Status Mata Kuliah
enum class CourseStatus {
    ACTIVE,
    COMPLETED,
    DROPPED // Ditambahkan pada Latihan 2
}

// Latihan 1 & 5: Data Class Course dengan Companion Object
data class Course(
    val code: String,
    val name: String,
    val status: CourseStatus
) {
    companion object {
        const val PREFIX = "PAB" // Latihan 5
    }
}

// Latihan 1: Extension Function pada Course
fun Course.displayInfo(): String {
    return "$code - $name - $status"
}

// Latihan 5: Extension Function pada MutableList<Course>
fun MutableList<Course>.addCourse(course: Course): Boolean {
    // Menambahkan hanya jika jumlah < MAX_COURSES dan kodenya diawali PREFIX ("PAB")
    if (this.size < AppConfig.MAX_COURSES && course.code.startsWith(Course.PREFIX)) {
        this.add(course)
        return true
    }
    return false
}

// Latihan 2: Fungsi describe dengan 'when' tanpa cabang 'else'
fun describe(status: CourseStatus): String {
    return when (status) {
        CourseStatus.ACTIVE -> "Mata kuliah sedang berlangsung"
        CourseStatus.COMPLETED -> "Mata kuliah telah selesai diambil"
        CourseStatus.DROPPED -> "Mata kuliah telah dibatalkan/dilepas"
    }
}

// ============================================================================
// FUNGSI UTAMA (MAIN FUNCTION)
// ============================================================================
fun main() {
    println("==================================================")
    println("          TUGAS LATIHAN PEMROGRAMAN KOTLIN        ")
    println("  Nama : Ahmad Dzaky Al Farras                    ")
    println("  NIM  : 24523280                                 ")
    println("==================================================")

    // ------------------------------------------------------------------------
    // LATIHAN 1: Program Mata Kuliah Mahasiswa
    // ------------------------------------------------------------------------
    println("\n--- LATIHAN 1: Program Mata Kuliah ---")

    // 3. Masukkan minimal 3 mata kuliah ke MutableList
    val courses = mutableListOf(
        Course("PAB101", "Mobile App Development", CourseStatus.ACTIVE),
        Course("PAB102", "Web Development", CourseStatus.COMPLETED),
        Course("PAB103", "Database Systems", CourseStatus.ACTIVE)
    )

    // 4. Tambahkan 1 mata kuliah baru & hapus 1 mata kuliah (indeks ke-1)
    courses.add(Course("PAB104", "Algorithms", CourseStatus.ACTIVE))
    courses.removeAt(1) // Menghapus PAB102 (Web Development)

    // Cetak sisa mata kuliah menggunakan displayInfo()
    println("Daftar Sisa Mata Kuliah:")
    for (course in courses) {
        println(course.displayInfo())
    }

    // 6. Destructuring pada satu mata kuliah
    val sampleCourse = courses[0]
    val (code, name, status) = sampleCourse
    println("\nHasil Destructuring (Mata Kuliah Pertama):")
    println("Kode: $code | Nama: $name | Status: $status")

    // ------------------------------------------------------------------------
    // LATIHAN 2: Deskripsi Status
    // ------------------------------------------------------------------------
    println("\n--- LATIHAN 2: Deskripsi Status ---")

    // Menambahkan contoh mata kuliah dengan status DROPPED
    courses.add(Course("PAB105", "Computer Network", CourseStatus.DROPPED))

    // Cetak deskripsi status untuk setiap mata kuliah
    println("Deskripsi Status Setiap Mata Kuliah:")
    for (course in courses) {
        println("${course.code} (${course.name}): ${describe(course.status)}")
    }

    /* Catatan Latihan 2:
     * Ketika 'DROPPED' ditambahkan ke CourseStatus, compiler memberikan pesan error:
     * "'when' expression must be exhaustive, add necessary 'DROPPED' branch or 'else' branch".
     * Hal ini terjadi karena 'when' digunakan sebagai expression yang mewajibkan
     * semua nilai enum tertangani.
     */

    // ------------------------------------------------------------------------
    // LATIHAN 3: Tag Keahlian Unik
    // ------------------------------------------------------------------------
    println("\n--- LATIHAN 3: Tag Keahlian Unik ---")

    val skills = mutableSetOf("Kotlin", "Java")
    skills.add("Python")
    skills.add("Kotlin") // Menambahkan elemen duplikat

    println("Ukuran Set Keahlian: ${skills.size}") // Output: 3

    // Periksa keberadaan elemen menggunakan operator 'in'
    println("Apakah 'Swift' ada di Set? ${"Swift" in skills}")   // false
    println("Apakah 'Python' ada di Set? ${"Python" in skills}") // true

    /* Catatan Latihan 3:
     * Ukuran Set tidak bertambah saat "Kotlin" ditambahkan kembali karena Set
     * dirancang hanya menyimpan elemen yang unik. Elemen duplikat diabaikan.
     */

    // ------------------------------------------------------------------------
    // LATIHAN 4: Daftar Nilai
    // ------------------------------------------------------------------------
    println("\n--- LATIHAN 4: Daftar Nilai ---")

    // Map memetakan NIM ke Nilai Ujian (menggunakan NIM asli)
    val scores = mutableMapOf(
        24523280 to 85, // NIM Ahmad Dzaky Al Farras
        24523281 to 78,
        24523282 to 92
    )

    // Perbarui nilai mahasiswa (NIM 24523280)
    scores[24523280] = 95

    // Hapus satu mahasiswa (NIM 24523281)
    scores.remove(24523281)

    // Cetak setiap entri dengan destructuring for-loop
    println("Daftar Nilai Mahasiswa:")
    for ((nim, score) in scores) {
        println("NIM: $nim | Nilai Ujian: $score")
    }

    // Akses NIM yang tidak ada di dalam Map
    val unknownNim = 99999999
    val unknownScore = scores[unknownNim]
    println("\nNilai untuk NIM $unknownNim (tidak ada di Map): $unknownScore")
    // Catatan Latihan 4: Hasilnya 'null' karena kunci tidak ditemukan dalam Map.

    // ------------------------------------------------------------------------
    // LATIHAN 5: Konfigurasi Aplikasi
    // ------------------------------------------------------------------------
    println("\n--- LATIHAN 5: Konfigurasi Aplikasi ---")

    println("Jumlah mata kuliah saat ini: ${courses.size}") // 4

    // Uji Coba 1: Menambah mata kuliah valid (Prefix "PAB" dan belum penuh)
    val validCourse = Course("PAB106", "Cloud Computing", CourseStatus.ACTIVE)
    val result1 = courses.addCourse(validCourse)
    println("Berhasil menambah PAB106? $result1") // true (jumlah menjadi 5)

    // Uji Coba 2: Menambah mata kuliah saat jumlah sudah mencapai MAX_COURSES (5)
    val extraCourse = Course("PAB107", "Cyber Security", CourseStatus.ACTIVE)
    val result2 = courses.addCourse(extraCourse)
    println("Berhasil menambah PAB107 (melebihi MAX_COURSES = 5)? $result2") // false

    // Uji Coba 3: Menambah mata kuliah dengan Prefix salah ("TIF")
    val testList = mutableListOf<Course>()
    val invalidPrefixCourse = Course("TIF101", "Artificial Intelligence", CourseStatus.ACTIVE)
    val result3 = testList.addCourse(invalidPrefixCourse)
    println("Berhasil menambah TIF101 (prefix bukan 'PAB')? $result3") // false
}
