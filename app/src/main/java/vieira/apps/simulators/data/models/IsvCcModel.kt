package vieira.apps.simulators.data.models

import vieira.apps.simulators.data.entities.IsvCcEntity

data class IsvCcModel(
    val cylinderCapacityFrom: Int,
    val cylinderCapacityUntil: Int,
    val rate: Float,
    val portionToDeduct: Float
){
    fun IsvCcEntity.toIsvCcDomainModel() = IsvCcModel(
        cylinderCapacityFrom = cylinderCapacityFrom,
        cylinderCapacityUntil = cylinderCapacityUntil,
        rate = rate,
        portionToDeduct = portionToDeduct
    )
}
