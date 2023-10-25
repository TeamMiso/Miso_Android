package com.example.miso.ui.main.component.main

import androidx.compose.animation.animateColor
import androidx.compose.animation.core.tween
import androidx.compose.animation.core.updateTransition
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Divider
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.miso.R
import com.example.miso.ui.theme.MisoTheme

@Composable
fun MoveListButton(
    isFocus: Boolean = false,
    onClick: () -> Unit
) {
    MisoTheme { colors, typography ->
        val transition = updateTransition(targetState = isFocus)
        val backgroundColor by transition.animateColor(
            transitionSpec = {
                tween(durationMillis = 1000)
            }
        ) { isFocused ->
            if (isFocused) colors.M1 else colors.TRANSPARENTM1
        }
        Box(
            modifier = Modifier
                .size(290.dp, 400.dp)
                .clip(RoundedCornerShape(10.dp))
                .background(color = backgroundColor)
                .clickable {
                    onClick()
                }
        ) {
            Column {
                Image(
                    painter = painterResource(id = R.drawable.ic_list_example),
                    contentDescription = "List Example Icon",
                    modifier = Modifier.size(290.dp, 230.dp)
                )
                Column(modifier = Modifier.padding(start = 20.dp, end = 20.dp)) {
                    Spacer(modifier = Modifier.height(16.dp))
                    Row {
                        Box(
                            contentAlignment= Alignment.Center,
                            modifier = Modifier
                                .size(40.dp)
                                .clip(CircleShape)
                                .background(color = colors.WHITE2)
                        ) {
                            Image(
                                painter = painterResource(id = R.drawable.ic_list),
                                contentDescription = "List Icon",
                                modifier = Modifier.size(20.dp, 20.dp)
                            )
                        }
                        Spacer(modifier = Modifier.width(16.dp))
                        Column {
                            Text(
                                text = "문의 내역 보기.",
                                color = colors.WHITE2,
                                style = typography.title3,
                                fontWeight = FontWeight.Normal
                            )
                            Text(
                                text = "List.",
                                color = colors.GRAY1,
                                style = typography.content1,
                                fontWeight = FontWeight.ExtraLight
                            )
                        }
                    }
                    Spacer(modifier = Modifier.height(20.dp))
                    Divider(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(1.dp),
                        color = colors.GRAY1
                    )
                    Spacer(modifier = Modifier.height(20.dp))
                    Text(
                        text = "지금까지 문의한 내역을\n" +
                                "확인해보자.",
                        color = colors.WHITE,
                        style = typography.title3,
                        fontWeight = FontWeight.ExtraLight
                    )
                }
            }
        }
    }
}

@Composable
@Preview(showBackground = true)
fun MoveListButtonPreView() {
    MoveListButton(onClick = {})
}