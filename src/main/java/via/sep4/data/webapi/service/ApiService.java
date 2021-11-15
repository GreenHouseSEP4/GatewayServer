package via.sep4.data.webapi.service;

import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException;

public interface ApiService {
    String findById(int id) throws NotFoundException;
}
