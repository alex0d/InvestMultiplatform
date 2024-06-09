package data.mappers

import data.remote.models.ShareDto
import domain.models.Share

fun ShareDto.toShare() = Share(
    uid = uid,
    figi = figi,
    ticker = ticker,
    classCode = classCode,
    isin = isin,
    currency = currency,
    name = name,
    countryOfRisk = countryOfRisk,
    countryOfRiskName = countryOfRiskName,
    sector = sector,
    lastPrice = lastPrice,
    lot = lot,
    url = url,
    textColor = textColor,
    backgroundColor = backgroundColor
)