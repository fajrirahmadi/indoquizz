package co.id.indoquizz.objects

class Quiz(
    var qid: String?,
    var uid: String?,
    var question: String?,
    var questionType: QuestionType,
    var urlImage: String?,
    var mapAnswer: Map<String, Boolean> = HashMap(),
    var userAnswered: Map<String, Boolean> = HashMap(),
    var userLoved: Map<String, Boolean> = HashMap(),
    var createdDate: Long = System.currentTimeMillis()
) {

    constructor() : this(null, QuestionType.TRUEFALSE, HashMap())

    constructor(question: String?, questionType: QuestionType, mapAnswer: Map<String, Boolean>) : this(
        null,
        null,
        question,
        questionType,
        null,
        mapAnswer
    )
}

enum class QuestionType {
    TRUEFALSE, MULTIPLECHOISE
}