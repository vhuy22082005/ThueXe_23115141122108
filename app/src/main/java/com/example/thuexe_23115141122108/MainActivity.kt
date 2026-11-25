package com.example.thuexe_23115141122108

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

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

    fun tinhTienThue(soLuong: Int, donGia: Double): Double = soLuong * donGia

    fun dinhDang(ma: String, ten: String, soLuong: Int, donGia: Double, tong: Double): String {
        return "Thành công: $ma | $ten | $soLuong lượt | Tổng: %,.0f đ".format(tong)
    }
}
