package com.example.miso.ui.camera.screen

import android.content.Context
import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import com.example.miso.ui.camera.component.CameraBackground
import com.example.miso.ui.camera.component.CameraConfirmBtn
import com.example.miso.ui.camera.component.CameraReCaptureBtn
import com.example.miso.ui.camera.state.AiAnswerState
import com.example.miso.ui.camera.state.BitmapState
import com.example.miso.ui.main.MainPage
import com.example.miso.ui.theme.MisoTheme
import com.example.miso.viewmodel.CameraViewModel
import com.example.miso.viewmodel.RecyclablesViewModel
import org.w3c.dom.Text

@Composable
fun CameraResultScreen(context: Context,navController: NavController,viewModel: CameraViewModel,viewModelResult: RecyclablesViewModel) {
    val uploadFirebaseState by viewModel.uploadFirebaseState.collectAsState()
    val aiAnswer by viewModel.aiAnswer.collectAsState()
    var callSendBitmap by remember { mutableStateOf(BitmapState(callSendBitmap = null)) }

    LaunchedEffect(uploadFirebaseState.uploadedBitmap){
        when(uploadFirebaseState.uploadedBitmap){
            true -> toastMsg(context,"업로드 성공")
            false -> toastMsg(context,"네트워크 상태를 확인해 주세요.")
            else -> {}
        }
    }

    LaunchedEffect(aiAnswer.aiAnswerUploaded){
        when(aiAnswer.aiAnswerUploaded){
            true -> {
                Log.d("testt-Ai",aiAnswer.aiAnswerData.toString())
                viewModelResult.result(aiAnswer.aiAnswerData.toString())
                navController.navigate(MainPage.Result.value)
            }
            false -> Log.d("testt-Ai","fail")
            else -> {Log.d("testt-Ai","error")}
        }
    }

    getBitmap(viewModel = viewModel)
    MisoTheme { colors, typography ->
        CameraBackground()
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Spacer(modifier = Modifier.fillMaxHeight(0.87f))
            Text(text = "정말 이 사진을 등록 하시겠습니까?", color = colors.M3)
            Spacer(modifier = Modifier.fillMaxHeight(0.12f))
            Row(modifier = Modifier.fillMaxWidth()) {
                Spacer(modifier = Modifier.fillMaxWidth(0.07f))
                CameraReCaptureBtn {navController.popBackStack()}
                Spacer(modifier = Modifier.fillMaxWidth(0.06f))
                CameraConfirmBtn{ callSendBitmap = BitmapState(callSendBitmap = true) }
            }

        }
    }
    if(callSendBitmap.callSendBitmap == true){
        sendBitmap(context = context, viewModel = viewModel,navController = navController,uploadFirebaseState)
        callSendBitmap = BitmapState(callSendBitmap = false)
    }
}
@Composable
private fun getBitmap(viewModel: CameraViewModel){
    val captureImgBitmapState by viewModel.captureImgBitmapState.collectAsState()
    LaunchedEffect(captureImgBitmapState){ Log.d("testt",captureImgBitmapState.toString()) }
    Box(modifier = Modifier.fillMaxSize()){
        (captureImgBitmapState.capturedImage?.asImageBitmap() ?: null)?.let {
            Image(
                bitmap = it,
                modifier = Modifier.fillMaxSize(),
                contentDescription = "camera result preview")
        }
    }
}

@Composable
private fun sendBitmap(context: Context,viewModel: CameraViewModel,navController: NavController,uploadFirebaseState: BitmapState){
    viewModel.sendImgBitmap()
}
fun toastMsg(context: Context,text: String){
    Toast.makeText(context,text,Toast.LENGTH_SHORT).show()
}
@Composable
@Preview(showBackground = true)
fun CameraResultScreenPreView() {

}
