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

public val MyIcons.Plus: ImageVector
    get() {
        if (_plus != null) {
            return _plus!!
        }
        _plus = Builder(name = "Plus", defaultWidth = 24.0.dp, defaultHeight = 24.0.dp,
                viewportWidth = 960.0f, viewportHeight = 960.0f).apply {
            path(fill = SolidColor(Color(0xFF000000)), stroke = null, strokeLineWidth = 0.0f,
                    strokeLineCap = Butt, strokeLineJoin = Miter, strokeLineMiter = 4.0f,
                    pathFillType = NonZero) {
                moveTo(440.0f, 520.0f)
                lineTo(200.0f, 520.0f)
                lineTo(200.0f, 440.0f)
                lineTo(440.0f, 440.0f)
                lineTo(440.0f, 200.0f)
                lineTo(520.0f, 200.0f)
                lineTo(520.0f, 440.0f)
                lineTo(760.0f, 440.0f)
                lineTo(760.0f, 520.0f)
                lineTo(520.0f, 520.0f)
                lineTo(520.0f, 760.0f)
                lineTo(440.0f, 760.0f)
                lineTo(440.0f, 520.0f)
                close()
            }
        }
        .build()
        return _plus!!
    }

private var _plus: ImageVector? = null
