/*
 * Copyright 2019 New Vector Ltd
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package im.vector.app.features.notifications

import im.vector.app.ActiveSessionDataSource
import org.matrix.android.sdk.api.session.getRoom
import javax.inject.Inject
import timber.log.Timber

class OutdatedEventDetector @Inject constructor(
        private val activeSessionDataSource: ActiveSessionDataSource
) {

    /**
     * Returns true if the given event is outdated.
     * Used to clean up notifications if a displayed message has been read on an
     * other device.
     */
    fun isMessageOutdated(notifiableEvent: NotifiableEvent): Boolean {
        val session = activeSessionDataSource.currentValue?.orNull() ?: 
            return false.also{Timber.e("DEADBEEF: ID = 10, session null")}

        Timber.e("DEADBEEF: ID = 11, notifiableEvent: ready")
        if (notifiableEvent is NotifiableMessageEvent) {
            Timber.e("DEADBEEF: ID = 11, notifiableEvent: taken")

            val eventID = notifiableEvent.eventId
            val roomID = notifiableEvent.roomId
            val room = session.getRoom(roomID) ?: return false.also{Timber.e("DEADBEEF: ID = 12, room null: return false")}
            return room.readService().isEventRead(eventID).also{Timber.e("DEADBEEF: ID = 13, return value: isEventRead() ")}
        }
        return false.also{Timber.e("DEADBEEF: ID = 14, return false")}
    }
}
