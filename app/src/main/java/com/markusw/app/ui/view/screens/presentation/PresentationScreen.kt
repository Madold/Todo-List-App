@file:OptIn(ExperimentalPagerApi::class, ExperimentalMaterial3Api::class)

package com.markusw.app.ui.view.screens.presentation

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.HorizontalPagerIndicator
import com.google.accompanist.pager.rememberPagerState
import com.markusw.app.ui.theme.AppTheme
import com.markusw.app.ui.theme.spacing
import com.orhanobut.logger.Logger

@Composable
fun PresentationScreen(
    navController: NavController,
    selectedSlide: Int = 0,
) {
    val pagerState = rememberPagerState()
    val slides = listOf(
        PagerItem.FirstSlide,
        PagerItem.SecondSlide,
        PagerItem.LoginSlide
    )
    Scaffold(
        bottomBar = {
            Box(
                contentAlignment = Alignment.Center,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = MaterialTheme.spacing.small)
            ) {
                HorizontalPagerIndicator(
                    pagerState = pagerState,
                )
            }
        }
    ) { padding ->

        HorizontalPager(
            count = slides.size,
            state = pagerState,
            modifier = Modifier.padding(padding)
        ) {
            slides[it].content(navController)
        }

        LaunchedEffect(key1 = pagerState) {
            pagerState.animateScrollToPage(selectedSlide)
        }
    }
}

@Preview(showSystemUi = true)
@Composable
fun PresentationScreenPreview() {
    AppTheme(
        dynamicColor = false,
    ) {
        PresentationScreen(navController = rememberNavController())
    }
}