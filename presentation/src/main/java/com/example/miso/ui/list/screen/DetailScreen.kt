package com.example.miso.ui.list.screen

import android.content.Context
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
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
import androidx.core.net.toUri
import androidx.navigation.NavController
import com.example.miso.ui.component.button.MisoBackBlackButton
import com.example.miso.ui.util.keyboardAsState
import com.example.miso.ui.inquiry.component.InquiryButton
import com.example.miso.ui.inquiry.component.InquiryContentText
import com.example.miso.ui.inquiry.component.InquiryContentTitleText
import com.example.miso.ui.inquiry.component.InquiryImageText
import com.example.miso.ui.inquiry.component.InquiryTextField
import com.example.miso.ui.inquiry.component.InquiryTitleText
import com.example.miso.ui.inquiry.component.MoveGalleryButton
import com.example.miso.ui.list.component.detail.DetailImage
import com.example.miso.ui.list.component.detail.DetailImageText
import com.example.miso.ui.list.component.detail.DetailTitleText
import com.example.miso.ui.list.component.detail.SelectButton
import com.example.miso.ui.list.component.detail.UnselectButton
import com.example.miso.viewmodel.InquiryViewModel

@Composable
fun DetailScreen(
    context: Context,
    viewModel: InquiryViewModel,
    role: String,
    onBackClick: () -> Unit
) {
    var isManager by remember { mutableStateOf(true) }

    var title by remember { mutableStateOf(viewModel.title.value) }
    var content by remember { mutableStateOf(viewModel.content.value) }

    Box(
        modifier = Modifier.fillMaxSize()
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
            DetailTitleText()
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
                readOnly = true,
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
                readOnly = true,
                onValueChange = {
                    content = it
                }
            )
            Spacer(modifier = Modifier.fillMaxHeight(0.06f))
            DetailImageText()
            Spacer(modifier = Modifier.height(8.dp))
            DetailImage(
                modifier = Modifier.weight(1f),
                selectedImageUri = viewModel.imageUrl.value?.toUri()
            )
            Spacer(modifier = Modifier.height(50.dp))
            when (role) {
                "ROLE_USER" -> {}
                "ROLE_ADMIN" -> {
                    Row {
                        SelectButton(modifier = Modifier.weight(1f)) {
                            viewModel.adopt(viewModel.id.value)
                        }
                        Spacer(modifier = Modifier.width(13.dp))
                        UnselectButton(modifier = Modifier.weight(1f)) {
                            viewModel.unadopt(viewModel.id.value)
                        }
                    }
                }
                else -> {}
            }
            Spacer(modifier = Modifier.height(55.dp))
        }
    }
}

@Composable
@Preview(showBackground = true)
fun DetailScreenPreView() {
    //DetailScreen(LocalContext.current, ,NavController(LocalContext.current))
}