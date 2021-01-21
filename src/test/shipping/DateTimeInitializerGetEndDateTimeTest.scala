package shipping

import org.junit.jupiter.api.Assertions.assertDoesNotThrow

class DateTimeInitializerGetEndDateTimeTest {

  @org.junit.Test
  def endDateTimeTest(): Unit ={
    assertDoesNotThrow(() => (new DateTimeInitializer).endDateTime)
  }

}
