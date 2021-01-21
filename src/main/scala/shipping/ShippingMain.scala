package shipping

object ShippingMain {

  def main(args: Array[String]) {

    val dateTimeInitializer = new DateTimeInitializer()

    val inputShipments = ReadCsv.readFile(FileNameInitializer().dataPath, FileNameInitializer().inputFileName)

    val shipmentDataFormatter = new ShipmentDataFormatter()

    val shipmentScheduleCalculation = new ShipmentScheduleCalculation()

    val shipmentResult = shipmentScheduleCalculation.shipmentCalculation(shipmentDataFormatter, inputShipments, ShipmentInitializer(), dateTimeInitializer)

    WriteCsv.writeFile(shipmentResult, FileNameInitializer().dataPath, FileNameInitializer().outputFileName)

  }

}
