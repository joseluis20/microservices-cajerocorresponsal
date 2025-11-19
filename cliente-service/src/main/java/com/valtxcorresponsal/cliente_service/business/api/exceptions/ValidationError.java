package com.valtxcorresponsal.cliente_service.business.api.exceptions;

public record ValidationError(String field, String message) {}