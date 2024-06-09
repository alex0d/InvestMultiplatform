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

public val MyIcons.Cards: ImageVector
    get() {
        if (_cards != null) {
            return _cards!!
        }
        _cards = Builder(name = "Cards", defaultWidth = 24.0.dp, defaultHeight = 24.0.dp,
                viewportWidth = 960.0f, viewportHeight = 960.0f).apply {
            path(fill = SolidColor(Color(0xFF000000)), stroke = null, strokeLineWidth = 0.0f,
                    strokeLineCap = Butt, strokeLineJoin = Miter, strokeLineMiter = 4.0f,
                    pathFillType = NonZero) {
                moveTo(608.0f, 592.0f)
                lineTo(654.0f, 426.0f)
                lineTo(512.0f, 328.0f)
                lineTo(466.0f, 494.0f)
                lineTo(608.0f, 592.0f)
                close()
                moveTo(160.0f, 753.0f)
                lineTo(127.0f, 737.0f)
                quadTo(96.0f, 724.0f, 85.0f, 692.5f)
                quadTo(74.0f, 661.0f, 88.0f, 630.0f)
                lineTo(160.0f, 474.0f)
                lineTo(160.0f, 753.0f)
                close()
                moveTo(320.0f, 840.0f)
                quadTo(287.0f, 840.0f, 263.5f, 816.0f)
                quadTo(240.0f, 792.0f, 240.0f, 759.0f)
                lineTo(240.0f, 520.0f)
                lineTo(347.0f, 814.0f)
                quadTo(350.0f, 821.0f, 352.0f, 827.5f)
                quadTo(354.0f, 834.0f, 359.0f, 840.0f)
                lineTo(320.0f, 840.0f)
                close()
                moveTo(526.0f, 835.0f)
                quadTo(495.0f, 846.0f, 464.0f, 832.0f)
                quadTo(433.0f, 818.0f, 422.0f, 787.0f)
                lineTo(245.0f, 298.0f)
                quadTo(234.0f, 267.0f, 248.0f, 236.5f)
                quadTo(262.0f, 206.0f, 293.0f, 195.0f)
                lineTo(594.0f, 85.0f)
                quadTo(625.0f, 74.0f, 655.5f, 88.0f)
                quadTo(686.0f, 102.0f, 697.0f, 133.0f)
                lineTo(875.0f, 622.0f)
                quadTo(886.0f, 653.0f, 872.0f, 683.5f)
                quadTo(858.0f, 714.0f, 827.0f, 725.0f)
                lineTo(526.0f, 835.0f)
                close()
                moveTo(498.0f, 760.0f)
                lineTo(800.0f, 650.0f)
                quadTo(800.0f, 650.0f, 800.0f, 650.0f)
                quadTo(800.0f, 650.0f, 800.0f, 650.0f)
                lineTo(621.0f, 160.0f)
                quadTo(621.0f, 160.0f, 621.0f, 160.0f)
                quadTo(621.0f, 160.0f, 621.0f, 160.0f)
                lineTo(320.0f, 270.0f)
                quadTo(320.0f, 270.0f, 320.0f, 270.0f)
                quadTo(320.0f, 270.0f, 320.0f, 270.0f)
                lineTo(498.0f, 760.0f)
                quadTo(498.0f, 760.0f, 498.0f, 760.0f)
                quadTo(498.0f, 760.0f, 498.0f, 760.0f)
                close()
                moveTo(560.0f, 460.0f)
                quadTo(560.0f, 460.0f, 560.0f, 460.0f)
                quadTo(560.0f, 460.0f, 560.0f, 460.0f)
                lineTo(560.0f, 460.0f)
                quadTo(560.0f, 460.0f, 560.0f, 460.0f)
                quadTo(560.0f, 460.0f, 560.0f, 460.0f)
                lineTo(560.0f, 460.0f)
                quadTo(560.0f, 460.0f, 560.0f, 460.0f)
                quadTo(560.0f, 460.0f, 560.0f, 460.0f)
                lineTo(560.0f, 460.0f)
                quadTo(560.0f, 460.0f, 560.0f, 460.0f)
                quadTo(560.0f, 460.0f, 560.0f, 460.0f)
                lineTo(560.0f, 460.0f)
                close()
            }
        }
        .build()
        return _cards!!
    }

private var _cards: ImageVector? = null
