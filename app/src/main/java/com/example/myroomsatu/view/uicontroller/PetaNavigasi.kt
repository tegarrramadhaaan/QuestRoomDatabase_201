package com.example.myroomsatu.uicontroller

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.myroomsatu.view.DetailSiswaScreen
import com.example.myroomsatu.view.EntrySiswaScreen
import com.example.myroomsatu.view.HomeScreen
import com.example.myroomsatu.view.route.DestinasiDetailSiswa
import com.example.myroomsatu.view.route.DestinasiEntry
import com.example.myroomsatu.view.route.DestinasiHome

@Composable
fun SiswaApp(navController: NavHostController = rememberNavController(), modifier: Modifier = Modifier) {
    HostNavigasi(navController = navController, modifier = modifier)
}

@Composable
fun HostNavigasi(
    navController: NavHostController,
    modifier: Modifier = Modifier
) {
    NavHost(
        navController = navController,
        startDestination = DestinasiHome.route,
        modifier = modifier
    ) {
        composable(route = DestinasiHome.route) {
            HomeScreen(
                navigateToItemEntry = { navController.navigate(route = DestinasiEntry.route) },
                onItemClick = { itemId ->
                    navController.navigate("${DestinasiDetailSiswa.route}/$itemId")
                }
            )
        }
        composable(route = DestinasiEntry.route) {
            EntrySiswaScreen(navigateBack = { navController.popBackStack() })
        }
        composable(
            route = DestinasiDetailSiswa.routeWithArgs,
            arguments = DestinasiDetailSiswa.arguments
        ) {
            DetailSiswaScreen(
                navigateBack = { navController.popBackStack() },
                navigateToEditItem = {}
            )
        }
    }
}
