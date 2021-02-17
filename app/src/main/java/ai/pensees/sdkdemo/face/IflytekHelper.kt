package ai.pensees.sdkdemo.face

import android.content.Context
import android.os.Bundle
import com.iflytek.cloud.*

/**
 * @author liujiansheng
 * @since 2021/2/18
 */
object IflytekHelper {
    private var mIsInit = false

    fun init(context: Context) {
        if (mIsInit) {
            return
        }
        mIsInit = true
        getSynthesizer(context)
    }

    fun speaking(context: Context, message: String) {
        getSynthesizer(context)?.startSpeaking(message, object : SynthesizerListener {
            override fun onSpeakBegin() {
            }

            override fun onBufferProgress(p0: Int, p1: Int, p2: Int, p3: String?) {
            }

            override fun onSpeakPaused() {
            }

            override fun onSpeakResumed() {
            }

            override fun onSpeakProgress(p0: Int, p1: Int, p2: Int) {
            }

            override fun onCompleted(p0: SpeechError?) {
            }

            override fun onEvent(p0: Int, p1: Int, p2: Int, p3: Bundle?) {
            }
        })
    }

    private fun getSynthesizer(context: Context): SpeechSynthesizer? {
        if (SpeechSynthesizer.getSynthesizer() == null) {
            SpeechSynthesizer.createSynthesizer(context, InitListener {

            })
        }
        return SpeechSynthesizer.getSynthesizer()
    }

    fun releaseSDK() {
        if (SpeechSynthesizer.getSynthesizer() != null) {
            SpeechSynthesizer.getSynthesizer().destroy()
        }
        mIsInit = false
    }
}