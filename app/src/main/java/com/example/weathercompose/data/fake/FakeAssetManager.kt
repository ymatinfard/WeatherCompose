package com.example.weathercompose.data.fake

import java.io.InputStream

interface FakeAssetManager {
    fun open(fileName: String): InputStream
}
