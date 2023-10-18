package com.example.miso.ui.main.component

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Divider
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import com.example.miso.ui.theme.MisoTheme

@Composable
fun LogoutDialog(
    openDialog: Boolean,
    onStateChange: (Boolean) -> Unit
) {
    var openDialog by remember { mutableStateOf(openDialog) }

    if (openDialog) {
        Dialog(onDismissRequest = { openDialog = false }) {
            MisoTheme { colors, typography ->
                Card(
                    shape = RoundedCornerShape(14.dp),
                    modifier = Modifier
                        .size(355.dp, 180.dp)
                        .padding(top = 5.dp, bottom = 10.dp),
                    elevation = 8.dp
                ) {
                    Column(modifier = Modifier.background(colors.TRANSPARENTBLACK)) {
                        Column(modifier = Modifier
                            .background(colors.TRANSPARENTBLACK)
                            .padding(top = 16.dp, bottom = 20.dp)) {
                            Text(
                                text = "로그아웃",
                                textAlign = TextAlign.Center,
                                modifier = Modifier.fillMaxWidth(),
                                color = colors.WHITE,
                                style = typography.title3,
                                fontWeight = FontWeight.Normal,
                            )
                            Text(
                                text = "정말 이대로 로그아웃 하시겠습니까?",
                                textAlign = TextAlign.Center,
                                modifier = Modifier.fillMaxWidth(),
                                color = colors.TRANSPARENTWHITE,
                                style = typography.content1,
                                fontWeight = FontWeight.Normal,
                            )
                        }
                        Divider(
                            color = colors.TRANSPARENTGRAY,
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(0.5.dp)
                        )
                        Row(
                            Modifier
                                .fillMaxWidth()
                                .background(colors.TRANSPARENTBLACK),
                            horizontalArrangement = Arrangement.SpaceAround,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            TextButton(
                                modifier = Modifier
                                    .fillMaxHeight()
                                    .weight(1f),
                                onClick = {
                                    openDialog = false
                                }
                            ) {
                                Text(
                                    "취소",
                                    color = colors.WHITE,
                                    style = typography.title3,
                                    fontWeight = FontWeight.Normal,
                                )
                            }
                            Divider(
                                color = colors.TRANSPARENTGRAY,
                                modifier = Modifier
                                    .fillMaxHeight()
                                    .width(0.5.dp)
                            )
                            TextButton(
                                modifier = Modifier
                                    .fillMaxHeight()
                                    .weight(1f),
                                onClick = {
                                    openDialog = false
                                }
                            ) {
                                Text(
                                    "로그아웃",
                                    color = colors.BLUE2,
                                    style = typography.title3,
                                    fontWeight = FontWeight.Normal,
                                )
                            }
                        }
                    }
                }
            }
        }
    }
    else {
        onStateChange(openDialog)
    }
}

@Preview(showBackground = true)
@Composable
fun LogoutDialogPreview() {
    LogoutDialog(openDialog = true, {})
}