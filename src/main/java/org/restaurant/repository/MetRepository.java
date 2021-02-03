package org.restaurant.repository;

import java.time.LocalDateTime;
import java.util.List;

import org.restaurant.entity.Met;
import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.query.Param;

public interface MetRepository extends MetBaseRepository<Met>{
	
}
