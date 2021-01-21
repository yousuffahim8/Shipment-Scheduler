package shipping

object ReadCsv extends BaseReadFile {

  def readFile(dataPath: String, inputFileName: String) : Array[Array[String]] = {
    io.Source.fromFile(dataPath +"/"+ inputFileName+".csv")
      .getLines().drop(1)
      .map(_.split(",", 3).map(_.trim))
      .toArray
  }

}
