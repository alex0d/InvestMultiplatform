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

public val MyIcons.LineChart: ImageVector
    get() {
        if (_lineChart != null) {
            return _lineChart!!
        }
        _lineChart = Builder(name = "LineChart", defaultWidth = 24.0.dp, defaultHeight = 24.0.dp,
                viewportWidth = 960.0f, viewportHeight = 960.0f).apply {
            path(fill = SolidColor(Color(0xFF000000)), stroke = null, strokeLineWidth = 0.0f,
                    strokeLineCap = Butt, strokeLineJoin = Miter, strokeLineMiter = 4.0f,
                    pathFillType = NonZero) {
                moveTo(140.0f, 740.0f)
                lineTo(80.0f, 680.0f)
                lineTo(380.0f, 380.0f)
                lineTo(540.0f, 540.0f)
                lineTo(824.0f, 220.0f)
                lineTo(880.0f, 276.0f)
                lineTo(540.0f, 660.0f)
                lineTo(380.0f, 500.0f)
                lineTo(140.0f, 740.0f)
                close()
            }
        }
        .build()
        return _lineChart!!
    }

private var _lineChart: ImageVector? = null
