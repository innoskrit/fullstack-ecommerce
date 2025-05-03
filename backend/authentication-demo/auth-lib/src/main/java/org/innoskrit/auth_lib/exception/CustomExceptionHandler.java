package org.innoskrit.auth_lib.exception;

import io.jsonwebtoken.JwtException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.naming.AuthenticationException;

@ControllerAdvice
public class CustomExceptionHandler {
    @ExceptionHandler({AuthenticationServiceException.class, JwtException.class, AuthenticationException.class})
    public ResponseEntity<ApiError> handleUnauthorizedException(Exception ex) {
        System.out.println("handleUnauthorizedException: " + ex);
        ApiError error = new ApiError(401, "Invalid or expired token");
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(error);
    }

    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<ApiError> handleNotFoundException(NotFoundException ex) {
        System.out.println("handleNotFoundException: " + ex);
        ApiError error = new ApiError(404, ex.getMessage());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
    }

    @ExceptionHandler(ClientException.class)
    public ResponseEntity<ApiError> handleClientException(ClientException ex) {
        System.out.println("handleClientException: " + ex);
        ApiError error = new ApiError(400, ex.getMessage());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
    }

    @ExceptionHandler(ServerException.class)
    public ResponseEntity<ApiError> handleServerException(ServerException ex) {
        System.out.println("handleServerException: " + ex);
        ApiError error = new ApiError(500, ex.getMessage());
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(error);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ApiError> handleGenericException(Exception ex) {
        System.out.println("handleGenericException: " + ex);
        ApiError error = new ApiError(500, ex.getMessage());
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(error);
    }
}
