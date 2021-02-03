package org.restaurant.services;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import org.modelmapper.ModelMapper;
import org.restaurant.dto.Metdto;
import org.restaurant.entity.Met;
import org.restaurant.entity.Plat;
import org.restaurant.exceptions.TableException;
import org.restaurant.repository.MetRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class MetServiceImpl  implements MetService{

	MetRepository metRepository;
	ModelMapper modelMapper;
	EntityManager entityManager;

	@Override
	public Met ajouterMet(Metdto met) {
		System.out.println("***************" +met.getType());
		
		return metRepository.save(metMapper(met));
	}
	
	@Override
	public ResponseEntity<String> deleteMet(Metdto met) {
		// TODO Auto-generated method stub
		metRepository.delete(metMapper(met));
		return new ResponseEntity<String>("le "+met.getType()+" est suprimé avec succées",HttpStatus.OK );
	}
	
	
	public Met metMapper(Metdto met) {
		Class c = null;
		try {
			c = Class.forName("org.restaurant.entity."+met.getType());
			
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Met m = (Met)modelMapper.map(met, c);
		return m;
	}

	@Override
	public List<Metdto> listMet() {
		// TODO Auto-generated method stub
		List<Met> metdb= metRepository.findAll(); 
		List<Metdto> list= metdb.stream().map((s) -> modelMapper.map(s, Metdto.class)).collect(Collectors.toList());
		list.stream()
		.forEach(
				(s) -> s.setType(metdb.stream()
						.filter(k->k.getNom().equals(s.getNom()))
						.findFirst().get().getClass().getSimpleName()
						));
		return list;
	}
	
	
	public Metdto getMetById(String metName) {
		
		Met m = metRepository.findById(metName).orElseThrow(()->new TableException("le Met avec le nom  "+ metName + " n'existe pas dans la base !!" ));
		Metdto met = modelMapper.map( m, Metdto.class);
		met.setType(m.getClass().getSimpleName());
		return met;
	}

	@Override
	
	// Requete sql 
	public Metdto getPlatPlusReserve(LocalDate d1, LocalDate d2) {
		// TODO Auto-generated method stub
		int max = 0;
		HashMap<Plat, Integer> f= new HashMap<>();
		LocalDateTime d11 = LocalDateTime.of(d1, LocalTime.of(0, 0, 0));
		LocalDateTime d22 = LocalDateTime.of(d2, LocalTime.of(23, 59, 59));
		Query query =entityManager.createNativeQuery("select m.* from ticket_mets e ,ticket t , met m where t.date between :d1 and :d2 and m.type = 'Plat' and e.met_id = m.nom and e.ticket_id = t.numero ",Plat.class);
		query.setParameter("d1", d11);
		query.setParameter("d2", d22);
		List<Plat> plats = query.getResultList();
		Plat pp = plats.get(0);
		for (Plat p : plats) {
			System.out.println(p.getNom());

			if(f.containsKey(p)) {
				System.out.println(p.getNom());
				f.put(p, f.get(p) + 1);
				if(f.get(p) > max) {
					max = f.get(p);
					pp = p;
				}
				
			}
			else {
				f.put(p, 1);
			}
		}
		Metdto met = modelMapper.map(pp, Metdto.class);
		met.setType("Plat");
		return met;
	}
	
// java	
//	public Plat getPlatPlusAchete(LocalDate d1 , LocalDate d2) {
//		// TODO Auto-generated method stub
//		List<Ticket> tickets =ticketRepository.getTicketbyPeriod(LocalDateTime.of(d1, 8, 0, 0), LocalDateTime.of(d2, 10, 0, 0));		
//		tickets.stream().forEach(t -> t.setMetsOfTicket(t.getMetsOfTicket().stream().filter(m -> m.getClass().getSimpleName().equals("Plat")).collect(Collectors.toList())));		
//		HashMap<String,Integer> plats = new HashMap<>(); 
//		int max = 0;
//		Plat pp = null;
//		for (Ticket T : tickets) {
//			
//			for (Met p : T.getMetsOfTicket()) {
//				
//				if (plats.containsKey(p.getNom())) {
//					System.out.println(p.getNom() + plats.get(p.getNom()) );
//					plats.put(p.getNom(),plats.get(p.getNom())+1);
//				if (max < plats.get(p.getNom())){
//					
//					max = plats.get(p.getNom());
//					pp = (Plat) p;
//					System.out.println(max);
//				}
//				
//				}
//				else {
//					plats.put(p.getNom(), 1);
//				}
//				
//			}
//		}
//		
//		
//		return pp;
//	}

	
}
