package com.example.miso.ui.component.snackbar

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeOut
import androidx.compose.animation.shrinkOut
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOut
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material.icons.rounded.Close
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.lifecycleScope
import com.example.miso.R
import com.example.miso.ui.theme.MisoTheme
import com.example.miso.viewmodel.util.Event
import kotlinx.coroutines.launch

@Composable
fun MisoSnackber(
    modifier: Modifier = Modifier,
    text: String,
    visible: Boolean,
    icon: Int,
    onClick: () -> Unit,
) {
    MisoTheme { colors, typography ->
        AnimatedVisibility(
            modifier = modifier,
            visible = visible,
            enter = slideInVertically(
                initialOffsetY = { -it }
            ),
            exit = fadeOut()
        ) {
            Card(
                modifier = Modifier
                    .clickable(
                        interactionSource = remember { MutableInteractionSource() },
                        indication = null
                    ) {
                        onClick()
                    },
                shape = RoundedCornerShape(50)
            ) {
                Row(
                    modifier = Modifier
                        .background(colors.WHITE)
                        .padding(vertical = 14.dp, horizontal = 16.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Icon(
                        painter = painterResource(id = icon),
                        contentDescription = null,
                        modifier = Modifier.size(24.dp),
                    )
                    Spacer(modifier = Modifier.width(6.dp))
                    Text(
                        text = text,
                        style = typography.content1,
                        fontWeight = FontWeight.Normal,
                        color = colors.BLACK
                    )
                }
            }
        }
    }
}

@Preview
@Composable
fun MisoSnackberPre() {
    MisoSnackber(text = "text", icon = R.drawable.ic_cancel, visible = true) {

    }
}