package com.benmohammad.micomplie.ui.views

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.Rect
import android.util.AttributeSet
import androidx.appcompat.widget.AppCompatEditText
import com.benmohammad.micomplie.R

class EditorView: AppCompatEditText {

    constructor(context: Context): super(context)

    constructor(context: Context, attrs: AttributeSet?): super(context, attrs)

    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int): super(
        context, attrs, defStyleAttr
    ) {
        init(context, attrs, defStyleAttr)
    }

    private var editorWrapView = false
    private var mLineBounds = Rect()
    private var mPaintHighlight = Paint().apply {
        isAntiAlias = false
        style = Paint.Style.FILL
        color = Color.parseColor("#45e7e8d1")
    }

    private var mHighlightedLine = -1
    private var mHighlightStart = -1

    private fun init(context: Context, attrs: AttributeSet?, defStyleAttr: Int) {
        val attributes =
            context.obtainStyledAttributes(
                attrs,
                R.styleable.EditorView, defStyleAttr, 0
            )

        editorWrapView =
            attributes.getBoolean(R.styleable.EditorView_editor_wrap_text, false)

        attributes.recycle()
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        computeLineHighlight()

        for(i in 0 until lineCount) {
            if((i == mHighlightedLine)) {
                getLineBounds(mHighlightedLine, mLineBounds)
                mLineBounds.left = 0
                canvas.drawRect(mLineBounds, mPaintHighlight)

            }
        }
    }

    private fun computeLineHighlight() {
        var i: Int
        var line: Int
        val selStart: Int = selectionStart
        val text: String

        if(!isEnabled) {
            return
        }

        if(mHighlightStart != selStart) {
            text = getText()!!.toString()
            i = 0
            line = i
            while(i < selStart) {
                i = text.indexOf("\n", i)
                if(i < 0) {
                    break
                }
                if(i < selStart) {
                    ++line
                }
                ++i
            }
            mHighlightedLine = line
        }
    }

    fun insert(s: String) {
        val start = selectionStart
        val end = selectionEnd


            text?.replace(
                start.coerceAtMost(end),
                start.coerceAtLeast(end),
                s,
                0,
                s.length

            )
        }
    }




