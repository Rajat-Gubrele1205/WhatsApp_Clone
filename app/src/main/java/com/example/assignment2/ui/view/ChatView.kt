package com.example.assignment2.ui.view

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
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
import androidx.compose.material3.Text
import androidx.compose.ui.res.painterResource
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.*
import androidx.navigation.NavController
import com.example.assignment2.Screen
import com.example.assignment2.chatList
import com.example.assignment2.chats
import com.example.assignment2.setChat


@Composable
fun ChatView(navController: NavController) {

    LazyColumn(
        modifier = Modifier.fillMaxSize()
    ) {

        val length = chatList.size
        items(length) {
            setChat()
            val size = chats[it]?.size ?: 0
            val chatName = chatList[it].chatName
            val imageID = chatList[it].imageID
            val prevMessage = chats[it]?.get(size - 1)?.message ?: ""
            val chatID = chatList[it].chatID
            val chatTime = chatList[it].time
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
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {


                        Text(
                            text = chatName,
                            fontWeight = FontWeight.SemiBold,
                            fontSize = 17.sp,
                            //color = Color.White
                        )
                        Text(text = chatTime, textAlign = TextAlign.End)
                    }
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

//@OptIn(ExperimentalMaterial3Api::class)
//@Composable
//fun AddChatDialog(
//    state: ChatState,
//    onEvent: (ChatEvent) -> Unit,
//    modifier: Modifier = Modifier
//) {
//    AlertDialog(
//        modifier = modifier,
//        onDismissRequest = { onEvent(ChatEvent.hideDialog) },
//        title = { Text(text = "Add Chat") },
//        text = {
//            Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
//                TextField(
//                    value = state.chatName,
//                    onValueChange = { onEvent(ChatEvent.SetChatName(it)) },
//                    placeholder = {
//                        Text(text = "ChatName")
//                    }
//                )
//                TextField(
//                    value = state.imageID,
//                    onValueChange = { onEvent(ChatEvent.SetChatImage(it.toInt())) },
//                    placeholder = {
//                        Text(text = "ChatName")
//                    }
//                )
//            }
//        },
//        confirmButton = {
//            TextButton(onClick = { onEvent(ChatEvent.SaveChat) }) {
//                Text(text = "Save")
//            }
//        }
//    )
//}

//@Preview(showBackground = true)
//@Composable
//fun Preview() {
//    ChatView(navController = rememberNavController())
//}