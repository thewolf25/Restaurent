package org.restaurant.services;

import org.restaurant.dto.TableDto;
import org.restaurant.entity.Table;

public interface TableService {
	TableDto addTable(TableDto table);
	TableDto getTable(int numTable);
	TableDto getTableMostReserved();
}
