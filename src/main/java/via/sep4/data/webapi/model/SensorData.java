package via.sep4.data.webapi.model;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.stereotype.Component;

@Component
@Entity
@Table(name = "SensorData", schema = "dbo")
public class SensorData {
    @Id
    @JsonProperty("id")
    @Column(name = "Id")
    private int id;

    @JsonProperty("value")
    @Column(name = "Value")
    private String value;

    public SensorData() {}

    public void setValue(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public String toString(){
        return "SensorData [id=" + id + " value=" + value + "]";
    }
}
