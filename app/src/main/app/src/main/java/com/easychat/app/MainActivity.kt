package com.easychat.app

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            EasyChatApp()
        }
    }
}

@Composable
fun EasyChatApp() {

    var selectedTab by remember { mutableIntStateOf(0) }

    Scaffold(
        bottomBar = {
            NavigationBar {

                NavigationBarItem(
                    selected = selectedTab == 0,
                    onClick = { selectedTab = 0 },
                    icon = { Text("🏠") },
                    label = { Text("Home") }
                )

                NavigationBarItem(
                    selected = selectedTab == 1,
                    onClick = { selectedTab = 1 },
                    icon = { Text("💬") },
                    label = { Text("Chat") }
                )

                NavigationBarItem(
                    selected = selectedTab == 2,
                    onClick = { selectedTab = 2 },
                    icon = { Text("🎮") },
                    label = { Text("Games") }
                )

                NavigationBarItem(
                    selected = selectedTab == 3,
                    onClick = { selectedTab = 3 },
                    icon = { Text("👤") },
                    label = { Text("Profile") }
                )
            }
        }
    ) { padding ->

        when (selectedTab) {

            0 -> HomeScreen(Modifier.padding(padding))

            1 -> ChatScreen(Modifier.padding(padding))

            2 -> GamesScreen(Modifier.padding(padding))

            3 -> ProfileScreen(Modifier.padding(padding))
        }
    }
}

@Composable
fun HomeScreen(modifier: Modifier = Modifier) {

    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(20.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Spacer(Modifier.height(40.dp))

        Text(
            text = "EASY CHAT",
            fontSize = 32.sp,
            fontWeight = FontWeight.Bold
        )

        Spacer(Modifier.height(12.dp))

        Text(
            text = "Chat • Games • Coins",
            fontSize = 18.sp
        )

        Spacer(Modifier.height(30.dp))

        Card(
            modifier = Modifier.fillMaxWidth()
        ) {
            Column(
                modifier = Modifier.padding(20.dp)
            ) {

                Text(
                    "🪙 Coin Balance",
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold
                )

                Spacer(Modifier.height(8.dp))

                Text(
                    "1,000 Coins",
                    fontSize = 28.sp
                )
            }
        }

        Spacer(Modifier.height(20.dp))

        Text(
            "Welcome to Easy Chat!",
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold
        )
    }
}

@Composable
fun ChatScreen(modifier: Modifier = Modifier) {

    var message by remember { mutableStateOf("") }

    var messages by remember {
        mutableStateOf(
            listOf(
                "Welcome to Easy Chat 👋"
            )
        )
    }

    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {

        Text(
            "Chat Room",
            fontSize = 26.sp,
            fontWeight = FontWeight.Bold
        )

        Spacer(Modifier.height(12.dp))

        LazyColumn(
            modifier = Modifier.weight(1f)
        ) {

            items(messages) { msg ->

                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 4.dp)
                ) {
                    Text(
                        msg,
                        modifier = Modifier.padding(12.dp)
                    )
                }
            }
        }

        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {

            OutlinedTextField(
                value = message,
                onValueChange = { message = it },
                modifier = Modifier.weight(1f),
                placeholder = {
                    Text("Type message...")
                }
            )

            Spacer(Modifier.width(8.dp))

            Button(
                onClick = {
                    if (message.isNotBlank()) {
                        messages = messages + message
                        message = ""
                    }
                }
            ) {
                Text("Send")
            }
        }
    }
}

@Composable
fun GamesScreen(modifier: Modifier = Modifier) {

    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(20.dp)
    ) {

        Text(
            "Games",
            fontSize = 28.sp,
            fontWeight = FontWeight.Bold
        )

        Spacer(Modifier.height(20.dp))

        GameButton("7️⃣", "Greedy 777")
        GameButton("🎰", "Slot")
        GameButton("🎲", "Dice")
        GameButton("🎡", "Lucky Spin")
    }
}

@Composable
fun GameButton(icon: String, name: String) {

    Button(
        onClick = { },
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 6.dp)
            .height(60.dp)
    ) {

        Text(
            "$icon  $name",
            fontSize = 18.sp
        )
    }
}

@Composable
fun ProfileScreen(modifier: Modifier = Modifier) {

    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(20.dp)
    ) {

        Text(
            "Profile",
            fontSize = 28.sp,
            fontWeight = FontWeight.Bold
        )

        Spacer(Modifier.height(20.dp))

        Text("👤 Easy Chat User", fontSize = 20.sp)

        Spacer(Modifier.height(12.dp))

        Text("🪙 Coins: 1,000", fontSize = 18.sp)

        Spacer(Modifier.height(20.dp))

        Button(
            onClick = { },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Login with OTP")
        }
    }
}
