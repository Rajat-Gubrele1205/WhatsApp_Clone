package com.example.assignment2

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

enum class HomeTab {
    Chats,
    Status,
    Calls
}
private var chatIDGenerator = 6
data class ChatInfo(val chatName:String,val imageID:Int,val chatID:Int,val time:String)


val chatList = mutableListOf(
    ChatInfo(
        "Ved Vitthal",
        R.drawable.joker,
        0,
        "12:23 PM"
    ),ChatInfo(
        "Himanshu",
        R.drawable.joker,
        1,
        "10:55 AM"
    ),ChatInfo(
        "Vishal",
        R.drawable.joker,
        2,
        "12:33 AM"
    ),ChatInfo(
        "Himanshu Patidar",
        R.drawable.joker,
        3,
        "Yesterday"
    ),ChatInfo(
        "Piyush Tiwari",
        R.drawable.tyrion,
        4,
        "Yesterday"
    ),ChatInfo(
        "Pranjal",
        R.drawable.tyrion,
        5,
        "13/08/23"
    ),ChatInfo(
        "Prerak",
        R.drawable.tyrion,
        6,
        "12/08/23"
    ),
)

data class Chats(val message:String,val isUser:Boolean = false)

val list0 = listOf(
    Chats(
        "Kaisa Hai",
        false
    ),Chats(
        "Accha hu",
        true
    ),Chats(
        "Kaha hai",
        false
    ),Chats(
        "Ghar pe",
        true
    )
)
val list1 = listOf(
    Chats(
        "Hi",
        false
    ),Chats(
        "Hi",
        true
    ),Chats(
        "Kaha hai",
        false
    ),Chats(
        "Ghar pe",
        true
    )
)
val list2 = listOf(
    Chats(
        "Hey",
        false
    ),Chats(
        "Hey",
        true
    ),Chats(
        "Kaha hai",
        false
    ),Chats(
        "Ghar pe",
        true
    )
)
val list3 = listOf(
    Chats(
        "Hello",
        false
    ),Chats(
        "Hello",
        true
    ),Chats(
        "Kaha hai",
        false
    ),Chats(
        "Ghar pe",
        true
    )
)
val list4 = listOf(
    Chats(
        "Kaha pr hai",
        false
    ),Chats(
        "Office",
        true
    ),Chats(
        "Kab tak aayega",
        false
    ),Chats(
        "Kyu Aayu",
        true
    ) ,Chats(
        "Please Bhai aaja",
        false
    ),Chats(
        "Nikal Laude",
        true
    ),Chats(
        "Bhai Please Bhai",
        false
    ),Chats(
        "Chal theek hai tu bhi kya yaad karega",
        true
    )
)
val list:List<List<Chats>> = listOf(list0, list1, list2, list3, list4)
val chats: MutableMap<Int,List<Chats>> = mutableMapOf()

fun setChat(){
    var counter = 0
    list.forEach {
        chats[counter] = it
        counter++
    }
}

