package com.islam.prayer.presentation.main.sub

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.islam.prayer.domain.model.Prayer
import com.islam.prayer.presentation.main.util.DateTimeUtil
import com.test.simpleapp.presentation.uiutil.bodyMediumStyle
import com.test.simpleapp.presentation.uiutil.primaryColor
import com.test.simpleapp.presentation.uiutil.shapeMedium
import com.test.simpleapp.presentation.uiutil.surfaceVariant
import java.time.LocalTime

@Composable
fun PrayersList(
    prayers: List<Prayer>,
    modifier: Modifier = Modifier
) {
    LazyColumn(
        modifier = modifier,
        contentPadding = PaddingValues(8.dp),
        verticalArrangement = Arrangement.Center
    ) {
        items(prayers) { prayer ->
            PrayerCard(
                prayer = prayer,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(100.dp),
            )
            Spacer(modifier = Modifier.height(12.dp))
        }
    }
}

@Composable
fun PrayerCard(
    prayer: Prayer,
    modifier: Modifier = Modifier,
) {
    val (borderWidth, borderColor) = if (prayer.prayerInfo.isCurrent) 2.dp to primaryColor() else 0.dp to surfaceVariant()
    Card(
        modifier = modifier,
        shape = shapeMedium(),
        colors = CardDefaults.cardColors(
            containerColor = surfaceVariant()
        ),
        border = BorderStroke(
            width = borderWidth,
            color = borderColor
        )
    ) {
        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            TextComponent(
                prayerName = prayer.prayerInfo.name,
                time = prayer.prayerInfo.time,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 12.dp)
            )
        }
    }
}

@Composable
fun TextComponent(
    prayerName: String,
    time: LocalTime,
    modifier: Modifier = Modifier,
) {
    val formattedTime = remember{
        DateTimeUtil.formatLocalTime(time)
    }
    Row(
        modifier = modifier,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(
            text = prayerName,
            fontWeight = FontWeight.Bold,
            style = bodyMediumStyle()
        )
        Text(
            text = formattedTime,
            style = bodyMediumStyle()
        )
    }
}