package edu.iau.cshj.csc402.lab2

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import edu.iau.cshj.csc402.lab2.ui.theme.MyApplicationTheme
import sa.edu.iau.cshj.lab05.ui.HomeRoute

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MyApplicationTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    // تشغيل الشاشة الرئيسية الخاصة بـ Lab 5
                    HomeRoute(
                        onCoursesClick = { /* يتم استخدامها عند إضافة Navigation بين الشاشات */ }
                    )
                }
            }
        }
    }
}