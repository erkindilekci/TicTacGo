package com.erkindilekci.tictacgo.data.client

import com.erkindilekci.tictacgo.data.model.GameState
import com.erkindilekci.tictacgo.data.model.MakeTurn
import kotlinx.coroutines.flow.Flow

interface GameClient {

    fun getGameStateStream(): Flow<GameState>
    suspend fun sendAction(action: MakeTurn)
    suspend fun close()
}
