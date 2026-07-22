package com.finanzas.app.shared.error;

import org.springframework.http.HttpStatus;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

/**
 * Clasificación semántica de errores de dominio.
 *
 * Este tipo NO representa códigos HTTP ni detalles de transporte.
 * Su objetivo es agrupar excepciones de dominio según la naturaleza
 * de la regla violada, para permitir una traducción posterior
 * (por ejemplo a HTTP, eventos, logs, etc.) fuera del dominio.
 *
 */

@Getter
@RequiredArgsConstructor
public enum ErrorType {

    // 400
    INVALID_REQUEST(HttpStatus.BAD_REQUEST),
    INVALID_BODY(HttpStatus.BAD_REQUEST),

    // 401
    NOT_AUTHENTICATED(HttpStatus.UNAUTHORIZED),
    INVALID_CREDENTIALS(HttpStatus.UNAUTHORIZED),

    // 403
    FORBIDDEN(HttpStatus.FORBIDDEN),

    // 404
    NOT_FOUND(HttpStatus.NOT_FOUND),

    // 405
    METHOD_NOT_ALLOWED(HttpStatus.METHOD_NOT_ALLOWED),

    // 409
    CONFLICT(HttpStatus.CONFLICT),

    // 422
    VALIDATION_ERROR(HttpStatus.UNPROCESSABLE_CONTENT),

    // 429
    TOO_MANY_REQUESTS(HttpStatus.TOO_MANY_REQUESTS),

    // 500
    INTERNAL_SERVER_ERROR(HttpStatus.INTERNAL_SERVER_ERROR),

    // 503
    SERVICE_UNAVAILABLE(HttpStatus.SERVICE_UNAVAILABLE); //implementar a futuro

    private final HttpStatus status;
}