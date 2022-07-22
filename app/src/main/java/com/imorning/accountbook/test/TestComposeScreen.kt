package com.imorning.accountbook.test

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color


const val ROUTE_FIRST = "routeFirst"
const val ROUTE_SECOND = "routeSecond"
const val ROUTE_THIRD = "routeThird"


@Composable
fun FirstScreen(navigateTo: () -> Unit) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Red),

        horizontalAlignment = Alignment.CenterHorizontally,  //横向居中
        verticalArrangement = Arrangement.Center,  //纵向居中
    ) {
        Text(text = "这是第一个界面")

        Button(onClick = {
            navigateTo.invoke()
        }) {
            Text(text = "跳转到第二页")
        }
    }
}

@Composable
fun SecondScreen(name: String?, age: Int?, navigateTo: () -> Unit) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Green),

        horizontalAlignment = Alignment.CenterHorizontally,  //横向居中
        verticalArrangement = Arrangement.Center,  //纵向居中
    ) {
        Text(text = "这是第二个界面 传递的参数为：姓名：$name; 年龄：${age}岁")

        Button(onClick = {
            navigateTo.invoke()
        }) {
            Text(text = "跳转到第三页")
        }
    }
}


@Composable
fun ThirdScreen(carName: String?, navigateTo: () -> Unit) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Gray),

        horizontalAlignment = Alignment.CenterHorizontally,  //横向居中
        verticalArrangement = Arrangement.Center,  //纵向居中
    ) {
        Text(text = "这是第三个界面 carName: $carName")

        Button(onClick = {
            navigateTo.invoke()
        }) {
            Text(text = "回到第一页")
        }
    }
}
