package via.sep4.data.webapi.util;

import javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.stereotype.Component;
import via.sep4.data.webapi.service.api.ApiService;

@Component
public class ApiKeyUtil {

    @Autowired
    private ApiService apiService;

    private String getKey() throws NotFoundException {
        String key = "";
        try {
            key = apiService.findById(1);
            return key;
        } catch (NotFoundException | ChangeSetPersister.NotFoundException e) {
            e.printStackTrace();
            throw new NotFoundException("Cannot find the API key!");
        }
    }

    public boolean checkApi(String apiKey) throws NotFoundException {
        try {
            if (!(apiKey.equals("")) && apiKey.equals(getKey())) {
                return true;
            } else {
                return false;
            }
        } catch (NotFoundException e) {
            throw new NotFoundException("API key not found");
        }
    }
}
