package via.sep4.data.webapi.model;

import java.util.Date;
import javax.persistence.*;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * This model is for to hold the received measurement information and send this model for the client.
 */
@Entity
@Table(name = "SensorData", schema = "dbo")
public class SensorData {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Id")
    @JsonProperty("id")
    private int id;

    @JsonProperty("eui")
    @Column(name = "Eui")
    private String eui;

    @JsonProperty("date")
    @Column(name = "Date")
    private Date date;

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
        if (humidity >= 0 && humidity <= 99) {
            this.humidity = humidity;
        } else {
            this.humidity = -4444;
        }
    }

    public int getTemperature() {
        return temperature;
    }

    public void setTemperature(int temperature) {
        if (temperature >= -100 && temperature <= 100) {
            this.temperature = temperature;
        } else {
            this.temperature = -4444;
        }
    }

    public int getLight() {
        return light;
    }

    public void setLight(int light) {
        if (light >= 0 && light <= 130000) {
            this.light = light;
        } else {
            this.light = -4444;
        }
    }

    public int getCo2() {
        return co2;
    }

    public void setCo2(int co2) {
        if (co2 >= 0 && co2 <= 9999) {
            this.co2 = co2;
        } else {
            this.co2 = -4444;
        }
    }

    public String getEui() {
        return eui;
    }

    public void setEui(String eui) {
        this.eui = eui;
    }

    @Override
    public String toString() {
        return "SensorData{" +
                "id=" + id +
                ", EUI=" + eui +
                ", date=" + date +
                ", humidity=" + humidity +
                ", temperature=" + temperature +
                ", light=" + light +
                ", co2=" + co2 +
                '}';
    }
}
