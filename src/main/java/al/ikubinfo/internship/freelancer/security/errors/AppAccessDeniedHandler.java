package al.ikubinfo.internship.freelancer.security.errors;

import al.ikubinfo.internship.freelancer.security.errors.model.AuthenticationErrorResponse;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.time.LocalDateTime;

@Component
@Slf4j
public class AppAccessDeniedHandler implements AccessDeniedHandler {

    private final ObjectMapper objectMapper;

    public AppAccessDeniedHandler(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }

    @Override
    @SneakyThrows
    public void handle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, AccessDeniedException e) {
        httpServletResponse.setContentType(MediaType.APPLICATION_JSON_VALUE);
        httpServletResponse.getWriter().write(this.createErrorResponse(httpServletRequest, e));
    }

    private String createErrorResponse(HttpServletRequest httpServletRequest, AccessDeniedException e) throws JsonProcessingException {
        log.error("Authentication error", e);
        AuthenticationErrorResponse errorResponse = new AuthenticationErrorResponse();
        errorResponse.setMessage(e.getMessage());
        errorResponse.setTimestamp(LocalDateTime.now());
        errorResponse.setPath(httpServletRequest.getRequestURI());
        return objectMapper.writeValueAsString(errorResponse);
    }
}
