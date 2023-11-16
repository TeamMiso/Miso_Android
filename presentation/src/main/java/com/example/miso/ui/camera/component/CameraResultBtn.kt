package com.example.miso.ui.camera.component

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.miso.ui.component.button.MisoButton
import com.example.miso.ui.theme.MisoTheme

@Composable
fun CameraReCaptureBtn(onClick: () -> Unit){
    MisoTheme { colors, typography ->
        Row() {
            Button(
                modifier = Modifier
                    .width(165.dp)
                    .height(48.dp),
                shape = RoundedCornerShape(5.dp),
                colors = ButtonDefaults.buttonColors(colors.GRAY8),
                onClick = { onClick() }
            ) {
                Text(
                    text = "채촬영",
                    color = colors.M3,
                    style = typography.title3,
                    fontWeight = FontWeight.ExtraLight
                )
            }
        }
    }
}
@Composable
fun CameraConfirmBtn(onClick: () -> Unit){
    val buttonClick: () -> Unit = {
        onClick() // 전달된 클릭 이벤트 실행
    }
    MisoTheme { colors, typography ->
        Row() {
            Button(
                modifier = Modifier
                    .width(165.dp)
                    .height(48.dp),
                shape = RoundedCornerShape(5.dp),
                colors = ButtonDefaults.buttonColors(colors.M1),
                onClick = buttonClick
            ) {
                Text(
                    text = "확인",
                    color = colors.M3,
                    style = typography.title3,
                    fontWeight = FontWeight.ExtraLight
                )
            }
        }
    }
}

@Composable
@Preview(showBackground = true)
fun CameraResultBtnPreView() {
    Column(modifier = Modifier.fillMaxSize()){
        CameraReCaptureBtn({})
        CameraConfirmBtn({})
    }
}