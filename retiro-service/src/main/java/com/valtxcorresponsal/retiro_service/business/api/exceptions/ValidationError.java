package com.valtxcorresponsal.retiro_service.business.api.exceptions;

public record ValidationError(String field, String message) {}