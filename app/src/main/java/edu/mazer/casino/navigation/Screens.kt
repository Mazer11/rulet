package edu.mazer.casino.navigation

import edu.mazer.casino.utils.Constants

sealed class Screens(val route: String){

    object Main: Screens(Constants.Screens.Main)

}
