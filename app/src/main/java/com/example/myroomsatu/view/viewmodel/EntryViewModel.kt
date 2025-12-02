package com.example.myroomsatu.view.viewmodel

import androidx.lifecycle.ViewModel
import com.example.myroomsatu.repositori.RepositoriSiswa
import com.example.myroomsatu.room.Siswa
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

// Data class untuk merepresentasikan UI State dari halaman entri
data class UIStateSiswa(
    val detailSiswa: DetailSiswa = DetailSiswa(),
    val isEntryValid: Boolean = false
)

// Data class untuk menampung detail siswa yang diinput pengguna
data class DetailSiswa(
    val id: Int = 0,
    val nama: String = "",
    val alamat: String = "",
    val telpon: String = "",
)

// Fungsi ekstensi untuk mengubah DetailSiswa menjadi entitas Siswa untuk disimpan ke database
fun DetailSiswa.toSiswa(): Siswa = Siswa(
    id = id,
    nama = nama,
    alamat = alamat,
    telpon = telpon
)

class EntryViewModel(private val repositoriSiswa: RepositoriSiswa) : ViewModel() {

    // StateFlow privat yang bisa diubah
    private val _uiStateSiswa = MutableStateFlow(UIStateSiswa())
    // StateFlow publik yang hanya bisa dibaca
    val uiStateSiswa: StateFlow<UIStateSiswa> = _uiStateSiswa.asStateFlow()

    // Fungsi untuk memperbarui UI state berdasarkan input pengguna
    fun updateUiState(detailSiswa: DetailSiswa) {
        _uiStateSiswa.update {
            it.copy(
                detailSiswa = detailSiswa,
                isEntryValid = validasiInput(detailSiswa)
            )
        }
    }

    // Fungsi untuk menyimpan siswa baru ke repositori
    suspend fun saveSiswa() {
        if (validasiInput()) {
            repositoriSiswa.insertSiswa(_uiStateSiswa.value.detailSiswa.toSiswa())
        }
    }

    // Fungsi privat untuk memvalidasi input
    private fun validasiInput(detailSiswa: DetailSiswa = _uiStateSiswa.value.detailSiswa): Boolean {
        return with(detailSiswa) {
            nama.isNotBlank() && alamat.isNotBlank() && telpon.isNotBlank()
        }
    }
}

// Fungsi ekstensi (opsional, tapi praktik yang baik) untuk konversi dari entitas ke UI state
fun Siswa.toUiStateSiswa(isEntryValid: Boolean = false): UIStateSiswa = UIStateSiswa(
    detailSiswa = this.toDetailSiswa(),
    isEntryValid = isEntryValid
)

fun Siswa.toDetailSiswa(): DetailSiswa = DetailSiswa(
    id = id,
    nama = nama,
    alamat = alamat,
    telpon = telpon
)
