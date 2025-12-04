package com.example.myroomsatu.view.viewmodel.provider

import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.CreationExtras
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.example.myroomsatu.AplikasiSiswa
import com.example.myroomsatu.view.viewmodel.EntryViewModel
import com.example.myroomsatu.view.viewmodel.HomeViewModel

/**
 * Pabrik untuk menyediakan instance ViewModel dengan dependensi yang dibutuhkan.
 */
object PenyediaViewModel {
    val Factory = viewModelFactory {
        // Initializer untuk HomeViewModel
        initializer {
            HomeViewModel(aplikasiSiswa().container.repositoriSiswa)
        }

        // Initializer untuk EntryViewModel
        initializer {
            EntryViewModel(aplikasiSiswa().container.repositoriSiswa)
        }
    }
}

/**
 * Fungsi ekstensi untuk mendapatkan instance AplikasiSiswa dari CreationExtras.
 * Ini adalah cara standar untuk mengakses Application context dalam ViewModel factory.
 */
fun CreationExtras.aplikasiSiswa(): AplikasiSiswa =
    (this[ViewModelProvider.AndroidViewModelFactory.APPLICATION_KEY] as AplikasiSiswa)
