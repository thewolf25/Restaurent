package org.restaurant.services;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.restaurant.dto.ClientDto;
import org.restaurant.dto.TableDto;
import org.restaurant.entity.Client;
import org.restaurant.entity.Table;
import org.restaurant.entity.Ticket;
import org.restaurant.exceptions.TableException;
import org.restaurant.repository.TableRepository;
import org.springframework.stereotype.Service;

import lombok.AllArgsConstructor;
@Service
@AllArgsConstructor
public class TableServiceImpl implements TableService{
	private ModelMapper mapper;
	private TableRepository tableRepository;
	@Override
	public TableDto addTable(TableDto table) {
		// TODO Auto-generated method stub
		Optional<Table> ta = tableRepository.findById(table.getNumero()) ;
		Table t;
		if(ta.isPresent() ) {
			t = ta.get();
			if(table.getNbCouvert() > 0) {
				t.setNbCouvert(table.getNbCouvert());
			}
			if(table.getSupplement()> 0) {
				t.setSupplement(table.getSupplement());
			}
			if(table.getType() != null) {
				t.setType(table.getType());
			}
			return mapper.map(tableRepository.save(t), TableDto.class);		
		}
		else {
			t = mapper.map(table, Table.class);
			return mapper.map(tableRepository.save(t), TableDto.class);
		}
		
		
	}

	@Override
	public TableDto getTable(int numTable) {
		// TODO Auto-generated method stub
		Table t = tableRepository
				.findById(numTable)
				.orElseThrow(() ->new TableException("Table Not found !!!"));
		return mapper.map(t, TableDto.class);
	}
	
	public TableDto getTableMostReserved() {
		List<Table> t = tableRepository.findAll().stream().sorted((c1,c2) -> ((Integer)c1.getTicket().size()).compareTo((Integer)c2.getTicket().size())).collect(Collectors.toList());	
		return mapper.map(t.get(t.size() -1), TableDto.class);
	}
	
	

}
