package vieira.apps.simulators.ui

import android.app.DatePickerDialog
import android.os.Bundle
import android.view.*
import android.widget.NumberPicker
import androidx.fragment.app.DialogFragment
import vieira.apps.simulators.R
import vieira.apps.simulators.databinding.DialogDatePickerBinding
import vieira.apps.simulators.setSafeOnClickListener


/**
 * https://stackoverflow.com/questions/21321789/android-datepicker-change-to-only-month-and-year
 */

class AppDatePickerDialog : DialogFragment() {

    private var listener: DatePickerDialog.OnDateSetListener? = null

    private var _binding: DialogDatePickerBinding? = null
    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    private var monthVal = -1
    private var yearVal = -1
    private var maxYearVal = -1
    private var minYearVal = -1


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val extras = arguments
        if (extras != null) {
            monthVal = extras.getInt(MONTH_KEY, -1)
            yearVal = extras.getInt(YEAR_KEY, -1)
            maxYearVal = extras.getInt(MAX_YEAR_KEY, -1)
            minYearVal = extras.getInt(MIN_YEAR_KEY, -1)
        }
    }

    fun setListener(listener: DatePickerDialog.OnDateSetListener) {
        this.listener = listener
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        dialog?.window?.setBackgroundDrawableResource(R.drawable.bg_dialog_picker)

        _binding = DialogDatePickerBinding.inflate(inflater, container, false)

        binding.datepickerCancel.setSafeOnClickListener {
            this@AppDatePickerDialog.dialog!!.cancel()
        }

        binding.datepickerConfirm.setSafeOnClickListener {
            confirmDatePicker()
        }
        setNumberPickerMonth()
        setNumberPickerYear()
        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun setNumberPickerMonth() {
        val monthPicker: NumberPicker = binding.datepickerMonth

        monthPicker.minValue = 0
        monthPicker.maxValue = 11

        monthPicker.value = monthVal

        monthPicker.displayedValues = resources.getStringArray(R.array.months_array)

    }

    private fun setNumberPickerYear() {
        val yearPicker: NumberPicker = binding.datepickerYear

        val maxYear = maxYearVal
        val minYear = minYearVal
        val arraySize = maxYear - minYear + 1

        val tempArray = arrayOfNulls<String>(arraySize)

        var tempYear = minYear

        for (i in 0 until arraySize) {
            if (i == 0 && arraySize > 10) {
                tempArray[i] = "$tempYear".plus(" ").plus(getString(R.string.or_before))
            } else {
                tempArray[i] = "$tempYear"
            }
            tempYear++
        }

        yearPicker.minValue = minYear
        yearPicker.maxValue = maxYear

        yearPicker.value = yearVal

        yearPicker.displayedValues = tempArray
    }

    override fun onStart() {
        super.onStart()
        dialog?.window?.setLayout(
            WindowManager.LayoutParams.MATCH_PARENT,
            WindowManager.LayoutParams.WRAP_CONTENT
        )
    }

    private fun confirmDatePicker() {
        val yearPicker: NumberPicker = binding.datepickerYear
        val monthPicker: NumberPicker = binding.datepickerMonth

        listener!!.onDateSet(null, yearPicker.value, monthPicker.value, 0)

        dialog!!.dismiss()
    }

    companion object {

        private const val TAG = "YearPickerDialog"

        const val MONTH_KEY = "monthValue"
        const val YEAR_KEY = "yearValue"
        const val MAX_YEAR_KEY = "maxyearValue"
        const val MIN_YEAR_KEY = "minyearValue"

        fun newInstance(
            appDateReceiver: AppDateReceiver
        ): AppDatePickerDialog {

            val pickerDialog = AppDatePickerDialog()

            // Supply num input as an argument.
            val args = Bundle()
            args.putInt(MONTH_KEY, appDateReceiver.currentMonthIndex)
            args.putInt(YEAR_KEY, appDateReceiver.currentYearIndex)
            args.putInt(MAX_YEAR_KEY, appDateReceiver.maxYearIndex)
            args.putInt(MIN_YEAR_KEY, appDateReceiver.minYearIndex)
            pickerDialog.arguments = args

            return pickerDialog
        }

        data class AppDateReceiver(
            val currentMonthIndex: Int,
            val currentYearIndex: Int,
            val maxYearIndex: Int,
            val minYearIndex: Int
        )
    }
}
