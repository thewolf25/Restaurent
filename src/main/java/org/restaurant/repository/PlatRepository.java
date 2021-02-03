package org.restaurant.repository;

import java.time.LocalDateTime;
import java.util.List;

import org.restaurant.entity.Met;
import org.restaurant.entity.Plat;
import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

@Transactional
public interface PlatRepository extends MetBaseRepository<Plat>{


}
