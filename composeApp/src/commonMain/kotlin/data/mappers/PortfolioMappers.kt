package data.mappers

import data.remote.models.PortfolioInfoDto
import data.remote.models.PortfolioStockInfoDto
import domain.models.PortfolioInfo
import domain.models.PortfolioStockInfo

fun PortfolioInfoDto.toPortfolioInfo() = PortfolioInfo(
    totalValue = totalValue,
    totalProfit = totalProfit,
    totalProfitPercent = totalProfitPercent,
    stocks = stocks.map { it.toPortfolioStockInfo() }
)

fun PortfolioStockInfoDto.toPortfolioStockInfo() = PortfolioStockInfo(
    uid = uid,
    ticker = ticker,
    name = name,
    amount = amount,
    averagePrice = averagePrice,
    lastPrice = lastPrice,
    totalValue = totalValue,
    profit = profit,
    profitPercent = profitPercent,
    logoUrl = logoUrl,
    backgroundColor = backgroundColor,
    textColor = textColor
)
