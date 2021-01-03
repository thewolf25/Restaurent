package org.restaurant.repository;

import org.restaurant.entity.Plat;
import org.springframework.transaction.annotation.Transactional;

@Transactional
public interface PlatRepository extends MetBaseRepository<Plat>{

}
