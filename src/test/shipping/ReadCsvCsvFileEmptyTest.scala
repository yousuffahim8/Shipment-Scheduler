package shipping

import org.junit.jupiter.api.Assertions.assertFalse

import java.io.File

class ReadCsvCsvFileEmptyTest {

  @org.junit.Test
  def CsvFileEmpty(): Unit ={
    val file = new File(FileNameInitializer().dataPath+ "/"+ FileNameInitializer().inputFileName+ ".csv")
    var empty = true
    if(file.length()>0){
      empty = false
    }
    assertFalse(empty)
  }
}
