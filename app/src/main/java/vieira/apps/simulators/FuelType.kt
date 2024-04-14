package vieira.apps.simulators
//
//sealed class FuelType {
//    data object Gasoline : FuelType()
//    data object Diesel : FuelType()
//    data object Hybrid : FuelType()
//    data object HybridPlugIn : FuelType()
//    data object NotDefined : FuelType()
//}


enum class FuelType(value: Int) {
    Gasoline(0),
    Diesel(1),
    Hybrid(2),
    HybridPlugIn(3),
    NotDefined(4)
}
