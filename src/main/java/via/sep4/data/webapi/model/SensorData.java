package via.sep4.data.webapi.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.*;

@Entity
@Table(name = "SensorData", schema = "dbo")
public class SensorData {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Id")
    @JsonProperty("id")
    private int id;

    @JsonProperty("value")
    @Column(name = "Value")
    private String value;

    public SensorData() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String toString(){
        return "SensorData [id=" + id + " value=" + value + "]";
    }
}
