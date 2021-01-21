package shipping

trait BaseWriteFile {
  def writeFile(totalShipment:List[ShipmentOutput], dataPath: String, outputFileName: String );
}
