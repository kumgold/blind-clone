package com.example.blindclone.core.feature.write

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.graphics.painter.BrushPainter
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.blindclone.R

@Composable
fun WriteScreen() {
    Scaffold(
        topBar = {
            WriteTopAppBar()
        }
    ) { padding ->
        val scrollState = rememberScrollState()

        Column(
            modifier = Modifier
                .padding(padding)
                .verticalScroll(scrollState)
        ) {
            Row(
                modifier = Modifier
                    .padding(horizontal = dimensionResource(id = R.dimen.screen_horizontal_margin))
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(text = "등록 위치를 선택하세요")
                Icon(imageVector = Icons.Default.ArrowDropDown, contentDescription = "등록 위치를 선택하세요")
            }

            var title by rememberSaveable { mutableStateOf("") }
            BasicTextField(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(dimensionResource(id = R.dimen.screen_horizontal_margin)),
                value = title,
                onValueChange = {
                    title = it
                },
                singleLine = true,
                textStyle = TextStyle.Default.copy(
                    color = MaterialTheme.colorScheme.onSurface,
                ),
                cursorBrush = SolidColor(MaterialTheme.colorScheme.onSurface)
            )
        }
    }
}

@Composable
private fun WriteTopAppBar() {
    Row(
        modifier = Modifier
            .statusBarsPadding()
            .height(64.dp)
            .fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        TextButton(onClick = {  }) {
            Text(text = "취소")
        }
        TextButton(onClick = { /*TODO*/ }) {
            Text(text = "등록")
        }
    }
}

@Composable
private fun SelectTopicButton() {
    Row(
        modifier = Modifier
            .padding(horizontal = dimensionResource(id = R.dimen.screen_horizontal_margin))
            .fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(text = "등록 위치를 선택하세요")
        Icon(imageVector = Icons.Default.ArrowDropDown, contentDescription = "등록 위치를 선택하세요")
    }
}

@Preview
@Composable
private fun WriteScreenPreview() {
    Surface {
        WriteScreen()
    }
}