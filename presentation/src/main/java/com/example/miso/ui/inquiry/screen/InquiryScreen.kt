package com.example.miso.ui.inquiry.screen

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.navigation.NavController
import com.example.miso.ui.component.button.MisoBackBlackButton
import com.example.miso.ui.inquiry.component.InquiryButton
import com.example.miso.ui.inquiry.component.InquiryTitleText
import android.content.Context
import android.net.Uri
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.domain.model.inquiry.request.InquiryRequestModel
import com.example.miso.ui.util.keyboardAsState
import com.example.miso.ui.inquiry.component.InquiryContentText
import com.example.miso.ui.inquiry.component.InquiryContentTitleText
import com.example.miso.ui.inquiry.component.InquiryImageText
import com.example.miso.ui.inquiry.component.InquiryTextField
import com.example.miso.ui.inquiry.component.MoveGalleryButton
import com.example.miso.ui.util.toMultipartBody
import com.google.gson.Gson
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.MultipartBody
import okhttp3.RequestBody
import okhttp3.RequestBody.Companion.toRequestBody

@Composable
fun InquiryScreen(
    context: Context,
    onBackClick: () -> Unit,
    onInquiryClick: (filePart: MultipartBody.Part?, inquiryPart: RequestBody) -> Unit
) {
    val isKeyboardOpen by keyboardAsState()
    var isManager by remember { mutableStateOf(false) }

    val focusManager = LocalFocusManager.current

    LaunchedEffect(isKeyboardOpen) {
        if (!isKeyboardOpen) {
            focusManager.clearFocus()
        }
    }

    var title by remember { mutableStateOf("") }
    var content by remember { mutableStateOf("") }
    var imageUri by remember { mutableStateOf(Uri.EMPTY) }

    val filePart = if (imageUri != Uri.EMPTY) {
        imageUri.toMultipartBody(context)
    } else null

    val inquiryData = InquiryRequestModel(
        title = title,
        content = content
    )

    val inquiryJson = Gson().toJson(inquiryData)

    val inquiryRequestBody = inquiryJson.toRequestBody("application/json".toMediaType())

    Box(
        modifier = Modifier
            .fillMaxSize()
            .pointerInput(Unit) {
                detectTapGestures { offset ->
                    focusManager.clearFocus()
                }
            }
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 16.dp, end = 16.dp, top = 48.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            MisoBackBlackButton { onBackClick() }
        }
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 16.dp, end = 16.dp, top = 48.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center
        ) {
            InquiryTitleText()
        }
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(start = 16.dp, end = 16.dp),
        ) {
            Spacer(modifier = Modifier.fillMaxHeight(0.11f))
            InquiryContentTitleText()
            Spacer(modifier = Modifier.height(8.dp))
            InquiryTextField(
                isManager = isManager,
                modifier = Modifier.height(35.dp),
                setChangeText = title,
                singleLine = true,
                onValueChange = {
                    title = it
                }
            )
            Spacer(modifier = Modifier.fillMaxHeight(0.04f))
            InquiryContentText()
            Spacer(modifier = Modifier.height(8.dp))
            InquiryTextField(
                isManager = isManager,
                modifier = Modifier.fillMaxHeight(0.23f),
                setChangeText = content,
                singleLine = false,
                onValueChange = {
                    content = it
                }
            )
            Spacer(modifier = Modifier.fillMaxHeight(0.06f))
            InquiryImageText()
            Spacer(modifier = Modifier.height(8.dp))
            MoveGalleryButton(
                modifier = Modifier.weight(1f),
                selectedImageUri = { uri ->
                    if (uri != null) {
                        imageUri = uri
                    }
                }
            )
            Spacer(modifier = Modifier.height(50.dp))
            InquiryButton {
                onInquiryClick(filePart, inquiryRequestBody)
            }
            Spacer(modifier = Modifier.height(55.dp))
        }
    }
}

@Composable
@Preview(showBackground = true)
fun InquiryScreenPreView() {
    // InquiryScreen(LocalContext.current, NavController(LocalContext.current), {})
}