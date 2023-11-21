package navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.nasteeysha_notes.MainViewModel
import com.example.nasteeysha_notes.utils.Constants.Keys.ID
import com.example.nasteeysha_notes.utils.Constants.Screens.ADD_SCREEN
import com.example.nasteeysha_notes.utils.Constants.Screens.MAIN_SCREEN
import com.example.nasteeysha_notes.utils.Constants.Screens.NOTE_SCREEN
import com.example.nasteeysha_notes.utils.Constants.Screens.START_SCREEN
import screens.AddScreen
import screens.MainScreen
import screens.NoteScreen
import screens.StartScreen

// создание экранов
sealed class NavRoute(val route: String) {
    object Start: NavRoute(START_SCREEN) // наследование от класса NavRoute
    object Main: NavRoute(MAIN_SCREEN)
    object Add: NavRoute(ADD_SCREEN)
    object Note: NavRoute(NOTE_SCREEN)
}

@Composable
fun NotesNavHost(mViewModel: MainViewModel) {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = NavRoute.Start.route) {
        composable(NavRoute.Start.route) { StartScreen(navController = navController, viewModel = mViewModel) }
        composable(NavRoute.Main.route) { MainScreen(navController = navController, viewModel = mViewModel) }
        composable(NavRoute.Add.route) { AddScreen(navController = navController, viewModel = mViewModel) }
        composable(NavRoute.Note.route + "/{${ID}}") {backStackEntry->
            NoteScreen(navController = navController, viewModel = mViewModel,noteId =  backStackEntry.arguments?.getString(ID)) }
    }

}