package com.erkindilekci.tictacgo.di

import com.erkindilekci.tictacgo.data.client.GameClient
import com.erkindilekci.tictacgo.data.client.KtorGameClient
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import io.ktor.client.HttpClient
import io.ktor.client.engine.cio.CIO
import io.ktor.client.plugins.logging.Logging
import io.ktor.client.plugins.websocket.WebSockets
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideHttpClient(): HttpClient {
        return HttpClient(CIO) {
            install(Logging)
            install(WebSockets)
        }
    }

    @Provides
    @Singleton
    fun provideGameClient(httpClient: HttpClient): GameClient = KtorGameClient(httpClient)
}
