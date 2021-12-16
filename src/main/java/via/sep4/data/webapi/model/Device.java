package via.sep4.data.webapi.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import org.springframework.context.annotation.Description;

import javax.persistence.*;

/**
 * This model is for to hold the information on the different physical devices registered by the client.
 */
@Entity
@Table(name = "Device", schema = "dbo")
public class Device {

    @Id
    @Column(name = "Eui")
    @JsonProperty("eui")
    private String EUI;

    @JsonProperty("location")
    @Column(name = "Location")
    private String location;

    @JsonProperty("minTemperature")
    @Column(name = "MinTemperature")
    private int minTemperature;

    @JsonProperty("maxTemperature")
    @Column(name = "MaxTemperature")
    private int maxTemperature;

    @JsonProperty("minHumidity")
    @Column(name = "MinHumidity")
    private int minHumidity;

    @JsonProperty("maxHumidity")
    @Column(name = "MaxHumidity")
    private int maxHumidity;

    @JsonProperty("minCO2")
    @Column(name = "MinCO2")
    private int minCO2;

    @JsonProperty("maxCO2")
    @Column(name = "MaxCO2")
    private int maxCO2;

    @JsonProperty("minLight")
    @Column(name = "MinLight")
    private int minLight;

    @JsonProperty("maxLight")
    @Column(name = "MaxLight")
    private int maxLight;

    @JsonProperty("targetTemperature")
    @Column(name = "TargetTemperature")
    private int targetTemperature;

    @JsonProperty("targetHumidity")
    @Column(name = "TargetHumidity")
    private int targetHumidity;

    @JsonProperty("targetLight")
    @Column(name = "TargetLight")
    private int targetLight;

    @JsonProperty("targetCO2")
    @Column(name = "TargetCO2")
    private int targetCO2;

    public String getEUI() {
        return EUI;
    }

    public void setEUI(String EUI) {
        this.EUI = EUI;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public int getMinTemperature() {
        return minTemperature;
    }

    public void setMinTemperature(int minTemperature) {
        this.minTemperature = minTemperature;
    }

    public int getMaxTemperature() {
        return maxTemperature;
    }

    public void setMaxTemperature(int maxTemperature) {
        this.maxTemperature = maxTemperature;
    }

    public int getMinHumidity() {
        return minHumidity;
    }

    public void setMinHumidity(int minHumidity) {
        this.minHumidity = minHumidity;
    }

    public int getMaxHumidity() {
        return maxHumidity;
    }

    public void setMaxHumidity(int maxHumidity) {
        this.maxHumidity = maxHumidity;
    }

    public int getMinCO2() {
        return minCO2;
    }

    public void setMinCO2(int minCO2) {
        this.minCO2 = minCO2;
    }

    public int getMaxCO2() {
        return maxCO2;
    }

    public void setMaxCO2(int maxCO2) {
        this.maxCO2 = maxCO2;
    }

    public int getMinLight() {
        return minLight;
    }

    public void setMinLight(int minLight) {
        this.minLight = minLight;
    }

    public int getMaxLight() {
        return maxLight;
    }

    public void setMaxLight(int maxLight) {
        this.maxLight = maxLight;
    }

    public int getTargetTemperature() {
        return targetTemperature;
    }

    public void setTargetTemperature(int targetTemperature) {
        this.targetTemperature = targetTemperature;
    }

    public int getTargetHumidity() {
        return targetHumidity;
    }

    public void setTargetHumidity(int targetHumidity) {
        this.targetHumidity = targetHumidity;
    }

    public int getTargetLight() {
        return targetLight;
    }

    public void setTargetLight(int targetLight) {
        this.targetLight = targetLight;
    }

    public int getTargetCO2() {
        return targetCO2;
    }

    public void setTargetCO2(int targetCO2) {
        this.targetCO2 = targetCO2;
    }

    @Override
    public String toString() {
        return "EUI: " + EUI + "\n" +
            "Location: " + location + "\n" +
            "MinTemperature: " + minTemperature + "\n" +
            "MaxTemperature: " + maxTemperature + "\n" +
            "TargetTemperature: " + targetTemperature + "\n" +
            "MinHumidity: " + minHumidity + "\n" +
            "MaxHumidity: " + maxHumidity + "\n" +
            "TargetHumidity: " + targetHumidity + "\n" +
            "MinCO2: " + minCO2 + "\n" +
            "MaxCO2: " + maxCO2 + "\n" +
            "TargetCO2: " + targetCO2 + "\n" +
            "MinLight: " + minLight + "\n" +
            "MaxLight: " + maxLight + "\n" +
            "TargetLight: " + targetLight; 
    }
}
