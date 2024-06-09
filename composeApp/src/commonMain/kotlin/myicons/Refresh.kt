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

public val MyIcons.Refresh: ImageVector
    get() {
        if (_refresh != null) {
            return _refresh!!
        }
        _refresh = Builder(name = "Refresh", defaultWidth = 24.0.dp, defaultHeight = 24.0.dp,
                viewportWidth = 960.0f, viewportHeight = 960.0f).apply {
            path(fill = SolidColor(Color(0xFF000000)), stroke = null, strokeLineWidth = 0.0f,
                    strokeLineCap = Butt, strokeLineJoin = Miter, strokeLineMiter = 4.0f,
                    pathFillType = NonZero) {
                moveTo(480.0f, 800.0f)
                quadTo(346.0f, 800.0f, 253.0f, 707.0f)
                quadTo(160.0f, 614.0f, 160.0f, 480.0f)
                quadTo(160.0f, 346.0f, 253.0f, 253.0f)
                quadTo(346.0f, 160.0f, 480.0f, 160.0f)
                quadTo(549.0f, 160.0f, 612.0f, 188.5f)
                quadTo(675.0f, 217.0f, 720.0f, 270.0f)
                lineTo(720.0f, 160.0f)
                lineTo(800.0f, 160.0f)
                lineTo(800.0f, 440.0f)
                lineTo(520.0f, 440.0f)
                lineTo(520.0f, 360.0f)
                lineTo(688.0f, 360.0f)
                quadTo(656.0f, 304.0f, 600.5f, 272.0f)
                quadTo(545.0f, 240.0f, 480.0f, 240.0f)
                quadTo(380.0f, 240.0f, 310.0f, 310.0f)
                quadTo(240.0f, 380.0f, 240.0f, 480.0f)
                quadTo(240.0f, 580.0f, 310.0f, 650.0f)
                quadTo(380.0f, 720.0f, 480.0f, 720.0f)
                quadTo(557.0f, 720.0f, 619.0f, 676.0f)
                quadTo(681.0f, 632.0f, 706.0f, 560.0f)
                lineTo(790.0f, 560.0f)
                quadTo(762.0f, 666.0f, 676.0f, 733.0f)
                quadTo(590.0f, 800.0f, 480.0f, 800.0f)
                close()
            }
        }
        .build()
        return _refresh!!
    }

private var _refresh: ImageVector? = null
