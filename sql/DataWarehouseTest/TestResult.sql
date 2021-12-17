
 SELECT COUNT(*) AS CountFromTestDB
 FROM TestDB.dbo.sensor_data

 SELECT COUNT(*) AS CountFromTestDW
 FROM TestDW.edw.Fact_Measurement


 SELECT COUNT(*) AS CountFromTestDB
 FROM TestDB.dbo.device

 SELECT COUNT(*) AS CountFromTestDW
 FROM TestDW.edw.Dim_Device


 SELECT MAX(sd.temperature) AS CountFromTestSource
 FROM TestDB.dbo.sensor_data AS sd

 SELECT MAX(m.TempValue) AS CountFromTestSource
 FROM TestDW.edw.Fact_Measurement AS m


 SELECT MIN(sd.temperature) AS CountFromTestSource
 FROM TestDB.dbo.sensor_data AS sd

 SELECT MIN(m.TempValue) AS CountFromTestSource
 FROM TestDW.edw.Fact_Measurement AS m


 SELECT AVG(sd.temperature) AS CountFromTestSource
 FROM TestDB.dbo.sensor_data AS sd

 SELECT AVG(m.TempValue) AS CountFromTestSource
 FROM TestDW.edw.Fact_Measurement AS m


  SELECT MAX(sd.humidity) AS CountFromTestSource
 FROM TestDB.dbo.sensor_data AS sd

 SELECT MAX(m.HumValue) AS CountFromTestSource
 FROM TestDW.edw.Fact_Measurement AS m


 SELECT MIN(sd.humidity) AS CountFromTestSource
 FROM TestDB.dbo.sensor_data AS sd

 SELECT MIN(m.HumValue) AS CountFromTestSource
 FROM TestDW.edw.Fact_Measurement AS m


 SELECT AVG(sd.humidity) AS CountFromTestSource
 FROM TestDB.dbo.sensor_data AS sd

 SELECT AVG(m.HumValue) AS CountFromTestSource
 FROM TestDW.edw.Fact_Measurement AS m



  SELECT MAX(sd.co2) AS CountFromTestSource
 FROM TestDB.dbo.sensor_data AS sd

 SELECT MAX(m.Co2Value) AS CountFromTestSource
 FROM TestDW.edw.Fact_Measurement AS m


 SELECT MIN(sd.co2) AS CountFromTestSource
 FROM TestDB.dbo.sensor_data AS sd

 SELECT MIN(m.Co2Value) AS CountFromTestSource
 FROM TestDW.edw.Fact_Measurement AS m


 SELECT AVG(sd.co2) AS CountFromTestSource
 FROM TestDB.dbo.sensor_data AS sd

 SELECT AVG(m.Co2Value) AS CountFromTestSource
 FROM TestDW.edw.Fact_Measurement AS m



 SELECT MAX(sd.light) AS CountFromTestSource
 FROM TestDB.dbo.sensor_data AS sd

 SELECT MAX(m.LightValue) AS CountFromTestSource
 FROM TestDW.edw.Fact_Measurement AS m


 SELECT MIN(sd.light) AS CountFromTestSource
 FROM TestDB.dbo.sensor_data AS sd

 SELECT MIN(m.LightValue) AS CountFromTestSource
 FROM TestDW.edw.Fact_Measurement AS m


 SELECT AVG(sd.light) AS CountFromTestSource
 FROM TestDB.dbo.sensor_data AS sd

 SELECT AVG(m.LightValue) AS CountFromTestSource
 FROM TestDW.edw.Fact_Measurement AS m
