package com.markusw.app.ui.view.screens.presentation.composables

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.markusw.app.ui.theme.AppTheme
import com.markusw.app.R

@Composable
fun FirstSlide(
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Column(
            verticalArrangement = Arrangement.spacedBy(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Image(
                painter = painterResource(id = R.drawable.productivity_ilustration),
                contentDescription = null,
                modifier = Modifier.fillMaxWidth(0.8f)
            )
            Text("Welcome to Todoit", style = TextStyle(
                fontWeight = FontWeight.Bold,
                fontSize = 18.sp
            ))
            Text("Managing your tasks never has been so easy", style = TextStyle(
                fontSize = 18.sp,
                textAlign = TextAlign.Center
            ))
        }
    }
}

@Preview(showSystemUi = true)
@Composable
fun FirstSlidePreview() {
    AppTheme(
        dynamicColor = false,
    ) {
        FirstSlide(
            modifier = Modifier.fillMaxSize()
        )
    }
}