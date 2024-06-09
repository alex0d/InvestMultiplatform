package data.mappers

import data.remote.models.CandleDto
import domain.models.Candle

fun CandleDto.toCandle(): Candle {
    return Candle(
        timestamp = timestamp,
        open = open,
        close = close,
        high = high,
        low = low,
        volume = volume
    )
}