package com.example.blindclone.core.feature.write

import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.blindclone.R

@Composable
fun WriteScreen(
    viewModel: WriteViewModel = hiltViewModel(),
    popBackStack: () -> Unit
) {
    val context = LocalContext.current
    val saveState by viewModel.saveState.collectAsStateWithLifecycle()

    var title by rememberSaveable { mutableStateOf("") }
    var content by rememberSaveable { mutableStateOf("") }

    LaunchedEffect(saveState) {
        when (val state = saveState) {
            is WriteViewModel.SaveState.Success -> {
                Toast.makeText(context, "저장되었습니다!", Toast.LENGTH_SHORT).show()
                popBackStack()
            }
            is WriteViewModel.SaveState.Error -> {
                Toast.makeText(context, "오류: ${state.message}", Toast.LENGTH_LONG).show()
            }
            is WriteViewModel.SaveState.Saving -> {
                // 로딩 인디케이터 표시
                Log.d("WriteScreen", "Saving...")
            }
            is WriteViewModel.SaveState.Idle -> {
                // 기본 상태
            }
        }
    }

    Scaffold(
        topBar = {
            WriteTopAppBar(
                savePost = {
                    viewModel.savePost(
                        title = title,
                        content = content
                    )
                },
                popBackStack = popBackStack
            )
        }
    ) { padding ->
        val scrollState = rememberScrollState()

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
                .verticalScroll(scrollState)
        ) {
            SelectTopicButton(
                setKeyword = { keyword ->
                    viewModel.setKeyword(keyword)
                }
            )

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
                cursorBrush = SolidColor(MaterialTheme.colorScheme.onSurface),
                decorationBox = { innerTextField ->
                    Box {
                        if (title.isEmpty()) {
                            Text(
                                text = "제목을 입력해주세요",
                                style = TextStyle.Default.copy(
                                    color = Color.LightGray
                                )
                            )
                        }
                        innerTextField()
                    }
                }
            )
            BasicTextField(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(dimensionResource(id = R.dimen.screen_horizontal_margin)),
                value = content,
                onValueChange = {
                    content = it
                },
                textStyle = TextStyle.Default.copy(
                    color = MaterialTheme.colorScheme.onSurface,
                ),
                cursorBrush = SolidColor(MaterialTheme.colorScheme.onSurface),
                decorationBox = { innerTextField ->
                    Box {
                        if (content.isEmpty()) {
                            Text(
                                text = "내용을 입력해주세요",
                                style = TextStyle.Default.copy(
                                    color = Color.LightGray
                                )
                            )
                        }
                        innerTextField()
                    }
                }
            )
        }
    }
}

@Composable
private fun WriteTopAppBar(
    savePost: () -> Unit,
    popBackStack: () -> Unit
) {
    Row(
        modifier = Modifier
            .statusBarsPadding()
            .height(64.dp)
            .fillMaxWidth()
            .background(
                color = MaterialTheme.colorScheme.background
            ),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        TextButton(onClick = { popBackStack() }) {
            Text(
                text = stringResource(id = R.string.cancel),
                color = MaterialTheme.colorScheme.onSurface
            )
        }
        TextButton(onClick = { savePost() }) {
            Text(
                text = stringResource(id = R.string.enroll),
                color = MaterialTheme.colorScheme.onSurface
            )
        }
    }
}

@Composable
private fun SelectTopicButton(
    setKeyword: (String) -> Unit
) {
    val options = listOf("IT 엔지니어", "주식, 투자", "부동산", "자동차")
    var expanded by remember { mutableStateOf(false) }
    var selectedText by remember { mutableStateOf("") }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(
                horizontal = dimensionResource(id = R.dimen.default_margin)
            ),
    ) {
        OutlinedButton(
            shape = RoundedCornerShape(10.dp),
            onClick = { expanded = true }
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    text = if (selectedText.isEmpty()) "등록 위치를 선택하세요." else selectedText,
                    color = MaterialTheme.colorScheme.onSurface
                )
                Icon(
                    imageVector = Icons.Default.ArrowDropDown,
                    tint = MaterialTheme.colorScheme.onSurface,
                    contentDescription = null
                )
            }
        }

        DropdownMenu(
            expanded = expanded,
            onDismissRequest = { expanded = false },
            modifier = Modifier.fillMaxWidth()
        ) {
            options.forEach { label ->
                DropdownMenuItem(
                    text = { Text(label) },
                    onClick = {
                        setKeyword(label)
                        selectedText = label
                        expanded = false
                    }
                )
            }
        }
    }
}

@Preview
@Composable
private fun SelectTopicButtonPreview() {
    Surface {
        SelectTopicButton({})
    }
}

@Preview
@Composable
private fun WriteScreenPreview() {
    Surface {
        WriteScreen(
            popBackStack = {}
        )
    }
}