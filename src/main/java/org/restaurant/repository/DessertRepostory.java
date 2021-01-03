package org.restaurant.repository;

import org.restaurant.entity.Dessert;
import org.springframework.transaction.annotation.Transactional;
@Transactional
public interface DessertRepostory extends MetBaseRepository<Dessert>
{
}
