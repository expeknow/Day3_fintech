package com.expeknow.day3_fintech.ui.windows


import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import com.expeknow.day3_fintech.R
import kotlinx.coroutines.launch


private const val Url = "material/BottomSheetScaffoldScreen.kt"

@Composable
fun BottomSheetScaffoldScreen() {
    DefaultScaffold(
        title = "MaterialNavRoutes.BottomSheetScaffold",
    ) {
        BottomSheetScaffoldExample()
    }
}

@OptIn(ExperimentalMaterialApi::class, ExperimentalMaterial3Api::class)
@Composable
fun BottomSheetScaffoldExample() {
    val context = LocalContext.current
    val scope = rememberCoroutineScope()
    val scaffoldState = rememberBottomSheetScaffoldState()
    BottomSheetScaffold(
        sheetContent = {
            Box(
                Modifier
                    .fillMaxWidth()
                    .height(128.dp)
                    .background(Color.Blue),
                contentAlignment = Alignment.Center,
            ) {
                Text("text")
            }
            Column(
                Modifier
                    .fillMaxWidth()
                    .padding(64.dp)
                    .background(Color.Red),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text("Text")
                Spacer(Modifier.height(20.dp))
                Button(
                    onClick = {
                        scope.launch { scaffoldState.bottomSheetState.collapse() }
                    }
                ) {
                    Text("Text")
                }
            }
        },
        scaffoldState = scaffoldState,
        topBar = {
            TopAppBar(
                title = { Text("Text") },
                navigationIcon = {
                    IconButton(onClick = { scope.launch { scaffoldState.drawerState.open() } }) {
                        Icon(Icons.Filled.Menu, contentDescription = null)
                    }
                }
            )
        },
        floatingActionButton = {
            FloatingActionButton(
                onClick = {
                    scope.launch {
                        scaffoldState.snackbarHostState.showSnackbar(
                            "a string"
                        )
                    }
                }
            ) {
                Icon(Icons.Filled.Favorite, contentDescription = null)
            }
        },
        floatingActionButtonPosition = androidx.compose.material.FabPosition.End,
        sheetPeekHeight = 128.dp,
        drawerContent = {
            Column(
                Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(context.getString(R.string.app_name))
                Spacer(Modifier.height(20.dp))
                Button(onClick = { scope.launch { scaffoldState.drawerState.close() } }) {
                    Text("click to close")
                }
            }
        }
    ) { innerPadding ->
        LazyColumn(contentPadding = innerPadding) {
            items(100) {
                Box(
                    Modifier
                        .fillMaxWidth()
                        .height(50.dp)
                        .background(Color(com.expeknow.day3_fintech.ui.theme.colors
                                [it % com.expeknow.day3_fintech.ui.theme.colors.size]))
                )
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DefaultScaffold(
    title: String,
    content: @Composable (PaddingValues) -> Unit,
) {
    DefaultScaffold(
        topBar = {
            TopAppBar(
                title = { Text(text = "Top Bar")},
                navigationIcon = { Icon(imageVector = Icons.Default.Menu, contentDescription = "")}
            )
        },
        content = content
    )
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DefaultScaffold(
    topBar: @Composable () -> Unit = {},
    content: @Composable (PaddingValues) -> Unit,
) {

//    val context = LocalContext.current
//    val inAppReviewer = LocalInAppReviewer.current
    Scaffold(
        modifier = Modifier.systemBarsPadding(),
//        topBar = topBar,
        content = content,
    )
//    LaunchedEffect(Unit) {
//        if (context is Activity) inAppReviewer.requestReview(context)
//    }
}


