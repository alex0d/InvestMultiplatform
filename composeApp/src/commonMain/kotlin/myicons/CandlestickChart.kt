package myicons

import MyIcons
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.PathFillType.Companion.NonZero
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.graphics.StrokeCap.Companion.Butt
import androidx.compose.ui.graphics.StrokeJoin.Companion.Miter
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.graphics.vector.ImageVector.Builder
import androidx.compose.ui.graphics.vector.path
import androidx.compose.ui.unit.dp

public val MyIcons.CandlestickChart: ImageVector
    get() {
        if (_candlestickChart != null) {
            return _candlestickChart!!
        }
        _candlestickChart = Builder(name = "CandlestickChart", defaultWidth = 24.0.dp, defaultHeight
                = 24.0.dp, viewportWidth = 960.0f, viewportHeight = 960.0f).apply {
            path(fill = SolidColor(Color(0xFF000000)), stroke = null, strokeLineWidth = 0.0f,
                    strokeLineCap = Butt, strokeLineJoin = Miter, strokeLineMiter = 4.0f,
                    pathFillType = NonZero) {
                moveTo(280.0f, 800.0f)
                lineTo(280.0f, 720.0f)
                lineTo(200.0f, 720.0f)
                lineTo(200.0f, 240.0f)
                lineTo(280.0f, 240.0f)
                lineTo(280.0f, 160.0f)
                lineTo(360.0f, 160.0f)
                lineTo(360.0f, 240.0f)
                lineTo(440.0f, 240.0f)
                lineTo(440.0f, 720.0f)
                lineTo(360.0f, 720.0f)
                lineTo(360.0f, 800.0f)
                lineTo(280.0f, 800.0f)
                close()
                moveTo(600.0f, 800.0f)
                lineTo(600.0f, 600.0f)
                lineTo(520.0f, 600.0f)
                lineTo(520.0f, 320.0f)
                lineTo(600.0f, 320.0f)
                lineTo(600.0f, 160.0f)
                lineTo(680.0f, 160.0f)
                lineTo(680.0f, 320.0f)
                lineTo(760.0f, 320.0f)
                lineTo(760.0f, 600.0f)
                lineTo(680.0f, 600.0f)
                lineTo(680.0f, 800.0f)
                lineTo(600.0f, 800.0f)
                close()
            }
        }
        .build()
        return _candlestickChart!!
    }

private var _candlestickChart: ImageVector? = null
