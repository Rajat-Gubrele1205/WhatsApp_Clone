package com.example.assignment2

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Call
import androidx.compose.material.icons.filled.Face
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Send
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.FloatingActionButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.*
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import androidx.room.Room
import com.example.assignment2.statusBar.StatusDatabase
import com.example.assignment2.statusBar.StatusViewModel
import com.example.assignment2.ui.view.CallView
import com.example.assignment2.ui.view.ChatView
import com.example.assignment2.ui.view.StatusView
import kotlinx.coroutines.launch
import kotlin.random.Random

class MainActivity : ComponentActivity() {


    private val statusdb by lazy {
        Room.databaseBuilder(
            applicationContext,
            StatusDatabase::class.java,
            "status.db"
        ).build()
    }
    private val statusViewModel by viewModels<StatusViewModel>( factoryProducer = {
        object : ViewModelProvider.Factory{
            override fun <T : ViewModel> create(modelClass: Class<T>): T {
                return StatusViewModel(statusdb.dao) as T
            }
        }
    })
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //statusViewModel.deleteAllData()
        setContent {

            Navigation(statusViewModel)
           //HomeScreen(navController = rememberNavController(),statusViewModel)
        }

    }


}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(navController: NavController,statusViewModel: StatusViewModel) {

    val snackbarHostState = remember {
        SnackbarHostState()
    }
    val scope = rememberCoroutineScope()
    Scaffold(modifier = Modifier.fillMaxSize(),
        snackbarHost = {
            SnackbarHost(snackbarHostState)
        },
        topBar = {
            TopAppBar(
                colors = TopAppBarDefaults.smallTopAppBarColors(
                    containerColor = Color(
                        0xFF018786
                    ),
                    titleContentColor = Color.White,
                    actionIconContentColor = Color.White
                ), title = { Text("WhatsApp") }, actions = {


                    IconButton(onClick = { /*TODO*/ }) {
                        Icon(imageVector = Icons.Default.Star, contentDescription = "Favourites")
                    }
                    IconButton(onClick = { /*TODO*/ }) {
                        Icon(imageVector = Icons.Default.Search, contentDescription = "Settings")
                    }
                    IconButton(onClick = { }) {
                        Icon(imageVector = Icons.Default.MoreVert, contentDescription = "Options")
                    }

                })
        }, floatingActionButton = {
            FloatingActionButton(
                onClick = {
                    scope.launch {
                        snackbarHostState.showSnackbar(
                            "Chat added",
                            withDismissAction = true,
                            actionLabel = "Dismiss"
                        )

                    }
                },
                shape = RoundedCornerShape(15.dp),
                containerColor = Color(0xFF018786),
                elevation = FloatingActionButtonDefaults.elevation(10.dp),
                contentColor = Color.White
            ) {
                Image(
                    painter = painterResource(id = R.drawable.baseline_message_24),
                    contentDescription = "Add Chat"
                )
            }
        }

    ) { values ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(values)
        ) {
            var tabSelected by remember {
                mutableStateOf(HomeTab.Chats)
            }
            HomeTabBar(tabSelected = tabSelected, onTabSelected = { tabSelected = it })
            when (tabSelected) {
                HomeTab.Chats -> ChatView(navController)
                HomeTab.Status -> StatusView(statusViewModel = statusViewModel)
                HomeTab.Calls -> CallView()
            }
        }
    }
}

