package com.example.assignment2.ui.view


import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage


import com.example.assignment2.network.StatusApi
import com.example.assignment2.statusBar.NetworkState
import com.example.assignment2.statusBar.StatusViewModel
import kotlinx.coroutines.flow.collect

@Composable
fun StatusView(statusViewModel: StatusViewModel) {

    val allProducts = statusViewModel.allProducts.collectAsState(initial = emptyList()).value



    LazyColumn(
        modifier = Modifier.fillMaxSize()
    ) {
        item { Spacer(modifier = Modifier.height(10.dp)) }
        items(allProducts) {

            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(start = 10.dp)
                    .fillMaxWidth()
            ) {
                AsyncImage(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(start = 15.dp, end = 15.dp),
                    model = it.imageURL,
                    contentDescription = "Image",
                    contentScale = ContentScale.FillWidth
                    )
                Spacer(modifier = Modifier.height(20.dp))
                Text(text = it.title, fontSize = 20.sp, fontWeight = FontWeight.SemiBold)
                Spacer(modifier = Modifier.height(20.dp))
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(end = 15.dp),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(text = "$" + it.price, fontWeight = FontWeight.SemiBold)
                    Button(
                        onClick = {}, colors = ButtonDefaults.buttonColors(
                            containerColor = Color(0xFF018786)
                        )
                    ) {
                        Text(text = "Add")
                    }
                }
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(end = 15.dp),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(text = it.description, modifier = Modifier.width(200.dp))
                    Button(
                        onClick = {}, colors = ButtonDefaults.buttonColors(
                            containerColor = Color(0xFF018786)
                        )
                    ) {
                        Text(text = "Subscribe")
                    }
                }
                Spacer(modifier = Modifier.height(10.dp))
                Box(modifier = Modifier
                    .width(300.dp)
                    .height(50.dp)
                    .padding(start = 15.dp)
                    .background(Color(0xFFDFE4E3), shape = RoundedCornerShape(10.dp)), contentAlignment = Alignment.Center) {
                    Text(text = it.shippingInfo)
                }
                Spacer(modifier = Modifier.height(20.dp))
                Divider(color = Color(0xFF018786))
                Spacer(modifier = Modifier.height(20.dp))

            }
        }

    }

}