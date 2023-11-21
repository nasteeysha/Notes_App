@file:OptIn(ExperimentalMaterial3Api::class, ExperimentalMaterial3Api::class)

package screens

import android.annotation.SuppressLint
import android.app.Application
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.nasteeysha_notes.MainViewModel
import com.example.nasteeysha_notes.MainViewModelFactory
import com.example.nasteeysha_notes.model.Note
import com.example.nasteeysha_notes.ui.theme.Nasteeysha_NotesTheme
import navigation.NavRoute

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun MainScreen(navController: NavHostController, viewModel: MainViewModel) {
    val notes = viewModel.readAllNotes().observeAsState(listOf()).value

    Scaffold(
        floatingActionButton = {
            FloatingActionButton(
                onClick = { navController.navigate(NavRoute.Add.route) }) {
                Icon(imageVector = Icons.Filled.Add, contentDescription = "Add notes", tint = Color.Black )
            }
        }
    ) {

        LazyColumn {
            items(notes) {
                note-> NoteItem(note = note, navController = navController)
            }
        }

    }
}

// добавить приоритет заметок
@Composable
fun NoteItem(note: Note, navController: NavHostController) {
    Card (
        elevation = CardDefaults.cardElevation(defaultElevation = 6.dp),
        modifier = Modifier
            .offset(y = 80.dp)
            .size(width = 500.dp, height = 100.dp)
            .padding(vertical = 12.dp, horizontal = 24.dp)
            .clickable {
                navController.navigate(NavRoute.Note.route + "/${note.id}")
            }
    ){
        Column(modifier = Modifier.padding(vertical = 12.dp), horizontalAlignment = Alignment.CenterHorizontally) {
            Text(text = note.title, fontWeight = FontWeight.Bold, fontSize = 18.sp )
            Text(text = note.subtitle)
        }
    }
}
@Composable
@Preview(showBackground = true)
fun PreviewMainScreen() {
    Nasteeysha_NotesTheme {
        val context = LocalContext.current
        val mViewModel: MainViewModel =
            viewModel(factory = MainViewModelFactory(context.applicationContext as Application) )
        MainScreen(navController = rememberNavController(), viewModel = mViewModel)
    }
}