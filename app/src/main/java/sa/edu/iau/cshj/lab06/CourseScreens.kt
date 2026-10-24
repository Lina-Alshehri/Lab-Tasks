package sa.edu.iau.cshj.lab06.ui

import android.content.Intent
import android.net.Uri
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument

// ---------- Home ----------
@Composable
fun HomeScreen(
    onWebsiteClick: () -> Unit,
    onCallClick: () -> Unit,
    onCoursesClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier.fillMaxSize().padding(24.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        Text("CSC Courses", style = MaterialTheme.typography.headlineMedium)
        Button(onClick = onWebsiteClick) { Text("University website") }
        Button(onClick = onCallClick) { Text("Call the department") }
        Button(onClick = onCoursesClick) { Text("Browse courses") }
    }
}

@Composable
fun HomeRoute(
    onCoursesClick: () -> Unit
) {
    val context = LocalContext.current
    HomeScreen(
        onWebsiteClick = {
            val intent = Intent(Intent.ACTION_VIEW, Uri.parse("https://www.iau.edu.sa"))
            context.startActivity(intent)
        },
        onCallClick = {
            val intent = Intent(Intent.ACTION_DIAL, Uri.parse("tel:+966133330000"))
            context.startActivity(intent)
        },
        onCoursesClick = onCoursesClick
    )
}

// ---------- Course list ----------
data class CourseListUiState(
    val courses: List<String> = emptyList()
)

@Composable
fun CourseListScreen(
    uiState: CourseListUiState,
    onCourseClick: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier.fillMaxSize().padding(24.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        Text("Courses", style = MaterialTheme.typography.headlineMedium)
        uiState.courses.forEach { code ->
            Button(onClick = { onCourseClick(code) }) { Text(code) }
        }
    }
}

@Composable
fun CourseListRoute(onCourseClick: (String) -> Unit) {
    CourseListScreen(
        uiState = CourseListUiState(listOf("CSC 402", "CSC 231", "CSC 311")),
        onCourseClick = onCourseClick
    )
}

// ---------- Course detail ----------
data class CourseDetailUiState(
    val code: String = ""
)

@Composable
fun CourseDetailScreen(
    uiState: CourseDetailUiState,
    onShareClick: () -> Unit,
    onBackClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier.fillMaxSize().padding(24.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        Text(uiState.code, style = MaterialTheme.typography.headlineMedium)
        Button(onClick = onShareClick) { Text("Share") }
        OutlinedButton(onClick = onBackClick) { Text("Back") }
    }
}

@Composable
fun CourseDetailRoute(code: String, onBackClick: () -> Unit) {
    val context = LocalContext.current
    CourseDetailScreen(
        uiState = CourseDetailUiState(code = code),
        onShareClick = {
            val sendIntent = Intent().apply {
                action = Intent.ACTION_SEND
                putExtra(Intent.EXTRA_TEXT, "Check out this course: $code")
                type = "text/plain"
            }
            val shareIntent = Intent.createChooser(sendIntent, null)
            context.startActivity(shareIntent)
        },
        onBackClick = onBackClick
    )
}

// ---------- Lab 6: Navigation Graph ----------
@Composable
fun CourseApp() {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = "home") {
        composable("home") {
            HomeRoute(
                onCoursesClick = { navController.navigate("course_list") }
            )
        }
        composable("course_list") {
            CourseListRoute(
                onCourseClick = { code ->
                    navController.navigate("course_detail/$code")
                }
            )
        }
        composable(
            route = "course_detail/{code}",
            arguments = listOf(navArgument("code") { type = NavType.StringType })
        ) { backStackEntry ->
            val code = backStackEntry.arguments?.getString("code") ?: ""
            CourseDetailRoute(
                code = code,
                onBackClick = { navController.popBackStack() }
            )
        }
    }
}

