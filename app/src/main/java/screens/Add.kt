@file:OptIn(ExperimentalMaterial3Api::class, ExperimentalMaterial3Api::class,
    ExperimentalMaterial3Api::class
)

package screens

import android.annotation.SuppressLint
import android.app.Application
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.Checkbox
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
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
import com.example.nasteeysha_notes.utils.Constants.Keys.ADD_NEW_NOTE
import com.example.nasteeysha_notes.utils.Constants.Keys.ADD_NOTE
import com.example.nasteeysha_notes.utils.Constants.Keys.NOTE_SUBTITLE
import com.example.nasteeysha_notes.utils.Constants.Keys.NOTE_TITLE
import navigation.NavRoute

@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter", "SuspiciousIndentation")
@Composable
fun AddScreen(navController: NavHostController, viewModel: MainViewModel) {
    var title by remember {
        mutableStateOf("")
    }
    var subtitle by remember {
        mutableStateOf("")
    }
    var isButtonEnabled by remember {
        mutableStateOf(false)
    }

    var firstChecked by remember { mutableStateOf(false) }
    var secondChecked by remember { mutableStateOf(false) }
    var thirdChecked by remember { mutableStateOf(false) }

    Scaffold(

    ) {
        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Text(
                text = ADD_NEW_NOTE,
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold,
                modifier=Modifier.padding(vertical = 8.dp)
            )

            OutlinedTextField(value = title, onValueChange = {
                title = it
                isButtonEnabled = title.isNotEmpty() && subtitle.isNotEmpty()
                                                             },
                label = { Text(text = NOTE_TITLE)},
                isError = title.isEmpty()
            )
            OutlinedTextField(value = subtitle, onValueChange = {
                subtitle = it
                isButtonEnabled = title.isNotEmpty() && subtitle.isNotEmpty()
                                                                },
                label = { Text(text = NOTE_SUBTITLE)},
                        isError = subtitle.isEmpty()
            )
            Row{
                Checkbox(
                    checked = firstChecked,
                    onCheckedChange = { firstChecked = it
                                      if(it) {
                                          secondChecked = false
                                          thirdChecked = false
                                      }},
                    modifier = Modifier
                        .padding(8.dp)
                        .size(7.dp)

                )
                Text("More important", fontSize = 17.sp, )
                Checkbox(
                    checked = secondChecked,
                    onCheckedChange = {
                        secondChecked = it
                                      if(it) {
                                          firstChecked = false
                                          thirdChecked = false
                                      }},
                    modifier = Modifier
                        .padding(8.dp)
                        .size(7.dp)

                )
                Text("Important", fontSize = 17.sp, )
                Checkbox(
                    checked = thirdChecked,
                    onCheckedChange = {
                        thirdChecked = it
                                      if(it) {
                                          firstChecked = false
                                          secondChecked = false
                                      }},
                    modifier = Modifier
                        .padding(8.dp)
                        .size(7.dp)

                )
                Text("Unimportant", fontSize = 17.sp, )
            }


            Button(
                modifier= Modifier.padding(top = 16.dp),
                enabled = isButtonEnabled,
                onClick = {
                    viewModel.addNote(note = Note(title = title, subtitle = subtitle)) {
                        navController.navigate(NavRoute.Main.route)
                    }

                }) {
                Text(text = ADD_NOTE)
            }

        }

    }
}

@Composable
@Preview(showBackground = true)
fun PreviewAddScreen() {
    Nasteeysha_NotesTheme {
        val context = LocalContext.current
        val mViewModel: MainViewModel =
            viewModel(factory = MainViewModelFactory(context.applicationContext as Application) )
        AddScreen(navController = rememberNavController(), viewModel = mViewModel)
    }
}