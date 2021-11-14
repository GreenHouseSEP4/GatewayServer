package via.sep4.data.webapi.repository.implementation;

import java.util.List;
import java.util.Optional;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaContext;
import org.springframework.stereotype.Repository;

import via.sep4.data.webapi.model.SensorData;
import via.sep4.data.webapi.repository.SensorDataRepository;

@Repository
public class SensorDataRepositoryImpl implements SensorDataRepository {

    @Autowired
    private final EntityManager em;

    public SensorDataRepositoryImpl(JpaContext context) {
        this.em = context.getEntityManagerByManagedType(SensorData.class);
    }

    @Override
    public void deleteAllInBatch() {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void deleteInBatch(Iterable<SensorData> arg0) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public List<SensorData> findAll() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public List<SensorData> findAll(Sort arg0) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public <S extends SensorData> List<S> findAll(Example<S> arg0) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public <S extends SensorData> List<S> findAll(Example<S> arg0, Sort arg1) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public List<SensorData> findAllById(Iterable<Integer> arg0) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public void flush() {
        // TODO Auto-generated method stub
        
    }

    @Override
    public SensorData getOne(Integer arg0) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public <S extends SensorData> List<S> saveAll(Iterable<S> arg0) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public <S extends SensorData> S saveAndFlush(S arg0) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Page<SensorData> findAll(Pageable arg0) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public long count() {
        // TODO Auto-generated method stub
        return 0;
    }

    @Override
    public void delete(SensorData arg0) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void deleteAll() {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void deleteAll(Iterable<? extends SensorData> arg0) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void deleteById(Integer arg0) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public boolean existsById(Integer arg0) {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public Optional<SensorData> findById(Integer arg0) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public <S extends SensorData> S save(S arg0) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public <S extends SensorData> long count(Example<S> arg0) {
        // TODO Auto-generated method stub
        return 0;
    }

    @Override
    public <S extends SensorData> boolean exists(Example<S> arg0) {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public <S extends SensorData> Page<S> findAll(Example<S> arg0, Pageable arg1) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public <S extends SensorData> Optional<S> findOne(Example<S> arg0) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public SensorData findById(int id) {
        TypedQuery<SensorData> q = em.createQuery("select s from SensorData where Id = :id", SensorData.class);
        q.setParameter("id", id);
        return q.getSingleResult();
    }

    @Override
    public SensorData addSensorData(String value) {
        // TODO Auto-generated method stub
        return null;
    }

}