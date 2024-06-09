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

public val MyIcons.NavbarIconsSearchInactive: ImageVector
    get() {
        if (_navbarIconsSearchInactive != null) {
            return _navbarIconsSearchInactive!!
        }
        _navbarIconsSearchInactive = Builder(name = "NavbarIconsSearchInactive", defaultWidth =
                24.0.dp, defaultHeight = 24.0.dp, viewportWidth = 24.0f, viewportHeight =
                24.0f).apply {
            path(fill = SolidColor(Color(0xFFC0C0C0)), stroke = null, strokeLineWidth = 0.0f,
                    strokeLineCap = Butt, strokeLineJoin = Miter, strokeLineMiter = 4.0f,
                    pathFillType = EvenOdd) {
                moveTo(11.0f, 18.0f)
                curveTo(7.134f, 18.0f, 4.0f, 14.866f, 4.0f, 11.0f)
                curveTo(4.0f, 7.134f, 7.134f, 4.0f, 11.0f, 4.0f)
                curveTo(14.866f, 4.0f, 18.0f, 7.134f, 18.0f, 11.0f)
                curveTo(18.0f, 14.866f, 14.866f, 18.0f, 11.0f, 18.0f)
                close()
                moveTo(2.0f, 11.0f)
                curveTo(2.0f, 6.029f, 6.029f, 2.0f, 11.0f, 2.0f)
                curveTo(15.971f, 2.0f, 20.0f, 6.029f, 20.0f, 11.0f)
                curveTo(20.0f, 13.125f, 19.264f, 15.078f, 18.032f, 16.618f)
                lineTo(21.707f, 20.293f)
                curveTo(22.098f, 20.683f, 22.098f, 21.317f, 21.707f, 21.707f)
                curveTo(21.317f, 22.098f, 20.683f, 22.098f, 20.293f, 21.707f)
                lineTo(16.618f, 18.032f)
                curveTo(15.078f, 19.264f, 13.125f, 20.0f, 11.0f, 20.0f)
                curveTo(6.029f, 20.0f, 2.0f, 15.971f, 2.0f, 11.0f)
                close()
            }
        }
        .build()
        return _navbarIconsSearchInactive!!
    }

private var _navbarIconsSearchInactive: ImageVector? = null
