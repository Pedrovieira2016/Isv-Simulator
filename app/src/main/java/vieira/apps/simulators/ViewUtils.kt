package vieira.apps.simulators

import android.content.res.TypedArray
import android.util.AttributeSet
import android.view.View
import androidx.annotation.AttrRes
import androidx.annotation.StyleRes
import androidx.core.content.res.use


fun View.setSafeOnClickListener(onSafeClick: (View) -> Unit) {
    val safeClickListener = SafeClickListener {
        onSafeClick(it)
    }
    setOnClickListener(safeClickListener)
}

fun View.withTypedArray(
    attrs: AttributeSet?,
    styleable: IntArray,
    @AttrRes defStyleAttr: Int = 0,
    @StyleRes defStyleRes: Int = 0,
    body: (ta: TypedArray) -> Unit
) {
    val ta = context.obtainStyledAttributes(attrs, styleable, defStyleAttr, defStyleRes)
    ta.use(body)
}
