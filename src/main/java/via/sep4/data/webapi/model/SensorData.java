package via.sep4.data.webapi.model;


import javax.persistence.Entity;
import javax.persistence.Id;

import com.fasterxml.jackson.annotation.JsonProperty;

@Entity
public class SensorData {
    @Id
    @JsonProperty("id")
    private int id;

    @JsonProperty("value")
    private String value;

    public SensorData(String value) {
        this.value = value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
