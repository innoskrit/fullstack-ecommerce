package org.innoskrit.auth_lib.exception;

import lombok.Data;
import lombok.AllArgsConstructor;

@Data
@AllArgsConstructor
public class ApiError {
    private int status;
    private String message;
}
