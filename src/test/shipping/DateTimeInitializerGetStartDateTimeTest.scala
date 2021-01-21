package shipping

import org.junit.jupiter.api.Assertions.assertDoesNotThrow

class DateTimeInitializerGetStartDateTimeTest {

  @org.junit.Test
  def startDateTimeTest(): Unit ={
    assertDoesNotThrow(() => (new DateTimeInitializer).startDateTime)
  }
}
