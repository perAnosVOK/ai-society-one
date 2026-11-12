package org.aisociety.one

import java.util.Locale

/** Offline deterministic assistant. No network calls, model downloads or personal data uploads. */
object LocalAssistant {
    fun reply(message: String): String {
        val text = message.trim().lowercase(Locale.ROOT)
        return when {
            text.isBlank() -> "Напишите сообщение."
            listOf("привет", "здравствуй", "доброе утро", "добрый день", "добрый вечер").any { text.contains(it) } ->
                "Привет! Как дела? Я могу помочь с простыми вопросами даже без интернета."
            "как дела" in text -> "Спасибо, всё хорошо! Чем могу помочь?"
            "спасибо" in text -> "Пожалуйста!"
            "пока" in text -> "До встречи!"
            "за рул" in text -> "Включите режим «За рулём», чтобы подготовить автоматический ответ."
            "время" in text -> "Время можно посмотреть в строке состояния телефона."
            else -> "Для корректного ответа на этот вопрос мне нужно подключение к интернету. Сейчас доступен только простой локальный помощник."
        }
    }
}
