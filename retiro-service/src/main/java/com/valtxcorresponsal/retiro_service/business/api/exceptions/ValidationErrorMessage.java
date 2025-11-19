package com.valtxcorresponsal.retiro_service.business.api.exceptions;

import java.util.List;

public record ValidationErrorMessage(String message, List<ValidationError> errors) {
}
