package com.example.myroomsatu.repositori

import com.example.myroomsatu.room.Siswa
import com.example.myroomsatu.room.SiswaDao
import kotlinx.coroutines.flow.Flow

/**
 * Interface untuk mendefinisikan operasi data pada Siswa.
 */
interface RepositoriSiswa {
    fun getAllSiswaStream(): Flow<List<Siswa>>

    fun getSiswaStream(id: Int): Flow<Siswa?>

    suspend fun insertSiswa(siswa: Siswa)

    suspend fun deleteSiswa(siswa: Siswa)

    suspend fun updateSiswa(siswa: Siswa)
}

/**
 * Implementasi Offline dari RepositoriSiswa yang berinteraksi dengan SiswaDao.
 */
class OfflineRepositoriSiswa(private val siswaDao: SiswaDao) : RepositoriSiswa {

    override fun getAllSiswaStream(): Flow<List<Siswa>> = siswaDao.getAllSiswa()

    override fun getSiswaStream(id: Int): Flow<Siswa?> = siswaDao.getSiswa(id)

    override suspend fun insertSiswa(siswa: Siswa) = siswaDao.insert(siswa)

    override suspend fun deleteSiswa(siswa: Siswa) = siswaDao.delete(siswa)

    override suspend fun updateSiswa(siswa: Siswa) = siswaDao.update(siswa)
}