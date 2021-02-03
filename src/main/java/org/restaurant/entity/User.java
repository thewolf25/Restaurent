package org.restaurant.entity;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Entity
@Getter
@Setter
@NoArgsConstructor
public class User {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private String userName;
	private String password;
	private Boolean active;
	
	@ManyToMany(fetch = FetchType.EAGER)
	@JoinTable(name = "User_Role",
		joinColumns = {@JoinColumn(name = "user_id")
		},
		inverseJoinColumns = {@JoinColumn(name = "role_id")}
	
		)
	
	private List<Role> roles;
}
