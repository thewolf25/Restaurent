package org.restaurant.response;

import java.util.List;

import org.springframework.http.HttpStatus;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ResponseClient<T> {
	private HttpStatus status;
	private T data;
	private List<String> erreurs;
	private String message;

}
