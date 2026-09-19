package edu.iau.cshj.csc402.lab2

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import edu.iau.cshj.csc402.lab2.ui.theme.*

data class TeamTask(
    val id: Int,
    val title: String,
    val owner: String,
    val isDone: Boolean = false
)

@Composable
fun TaskRow(
    task: TeamTask,
    onToggle: () -> Unit
) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        colors = CardDefaults.cardColors(containerColor = Color.White)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(12.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Checkbox(
                checked = task.isDone,
                onCheckedChange = { onToggle() },
                colors = CheckboxDefaults.colors(checkedColor = AndroidGreen)
            )

            Spacer(modifier = Modifier.width(8.dp))

            Column {
                Text(
                    text = task.title,
                    fontWeight = FontWeight.Bold,
                    fontSize = 16.sp,
                    color = if (task.isDone) Color.Gray else Navy,
                    textDecoration = if (task.isDone) TextDecoration.LineThrough else TextDecoration.None
                )
                Text(
                    text = "Owner: ${task.owner}",
                    fontSize = 12.sp,
                    color = Color.Gray
                )
            }
        }
    }
}

@Composable
fun TaskTrackerScreen(modifier: Modifier = Modifier) {
    val tasks = remember {
        mutableStateListOf(
            TeamTask(1, "Setup Repository", "Sara", isDone = true),
            TeamTask(2, "Design Wireframes", "Lina", isDone = true),
            TeamTask(3, "Implement Auth", "Ahmad", isDone = false),
            TeamTask(4, "Write Unit Tests", "Lina", isDone = false)
        )
    }

    val doneCount = tasks.count { it.isDone }
    val openCount = tasks.count { !it.isDone }
    val totalCount = tasks.size

    Column(
        modifier = modifier
            .fillMaxSize()
            .background(ScreenBackground)
    ) {
        // Navy Header with Integrated Summary Strip
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
                    text = "Task 11 - Team Task Tracker",
                    color = Color.LightGray,
                    fontSize = 14.sp
                )

                Spacer(modifier = Modifier.height(16.dp))

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceEvenly
                ) {
                    Column(horizontalAlignment = Alignment.CenterHorizontally) {
                        Text(
                            text = "$doneCount",
                            color = AndroidGreen,
                            fontSize = 22.sp,
                            fontWeight = FontWeight.Bold
                        )
                        Text(
                            text = "Done",
                            color = Color.LightGray,
                            fontSize = 12.sp
                        )
                    }
                    Column(horizontalAlignment = Alignment.CenterHorizontally) {
                        Text(
                            text = "$openCount",
                            color = Color.White,
                            fontSize = 22.sp,
                            fontWeight = FontWeight.Bold
                        )
                        Text(
                            text = "Open",
                            color = Color.LightGray,
                            fontSize = 12.sp
                        )
                    }
                    Column(horizontalAlignment = Alignment.CenterHorizontally) {
                        Text(
                            text = "$totalCount",
                            color = Color.White,
                            fontSize = 22.sp,
                            fontWeight = FontWeight.Bold
                        )
                        Text(
                            text = "Total",
                            color = Color.LightGray,
                            fontSize = 12.sp
                        )
                    }
                }
            }
        }

        LazyColumn(
            modifier = Modifier
                .weight(1f)
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(10.dp)
        ) {
            items(tasks, key = { it.id }) { task ->
                val index = tasks.indexOf(task)
                TaskRow(
                    task = task,
                    onToggle = {
                        tasks[index] = task.copy(isDone = !task.isDone)
                    }
                )
            }
        }

        Button(
            onClick = {
                val nextId = (tasks.maxOfOrNull { it.id } ?: 0) + 1
                tasks.add(TeamTask(nextId, "New task", "Lina"))
            },
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            colors = ButtonDefaults.buttonColors(containerColor = AndroidGreen)
        ) {
            Text(
                text = "+ Add task",
                color = Navy,
                fontWeight = FontWeight.Bold,
                fontSize = 16.sp
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun TaskTrackerScreenPreview() {
    TaskTrackerScreen()
}
