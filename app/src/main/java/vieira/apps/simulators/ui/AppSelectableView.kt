package vieira.apps.simulators.ui

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.FrameLayout
import vieira.apps.simulators.R
import vieira.apps.simulators.databinding.LayoutInputOptionBinding
import vieira.apps.simulators.withTypedArray

open class AppSelectableView(context: Context, attrs: AttributeSet?) :
    FrameLayout(context, attrs) {

    protected open val binding =
        LayoutInputOptionBinding.inflate(LayoutInflater.from(context), this, true)

    init {
        withTypedArray(attrs, R.styleable.AppSelectableView) {
            viewHint(it.getString(R.styleable.AppSelectableView_viewHint) ?: "")
        }
    }

    fun viewHint(hint: String) {
        binding.tlInputOption.hint = hint
    }

    fun setViewText(text: String) {
        binding.etInputOption.setText(text)
    }

    fun getInputOptionView() = binding.etInputOption
}
