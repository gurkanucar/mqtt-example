package com.gucardev.mqqtpoc.repository;

import com.gucardev.mqqtpoc.model.StateData;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StateDataRepository extends JpaRepository<StateData, Long> {}
