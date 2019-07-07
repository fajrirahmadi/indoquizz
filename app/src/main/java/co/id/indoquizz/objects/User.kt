package co.id.indoquizz.objects

import co.id.indoquizz.R

class User(
    var uid: String?,
    var name: String,
    var email: String,
    var gender: String?,
    var address: String?,
    var urlImage: String?,
    var grade: GradeType,
    var birthDate: Long? = 0L,
    var friendList: Map<String, Boolean> = HashMap(),
    var lat: Long = 0L,
    var lot: Long = 0L,
    var point: Long = 0,
    var life: Long = 0
) {
    constructor() : this("", "", GradeType.BRONZE)

    constructor(name: String, email: String, grade: GradeType) : this(null, name, email, null, null, null, grade)
}

enum class GradeType(val icon: Int, val label: String) {
    GOLD(R.drawable.ic_medal_gold, "Golongan Emas"),
    SILVER(R.drawable.ic_medal_silver, "Golongan Perak"),
    BRONZE(R.drawable.ic_medal_bronze, "Golongan Perunggu")
}