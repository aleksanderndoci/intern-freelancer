 package al.ikubinfo.internship.freelancer.security.model;

import lombok.Data;

@Data
public class LoginResponse {
    private String access_token;
    private String token_type;
    private Long expires_in;
    private String scope;
}
