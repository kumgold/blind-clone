package com.example.blindclone.core.feature.channel

import androidx.lifecycle.ViewModel
import com.example.blindclone.core.model.Channel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class ChannelViewModel @Inject constructor() : ViewModel() {

}

val channels = listOf(
    Channel(
        title = "IT 엔지니어",
        description = "IT 엔지니어 채널",
        thumbnail = ""
    ),
    Channel(
        title = "주식, 투자",
        description = "주식, 투자 채널",
        thumbnail = ""
    ),
    Channel(
        title = "자동차",
        description = "자동차 채널",
        thumbnail = ""
    ),
    Channel(
        title = "부동산",
        description = "부동산 채널",
        thumbnail = ""
    ),
    Channel(
        title = "IT 엔지니어",
        description = "IT 엔지니어 채널",
        thumbnail = ""
    ),
    Channel(
        title = "주식, 투자",
        description = "주식, 투자 채널",
        thumbnail = ""
    ),
    Channel(
        title = "자동차",
        description = "자동차 채널",
        thumbnail = ""
    ),
    Channel(
        title = "부동산",
        description = "부동산 채널",
        thumbnail = ""
    ),
)