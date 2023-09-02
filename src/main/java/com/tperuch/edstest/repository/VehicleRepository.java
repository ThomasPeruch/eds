package com.tperuch.edstest.repository;

import com.tperuch.edstest.entity.VehicleEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VehicleRepository extends JpaRepository<VehicleEntity, Long> {
    boolean existsByChassis(String chassis);
}
