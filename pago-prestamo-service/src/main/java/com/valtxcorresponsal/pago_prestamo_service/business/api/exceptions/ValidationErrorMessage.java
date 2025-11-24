package com.valtxcorresponsal.pago_prestamo_service.business.api.exceptions;

import java.util.List;

public record ValidationErrorMessage(String message, List<ValidationError> errors) {
}
