package com.example.vkclientcompose.ui.theme

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.MoreVert
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.vkclientcompose.R

@Composable
fun PostCard() {
    Card {
        Column(
            modifier = Modifier
                .padding(8.dp)
        ) {
            PostHeader()
            Spacer(modifier = Modifier.padding(8.dp))
            Text(text = stringResource(R.string.teplate_text))
            Spacer(modifier = Modifier.padding(8.dp))
            Image(
                modifier = Modifier.fillMaxWidth(),
                painter = painterResource(id = R.drawable.pixel),
                contentDescription = null,
                contentScale = ContentScale.FillWidth
            )
            Spacer(modifier = Modifier.padding(8.dp))
            Statistics()
        }
    }
}

@Composable
private fun Statistics() {
    Row {
        Row(
            modifier = Modifier.weight(1f)
        ) {
            IconWithText(iconResId = R.drawable.eye, text = "579")
        }
        Row(
            modifier = Modifier.weight(1f),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            IconWithText(iconResId = R.drawable.share_android, text = "15")
            IconWithText(iconResId = R.drawable.message, text = "38")
            IconWithText(iconResId = R.drawable.heart, text = "309")
        }
    }
}

@Composable
private fun IconWithText(
    iconResId: Int,
    text: String
) {
    Row(
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(
            painter = painterResource(id = iconResId),
            contentDescription = null,
            tint = MaterialTheme.colorScheme.onSecondary
        )
        Spacer(modifier = Modifier.padding(2.dp))
        Text(
            text = text,
            color = MaterialTheme.colorScheme.onSecondary
        )
    }
}

@Composable
private fun PostHeader() {
    Row(
        modifier = Modifier
            .fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Image(
            modifier = Modifier
                .size(50.dp)
                .clip(CircleShape),
            contentScale = ContentScale.Crop,
            painter = painterResource(R.drawable.w_19082024_1),
            contentDescription = null,
        )
        Spacer(modifier = Modifier.padding(8.dp))
        Column(
            modifier = Modifier.weight(1f)
        ) {
            Text(
                text = "DEV NULL",
                color = MaterialTheme.colorScheme.onPrimary,
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold,
            )
            Spacer(modifier = Modifier.padding(2.dp))
            Text(
                text = "14:00",
                color = MaterialTheme.colorScheme.onSecondary,
            )
        }
        Icon(
            imageVector = Icons.Rounded.MoreVert,
            contentDescription = null,
            tint = MaterialTheme.colorScheme.onSecondary
        )
    }
}

@Preview
@Composable
private fun PreviewLight() {
    VKClientComposeTheme(
        darkTheme = false
    ) {
        PostCard()
    }
}

@Preview
@Composable
private fun PreviewDark() {
    VKClientComposeTheme(
        darkTheme = true
    ) {
        PostCard()
    }
}
