package al.ikubinfo.internship.freelancer.security.errors.model;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class AuthenticationErrorResponse {
    private String path;
    private LocalDateTime timestamp;
    private String message;
}
