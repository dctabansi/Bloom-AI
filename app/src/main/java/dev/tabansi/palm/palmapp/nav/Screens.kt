package dev.tabansi.palm.palmapp.nav

sealed class Screens(val route: String) {
    data object Home : Screens(route = "home")
    data object Chat : Screens(route = "chat")
    data object Settings : Screens(route = "settings")

    companion object {
        fun fromRoute(route: String?): Screens {
            return when (route) {
                Chat.route -> Chat
                Settings.route -> Settings
                else -> Home
            }
        }
    }
}