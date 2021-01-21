package shipping

import java.sql.Timestamp


class DateTimeInitializer(val startDateTime: Timestamp =Timestamp.valueOf("2015-02-01 08:00:00"),
                          val midDateTime:Timestamp =Timestamp.valueOf("2015-02-16 00:00:00"),
                          val endDateTime: Timestamp =Timestamp.valueOf("2015-03-31 00:00:00")) {

  def getDay (dateTime: Timestamp): String={
     dateTime.toString.dropRight(11)
  }
  def getTime (dateTime: Timestamp): String={
    val Time = dateTime.toString.substring(11)

    if (Time.dropRight(8).toInt < 10){
       " "+Time.dropRight(5)
    }
    else{
      Time.dropRight(4)
    }
  }
}
