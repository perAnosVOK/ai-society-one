package org.aisociety.one

import android.app.Activity
import android.os.Bundle
import android.graphics.Color
import android.view.Gravity
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.LinearLayout
import android.widget.ScrollView
import android.widget.TextView

/** Minimal native-views shell: no third-party UI dependency and no network permission. */
class MainActivity : Activity() {
    private lateinit var content: LinearLayout
    private var driving = false
    private val messages = mutableListOf("ИИ: Доброе утро! Как дела?")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        driving = savedInstanceState?.getBoolean("driving") ?: false
        showHome()
    }

    override fun onSaveInstanceState(outState: Bundle) {
        outState.putBoolean("driving", driving)
        super.onSaveInstanceState(outState)
    }

    private fun base(title: String) {
        val root = LinearLayout(this).apply {
            orientation = LinearLayout.VERTICAL
            setPadding(18, 24, 18, 18)
            setBackgroundColor(Color.WHITE)
        }
        val header = TextView(this).apply {
            text = title
            textSize = 24f
            setTextColor(Color.rgb(25, 38, 65))
            setPadding(0, 0, 0, 16)
        }
        root.addView(header)
        val scroll = ScrollView(this)
        content = LinearLayout(this).apply { orientation = LinearLayout.VERTICAL }
        scroll.addView(content)
        root.addView(scroll, LinearLayout.LayoutParams(-1, 0, 1f))
        val nav = LinearLayout(this).apply { orientation = LinearLayout.HORIZONTAL; gravity = Gravity.CENTER }
        listOf("ИИ", "Чаты", "Лента", "Почта", "Сеть").forEach { tab ->
            nav.addView(Button(this).apply {
                text = tab
                textSize = 10f
                isAllCaps = false
                setOnClickListener { if (tab == "ИИ") showHome() else showPlaceholder(tab) }
            }, LinearLayout.LayoutParams(0, -2, 1f))
        }
        root.addView(nav)
        setContentView(root)
    }

    private fun line(text: String) {
        content.addView(TextView(this).apply {
            this.text = text
            textSize = 16f
            setTextColor(Color.DKGRAY)
            setPadding(6, 10, 6, 10)
        })
    }

    private fun showHome() {
        base("AI Society One · локальный ИИ")
        line("Демо без интернета. Сообщения не отправляются другим людям.")
        messages.forEach { line(it) }
        val entry = EditText(this).apply {
            hint = "Напишите сообщение"
            setSingleLine(false)
            minLines = 1
        }
        content.addView(entry)
        content.addView(Button(this).apply {
            text = "Отправить локальному ИИ"
            setOnClickListener {
                val value = entry.text.toString().trim()
                if (value.isNotEmpty()) {
                    messages.add("Вы: $value")
                    messages.add("ИИ: ${LocalAssistant.reply(value)}")
                    showHome()
                }
            }
        })
        line(if (driving) "Режим «За рулём»: включён (демо)" else "Режим «За рулём»: выключен")
        content.addView(Button(this).apply {
            text = if (driving) "Выключить автоответ" else "Включить автоответ"
            setOnClickListener { driving = !driving; showHome() }
        })
        if (driving) line("Шаблон: Сейчас я за рулём. Это автоматический ответ. Напишу, когда остановлюсь.")
        line("Автоответ пока только отображается: отправка появится после реализации собственного мессенджера.")
    }

    private fun showPlaceholder(name: String) {
        base(name)
        line("Раздел «$name» запланирован. Сетевые функции и пользовательские аккаунты пока не реализованы.")
        if (name == "Сеть") line("Встроенный браузер будет добавлен отдельно с безопасными настройками WebView.")
    }
}
