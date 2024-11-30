package com.sano4ek.sonata

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.sano4ek.sonata.ui.theme.SonataTheme
import kotlinx.coroutines.delay
import java.util.*

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            SonataTheme {
                CounterApp()
            }
        }
    }
}

@Composable
fun CounterApp() {
    // Використовуємо mutableIntStateOf для оптимізації роботи з цілочисельними даними
    var counter by remember { mutableIntStateOf(0) }

    // Таймер для скидання лічильника
    LaunchedEffect(Unit) {
        while (true) {
            val now = Calendar.getInstance(TimeZone.getTimeZone("Europe/Kiev"))
            val hours = now.get(Calendar.HOUR_OF_DAY)
            val minutes = now.get(Calendar.MINUTE)
            val seconds = now.get(Calendar.SECOND)
            if (hours ==23 && minutes == 59 && seconds == 59) {
                counter = 0
            }
            delay(1000) // Перевірка часу кожну секунду
        }
    }

    // Дизайн інтерфейсу
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xA888B5)), // Рожевий фон
        contentAlignment = Alignment.Center
    ) {
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Text(
                text = counter.toString(),
                fontSize = 48.sp,
                color = Color.Black,
                textAlign = TextAlign.Center,
                modifier = Modifier.padding(16.dp)
            )

            Row(
                horizontalArrangement = Arrangement.SpaceEvenly,
                modifier = Modifier.fillMaxWidth()
            ) {
                // Кнопка для зменшення
                Image(
                    painter = painterResource(
                        id = R.drawable.heart_minus // Використовується ресурс heart_minus
                    ),
                    contentDescription = "Minus Button",
                    modifier = Modifier
                        .size(100.dp)
                        .clickable { counter-- }
                )

                // Кнопка для збільшення
                Image(
                    painter = painterResource(
                        id = R.drawable.heart_plus // Використовується ресурс heart_plus
                    ),
                    contentDescription = "Plus Button",
                    modifier = Modifier
                        .size(100.dp)
                        .clickable { counter++ }
                )
            }
        }
    }
}
