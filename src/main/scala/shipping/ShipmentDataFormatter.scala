package shipping

import scala.collection.mutable.ListBuffer

class ShipmentDataFormatter {

  def makeListsOfPriorityAndWeight(matrix: Array[Array[String]]): ListBuffer[Shipment] = {
    val shipmentRows = new ListBuffer[Shipment]()
    for (row <- matrix) {
      val priorityAndWeight = row(2).split(",")
      shipmentRows += initializedShipmentObject(row, priorityAndWeight, priorityAndWeight(1))
    }
    shipmentRows
  }

  def initializedShipmentObject(row: Array[String], priorityAndWeight:Array[String], weightScale: String): Shipment = {

      if (priorityAndWeight.length >= 3) {
         Shipment(row(0).toInt, convertToKg(priorityAndWeight(0).toDouble, weightScale), priorityAndWeight(2).toDouble)
      }
      else {
         Shipment(row(0).toInt, convertToKg(priorityAndWeight(0).toDouble, weightScale), Double.PositiveInfinity)
      }
  }

  def convertToKg(weight: Double, weightScale: String): Double ={

    if (weightScale == "ton") {
       weight * 907.185
    }
    else if (weightScale == "lbs") {
       weight * 0.453592
    }
    else {
       weight
    }

  }

}
