import androidx.compose.ui.graphics.vector.ImageVector
import myicons.*
import kotlin.collections.List as ____KtList

public object MyIcons

private var __AllIcons: ____KtList<ImageVector>? = null

public val MyIcons.AllIcons: ____KtList<ImageVector>
  get() {
    if (__AllIcons != null) {
      return __AllIcons!!
    }
    __AllIcons= listOf(AccountCircle, CandlestickChart, Cards, IcChevronLeft, IcCloseSearch,
        LineChart, Minus, NavbarIconsSearchActive, NavbarIconsSearchInactive, Plus, Portfolio,
        Refresh, Search, SentimentDissatisfied)
    return __AllIcons!!
  }
