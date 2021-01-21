package shipping

import org.junit.jupiter.api.Assertions.assertDoesNotThrow

class DateTimeInitializerGetMidDateTimeTest {

  @org.junit.Test
  def midDateTimeTest(): Unit ={
    assertDoesNotThrow(() => (new DateTimeInitializer).midDateTime)
  }
}
