package al.ikubinfo.internship.freelancer.exception;

public class ResourceNotFoundException extends RuntimeException {

	private static final long serialVersionUID = -480522994909286004L;

	public ResourceNotFoundException(String message) {
		super(message);
	}

}
