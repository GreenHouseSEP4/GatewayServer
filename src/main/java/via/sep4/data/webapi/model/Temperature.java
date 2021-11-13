package via.sep4.data.webapi.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Temperature {
    @JsonProperty("id")
    private int id;

    @JsonProperty("value")
    private String value;

    public Temperature(String value) {
        this.value = value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
