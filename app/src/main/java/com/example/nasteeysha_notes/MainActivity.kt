package com.example.nasteeysha_notes

import android.annotation.SuppressLint
import android.app.Application
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontFamily
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.nasteeysha_notes.ui.theme.Nasteeysha_NotesTheme
import navigation.NotesNavHost

class MainActivity : ComponentActivity() {
    @SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Nasteeysha_NotesTheme {
                val context = LocalContext.current
                val mViewModel: MainViewModel =
                    viewModel(factory = MainViewModelFactory(context.applicationContext as Application) )

                Scaffold(
                 topBar = {
                     TopAppBar(title = { Text(text = "NASTEEYSHA NOTES APP", fontFamily = FontFamily.Serif)
                     },
                         colors = TopAppBarDefaults.smallTopAppBarColors(
                             Color.LightGray
                         )
                     )
                 },
                 content = {
                     Surface (
                         modifier = Modifier.fillMaxSize(),
                         color = MaterialTheme.colorScheme.background
                     ) {
                         NotesNavHost(mViewModel)
                     }


                 }
             )
            }
        }
    }
}


//@Preview(showBackground = true)
//@Composable
//fun GreetingPreview() {
//    Nasteeysha_NotesTheme {
//    }
//}