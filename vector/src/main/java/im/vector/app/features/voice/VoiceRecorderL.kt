/*
 * Copyright (c) 2021 New Vector Ltd
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

package im.vector.app.features.voice

import android.content.Context
import android.media.MediaRecorder
import java.io.File

class VoiceRecorderL(context: Context) : AbstractVoiceRecorder(context, "mp4") {
    override fun setOutputFormat(mediaRecorder: MediaRecorder) {
        // Use AAC/MP4 format here
        mediaRecorder.setOutputFormat(MediaRecorder.OutputFormat.MPEG_4)
        mediaRecorder.setAudioEncoder(MediaRecorder.AudioEncoder.AAC)
    }

    // Build note: com.arthenica:ffmpeg-kit-audio was pulled from every
    // public Maven repo, so the mp4->ogg conversion is stubbed to a
    // passthrough here instead of pulling in a full native module
    // replacement.
    override fun convertFile(recordedFile: File?): File? {
        return recordedFile
    }
}
