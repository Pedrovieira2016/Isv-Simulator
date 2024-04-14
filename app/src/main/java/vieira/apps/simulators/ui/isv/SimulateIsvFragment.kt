package vieira.apps.simulators.ui.isv

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import dagger.hilt.android.AndroidEntryPoint
import vieira.apps.simulators.databinding.FragmentSimulateIsvBinding
import vieira.apps.simulators.ui.AppDatePickerDialog
import java.util.Calendar
import androidx.fragment.app.viewModels
import vieira.apps.simulators.*
import vieira.apps.simulators.ui.AppItemPickerDialog
import vieira.apps.simulators.ui.ItemPickerListener
import java.util.Date


@AndroidEntryPoint
class SimulateIsvFragment : Fragment() {

    private val viewModel: SimulateIsvViewModel by viewModels()

    private var _binding: FragmentSimulateIsvBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    private var fuelType: FuelType = FuelType.NotDefined

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentSimulateIsvBinding.inflate(inflater, container, false)

        (activity as MainActivity).supportActionBar?.title = getString(R.string.simulate_isv)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        observeFuelType()
        originOfAcquisition()

        binding.acquisitionDatePickerSpinner.setSafeOnClickListener { openDatePicker() }
        binding.simulateButton.setSafeOnClickListener {
            viewModel.simulateIsv(
                cylinderCapacity = binding.ccInputEditText.text.toString(),
                co2Emissions = binding.emissionsInputEditText.text.toString(),
                fuelType = fuelType,
                dateOfFirstRegistration = getAcquisitionDate()
            )
        }
    }

    private fun getAcquisitionDate(): Date {
        val cal: Calendar = Calendar.getInstance()
        cal.set(Calendar.YEAR, selectedAcquisitionYear)
        cal.set(Calendar.MONTH, selectedAcquisitionMonth)
        return cal.time
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun observeFuelType() {
        binding.fuelTypeGroup.setOnCheckedChangeListener { _, i ->
            fuelType = when (i) {
                binding.gasolineRadioButton.id -> FuelType.Gasoline
                binding.dieselRadioButton.id -> FuelType.Diesel
                binding.hybridRadioButton.id -> FuelType.Hybrid
                binding.plugInRadioButton.id -> FuelType.HybridPlugIn
                else -> FuelType.NotDefined
            }
        }
    }

    private fun openDatePicker() {
        val calendar = Calendar.getInstance()
        // current date
        val yearDialog: AppDatePickerDialog = AppDatePickerDialog.newInstance(
            AppDatePickerDialog.Companion.AppDateReceiver(
                selectedAcquisitionMonth,
                selectedAcquisitionYear,
                calendar[Calendar.YEAR],
                2008
            )
        )
        yearDialog.setListener { _, receivedYear, receivedMonth, _ ->
            Log.e(
                TAG,
                "openDatePicker: Selected Date ( MM/dd  or. MM/dd/yyyy ) : $receivedYear $receivedMonth"
            )
            //set selected date
            selectedAcquisitionMonth = receivedMonth
            selectedAcquisitionYear = receivedYear

            binding.acquisitionDatePickerSpinner.setText(getAcquisitionDate().formatToDefaultDate())
        }
        yearDialog.show(childFragmentManager, EXTRA_DATE_PICKER)
    }

    private fun originOfAcquisition() {
        binding.originOfAcquisition.getInputOptionView().setSafeOnClickListener {
            val picker = AppItemPickerDialog.newInstance(
                resources.getStringArray(R.array.origin_of_acquisition),
                originOfAcquisition
            )
            val watcher = object : ItemPickerListener {
                override fun setItemPickerListener(itemPos: Int) {
                    binding.originOfAcquisition.setViewText(
                        resources.getStringArray(R.array.origin_of_acquisition)[itemPos]
                    )

                    originOfAcquisition = itemPos

                    //todo handle viewmodel data
                }
            }
            picker.setListener(watcher)
            picker.show(childFragmentManager, TAG)
        }
    }

    companion object {
        private const val TAG = "CapitalGainsFragment"
        private const val EXTRA_DATE_PICKER = "STRING_DATE_PICKER"

        private var selectedAcquisitionMonth = -1
        private var selectedAcquisitionYear = -1

        private var originOfAcquisition = -1

        fun newInstance() = SimulateIsvFragment()
    }
}
