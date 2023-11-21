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
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.Checkbox
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
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
import com.example.nasteeysha_notes.bottomSheet.BottomSheetValue
import com.example.nasteeysha_notes.bottomSheet.ModalBottomSheetLayout
import com.example.nasteeysha_notes.bottomSheet.rememberBottomSheetState
import com.example.nasteeysha_notes.model.Note
import com.example.nasteeysha_notes.ui.theme.Nasteeysha_NotesTheme
import com.example.nasteeysha_notes.utils.Constants.Keys.DELETE
import com.example.nasteeysha_notes.utils.Constants.Keys.EDIT_NOTE
import com.example.nasteeysha_notes.utils.Constants.Keys.EMPTY
import com.example.nasteeysha_notes.utils.Constants.Keys.NAV_BACK
import com.example.nasteeysha_notes.utils.Constants.Keys.NONE
import com.example.nasteeysha_notes.utils.Constants.Keys.SUBTITLE
import com.example.nasteeysha_notes.utils.Constants.Keys.TITLE
import com.example.nasteeysha_notes.utils.Constants.Keys.UPDATE
import com.example.nasteeysha_notes.utils.Constants.Keys.UPDATE_NOTE
import kotlinx.coroutines.launch
import navigation.NavRoute
import java.time.LocalDate

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NoteScreen(navController: NavHostController, viewModel: MainViewModel, noteId: String?) {
    val notes = viewModel.readAllNotes().observeAsState(listOf()).value
    val note = notes.firstOrNull{it.id == noteId?.toInt()} ?: Note(title = NONE, subtitle = NONE )
    val bottomSheetState = rememberBottomSheetState(initialValue = BottomSheetValue.Hidden)
    val coroutineScope = rememberCoroutineScope()
    var title by remember {
        mutableStateOf(EMPTY)
    }
    var subtitle by remember {
        mutableStateOf(EMPTY)
    }
    val cDate = remember { mutableStateOf(LocalDate.now()) }

    var firstChecked by remember { mutableStateOf(false) }
    var secondChecked by remember { mutableStateOf(false) }
    var thirdChecked by remember { mutableStateOf(false) }

    ModalBottomSheetLayout(
        sheetState= bottomSheetState,
        sheetElevation = 5.dp,
        sheetShape = RoundedCornerShape(topStart = 30.dp, topEnd = 30.dp),
        sheetContent = {
            Surface {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(all = 32.dp)
                ) {
                    Text(
                        text = EDIT_NOTE,
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Bold,
                        modifier = Modifier.padding(vertical = 8.dp)
                        )
                    OutlinedTextField(
                        value = title,
                        onValueChange = {title = it},
                        label = { Text(text = TITLE)},
                        isError = title.isEmpty()
                    )
                    OutlinedTextField(
                        value = subtitle,
                        onValueChange = {subtitle = it},
                        label = { Text(text = SUBTITLE)},
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
                                .size(7.dp),

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
                        modifier = Modifier.padding(top = 16.dp),
                        onClick = {
                            viewModel.updateNote(note =
                            Note(id = note.id, title = title, subtitle = subtitle)
                            ) {
                                navController.navigate(NavRoute.Note.route)
                            }
                        }
                    ) {
                        Text(text = UPDATE_NOTE)

                    }
                }
            }
        }
    ){

        Scaffold (
            modifier = Modifier.fillMaxSize()
        ) {
            Column(
                modifier = Modifier.fillMaxSize(),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 28.dp)
                ) {
                    Column (
                        modifier = Modifier.padding(vertical = 8.dp),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ){
                        Text(note.title, fontSize = 24.sp, fontWeight = FontWeight.Bold,
                            modifier = Modifier.padding(top = 18.dp))

                        Text(note.subtitle, fontSize = 18.sp, fontWeight = FontWeight.Light,
                            modifier = Modifier.padding(top = 14.dp))

                        Text(
                            text = "Дата: ${cDate.value}",
                            fontSize = 17.sp,
                            color = Color.Black
                        )
                    }
                }
            }

            Row(
                modifier = Modifier
                    .padding(horizontal = 70.dp)
                    .padding(top = 680.dp)
                    .fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceAround
            ) {
                Button(onClick = {
                    coroutineScope.launch {
                        title = note.title
                        subtitle = note.subtitle
                        bottomSheetState.show()
                    }
                }) {
                    Text(text = UPDATE)

                }
                Button(
                    onClick = {
                    viewModel.deleteNote(note = note) {
                        navController.navigate(NavRoute.Main.route)
                    }
                }) {
                    Text(text = DELETE)

                }
                Button(
//                    modifier = Modifier
//                        .padding(top = 20.dp)
//                        .padding(horizontal = 32.dp)
//                        .fillMaxWidth(),
                    onClick = {
                        navController.navigate(NavRoute.Main.route)
                    }) {
                    Text(text = NAV_BACK)

                }

            }
    }
    }
}



@Preview(showBackground = true)
@Composable
fun PrevNoteScreen() {
    Nasteeysha_NotesTheme {
        val context = LocalContext.current
        val mViewModel: MainViewModel =
            viewModel(factory = MainViewModelFactory(context.applicationContext as Application) )
        NoteScreen(
            navController = rememberNavController(),
            viewModel = mViewModel,
            noteId = "1"
        )
    }
}