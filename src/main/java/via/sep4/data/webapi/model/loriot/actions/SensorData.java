package via.sep4.data.webapi.model.loriot.actions;

import java.util.Date;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonProperty;

@Entity
@Table(name = "SensorData", schema = "dbo")
public class SensorData {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Id")
    @JsonProperty("id")
    private int id;

    @JsonProperty("date")
    @Column(name = "Date")
    private Date date;

    @JsonProperty("time")
    @Column(name = "Time")
    private String time;

    @JsonProperty("humidity")
    @Column(name = "Humidity")
    private int humidity;

    @JsonProperty("temperature")
    @Column(name = "Temperature")
    private int temperature;

    @JsonProperty("light")
    @Column(name = "Light")
    private int light;

    @JsonProperty("co2")
    @Column(name = "Co2")
    private int co2;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public int getHumidity() {
        return humidity;
    }

    public void setHumidity(int humidity) {
        this.humidity = humidity;
    }

    public int getTemperature() {
        return temperature;
    }

    public void setTemperature(int temperature) {
        this.temperature = temperature;
    }

    public int getLight() {
        return light;
    }

    public void setLight(int light) {
        this.light = light;
    }

    public int getCo2() {
        return co2;
    }

    public void setCo2(int co2) {
        this.co2 = co2;
    }

    @Override
    public String toString() {
        return "ReadData [co2=" + co2 
        + ", date=" + date 
        + ", humidity=" + humidity 
        + ", id=" + id 
        + ", light=" + light
        + ", temperature=" + temperature 
        + ", time=" + time + "]";
    }
}
