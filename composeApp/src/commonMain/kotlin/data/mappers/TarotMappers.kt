package data.mappers

import data.remote.models.TarotPredictionDto
import domain.models.TarotCard
import domain.models.TarotPrediction

//fun TarotPredictionDto.toTarotPredictionDbo(stockName: String) = TarotPredictionDbo(
//    stockName = stockName,
//    cardName = cardName,
//    prediction = prediction
//)

fun TarotPredictionDto.toTarotPrediction() = TarotPrediction(
    card = TarotCard.valueOf(cardName.uppercase()),
    prediction = prediction
)

//fun TarotPredictionDbo.toTarotPrediction() = TarotPrediction(
//    card = TarotCard.valueOf(cardName.uppercase()),
//    prediction = prediction
//)