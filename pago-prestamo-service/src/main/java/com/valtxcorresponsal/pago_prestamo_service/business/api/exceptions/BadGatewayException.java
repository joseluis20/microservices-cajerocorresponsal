package com.valtxcorresponsal.pago_prestamo_service.business.api.exceptions;

public class BadGatewayException extends RuntimeException {

  private static final String DESCRIPTION = "Bad Gateway Exception";

  public BadGatewayException(String detail) {
    super(DESCRIPTION + ". " + detail);
  }
}
