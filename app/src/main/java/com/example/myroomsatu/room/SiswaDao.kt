package com.example.myroomsatu.room

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import kotlinx.coroutines.flow.Flow

@Dao
interface SiswaDao {
    @Query("SELECT * from tblsiswa ORDER BY nama ASC")
    fun getAllSiswa(): Flow<List<Siswa>>

    @Query("SELECT * from tblsiswa WHERE id = :id")
    fun getSiswa(id: Int): Flow<Siswa>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(siswa: Siswa)

    @Update
    suspend fun update(siswa: Siswa)

    @Delete
    suspend fun delete(siswa: Siswa)
}