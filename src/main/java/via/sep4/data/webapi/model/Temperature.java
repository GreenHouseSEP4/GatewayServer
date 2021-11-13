package via.sep4.data.webapi.Model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Temperature {
    @JsonProperty("id")
    private int id;

    @JsonProperty("value")
    private String value;
}
