package edu.iau.cshj.csc402.lab2

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import edu.iau.cshj.csc402.lab2.ui.theme.*

@Composable
fun GreetingCard(modifier: Modifier = Modifier) {
    var inputName by remember { mutableStateOf("") }
    var submittedName by remember { mutableStateOf("") }

    Column(
        modifier = modifier
            .fillMaxSize()
            .background(ScreenBackground)
    ) {
        // Top Bar
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .background(Navy)
                .padding(20.dp)
        ) {
            Column {
                Text(
                    text = "CSC 402 Lab 2",
                    color = Color.White,
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold
                )
                Text(
                    text = "Task 9 - Text Input & Card",
                    color = Color.LightGray,
                    fontSize = 14.sp
                )
            }
        }

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            OutlinedTextField(
                value = inputName,
                onValueChange = { inputName = it },
                label = { Text("Your name") },
                singleLine = true,
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(modifier = Modifier.height(12.dp))

            Button(
                onClick = { submittedName = inputName.trim() },
                enabled = inputName.isNotBlank(),
                modifier = Modifier.fillMaxWidth(),
                colors = ButtonDefaults.buttonColors(containerColor = Navy)
            ) {
                Text("Show greeting")
            }

            Spacer(modifier = Modifier.height(20.dp))

            Card(
                modifier = Modifier.fillMaxWidth(),
                colors = CardDefaults.cardColors(containerColor = Color.White)
            ) {
                Column(
                    modifier = Modifier.padding(20.dp),
                    horizontalAlignment = Alignment.Start
                ) {
                    if (submittedName.isEmpty()) {
                        Text(
                            text = "Enter your name above and tap the button.",
                            color = Color.Gray,
                            fontSize = 14.sp
                        )
                    } else {
                        Text(
                            text = "Marhaba, $submittedName!",
                            fontSize = 22.sp,
                            fontWeight = FontWeight.Bold,
                            color = Navy
                        )
                        Spacer(modifier = Modifier.height(6.dp))
                        Text(
                            text = "Your name has ${submittedName.length} letters.",
                            color = Color.DarkGray,
                            fontSize = 14.sp
                        )
                        Spacer(modifier = Modifier.height(12.dp))
                        Surface(
                            color = AndroidGreen,
                            shape = RoundedCornerShape(12.dp)
                        ) {
                            Text(
                                text = "CSC 402 - Lab 2",
                                modifier = Modifier.padding(horizontal = 10.dp, vertical = 4.dp),
                                color = Navy,
                                fontSize = 12.sp,
                                fontWeight = FontWeight.Bold
                            )
                        }
                    }
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingCardPreview() {
    GreetingCard()
}
