package com.example.myroomsatu.view.viewmodel

import androidx.lifecycle.ViewModel
import com.example.myroomsatu.repositori.RepositoriSiswa
import com.example.myroomsatu.room.Siswa
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class EntryViewModel(private val repositoriSiswa: RepositoriSiswa) : ViewModel() {

    // StateFlow privat yang bisa diubah
    private val _uiStateSiswa = MutableStateFlow(UIStateSiswa())
    // StateFlow publik yang hanya bisa dibaca oleh UI
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

/**
 * Mewakili Status UI untuk Siswa
 */
data class UIStateSiswa(
    val detailSiswa: DetailSiswa = DetailSiswa(),
    val isEntryValid: Boolean = false
)

data class DetailSiswa(
    val id: Int = 0,
    val nama: String = "",
    val alamat: String = "",
    val telpon: String = ""
)

/**
 * Fungsi ekstensi untuk mengonversi data kelas [DetailSiswa] menjadi data kelas [Siswa]
 * yang dapat disimpan di database
 */
fun DetailSiswa.toSiswa(): Siswa = Siswa(
    id = id,
    nama = nama,
    alamat = alamat,
    telpon = telpon
)

fun Siswa.toUiStateSiswa(isEntryValid: Boolean = false): UIStateSiswa = UIStateSiswa(
    detailSiswa = this.toDetailSiswa(),
    isEntryValid = isEntryValid
)

/**
 * Fungsi ekstensi untuk mengubah data kelas [Siswa] menjadi data kelas [UIStateSiswa]
 */
fun Siswa.toDetailSiswa(): DetailSiswa = DetailSiswa(
    id = id,
    nama = nama,
    alamat = alamat,
    telpon = telpon
)
