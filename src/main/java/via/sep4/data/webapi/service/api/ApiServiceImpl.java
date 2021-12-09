package via.sep4.data.webapi.service.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import via.sep4.data.webapi.repository.ApiRepository;

@Service
public class ApiServiceImpl implements ApiService {
    
    @Autowired
    private ApiRepository apiRepository;

    @Override
    public String findById(int id) {
        try {
            return apiRepository.findById(id).toString();
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("API is not found!");
        }
    }
}