package sa.edu.iau.cshj.lab04.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

// --- Task 2: Data class ---
data class AttendanceUiState(
    val sessionTitle: String = "",
    val checkedInCount: Int = 0,
    val isCheckedIn: Boolean = false
)

// --- Task 3: Stateless Composable ---
@Composable
fun AttendanceScreen(
    uiState: AttendanceUiState,
    onCheckIn: () -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(24.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        Text(text = uiState.sessionTitle, style = MaterialTheme.typography.headlineMedium)

        Text(text = "Checked in: ${uiState.checkedInCount}", style = MaterialTheme.typography.bodyLarge)

        Button(
            onClick = onCheckIn,
            enabled = !uiState.isCheckedIn
        ) {
            Text(if (uiState.isCheckedIn) "Checked In" else "Check In")
        }
    }
}

// --- Task 4: AttendanceRoute ---
@Composable
fun AttendanceRoute() {
    var uiState by remember {
        mutableStateOf(AttendanceUiState("CSC 402 Week 4", 12))
    }

    AttendanceScreen(
        uiState = uiState,
        onCheckIn = {
            uiState = uiState.copy(
                checkedInCount = uiState.checkedInCount + 1,
                isCheckedIn = true
            )
        }
    )
}

// --- Task 3: Previews ---
@Preview(showBackground = true, name = "Not checked in yet")
@Composable
private fun AttendanceScreenPreview1() {
    MaterialTheme {
        AttendanceScreen(
            uiState = AttendanceUiState("CSC 402 Week 4", 12, false),
            onCheckIn = {}
        )
    }
}

@Preview(showBackground = true, name = "Already checked in")
@Composable
private fun AttendanceScreenPreview2() {
    MaterialTheme {
        AttendanceScreen(
            uiState = AttendanceUiState("CSC 402 Week 4", 13, true),
            onCheckIn = {}
        )
    }
}

