package shipping

import org.junit.jupiter.api.Assertions.assertFalse

class ReadCsvReadFileTest {
  @org.junit.Test
  def readCSVTest(): Unit ={
    val shipmentsMatrix = ReadCsv.readFile(FileNameInitializer().dataPath, FileNameInitializer().inputFileName)
    var empty = true
    if(!shipmentsMatrix.isEmpty){
      empty = false
    }
    assertFalse(empty)
  }
}
