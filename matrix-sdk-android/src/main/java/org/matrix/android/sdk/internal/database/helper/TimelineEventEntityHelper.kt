/*
 * Copyright 2020 The Matrix.org Foundation C.I.C.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.matrix.android.sdk.internal.database.helper

import io.realm.Realm
import org.matrix.android.sdk.internal.database.model.TimelineEventEntity
import org.matrix.android.sdk.internal.database.model.TimelineEventEntityFields
import org.matrix.android.sdk.internal.database.query.where
import timber.log.Timber

internal fun TimelineEventEntity.Companion.nextId(realm: Realm): Long {
    val currentIdNum = TimelineEventEntity.where(realm).max(TimelineEventEntityFields.LOCAL_ID)
    return if (currentIdNum == null) {
        1
    } else {
        currentIdNum.toLong() + 1
    }
}

internal fun TimelineEventEntity.isMoreRecentThan(eventToCheck: TimelineEventEntity): Boolean {
    val currentChunk = this.chunk?.first(null) ?: return false.also{Timber.w("DEADBEEF: ID=50 ${it}")}
    val chunkToCheck = eventToCheck.chunk?.first(null) ?: return false.also{Timber.w("DEADBEEF: ID=51 ${it}")}
    return if ((currentChunk == chunkToCheck).also{Timber.w("DEADBEEF: ID=52 ${it}")}) {
        val rez = this.displayIndex >= eventToCheck.displayIndex
        Timber.w("DEADBEEF: ID=53 ${rez}, compare: ${this.eventId} with ${eventToCheck.eventId}")
        return rez;
    } else {
        currentChunk.isMoreRecentThan(chunkToCheck).also{Timber.w("DEADBEEF: ID=54 ${it}")}
                
    }
}
