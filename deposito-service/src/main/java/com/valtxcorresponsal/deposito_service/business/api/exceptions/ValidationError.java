package com.valtxcorresponsal.deposito_service.business.api.exceptions;

public record ValidationError(String field, String message) {}