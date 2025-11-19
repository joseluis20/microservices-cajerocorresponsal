package com.valtxcorresponsal.seguridad_service.business.api.exceptions;

import java.util.List;

public record ValidationErrorMessage(String message, List<ValidationError> errors) {
}
