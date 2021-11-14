package via.sep4.data.webapi.model.loriot.actions;

public class ReadData {
    /*private int value;

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }*/

    private String date;
    private String time;
    private int humidity;
    private int temperature;
    private int light;
    private int co2;

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
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
        return "SensorData{" +
                ", date='" + date + '\'' +
                ", time='" + time + '\'' +
                ", humidity=" + humidity +
                ", temperature=" + temperature +
                ", light=" + light +
                ", co2=" + co2 +
                '}';
    }
}
