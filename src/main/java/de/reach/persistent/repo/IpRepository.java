package de.reach.persistent.repo;

import de.reach.persistent.model.IpEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface IpRepository extends JpaRepository<IpEntity, Long> {

    @Query("Select ip from IpEntity ip where ip.ipAddress = :ipAddress")
    IpEntity getIpEntityByIpAddress(@Param("ipAddress") String ipAddress);
}
