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
    CHATS,
    STATUS,
    CALLS
}

data class ChatInfo(val chatName:String,val imageID:Int,val prevMessage:String = "",val chatID:Int)

val chatList = listOf(
    ChatInfo(
        "Ved Vitthal",
        R.drawable.joker,
        "Kaha hai",
        0
    ),ChatInfo(
        "Himanshu",
        R.drawable.joker,
        "Saturday Chill",
        1
    ),ChatInfo(
        "Vishal",
        R.drawable.joker,
        "Hardcoder",
        2
    ),ChatInfo(
        "Himanshu Patidar",
        R.drawable.joker,
        "Ethical Hacker",
        3
    ),ChatInfo(
        "Piyush Tiwari",
        R.drawable.tyrion,
        "Gm",
        4
    ),ChatInfo(
        "Pranjal",
        R.drawable.tyrion,
        "Gm",
        5
    )
)

data class Chats(val message:String?=null,val isUser:Boolean = false)

val list0 = mutableListOf(
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
val list1 = mutableListOf(
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
val list2 = mutableListOf(
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
val list3 = mutableListOf(
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
val list4 = mutableListOf(
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
val list:MutableList<MutableList<Chats>> = mutableListOf(list0, list1, list2, list3, list4)
var chats: MutableMap<Int,List<Chats>> = mutableMapOf()

fun setChat(){
    var counter = 0
    list.forEach {
        chats[counter] = it
        counter++
    }
}

@Composable
fun MyMessages(message: String) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
        ,horizontalArrangement = Arrangement.End
    ) {
        Column(
            Modifier
                .padding(horizontal = 8.dp)
                .clip(
                    RoundedCornerShape(6.dp)
                )

        ) {

            Text(
                text = message,
                fontWeight = FontWeight.Medium,
                fontSize = 10.sp,
                modifier = Modifier.padding(horizontal = 4.dp),
            )

        }
    }
}

@Composable
fun SenderMessage(chatName: String?,imageID: Int,message: String) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
    ) {
        Image(
            painter = painterResource(id = imageID.toInt()),
            contentDescription = null,
            modifier = Modifier
                .clip(CircleShape)
                .size(20.dp),
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
                    fontSize = 8.sp,
                    //                            color = Color.White
                )
            }
            Spacer(modifier = Modifier.height(2.dp))
            Text(
                text = message,
                fontWeight = FontWeight.Medium,
                fontSize = 10.sp,
                modifier = Modifier.padding(horizontal = 4.dp),
//                            color = Color.White
            )

        }
    }
}