@Composable
fun HomeTabBar(
    tabSelected: HomeTab, onTabSelected: (HomeTab) -> Unit
) {
    AppTabBar { tabBarModifier ->
        AppTabs(modifier = tabBarModifier,
            titles = HomeTab.values().map { it.name },
            tabSelected = tabSelected,
            onTabSelected = { newTab ->
                onTabSelected(HomeTab.values()[newTab.ordinal])
            })
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ChatDetailScreen(navController: NavController, chatID: String?) {
    setChat()
    var chatName by remember {
        mutableStateOf("")
    }
    var imageID by remember {
        mutableIntStateOf(0)
    }
    chatList.forEach {
        if (it.chatID == chatID?.toInt()) {
            chatName = it.chatName
            imageID = it.imageID

        }
    }
    Scaffold(modifier = Modifier.fillMaxSize(), topBar = {
        TopAppBar(title = {
            Column(modifier = Modifier.padding(start = 5.dp)) {
                Text(text = chatName, fontSize = 20.sp, fontWeight = FontWeight.SemiBold)
            }
        },
            colors = TopAppBarDefaults.smallTopAppBarColors(
                containerColor = Color(0xFF018786),
                titleContentColor = Color.White,
                actionIconContentColor = Color.White
            ),
            navigationIcon = {

                Row {
                    IconButton(
                        onClick = { navController.popBackStack() },
                        colors = IconButtonDefaults.iconButtonColors(contentColor = Color.White)
                    ) {
                        Icon(imageVector = Icons.Default.ArrowBack, contentDescription = "Go Back")
                    }
                    Image(
                        painter = painterResource(id = imageID),
                        contentDescription = "Image",
                        contentScale = ContentScale.Crop,
                        modifier = Modifier
                            .padding(top = 5.dp)
                            .size(40.dp)
                            .clip(
                                CircleShape
                            )
                    )

                }


            },
            actions = {
                IconButton(onClick = { /*TODO*/ }) {
                    Icon(imageVector = Icons.Default.Call, contentDescription = "Call")
                }
                IconButton(onClick = { /*TODO*/ }) {
                    Icon(imageVector = Icons.Default.MoreVert, contentDescription = "Options")
                }
            })
    }, bottomBar = {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 5.dp),
        ) {
            IconButton(onClick = {}) {
                Icon(imageVector = Icons.Default.Face, contentDescription = "Emoji")
            }
            TextField(
                value = "",
                onValueChange = {},
                shape = RoundedCornerShape(30.dp),
                modifier = Modifier.width(250.dp)
            )
            IconButton(onClick = {}) {
                Icon(
                    imageVector = Icons.Default.ShoppingCart,
                    contentDescription = "ShoppingCart"
                )
            }
            IconButton(onClick = {}) {
                Icon(imageVector = Icons.Default.Send, contentDescription = "Message Send")
            }
        }
    }

    ) { values ->
        LazyColumn(
            modifier = Modifier
                .fillMaxWidth()
                .padding(values)
        ) {
            items(1) {
                chats[chatID?.toInt()]?.forEach {
                    val isUser = it.isUser
                    val message = it.message
                    if (isUser) {
                        MyMessages(message)
                    } else {
                        SenderMessage(chatName = chatName, imageID = imageID, message = message)
                    }
                }

            }

        }

    }
}

@Composable
fun MyMessages(message: String) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp), horizontalArrangement = Arrangement.End
    ) {
        Column(
            Modifier
                .padding(horizontal = 8.dp)
                .clip(
                    RoundedCornerShape(6.dp)
                )
                .background(Color.White)

        ) {

            Text(
                text = message,
                fontWeight = FontWeight.Medium,
                fontSize = 12.sp,
                modifier = Modifier.padding(horizontal = 4.dp),
            )

        }
    }
}

@Composable
fun SenderMessage(chatName: String?, imageID: Int, message: String) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
    ) {
        Image(
            painter = painterResource(id = imageID),
            contentDescription = null,
            modifier = Modifier
                .padding(vertical = 10.dp)
                .clip(CircleShape)
                .size(30.dp),
            contentScale = ContentScale.Crop
        )
        Column(
            Modifier
                .padding(horizontal = 8.dp)
                .clip(
                    RoundedCornerShape(6.dp)
                )
//                            .background(Color.Black)

        ) {
            if (chatName != null) {
                Text(
                    text = chatName,
                    fontWeight = FontWeight.SemiBold,
                    fontSize = 17.sp,
                    color = Color(0xFF018786)
                    //                            color = Color.White
                )
            }
            Spacer(modifier = Modifier.height(2.dp))
            Text(
                text = message,
                fontWeight = FontWeight.Medium,
                fontSize = 12.sp,
                modifier = Modifier.padding(horizontal = 4.dp),
//                            color = Color.White
            )
            Spacer(modifier = Modifier.height(2.dp))


        }

    }
}

@Preview
@Composable
fun Preview() {
    ChatDetailScreen(navController = rememberNavController(), "1")
}



