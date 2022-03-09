package com.spot.task.repository;

import com.spot.task.domains.SpotUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SpotUserRepository extends JpaRepository<SpotUser, Long> {

}
