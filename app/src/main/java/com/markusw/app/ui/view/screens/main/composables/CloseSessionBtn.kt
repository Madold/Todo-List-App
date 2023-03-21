package com.markusw.app.ui.view.screens.main.composables

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import com.markusw.app.ui.theme.spacing
import com.markusw.app.R

@Composable
fun MainDrawerFooter(
    onSignOutBtnClick: () -> Unit
) {
    Row(
        horizontalArrangement = Arrangement.End,
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = MaterialTheme.spacing.medium)
    ) {
        Button(
            onClick = onSignOutBtnClick,
            shape = RoundedCornerShape(MaterialTheme.spacing.small)
        ) {
            Icon(
                painter = painterResource(id = R.drawable.ic_exit),
                contentDescription = null
            )
            Spacer(modifier = Modifier.width(MaterialTheme.spacing.small))
            Text(text = "Sign out")
        }
    }
}