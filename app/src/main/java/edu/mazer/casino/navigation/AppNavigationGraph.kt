package edu.mazer.casino.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import edu.mazer.casino.ui.screens.rulescreen.RuleScreen

@Composable
fun AppNavigationGraph(
    navController: NavHostController
){

    NavHost(
        navController = navController,
        startDestination = Screens.Main.route
    ){

        composable(
            route = Screens.Main.route
        ){
            RuleScreen()
        }

    }

}