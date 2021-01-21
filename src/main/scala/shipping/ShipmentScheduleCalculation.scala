package shipping

import java.sql.Timestamp
import scala.collection.mutable.ListBuffer
import scala.util.control.Breaks

class ShipmentScheduleCalculation {

  private def sortedShipmentsPartition(partitionType: String, shipmentDataFormatter: ShipmentDataFormatter,
                                 shipmentsMatrix: Array[Array[String]], shipmentInitializer: ShipmentInitializer ): ListBuffer[Shipment] ={

    if(partitionType == "SmallerThan15Tons"){
      var shipmentsPartitionSmallerThan15Tons = shipmentDataFormatter.makeListsOfPriorityAndWeight(shipmentsMatrix)
      shipmentsPartitionSmallerThan15Tons --= shipmentsPartitionSmallerThan15Tons.filter(_.Weight > shipmentInitializer.weightLimit)
      sortShipments(shipmentsPartitionSmallerThan15Tons, partitionType)
    }
    else{
      var shipmentsPartitionLargerThan15Tons = shipmentDataFormatter.makeListsOfPriorityAndWeight(shipmentsMatrix)
      shipmentsPartitionLargerThan15Tons --= shipmentsPartitionLargerThan15Tons.filter(_.Weight <= shipmentInitializer.weightLimit)
      sortShipments(shipmentsPartitionLargerThan15Tons, partitionType)
    }

  }

   private def sortShipments(shipmentsPartition: ListBuffer[Shipment], partitionType: String): ListBuffer[Shipment] ={
     if(partitionType == "SmallerThan15Tons"){
       shipmentsPartition.sortBy(r => (r.Priority, r.Weight))
     }
     else{
       shipmentsPartition.sortBy(r => (r.Priority, -r.Weight))
     }

  }


  def shipmentCalculation(shipmentDataFormatter: ShipmentDataFormatter, shipmentsMatrix: Array[Array[String]],
                          shipmentInitializer: ShipmentInitializer, dateTime: DateTimeInitializer): List[ShipmentOutput] ={

    val shipmentsListsAscPriorityAscWeight = sortedShipmentsPartition("SmallerThan15Tons", shipmentDataFormatter, shipmentsMatrix, shipmentInitializer)

    val shipmentOutputs1 = scheduleShipments(shipmentsListsAscPriorityAscWeight, dateTime, shipmentInitializer, dateTime.startDateTime, dateTime.midDateTime)

    val shipmentListsAscPriorityDescWeight = sortedShipmentsPartition("LargerThan15Tons", shipmentDataFormatter, shipmentsMatrix, shipmentInitializer)

    val shipmentOutputs2 = scheduleShipments(shipmentListsAscPriorityDescWeight, dateTime, shipmentInitializer, dateTime.midDateTime, dateTime.endDateTime)

    val totalShipment = List.concat(shipmentOutputs1, shipmentOutputs2)
    totalShipment
  }

  private def scheduleShipments(shipmentsListsPriorityASCWeightASC: ListBuffer[Shipment], dateTime: DateTimeInitializer,
                                shipmentInitializer: ShipmentInitializer, startDateTime: Timestamp, endDateTime: Timestamp): ListBuffer[ShipmentOutput] = {

    var shipmentOutputs = new ListBuffer[ShipmentOutput]()
    var startingTimeMilliSec = startDateTime.getTime
    var slot = 1
    var shipmentListsCount = 1


    val loopTime = new Breaks
    startDateTime.setTime(startingTimeMilliSec)

    loopTime.breakable {
      while (startingTimeMilliSec <= endDateTime.getTime) {
        for (row <- shipmentsListsPriorityASCWeightASC) {
          shipmentOutputs += ShipmentOutput(dateTime.getDay(startDateTime), dateTime.getTime(startDateTime), slot, row.Id)
          slot = slot + 1
          shipmentListsCount = shipmentListsCount + 1

          if (slot > shipmentInitializer.shipmentLimitPerHour) {
            slot = 1
            startingTimeMilliSec = startingTimeMilliSec + shipmentInitializer.timeSlotChangeMilliSec
            startDateTime.setTime(startingTimeMilliSec)
          }

          if (shipmentListsCount > shipmentsListsPriorityASCWeightASC.size) {
            loopTime.break
          }
        }
      }
    }
    shipmentOutputs
  }

}
