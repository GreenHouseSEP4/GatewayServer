package via.sep4.data.webapi.model;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * This model is for to get the api key from the database.
 */
@Entity
@Table(name = "ApiKey", schema = "dbo")
public class ApiKey {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Id")
    @JsonProperty("id")
    private int id;

    @Column(name = "KeyValue")
    @JsonProperty("keyValue")
    private String keyValue;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getKeyValue() {
        return keyValue;
    }

    public void setKeyValue(String keyValue) {
        this.keyValue = keyValue;
    }

    public String toString() {
        return keyValue;
    }
}
