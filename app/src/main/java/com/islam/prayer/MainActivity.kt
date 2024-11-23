package com.islam.prayer

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.azan.Azan
import com.azan.Method
import com.azan.astrologicalCalc.Location
import com.azan.astrologicalCalc.SimpleDate
import com.islam.prayer.ui.theme.PrayerTheme
import java.util.GregorianCalendar

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            PrayerTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    Greeting(
                        name = "Android",
                        modifier = Modifier.padding(innerPadding)
                    )
                    azan()
                }
            }
        }
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}
fun azan() {
    val today = SimpleDate(GregorianCalendar())
    val location = Location(-96.9015296, 33.2136448, 2.0, 0)
    val azan = Azan(location, Method.EGYPT_SURVEY)
    val prayerTimes = azan.getPrayerTimes(today)
    val imsaak = azan.getImsaak(today)

    Log.d("alaa","----------------results------------------------")

    Log.d("alaa","date ---> " + today.day + " / " + today.month + " / " + today.year)
    Log.d("alaa","imsaak ---> $imsaak")
    Log.d("alaa","Fajr ---> " + prayerTimes.fajr())
    Log.d("alaa","sunrise --->" + prayerTimes.shuruq())
    Log.d("alaa","Zuhr --->" + prayerTimes.thuhr())
    Log.d("alaa","Asr --->" + prayerTimes.assr())

    Log.d("alaa","Maghrib --->" + prayerTimes.maghrib())
    Log.d("alaa","ISHA  --->" + prayerTimes.ishaa())
}
