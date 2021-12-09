package via.sep4.data.webapi.util;

import org.checkerframework.checker.units.qual.A;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import via.sep4.data.webapi.repository.ApiRepository;
import via.sep4.data.webapi.service.api.ApiService;
import via.sep4.data.webapi.service.api.ApiServiceImpl;

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
            throw new RuntimeException("Cannot find the API KEY!");
        }
    }

    public boolean checkApi(String apiKey) {
        if(!(apiKey.equals("")) && apiKey.equals(getKey())) {
            return true;
        }
        else {
           throw new RuntimeException("Wrong Api Key");
        }
    }
}
