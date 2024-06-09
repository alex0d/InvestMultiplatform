package myicons

import MyIcons
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.PathFillType.Companion.EvenOdd
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.graphics.StrokeCap.Companion.Butt
import androidx.compose.ui.graphics.StrokeJoin.Companion.Miter
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.graphics.vector.ImageVector.Builder
import androidx.compose.ui.graphics.vector.path
import androidx.compose.ui.unit.dp

public val MyIcons.IcCloseSearch: ImageVector
    get() {
        if (_icCloseSearch != null) {
            return _icCloseSearch!!
        }
        _icCloseSearch = Builder(name = "IcCloseSearch", defaultWidth = 16.0.dp, defaultHeight =
                16.0.dp, viewportWidth = 16.0f, viewportHeight = 16.0f).apply {
            path(fill = SolidColor(Color(0xFFC0C0C0)), stroke = null, strokeLineWidth = 0.0f,
                    strokeLineCap = Butt, strokeLineJoin = Miter, strokeLineMiter = 4.0f,
                    pathFillType = EvenOdd) {
                moveTo(12.472f, 4.471f)
                curveTo(12.732f, 4.211f, 12.732f, 3.789f, 12.472f, 3.529f)
                curveTo(12.211f, 3.268f, 11.789f, 3.268f, 11.529f, 3.529f)
                lineTo(8.0f, 7.057f)
                lineTo(4.472f, 3.529f)
                curveTo(4.211f, 3.268f, 3.789f, 3.268f, 3.529f, 3.529f)
                curveTo(3.268f, 3.789f, 3.268f, 4.211f, 3.529f, 4.471f)
                lineTo(7.057f, 8.0f)
                lineTo(3.529f, 11.528f)
                curveTo(3.268f, 11.789f, 3.268f, 12.211f, 3.529f, 12.471f)
                curveTo(3.789f, 12.732f, 4.211f, 12.732f, 4.472f, 12.471f)
                lineTo(8.0f, 8.943f)
                lineTo(11.529f, 12.471f)
                curveTo(11.789f, 12.732f, 12.211f, 12.732f, 12.472f, 12.471f)
                curveTo(12.732f, 12.211f, 12.732f, 11.789f, 12.472f, 11.528f)
                lineTo(8.943f, 8.0f)
                lineTo(12.472f, 4.471f)
                close()
            }
        }
        .build()
        return _icCloseSearch!!
    }

private var _icCloseSearch: ImageVector? = null
