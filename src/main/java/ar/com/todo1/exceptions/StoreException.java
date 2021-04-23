package ar.com.todo1.exceptions;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

/*** @author Andres Gonzalez ***/

@Getter
@Builder
@AllArgsConstructor
public class StoreException extends Exception {
	private static final long serialVersionUID = 1L;
	private String code;
	private String description;
	

	public StoreException(Exception exception, String code,String message) {
		super(exception);
		this.description = message;
		this.code=code;
	}

	@Override
	public String getMessage() {
		return description;
	}
}
