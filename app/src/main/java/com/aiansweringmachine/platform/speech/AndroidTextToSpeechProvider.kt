package com.aiansweringmachine.platform.speech

import android.content.Context
import android.os.Handler
import android.os.Looper
import android.speech.tts.TextToSpeech
import com.aiansweringmachine.domain.speech.TextToSpeechProvider
import dagger.hilt.android.qualifiers.ApplicationContext
import java.util.Locale
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class AndroidTextToSpeechProvider @Inject constructor(
    @ApplicationContext context: Context
) : TextToSpeechProvider, TextToSpeech.OnInitListener {
    private val mainHandler = Handler(Looper.getMainLooper())
    private val textToSpeech = TextToSpeech(context, this)
    private val pendingTexts = mutableListOf<String>()
    private val lock = Any()
    private var initialized = false

    override fun onInit(status: Int) {
        if (status == TextToSpeech.SUCCESS) {
            val arabicResult = textToSpeech.setLanguage(Locale("ar"))
            if (arabicResult == TextToSpeech.LANG_MISSING_DATA || arabicResult == TextToSpeech.LANG_NOT_SUPPORTED) {
                textToSpeech.language = Locale.US
            }
            synchronized(lock) {
                initialized = true
                pendingTexts.toList().forEach(::speakNow)
                pendingTexts.clear()
            }
        }
    }

    override fun speak(text: String) {
        mainHandler.post {
            synchronized(lock) {
                if (initialized) {
                    speakNow(text)
                } else {
                    pendingTexts.add(text)
                }
            }
        }
    }

    private fun speakNow(text: String) {
        textToSpeech.speak(text, TextToSpeech.QUEUE_ADD, null, "ai-demo-${System.nanoTime()}")
    }

    override fun stop() {
        mainHandler.post {
            synchronized(lock) { pendingTexts.clear() }
            textToSpeech.stop()
        }
    }
}
