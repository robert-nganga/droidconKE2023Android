/*
 * Copyright 2022 DroidconKE
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.android254.data.dao

import androidx.room.Dao
import androidx.room.Query
import com.android254.data.db.model.SpeakerEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface SpeakerDao : BaseDao<SpeakerEntity> {
    @Query("SELECT * FROM speakers")
    fun fetchSpeakers(): Flow<List<SpeakerEntity>>

    @Query("SELECT COUNT(*) FROM speakers")
    fun fetchSpeakerCount(): Flow<Int>

    @Query("SELECT * FROM speakers WHERE id = :id")
    suspend fun getSpeakerById(id: Int): SpeakerEntity?

    @Query("DELETE FROM speakers")
    suspend fun deleteAll()
}