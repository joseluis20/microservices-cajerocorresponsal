package com.valtxcorresponsal.seguridad_service.business.api.exceptions;

public class BadRequestException extends RuntimeException {

  private static final String DESCRIPTION = "Bad Request Exception";

  public BadRequestException(String detail) {
    super(DESCRIPTION + ". " + detail);
  }
}
