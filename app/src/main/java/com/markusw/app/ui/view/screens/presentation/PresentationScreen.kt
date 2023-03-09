@file:OptIn(ExperimentalPagerApi::class)

package com.markusw.app.ui.view.screens.presentation

import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.rememberPagerState
import com.markusw.app.ui.theme.AppTheme

@Composable
fun PresentationScreen() {

    val pagerState = rememberPagerState()

    HorizontalPager(count = 3, state = pagerState) {

    }
}

@Preview(showSystemUi = true)
@Composable
fun PresentationScreenPreview() {
    AppTheme(
        dynamicColor = false,
    ) {
        PresentationScreen()
    }
}