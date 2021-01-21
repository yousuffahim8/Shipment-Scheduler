package shipping

trait BaseReadFile {

  def readFile(dataPath: String, inputFileName: String) : Array[Array[String]];

}
