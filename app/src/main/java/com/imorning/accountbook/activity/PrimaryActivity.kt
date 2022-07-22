package com.imorning.accountbook.activity

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListScope
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Divider
import androidx.compose.material.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.imorning.accountbook.test.FirstScreen
import com.imorning.accountbook.test.ROUTE_FIRST
import com.imorning.accountbook.test.ROUTE_SECOND
import com.imorning.accountbook.test.SecondScreen
import com.imorning.accountbook.view.Colors
import hu.ma.charts.legend.data.LegendEntry
import hu.ma.charts.legend.data.LegendPosition
import hu.ma.charts.pie.PieChart
import hu.ma.charts.pie.data.PieChartData
import hu.ma.charts.pie.data.PieChartEntry
import org.jetbrains.annotations.TestOnly
import kotlin.math.roundToInt

private const val TAG = "PrimaryActivity"

class PrimaryActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val categories = resources.getStringArray(com.imorning.accountbook.R.array.income_type)
        val values: List<Double> = listOf(100.0, 200.0, 300.0, 800.0)
        setContent {

            val navController = rememberNavController()
            NavHost(navController = navController, startDestination = ROUTE_FIRST, builder = {
                composable(route = ROUTE_FIRST) {  //route: 表示路由名称，跳转时需要
                    FirstScreen {
                        navController.navigate(ROUTE_SECOND)
                    }
                }

                composable(route = ROUTE_SECOND) {

                    SecondScreen("name", 0) {
                        navController.navigate(ROUTE_FIRST)
                    }
                }
            })

//            OverviewTheme {
//                ContentScreen(categories.toList(), values)
//            }
        }
    }

    @Composable
    fun ContentScreen(categories: List<String>, values: List<Double>) {
        if (categories.isEmpty()) {
            return
        }
        ScreenContainer {
//            val categories = listOf(
//                "Teams",
//                "Locations",
//                "Devices",
//                "People",
//                "Laptops",
//                "Titles",
//                "Flowers",
//                "Bugs",
//                "Windows",
//                "Screens",
//                "Colors",
//                "Bottles",
//                "Cars",
//                "Tricks",
//            )
            val pieSampleData = LegendPosition.values().map {
                PieChartData(
                    entries = values.mapIndexed { idx, value ->
                        PieChartEntry(
                            value = value.toFloat(),
                            label = AnnotatedString(categories[idx])
                        )
                    },
                    colors = Colors.SimpleColorsLight,
                    legendPosition = it,
                    legendShape = CircleShape,
                )
            }

            items(pieSampleData) { data ->
                ChartContainer(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp)
                        .border(
                            BorderStroke(1.dp, Color.LightGray),
                            shape = RoundedCornerShape(16.dp)
                        )
                        .padding(16.dp)
                        .animateContentSize(),
                    title = "测试样式::${data.legendPosition.name}"
                ) {
                    PieChart(
                        data = data,
                        legend = { entries ->
                            CustomVerticalLegend(entries = entries)
                        }
                    )
                }
            }
        }
    }

    @Composable
    internal fun RowScope.CustomVerticalLegend(entries: List<LegendEntry>) {
        Column(
            modifier = Modifier.Companion.weight(1f),
        ) {
            entries.forEachIndexed { idx, item ->
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier.padding(vertical = 14.dp)
                ) {
                    Box(
                        Modifier
                            .requiredSize(item.shape.size)
                            .background(item.shape.color, item.shape.shape)
                    )

                    Spacer(modifier = Modifier.requiredSize(8.dp))

                    Text(
                        text = item.text,
                        style = MaterialTheme.typography.caption
                    )
                    Spacer(modifier = Modifier.weight(1f))

                    Text(
                        text = buildValuePercentString(item),
                        style = MaterialTheme.typography.caption,
                    )
                }

                if (idx != entries.lastIndex)
                    Divider()
            }
        }
    }

    @Composable
    fun ScreenContainer(
        content: LazyListScope.() -> Unit,
    ) {
        LazyColumn(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.spacedBy(24.dp),
            contentPadding = PaddingValues(
                top = 24.dp,
                bottom = 24.dp,
            ),
        ) {
            content()
        }
    }

    @Composable
    fun ChartContainer(
        modifier: Modifier = Modifier,
        title: String,
        chartOffset: Dp = 12.dp,
        content: @Composable () -> Unit,
    ) {
        Column(modifier = modifier) {
            androidx.compose.material.Text(title, style = MaterialTheme.typography.h6)
            Spacer(modifier = Modifier.requiredSize(chartOffset))
            content()
        }
    }

    @Composable
    fun buildValuePercentString(item: LegendEntry) = buildAnnotatedString {
        item.value?.let { value ->
            withStyle(
                style = MaterialTheme.typography.body2.toSpanStyle()
                    .copy(color = MaterialTheme.colors.primary)
            ) {
                append(value.toInt().toString())
            }
            append(" ")
        }

        withStyle(
            style = MaterialTheme.typography.caption.toSpanStyle()
                .copy(color = MaterialTheme.colors.secondary)
        ) {
            val percentString = item.percent.roundToInt().toString()
            append("($percentString %)")
        }
    }

}