package com.gucardev.mqttpoc.repository;

import com.gucardev.mqttpoc.model.StateData;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface StateDataRepository extends JpaRepository<StateData, Long> {

  @Query(nativeQuery = true, value = "select device_id from STATE_DATA group by device_id")
  List<String> retrieveDeviceNames();
}
