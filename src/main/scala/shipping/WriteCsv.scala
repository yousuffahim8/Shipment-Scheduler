package shipping

import java.io.PrintWriter

object WriteCsv extends BaseWriteFile {

  def writeFile(totalShipment:List[ShipmentOutput], dataPath: String, outputFileName: String ) {
    val pw = new PrintWriter(dataPath +"/"+ outputFileName+".csv")
    pw.println("day " +","+ "hour " + "," + "slot "+ ","+ "id")
    for(shipmentOutput <- totalShipment){
      pw.println(shipmentOutput.Date +","+ shipmentOutput.Time +"," + shipmentOutput.Slot + "," + shipmentOutput.Id)
    }
    pw.close()
  }
}
