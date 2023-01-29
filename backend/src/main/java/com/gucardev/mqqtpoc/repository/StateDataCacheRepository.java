package com.gucardev.mqqtpoc.repository;

import com.gucardev.mqqtpoc.model.StateDataCache;
import java.util.List;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StateDataCacheRepository extends CrudRepository<StateDataCache, String> {

  List<StateDataCache> findAllByDeviceId(String deviceId);
}
