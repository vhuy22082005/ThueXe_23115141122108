package com.example.thuexe_23115141122108

import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.thuexe_23115141122108.databinding.ActivityMainBinding

data class Thuexe2108(
    val ma_2108: String,
    val tenKhachHang_2108: String,
    val soNgayThue_2108: Int,
    val giaThue_2108: Double
)

fun String.chuanHoaTen_2108(): String = this.trim()
.split("\\s+".toRegex())
.joinToString(" ") { it.lowercase().replaceFirstChar(Char::uppercase) }

object Logic_2108 {
    private var stt2108 = 1

    fun sinhMa(tienTo: String = "HD"): String = "${tienTo}%02d2108".format(stt2108++)

    fun tenHopLe(text: String, min: Int = 2, max: Int = 50 ): String? {
        val trimmed = text.chuanHoaTen_2108()// Sửa trim thành tên hàm chuẩn hóa
        return if (trimmed.length !in min..max) null else trimmed
    }

    fun docSoNgayThue(text: String, min: Int = 1, max: Int = 30): Int? {
        return try {
            val so = text.trim().toInt()
            if (so !in min..max) null else so
        } catch (e: Exception) {
            null
        }
    }

    fun docGiaThue(text: String, min: Double = 50_000.0, max: Double = 1_500_000.0): Double? {
        return try {
            val so = text.trim().toDouble()
            if (so !in min..max) null else so
        } catch (e: Exception) {
            null
        }
    }

    fun tinhTienThue(soNgay: Int, donGia: Double): Double = soNgay * donGia

    fun dinhDang(ma: String, ten: String, soNgay: Int, donGia: Double, tong: Double): String {
        return "Thành công: $ma | $ten | $soNgay ngày | Tổng: %,.0f đ".format(tong)
    }
        fun ghiNhanNgayThue(tongNgayDaNhan2108: Int, soNgay: Int, gioiHan: Int = 15): Int? {
                val tongMoi = tongNgayDaNhan2108 + soNgay
                return if (tongMoi <= gioiHan) tongMoi else null
            }
}
class MainActivity : AppCompatActivity() {

    private lateinit var b: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        b = ActivityMainBinding.inflate(layoutInflater)
        setContentView(b.root)

        // 1. NÚT LƯU CHÍNH (btnLuu2108)
        b.btnTinh.setOnClickListener {
            b.edtTen.error = null
            b.edtSoLuong.error = null
            b.edtDonGia.error = null
            b.tvKetQua.text = ""

            var hopLe = true

            // Ô 1: Tên (ten)
            val ten = Logic_2108.tenHopLe(b.edtTen.text.toString(), min = 2, max = 50 )
            if (ten == null) {
                b.edtTen.error = "Tên phải từ 2 đến 50  ký tự"
                if (hopLe) b.edtTen.requestFocus()
                hopLe = false
            }

            // Ô 2: Số lượng (soLuong)
            val soLuong = Logic_2108.docSoNgayThue(b.edtSoLuong.text.toString(), min = 1, max = 30)
            if (soLuong == null) {
                b.edtSoLuong.error = "Số ngày thuê phải từ 1 đến 30 ngày"
                if (hopLe) b.edtSoLuong.requestFocus()
                hopLe = false
            }

            // Ô 3: Đơn giá (donGia)
            val donGia = Logic_2108.docGiaThue(b.edtDonGia.text.toString(), min = 50_000.0, max = 1_500_000.0)
            if (donGia == null) {
                b.edtDonGia.error = "Giá thuê một ngày phải từ 50000đ đến 1500000đ "
                if (hopLe) b.edtDonGia.requestFocus()
                hopLe = false
            }
            // NẾU CÓ BẤT KỲ Ô NÀO LỖI HOẶC NULL THÌ DỪNG
            if (!hopLe || ten == null || soLuong == null || donGia == null) {
                return@setOnClickListener
            }

            // (Commit 4 c4save sẽ dán tiếp ngay tại đây)
            val ma = Logic_2108.sinhMa("HD")
            val tong = Logic_2108.tinhTienThue(soLuong, donGia)
            b.tvKetQua.text = Logic_2108.dinhDang(ma, ten, soLuong, donGia, tong)
            Toast.makeText(this, "Đã lưu $ma", Toast.LENGTH_SHORT).show()

        }
        // 3. NÚT XÓA FORM (btnXoa2108)
        b.btnXoa.setOnClickListener {
             b.edtTen.text?.clear()
             b.edtSoLuong.text?.clear()
             b.edtDonGia.text?.clear()
             b.edtTen.error = null
             b.edtSoLuong.error = null
             b.edtDonGia.error = null
             b.tvKetQua.text = ""
             b.edtTen.requestFocus()
             Toast.makeText(this, "Đã xóa sạch form!",Toast.LENGTH_SHORT).show()
        }
    }
}