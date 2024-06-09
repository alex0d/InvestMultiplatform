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

public val MyIcons.Portfolio: ImageVector
    get() {
        if (_portfolio != null) {
            return _portfolio!!
        }
        _portfolio = Builder(name = "Portfolio", defaultWidth = 24.0.dp, defaultHeight = 24.0.dp,
                viewportWidth = 960.0f, viewportHeight = 960.0f).apply {
            path(fill = SolidColor(Color(0xFFFFFFFF)), stroke = null, strokeLineWidth = 0.0f,
                    strokeLineCap = Butt, strokeLineJoin = Miter, strokeLineMiter = 4.0f,
                    pathFillType = NonZero) {
                moveTo(160.0f, 840.0f)
                quadTo(127.0f, 840.0f, 103.5f, 816.5f)
                quadTo(80.0f, 793.0f, 80.0f, 760.0f)
                lineTo(80.0f, 320.0f)
                quadTo(80.0f, 287.0f, 103.5f, 263.5f)
                quadTo(127.0f, 240.0f, 160.0f, 240.0f)
                lineTo(320.0f, 240.0f)
                lineTo(320.0f, 160.0f)
                quadTo(320.0f, 127.0f, 343.5f, 103.5f)
                quadTo(367.0f, 80.0f, 400.0f, 80.0f)
                lineTo(560.0f, 80.0f)
                quadTo(593.0f, 80.0f, 616.5f, 103.5f)
                quadTo(640.0f, 127.0f, 640.0f, 160.0f)
                lineTo(640.0f, 240.0f)
                lineTo(800.0f, 240.0f)
                quadTo(833.0f, 240.0f, 856.5f, 263.5f)
                quadTo(880.0f, 287.0f, 880.0f, 320.0f)
                lineTo(880.0f, 760.0f)
                quadTo(880.0f, 793.0f, 856.5f, 816.5f)
                quadTo(833.0f, 840.0f, 800.0f, 840.0f)
                lineTo(160.0f, 840.0f)
                close()
                moveTo(400.0f, 240.0f)
                lineTo(560.0f, 240.0f)
                lineTo(560.0f, 160.0f)
                quadTo(560.0f, 160.0f, 560.0f, 160.0f)
                quadTo(560.0f, 160.0f, 560.0f, 160.0f)
                lineTo(400.0f, 160.0f)
                quadTo(400.0f, 160.0f, 400.0f, 160.0f)
                quadTo(400.0f, 160.0f, 400.0f, 160.0f)
                lineTo(400.0f, 240.0f)
                close()
                moveTo(800.0f, 600.0f)
                lineTo(600.0f, 600.0f)
                lineTo(600.0f, 680.0f)
                lineTo(360.0f, 680.0f)
                lineTo(360.0f, 600.0f)
                lineTo(160.0f, 600.0f)
                lineTo(160.0f, 760.0f)
                quadTo(160.0f, 760.0f, 160.0f, 760.0f)
                quadTo(160.0f, 760.0f, 160.0f, 760.0f)
                lineTo(800.0f, 760.0f)
                quadTo(800.0f, 760.0f, 800.0f, 760.0f)
                quadTo(800.0f, 760.0f, 800.0f, 760.0f)
                lineTo(800.0f, 600.0f)
                close()
                moveTo(440.0f, 600.0f)
                lineTo(520.0f, 600.0f)
                lineTo(520.0f, 520.0f)
                lineTo(440.0f, 520.0f)
                lineTo(440.0f, 600.0f)
                close()
                moveTo(160.0f, 520.0f)
                lineTo(360.0f, 520.0f)
                lineTo(360.0f, 440.0f)
                lineTo(600.0f, 440.0f)
                lineTo(600.0f, 520.0f)
                lineTo(800.0f, 520.0f)
                lineTo(800.0f, 320.0f)
                quadTo(800.0f, 320.0f, 800.0f, 320.0f)
                quadTo(800.0f, 320.0f, 800.0f, 320.0f)
                lineTo(160.0f, 320.0f)
                quadTo(160.0f, 320.0f, 160.0f, 320.0f)
                quadTo(160.0f, 320.0f, 160.0f, 320.0f)
                lineTo(160.0f, 520.0f)
                close()
                moveTo(480.0f, 560.0f)
                lineTo(480.0f, 560.0f)
                lineTo(480.0f, 560.0f)
                lineTo(480.0f, 560.0f)
                lineTo(480.0f, 560.0f)
                lineTo(480.0f, 560.0f)
                quadTo(480.0f, 560.0f, 480.0f, 560.0f)
                quadTo(480.0f, 560.0f, 480.0f, 560.0f)
                lineTo(480.0f, 560.0f)
                quadTo(480.0f, 560.0f, 480.0f, 560.0f)
                quadTo(480.0f, 560.0f, 480.0f, 560.0f)
                lineTo(480.0f, 560.0f)
                lineTo(480.0f, 560.0f)
                lineTo(480.0f, 560.0f)
                lineTo(480.0f, 560.0f)
                lineTo(480.0f, 560.0f)
                quadTo(480.0f, 560.0f, 480.0f, 560.0f)
                quadTo(480.0f, 560.0f, 480.0f, 560.0f)
                lineTo(480.0f, 560.0f)
                quadTo(480.0f, 560.0f, 480.0f, 560.0f)
                quadTo(480.0f, 560.0f, 480.0f, 560.0f)
                close()
            }
        }
        .build()
        return _portfolio!!
    }

private var _portfolio: ImageVector? = null
