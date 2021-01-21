package shipping

import org.junit.jupiter.api.Assertions.assertTrue
import shipping.ShippingMain.main

import scala.util.control.Breaks

class ShippingMainTest {

  @org.junit.Test
  def scheduleShipmentsTest(): Unit ={

    val args = null
    main(args)
    val expectedOutputFileName = "Shipments_Schedule_Expected_Output"
    val shipmentsOutputMatrix = ReadCsv.readFile(FileNameInitializer().dataPath, FileNameInitializer().outputFileName)
    val shipmentsExpectedOutputMatrix = ReadCsv.readFile(FileNameInitializer().dataPath, expectedOutputFileName)
    var expectedOutputMatch= shipmentsOutputMatrix.length == shipmentsExpectedOutputMatrix.length
    if(!expectedOutputMatch){
      assertTrue(expectedOutputMatch)
    }
    expectedOutputMatch = matrixComparison(shipmentsOutputMatrix,shipmentsExpectedOutputMatrix)
    assertTrue(expectedOutputMatch)
  }

  def matrixComparison(shipmentsOutputMatrix: Array[Array[String]], shipmentsExpectedOutputMatrix: Array[Array[String]]): Boolean ={
    var expectedOutputMatch=true
    val outputMatchLoop = new Breaks
    outputMatchLoop.breakable {
      for (row <- shipmentsExpectedOutputMatrix.indices) {
        for (column <- shipmentsExpectedOutputMatrix(row).indices) {
          if (shipmentsOutputMatrix(row)(column) != shipmentsExpectedOutputMatrix(row)(column)) {
            expectedOutputMatch = false
            outputMatchLoop.break
          }
        }
      }
    }
    expectedOutputMatch
  }
}
