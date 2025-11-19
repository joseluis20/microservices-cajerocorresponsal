package com.valtxcorresponsal.seguridad_service.business.api.exceptions;

public record ValidationError(String field, String message) {}