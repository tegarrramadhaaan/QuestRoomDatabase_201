package com.example.myroomsatu.view.route

import androidx.navigation.NavType
import androidx.navigation.navArgument
import com.example.myroomsatu.R

object DestinasiDetailSiswa : DestinasiNavigasi {
    override val route = "detail_siswa"
    override val titleRes = R.string.detail_siswa
    const val itemIdArg = "itemId"
    val routeWithArgs = "$route/{$itemIdArg}"
    val arguments = listOf(navArgument(itemIdArg) { type = NavType.IntType })
}
