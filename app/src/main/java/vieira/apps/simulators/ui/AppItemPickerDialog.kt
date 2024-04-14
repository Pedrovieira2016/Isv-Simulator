package vieira.apps.simulators.ui

import android.app.DatePickerDialog
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import androidx.fragment.app.DialogFragment
import androidx.recyclerview.widget.RecyclerView
import vieira.apps.simulators.R
import vieira.apps.simulators.databinding.ItemPickerBinding
import vieira.apps.simulators.databinding.LayoutItemPickerBinding
import vieira.apps.simulators.setSafeOnClickListener

class AppItemPickerDialog : DialogFragment() {

    private var selectedItem = -1
    private var itemsList: Array<String> = emptyArray()

    private var _binding: LayoutItemPickerBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    private var listener: ItemPickerListener? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            itemsList = it.getStringArray(ITEMS_KEY) as Array<String>
            selectedItem = it.getInt(SELECTED_ITEM_KEY)
        }
    }

    fun setListener(listener: ItemPickerListener) {
        this.listener = listener
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        dialog?.window?.setBackgroundDrawableResource(R.drawable.bg_dialog_picker)
        _binding = LayoutItemPickerBinding.inflate(inflater, container, false)

        binding.itemsPickerCancel.setSafeOnClickListener {
            this@AppItemPickerDialog.dialog?.cancel()
        }

        binding.apply {
            itemsPickerRecycler.adapter = ItemsPickerAdapter(itemsList.toList()).apply {
                itemClick = { position ->
                    Log.e(TAG, "onViewCreated: $position")
                    listener?.setItemPickerListener(position)
                    this@AppItemPickerDialog.dialog?.cancel()
                }
                setPreviousItemPosition(selectedItem)
            }
        }

        return binding.root
    }

    override fun onStart() {
        super.onStart()
        dialog?.window?.setLayout(
            WindowManager.LayoutParams.MATCH_PARENT,
            WindowManager.LayoutParams.WRAP_CONTENT
        )
    }

    companion object {
        const val ITEMS_KEY = "itemsValue"
        const val SELECTED_ITEM_KEY = "selectedItemPosition"
        const val TAG = "ItemPickerDialog"

        fun newInstance(
            itemsList: Array<String>,
            selectedItem: Int
        ): AppItemPickerDialog {

            val pickerDialog = AppItemPickerDialog()

            // Supply num input as an argument.
            val args = Bundle()
            args.putStringArray(ITEMS_KEY, itemsList)
            args.putInt(SELECTED_ITEM_KEY, selectedItem)

            pickerDialog.arguments = args

            return pickerDialog
        }
    }
}

private class ItemsPickerAdapter(val items: List<String>) :
    RecyclerView.Adapter<ItemsPickerAdapter.ItemsViewHolder>() {

    //    private val items: MutableList<String> = mutableListOf()

    var itemClick: ((Int) -> Unit)? = null
    var itemPosition: Int = -1

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemsViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemPickerBinding.inflate(inflater, parent, false)
        return ItemsViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return items.size
    }

    //    fun addItemsList(itemsList: List<String>) {
    //        items.addAll(itemsList)
    //        notifyDataSetChanged()
    //    }

    fun setPreviousItemPosition(position: Int) {
        itemPosition = position
    }

    override fun onBindViewHolder(holder: ItemsViewHolder, position: Int) {
        holder.binding.apply {
            itemText.text = items[position]
            root.setSafeOnClickListener {
                itemClick?.invoke(position)
            }
            holder.binding.itemText.isChecked = itemPosition == position
        }
    }

    class ItemsViewHolder(val binding: ItemPickerBinding) :
        RecyclerView.ViewHolder(binding.root)
}

interface ItemPickerListener {
    fun setItemPickerListener(itemPos: Int)
}
