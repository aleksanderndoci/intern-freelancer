package al.ikubinfo.internship.freelancer.security.errors;

import al.ikubinfo.internship.freelancer.security.errors.model.AuthenticationErrorResponse;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDateTime;

@Component
@Slf4j
public class AppAuthenticationEntryPoint implements AuthenticationEntryPoint {

    private final ObjectMapper objectMapper;

    public AppAuthenticationEntryPoint(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }

    @Override
    public void commence(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, AuthenticationException e) throws IOException {
        httpServletResponse.setContentType(MediaType.APPLICATION_JSON_VALUE);
        httpServletResponse.getWriter().write(this.createErrorResponse(httpServletRequest, e));
    }

    private String createErrorResponse(HttpServletRequest httpServletRequest, AuthenticationException e) throws JsonProcessingException {
        log.error("Authentication error", e);
        AuthenticationErrorResponse errorResponse = new AuthenticationErrorResponse();
        errorResponse.setMessage(e.getMessage());
        errorResponse.setTimestamp(LocalDateTime.now());
        errorResponse.setPath(httpServletRequest.getRequestURI());
        return objectMapper.writeValueAsString(errorResponse);
    }
}
