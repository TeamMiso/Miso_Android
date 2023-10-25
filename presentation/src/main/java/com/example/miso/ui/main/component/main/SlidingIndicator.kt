package com.example.miso.ui.main.component.main

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.example.miso.ui.theme.MisoTheme
import kotlin.math.absoluteValue

@Composable
fun SlidingIndicator(
    pageCount: Int,
    currentPage: Int,
    targetPage: Int,
    currentPageOffsetFraction: Float,
    modifier: Modifier = Modifier,
    unselectedIndicatorSize: Dp = 17.dp,
    selectedIndicatorSize: Dp = 17.dp,
    indicatorCornerRadius: Dp = 10.dp,
    indicatorPadding: Dp = 2.dp
) {
    MisoTheme { colors, typography ->
        val unselectedColor = colors.GRAY7
        Row(
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically,
            modifier = modifier
                .wrapContentSize()
                .height(selectedIndicatorSize + indicatorPadding * 2)
        ) {
            repeat(pageCount) { page ->
                val (color, size) =
                    if (currentPage == page || targetPage == page) {
                        val pageOffset =
                            ((currentPage - page) + currentPageOffsetFraction).absoluteValue
                        val offsetPercentage = 1f - pageOffset.coerceIn(0f, 1f)

                        val size =
                            unselectedIndicatorSize + ((selectedIndicatorSize - unselectedIndicatorSize) * offsetPercentage)

                        colors.M1.copy(
                            alpha = offsetPercentage
                        ) to size
                    } else {
                        unselectedColor to unselectedIndicatorSize
                    }
                Box(
                    modifier = Modifier
                        .padding(
                            horizontal = ((selectedIndicatorSize + indicatorPadding * 2) - size) / 2,
                            vertical = size / 9
                        )
                        .clip(RoundedCornerShape(indicatorCornerRadius))
                        .background(color)
                        .width(size)
                        .height(size / 3)
                )
            }
        }
    }
}

@Composable
@Preview(showBackground = true)
fun SlidingIndicatorPreView() {
    SlidingIndicator(
        pageCount = 4,
        currentPage = 1,
        targetPage = 1,
        currentPageOffsetFraction = 1f
    )
}