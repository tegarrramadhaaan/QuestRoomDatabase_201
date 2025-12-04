package com.example.myroomsatu.view.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.myroomsatu.repositori.RepositoriSiswa
import com.example.myroomsatu.room.Siswa
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.filterNotNull
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn

/**
 * ViewModel untuk halaman Home, bertanggung jawab untuk mengambil dan menyajikan daftar siswa.
 */
class HomeViewModel(private val repositoriSiswa: RepositoriSiswa) : ViewModel() {

    companion object {
        private const val TIMEOUT_MILLIS = 5_000L
    }

    // StateFlow yang akan diamati oleh UI
    val homeUiState: StateFlow<HomeUiState> = repositoriSiswa.getAllSiswaStream()
        .filterNotNull()
        .map { HomeUiState(listSiswa = it.toList()) }
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(TIMEOUT_MILLIS),
            initialValue = HomeUiState()
        )
}

/**
 * Data class untuk merepresentasikan UI State dari halaman Home.
 */
data class HomeUiState(
    val listSiswa: List<Siswa> = listOf()
)
