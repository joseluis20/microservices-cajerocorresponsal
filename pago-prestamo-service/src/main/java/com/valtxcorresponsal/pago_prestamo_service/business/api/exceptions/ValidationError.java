package com.valtxcorresponsal.pago_prestamo_service.business.api.exceptions;

public record ValidationError(String field, String message) {}