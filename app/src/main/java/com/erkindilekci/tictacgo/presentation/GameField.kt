package com.erkindilekci.tictacgo.presentation

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.drawscope.DrawScope
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.erkindilekci.tictacgo.data.model.GameState

@Composable
fun GameField(
    state: GameState,
    modifier: Modifier = Modifier,
    xColor: Color = Color.Red,
    oColor: Color = Color.Green,
    onTapInField: (x: Int, y: Int) -> Unit
) {
    Canvas(
        modifier = modifier
            .pointerInput(true) {
                detectTapGestures {
                    val x = (3 * it.x.toInt() / size.width)
                    val y = (3 * it.y.toInt() / size.height)
                    onTapInField(x, y)
                }
            }
    ) {
        drawField()

        state.field.forEachIndexed { y, _ ->
            state.field[y].forEachIndexed { x, player ->
                val offset = Offset(
                    x = x * size.width * (1 / 3f) + size.width / 6f,
                    y = y * size.height * (1 / 3f) + size.height / 6f,
                )
                if (player == 'X') {
                    drawX(
                        color = xColor,
                        center = offset
                    )
                } else if (player == 'O') {
                    drawO(
                        color = oColor,
                        center = offset
                    )
                }
            }
        }
    }
}

private fun DrawScope.drawField() {
    drawLine(
        color = Color.Black,
        start = Offset(
            x = size.width * (1 / 3f),
            y = 0f
        ),
        end = Offset(
            x = size.width * (1 / 3f),
            y = size.height
        ),
        strokeWidth = 3.dp.toPx(),
        cap = StrokeCap.Round
    )
    drawLine(
        color = Color.Black,
        start = Offset(
            x = size.width * (2 / 3f),
            y = 0f
        ),
        end = Offset(
            x = size.width * (2 / 3f),
            y = size.height
        ),
        strokeWidth = 3.dp.toPx(),
        cap = StrokeCap.Round
    )
    drawLine(
        color = Color.Black,
        start = Offset(
            x = 0f,
            y = size.height * (1 / 3f)
        ),
        end = Offset(
            x = size.width,
            y = size.height * (1 / 3f)
        ),
        strokeWidth = 3.dp.toPx(),
        cap = StrokeCap.Round
    )
    drawLine(
        color = Color.Black,
        start = Offset(
            x = 0f,
            y = size.height * (2 / 3f)
        ),
        end = Offset(
            x = size.width,
            y = size.height * (2 / 3f)
        ),
        strokeWidth = 3.dp.toPx(),
        cap = StrokeCap.Round
    )
}

private fun DrawScope.drawX(
    color: Color,
    center: Offset,
    size: Size = Size(50.dp.toPx(), 50.dp.toPx())
) {
    drawLine(
        color = color,
        start = Offset(
            x = center.x - size.width / 2f,
            y = center.y - size.height / 2f
        ),
        end = Offset(
            x = center.x + size.width / 2f,
            y = center.y + size.height / 2f
        ),
        strokeWidth = 3.dp.toPx(),
        cap = StrokeCap.Round
    )
    drawLine(
        color = color,
        start = Offset(
            x = center.x - size.width / 2f,
            y = center.y + size.height / 2f
        ),
        end = Offset(
            x = center.x + size.width / 2f,
            y = center.y - size.height / 2f
        ),
        strokeWidth = 3.dp.toPx(),
        cap = StrokeCap.Round
    )
}

private fun DrawScope.drawO(
    color: Color,
    center: Offset,
    size: Size = Size(60.dp.toPx(), 60.dp.toPx())
) {
    drawCircle(
        color = color,
        center = center,
        radius = size.width / 2f,
        style = Stroke(width = 3.dp.toPx())
    )
}

@Preview(showBackground = true)
@Composable
fun GameFieldPreview() {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp), contentAlignment = Alignment.Center
    ) {
        GameField(
            state = GameState(
                field = arrayOf(
                    arrayOf('O', 'X', 'O'),
                    arrayOf('O', 'X', 'X'),
                    arrayOf(null, null, 'O')
                )
            ),
            onTapInField = { _, _ -> },
            modifier = Modifier
                .fillMaxWidth()
                .height(400.dp)
        )
    }
}
