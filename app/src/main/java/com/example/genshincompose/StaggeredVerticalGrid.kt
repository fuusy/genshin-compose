/*
 * Designed and developed by 2020 skydoves (Jaewoong Eum)
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.example.genshincompose

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.Layout
import androidx.compose.ui.unit.Dp
import kotlin.math.ceil

/*
 * Copyright 2020 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

@Composable
fun StaggeredVerticalGrid(
  modifier: Modifier = Modifier,
  maxColumnWidth: Dp,
  content: @Composable () -> Unit
) {
  Layout(
    content = content,
    modifier = modifier
  ) { measurables, constraints ->
    check(constraints.hasBoundedWidth) {
      "Unbounded width not supported"
    }
    val columns = ceil(constraints.maxWidth / maxColumnWidth.toPx()).toInt()
    val columnWidth = constraints.maxWidth / columns
    val itemConstraints = constraints.copy(maxWidth = columnWidth)
    val colHeights = IntArray(columns) { 0 } // track each column's height
    val placeables = measurables.map { measurable ->
      val column = shortestColumn(colHeights)
      val placeable = measurable.measure(itemConstraints)
      colHeights[column] += placeable.height
      placeable
    }

    val height = colHeights.maxOrNull()?.coerceIn(constraints.minHeight, constraints.maxHeight)
      ?: constraints.minHeight
    layout(
      width = constraints.maxWidth,
      height = height
    ) {
      val colY = IntArray(columns) { 0 }
      placeables.forEach { placeable ->
        val column = shortestColumn(colY)
        placeable.place(
          x = columnWidth * column,
          y = colY[column]
        )
        colY[column] += placeable.height
      }
    }
  }
}

private fun shortestColumn(colHeights: IntArray): Int {
  var minHeight = Int.MAX_VALUE
  var column = 0
  colHeights.forEachIndexed { index, height ->
    if (height < minHeight) {
      minHeight = height
      column = index
    }
  }
  return column
}
