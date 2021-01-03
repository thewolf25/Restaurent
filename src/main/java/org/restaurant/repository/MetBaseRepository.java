package org.restaurant.repository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.NoRepositoryBean;

import org.restaurant.entity.Met;

@NoRepositoryBean
public interface MetBaseRepository<T extends Met> extends CrudRepository<T, Long> {

  /**
   * Method findByEmail
   * 
   * @param nameAtt the user email.
   * @return the Met having the passed nameAtt or null if no user is found.
   */
//  public T findByNameAttribute(String nameAtt);
  
} 