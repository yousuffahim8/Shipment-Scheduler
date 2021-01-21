package shipping

import org.junit.jupiter.api.Assertions.assertTrue

import java.nio.file.{Files, Paths}

class ReadCsvCsvFileFoundTest {

  @org.junit.Test
  def CsvFileFound(): Unit ={
    val fileFound = Files.exists(Paths.get(FileNameInitializer().dataPath+ "/"+ FileNameInitializer().inputFileName+ ".csv"))
    assertTrue(fileFound)
  }
}
