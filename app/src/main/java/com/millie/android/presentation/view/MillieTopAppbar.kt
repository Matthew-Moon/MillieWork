package com.millie.android.presentation.view

import android.R.color.white
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.millie.android.R
import com.millie.android.presentation.theme.MillieBackground


@Composable
fun MillieTopAppbar(
    title: String,
    onLeftClick: () -> Unit
) {
    Box(
        modifier = Modifier
            .background(MillieBackground)
            .statusBarsPadding()
    ) {
        Row(
            modifier = Modifier
                .padding(horizontal = 16.dp)
                .fillMaxWidth()
                .height(44.dp),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Icon(
                painter = painterResource(id = R.drawable.common_ic_close_24),
                contentDescription = "뒤로가기",
                tint = Color.White,
                modifier = Modifier
                    .clickable(
                        onClick = { onLeftClick() },
                        indication = null,
                        interactionSource = remember { MutableInteractionSource() }
                    ),
            )

            Spacer(modifier = Modifier.weight(1f))
            Text(
                text = title,
                style = TextStyle(
                    fontSize = 16.sp,
                    lineHeight = 24.sp,
                    fontWeight = FontWeight(500),
                    color = Color.White,

                    textAlign = TextAlign.Center,
                )
            )
            Spacer(modifier = Modifier.weight(1f))
        }

    }
}
