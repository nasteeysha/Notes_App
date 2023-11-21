@file:OptIn(ExperimentalMaterial3Api::class, ExperimentalMaterial3Api::class)

package screens

import android.annotation.SuppressLint
import android.app.Application
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.nasteeysha_notes.MainViewModel
import com.example.nasteeysha_notes.MainViewModelFactory
import com.example.nasteeysha_notes.ui.theme.Nasteeysha_NotesTheme
import com.example.nasteeysha_notes.utils.Constants.Keys.ROOM_DATABASE
import com.example.nasteeysha_notes.utils.Constants.Keys.WHAT_BD
import com.example.nasteeysha_notes.utils.TYPE_ROOM
import navigation.NavRoute

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun StartScreen(navController: NavHostController, viewModel: MainViewModel) {
    val context = LocalContext.current
    val mViewModel: MainViewModel =
        viewModel(factory = MainViewModelFactory(context.applicationContext as Application) )
    Scaffold(modifier = Modifier.fillMaxSize())
    {
        Column(modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center) {
            Text(text = WHAT_BD)
            Button(onClick = {
                mViewModel.initDatabase(TYPE_ROOM) {
                    navController.navigate(route = NavRoute.Main.route)

                }

                             },
                modifier = Modifier
                    .width(200.dp)
                    .padding(vertical = 8.dp)
            ) {
                Text(text = ROOM_DATABASE)
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PrevStartScreen() {
    Nasteeysha_NotesTheme {
        val context = LocalContext.current
        val mViewModel: MainViewModel =
            viewModel(factory = MainViewModelFactory(context.applicationContext as Application) )
        StartScreen(navController = rememberNavController(), viewModel = mViewModel)
    }
}