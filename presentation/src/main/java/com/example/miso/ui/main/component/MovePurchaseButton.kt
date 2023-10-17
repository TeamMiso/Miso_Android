package com.example.miso.ui.main.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.miso.R
import com.example.miso.ui.theme.MisoTheme

@Composable
fun MovePurchaseButton(
    onClick: () -> Unit
) {
    var isClicked by remember { mutableStateOf(false) }

    MisoTheme { colors, typography ->
        Column(modifier = Modifier.padding(start = 16.dp, end = 16.dp)) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(98.dp)
                    .shadow(
                        elevation = 16.dp,
                        shape = RoundedCornerShape(10.dp)
                    )
                    .clip(shape = RoundedCornerShape(10.dp))
                    .background(if (!isClicked) colors.GRAY3 else colors.M1)
                    .clickable(
                        interactionSource = remember { MutableInteractionSource() },
                        indication = null
                    ) {
                        isClicked = !isClicked
                        onClick()
                    }
                    .padding(start = 8.dp, end = 8.dp)
            ) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                ) {
                    Column {
                        Spacer(modifier = Modifier.height(10.dp))
                        Text(
                            text = "포인트 교환하기",
                            color = if (!isClicked) colors.BLACK else colors.WHITE,
                            style = typography.content1,
                            fontWeight = FontWeight.SemiBold
                        )
                        Text(
                            text = "재활용을 통해 얻은 포인트를\n" +
                                    "상품 구매에 사용해보자",
                            color = if (!isClicked) colors.BLACK else colors.WHITE,
                            style = typography.content2,
                            fontWeight = FontWeight.SemiBold
                        )
                    }
                    Image(
                        painter = painterResource(id = R.drawable.ic_move_purchase),
                        contentDescription = "Purchase Icon",
                        contentScale = ContentScale.FillBounds,
                        modifier = Modifier.size(width = 126.dp, height = 98.dp),
                    )
                }
            }
        }
    }
}

@Composable
@Preview(showBackground = true)
fun MovePurchaseButtonPreView() {
    MovePurchaseButton({})
}