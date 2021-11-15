package via.sep4.data.webapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import via.sep4.data.webapi.model.ApiKey;

@Repository
public interface ApiRepository extends JpaRepository<ApiKey, Integer>{
    @Query("SELECT a FROM ApiKey a WHERE a.id = :id")
    ApiKey findById(int id);
}
