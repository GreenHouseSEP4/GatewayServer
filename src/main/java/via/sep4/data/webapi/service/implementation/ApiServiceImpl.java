package via.sep4.data.webapi.service.implementation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException;
import org.springframework.stereotype.Service;

import via.sep4.data.webapi.repository.ApiRepository;
import via.sep4.data.webapi.service.ApiService;

@Service
public class ApiServiceImpl implements ApiService {
    @Autowired
    private ApiRepository apiRepository;

    @Override
    public String findById(int id) {
        try {
            return apiRepository.findById(id).toString();
        } catch (Exception e) {
            throw new NotFoundException("Api key not found");
        }
    }
}