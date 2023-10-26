package com.example.miso.ui.main.screen

import android.content.Context
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
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
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.miso.ui.component.button.MisoBackBlackButton
import com.example.miso.ui.component.util.keyboardAsState
import com.example.miso.ui.main.component.main.MainContentPager
import com.example.miso.ui.main.component.main.MainContentText
import com.example.miso.ui.main.component.search.SearchContentText
import com.example.miso.ui.main.component.search.SearchList
import com.example.miso.ui.main.component.search.SearchTextField

@Composable
fun SearchScreen(
    context: Context,
    navController: NavController
) {
    var isClick by remember { mutableStateOf(false) }
    val isKeyboardOpen by keyboardAsState()

    val focusManager = LocalFocusManager.current

    val focusRequester = remember { FocusRequester() }

    LaunchedEffect(Unit) {
        focusRequester.requestFocus()
    }

    LaunchedEffect(isKeyboardOpen) {
        isClick = isKeyboardOpen
        if (!isKeyboardOpen) {
            focusManager.clearFocus()
        }
    }

    var search by remember { mutableStateOf("") }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .clickable(
                indication = null,
                interactionSource = remember { MutableInteractionSource() }
            ) {
                focusManager.clearFocus()
            }
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 16.dp, end = 16.dp, top = 48.dp),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            MisoBackBlackButton { navController.popBackStack() }
            Spacer(modifier = Modifier.width(20.dp))
            SearchTextField(
                setChangeText = search,
                onFocusChange = {
                    isClick = it
                },
                onValueChange = {
                    search = it
                },
                modifier = Modifier.focusRequester(focusRequester)
            )
        }
        Column(modifier = Modifier.padding(start = 16.dp)) {
            Spacer(modifier = Modifier.height(110.dp))
            SearchContentText()
        }
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(32.dp)
        ) {
            Spacer(modifier = Modifier.height(130.dp))
            SearchList(items = 3)
        }
    }
}

@Composable
@Preview(showBackground = true)
fun SearchScreenPreView() {
    SearchScreen(LocalContext.current, navController = NavController(LocalContext.current))
}