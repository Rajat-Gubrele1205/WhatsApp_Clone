package com.example.assignment2.ui.view

import android.content.Intent
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.annotation.MainThread
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Divider
import androidx.compose.material3.Text
import androidx.compose.ui.res.painterResource
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.layout.ScaleFactor
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.*
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.assignment2.MainActivity
import com.example.assignment2.R
import com.example.assignment2.Screen
import com.example.assignment2.chatList


@Composable
fun ChatView(navController: NavController) {
    LazyColumn(
        modifier = Modifier.fillMaxSize()
    ) {
        val length = chatList.size
        items(length) {
            val chatName = chatList[it].chatName
            val imageID = chatList[it].imageID
            val prevMessage = chatList[it].prevMessage
            val chatID = chatList[it].chatID
            Row(
                modifier = Modifier
                    .padding(16.dp)
                    .fillMaxWidth()
                    .clickable { navController.navigate(Screen.ChatDetailScreen.withArgs(chatID.toString())) }) {
                Image(
                    painter = painterResource(imageID),
                    contentDescription = null,
                    modifier = Modifier
                        .clip(CircleShape)
                        .size(64.dp),
                    contentScale = ContentScale.Crop
                )
                Column(Modifier.padding(horizontal = 8.dp, vertical = 8.dp)) {

                    Text(
                        text = chatName,
                        fontWeight = FontWeight.SemiBold,
                        fontSize = 17.sp,
                        //color = Color.White
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    Text(
                        text = prevMessage,
                        fontWeight = FontWeight.Medium,
                        fontSize = 10.sp,
                        modifier = Modifier.padding(horizontal = 3.dp),
                        //color = Color.White
                    )

                }
            }
            //Divider(color = Color.Black)

        }
    }
}
