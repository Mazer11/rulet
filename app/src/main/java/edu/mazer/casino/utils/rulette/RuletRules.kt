package edu.mazer.casino.utils.rulette

data class RuletteItem(
    val number: Int,
    val color: RuletteColor?
)

object RuletRules {

    val list = listOf(
        RuletteItem(0, null),
        RuletteItem(32, RuletteColor.Red),
        RuletteItem(15, RuletteColor.Black),
        RuletteItem(19, RuletteColor.Red),
        RuletteItem(4, RuletteColor.Black),
        RuletteItem(21, RuletteColor.Red),
        RuletteItem(2, RuletteColor.Black),
        RuletteItem(25, RuletteColor.Red),
        RuletteItem(17, RuletteColor.Black),
        RuletteItem(34, RuletteColor.Red),
        RuletteItem(6, RuletteColor.Black),
        RuletteItem(27, RuletteColor.Red),
        RuletteItem(13, RuletteColor.Black),
        RuletteItem(36, RuletteColor.Red),
        RuletteItem(11, RuletteColor.Black),
        RuletteItem(30, RuletteColor.Red),
        RuletteItem(8, RuletteColor.Black),
        RuletteItem(23, RuletteColor.Red),
        RuletteItem(10, RuletteColor.Black),
        RuletteItem(5, RuletteColor.Red),
        RuletteItem(24, RuletteColor.Black),
        RuletteItem(16, RuletteColor.Red),
        RuletteItem(33, RuletteColor.Black),
        RuletteItem(1, RuletteColor.Red),
        RuletteItem(20, RuletteColor.Black),
        RuletteItem(14, RuletteColor.Red),
        RuletteItem(31, RuletteColor.Black),
        RuletteItem(9, RuletteColor.Red),
        RuletteItem(22, RuletteColor.Black),
        RuletteItem(18, RuletteColor.Red),
        RuletteItem(29, RuletteColor.Black),
        RuletteItem(7, RuletteColor.Red),
        RuletteItem(28, RuletteColor.Black),
        RuletteItem(12, RuletteColor.Red),
        RuletteItem(35, RuletteColor.Black),
        RuletteItem(3, RuletteColor.Red),
        RuletteItem(26, RuletteColor.Black)
    )

}