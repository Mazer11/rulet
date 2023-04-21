package edu.mazer.casino.utils.rulette

data class RulettePrediction(
    var color: RuletteColor? = null,
    var number: Int? = null,
)

sealed class RuletteColor(val color: String){
    object Red: RuletteColor("Red")
    object Black: RuletteColor("Black")
}