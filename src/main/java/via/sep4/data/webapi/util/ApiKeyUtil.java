package via.sep4.data.webapi.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import via.sep4.data.webapi.service.api.ApiService;

@Component
public class ApiKeyUtil {

    @Autowired
    private ApiService apiService;

    private String getKey() {
        String key = "";
        try {
            key = apiService.findById(1);
            return key;
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Cannot find the API key!");
        }
    }

    public boolean checkApi(String apiKey) {
        if(!(apiKey.equals("")) && apiKey.equals(getKey())) {
            return true;
        }
        else {
           throw new RuntimeException("Wrong API key!");
        }
    }
}
