package shipping

case class ShipmentInitializer(timeSlotChangeMilliSec: Long = 60 * 60 * 1000, shipmentLimitPerHour: Int = 7, weightLimit: Double =13607.8)


