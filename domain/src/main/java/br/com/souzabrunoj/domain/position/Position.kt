package br.com.souzabrunoj.domain.position

data class Position(
    val company: String,
    val date: String,
    val jobAdTile: String,
    val locations: List<String>,
    val salary: Salary,
    val totalPositions: Int,
    var showSalary: Boolean = true
) {
    fun getLocation(): String {
        return when {
            locations.isEmpty() -> "$totalPositions vagas"
            locations.size > 1 -> "$totalPositions vagas - ${locations[0]} + ${locations.size - 1} cidades"
            else -> "$totalPositions vaga - ${locations[0]}"
        }
    }
}

