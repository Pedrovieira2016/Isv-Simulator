package vieira.apps.simulators

import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale


fun Date.formatToDefaultDate(): String {
    val sdf = SimpleDateFormat("MM-yyyy", Locale.getDefault())
    return sdf.format(this)
}
