package com.example.assignment2


import androidx.compose.foundation.layout.Row
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.material3.TabRowDefaults
import androidx.compose.material3.TabRowDefaults.tabIndicatorOffset
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier

@Composable
fun AppTabBar(
    modifier: Modifier = Modifier,
    children: @Composable (Modifier) -> Unit
) {
    Row(modifier) {
        children(
            Modifier
                .weight(1f)
                .align(Alignment.CenterVertically)
        )
    }
}

@Composable
fun AppTabs(
    modifier: Modifier = Modifier,
    titles: List<String>,
    tabSelected: HomeTab,
    onTabSelected: (HomeTab) -> Unit
) {
    TabRow(
        selectedTabIndex = tabSelected.ordinal,
        modifier = modifier,
        indicator = {
            TabRowDefaults.Indicator(
                Modifier.tabIndicatorOffset(it[tabSelected.ordinal])
            )
        }, divider = {}

    ) {
        titles.forEachIndexed { index, title ->
            val selected = index == tabSelected.ordinal
            Tab(selected = selected, onClick = { onTabSelected(HomeTab.values()[index]) },
                text = { Text(text = titles[index]) })
        }
    }
}