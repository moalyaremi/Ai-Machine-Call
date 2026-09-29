package com.aiansweringmachine.domain.speech

interface TextToSpeechProvider {
    fun speak(text: String)
    fun stop()
}
