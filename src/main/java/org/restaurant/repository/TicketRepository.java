package org.restaurant.repository;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import org.restaurant.entity.Ticket;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface TicketRepository extends JpaRepository<Ticket, Integer>{
	@Query("select e from Ticket e where e.date between :d1 and :d2")
	public List<Ticket> getTicketbyPeriod(@Param("d1") LocalDateTime d1 , @Param("d2")LocalDateTime d2);
	
}
