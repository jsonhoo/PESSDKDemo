package ai.pensees.sdkdemo.layout

import ai.pensees.sdkdemo.R
import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import android.widget.FrameLayout
import android.widget.TextView

/**
 * @author liujiansheng
 * @since 2021/1/24
 */
class DialLayout : FrameLayout, View.OnClickListener {
    private var tv0: TextView? = null
    private var tv1: TextView? = null
    private var tv2: TextView? = null
    private var tv3: TextView? = null
    private var tv4: TextView? = null
    private var tv5: TextView? = null
    private var tv6: TextView? = null
    private var tv7: TextView? = null
    private var tv8: TextView? = null
    private var tv9: TextView? = null
    private var tvBack: TextView? = null
    private var tvClear: TextView? = null
    private var tvInputText: TextView? = null
    private var mInputTextBuilder: StringBuilder = StringBuilder("")
    var mListener: DialListener? = null

    constructor(context: Context) : super(context) {
        initView()
    }

    constructor(context: Context, attrs: AttributeSet?) : super(context, attrs) {
        initView()
    }

    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : super(context, attrs, defStyleAttr) {
        initView()
    }

    private fun initView() {
        LayoutInflater.from(context).inflate(R.layout.dial_layout, this, true)
        tvInputText = findViewById(R.id.inputText)
        tv0 = findViewById(R.id.tv0)
        tv1 = findViewById(R.id.tv1)
        tv2 = findViewById(R.id.tv2)
        tv3 = findViewById(R.id.tv3)
        tv4 = findViewById(R.id.tv4)
        tv5 = findViewById(R.id.tv5)
        tv6 = findViewById(R.id.tv6)
        tv7 = findViewById(R.id.tv7)
        tv8 = findViewById(R.id.tv8)
        tv9 = findViewById(R.id.tv9)
        tvBack = findViewById(R.id.tvDelete)
        tvClear = findViewById(R.id.tvClear)
        tv0?.setOnClickListener(this)
        tv1?.setOnClickListener(this)
        tv2?.setOnClickListener(this)
        tv3?.setOnClickListener(this)
        tv4?.setOnClickListener(this)
        tv5?.setOnClickListener(this)
        tv6?.setOnClickListener(this)
        tv7?.setOnClickListener(this)
        tv8?.setOnClickListener(this)
        tv9?.setOnClickListener(this)
        tvBack?.setOnClickListener(this)
        tvClear?.setOnClickListener(this)
        findViewById<View>(R.id.close_btn).setOnClickListener(this)
    }

    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.tv0 -> {
                mInputTextBuilder.append(0)
            }
            R.id.tv1 -> {
                mInputTextBuilder.append(1)
            }
            R.id.tv2 -> {
                mInputTextBuilder.append(2)
            }
            R.id.tv3 -> {
                mInputTextBuilder.append(3)
            }
            R.id.tv4 -> {
                mInputTextBuilder.append(4)

            }
            R.id.tv5 -> {
                mInputTextBuilder.append(5)
            }
            R.id.tv6 -> {
                mInputTextBuilder.append(6)
            }
            R.id.tv7 -> {
                mInputTextBuilder.append(7)
            }
            R.id.tv8 -> {
                mInputTextBuilder.append(8)
            }
            R.id.tv9 -> {
                mInputTextBuilder.append(9)
            }
            R.id.tvClear -> {
                mInputTextBuilder.clear()
            }
            R.id.tvDelete -> {
                if (mInputTextBuilder.isNotEmpty()) {
                    mInputTextBuilder.deleteCharAt(mInputTextBuilder.length - 1)
                }
            }
            R.id.tvConfirm -> {
                mListener?.onConfirm(mInputTextBuilder.toString())
            }
            R.id.close_btn -> {
                visibility = View.GONE
                mListener?.onClose()
            }
        }
        updateInputTextToView()
    }

    fun setInputHint(hintText: String) {
        tvInputText?.hint = hintText
    }

    private fun updateInputTextToView() {
        tvInputText?.text = mInputTextBuilder.toString()
    }

    abstract class DialListener {
        abstract fun onConfirm(inputText: String)
        open fun onClose() {}
    }
}