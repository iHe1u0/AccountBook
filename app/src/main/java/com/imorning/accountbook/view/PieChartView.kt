package com.imorning.accountbook.view

import android.util.Log
import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListScope
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Divider
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.imorning.accountbook.ui.home.HomeViewModel
import hu.ma.charts.legend.data.LegendEntry
import hu.ma.charts.legend.data.LegendPosition
import hu.ma.charts.pie.PieChart
import hu.ma.charts.pie.data.PieChartData
import hu.ma.charts.pie.data.PieChartEntry
import java.math.RoundingMode
import java.text.DecimalFormat

private const val TAG = "PieChartView"

@Composable
fun ContentScreen(viewModel: HomeViewModel = viewModel()) {

    val incomeLists = viewModel.incomeLists.observeAsState()
    val items = incomeLists.value ?: return
    val incomes: HashMap<String, Double> = HashMap()
    items.map { item ->
        val category = item.type
        val value = item.value
        if (incomes.contains(category)) {
            val sumResult = value + incomes[category]!!
            incomes[category] = sumResult
        } else {
            incomes.put(category, value)
        }
    }
    ContentScreen(incomes)
}


@Composable
fun ContentScreen(lists: HashMap<String, Double>) {

    val pieChartData = lists.map { item ->
        Log.i(TAG, "item is $item")
        PieChartEntry(
            value = item.value.toFloat(),
            label = AnnotatedString(item.key)
        )
    }.let {
        PieChartData(
            entries = it,
            colors = Colors.SimpleColorsLight,
            legendShape = CircleShape,
            legendPosition = LegendPosition.Bottom
        )
    }
    ScreenContainer {
        repeat(3) {
            item {
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
                    title = "测试"
                ) {
                    PieChart(
                        data = pieChartData,
                        legend = { entries ->
                            CustomVerticalLegend(entries = entries)
                        }
                    )
                }

            }
        }
    }
}

@Composable
fun ContentScreen(categories: List<String>, values: List<Double>) {

    val pieChartData = PieChartData(
        entries = values.mapIndexed { index, value ->
            PieChartEntry(
                value = value.toFloat(),
                label = AnnotatedString(categories[index])
            )
        },
        colors = Colors.SimpleColorsLight,
        legendShape = CircleShape,
        legendPosition = LegendPosition.Bottom
    )
    ScreenContainer {
        item {
            repeat(2) {
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
                    title = "测试"
                ) {
                    PieChart(
                        data = pieChartData,
                        legend = { entries ->
                            CustomVerticalLegend(entries = entries)
                        }
                    )
                }
            }
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
        Text(title, style = MaterialTheme.typography.h6)
        Spacer(modifier = Modifier.requiredSize(chartOffset))
        content()
    }
}

@Composable
internal fun RowScope.CustomVerticalLegend(entries: List<LegendEntry>) {
    Column(
        modifier = Modifier.weight(1f),
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
        val format = DecimalFormat("0.##")
        format.roundingMode = RoundingMode.FLOOR
        val percentString = format.format(item.percent).toString()
        append("($percentString %)")
    }
}