package com.valtxcorresponsal.autenticacion_service.security.app;

import java.util.Base64;

public class AppEncoderMain {

  public static void main(String[] args) {
    // System.out.println(new BCryptPasswordEncoder().encode("123"));

    String encodedString = Base64.getEncoder().encodeToString("GalaxyTrainingOnModeFullStack2025".getBytes());
    System.out.println(encodedString);

    byte[] encoded = Base64.getDecoder().decode("R2FsYXh5VHJhaW5pbmdPbk1vZGVGdWxsU3RhY2syMDI1");
    System.out.println(new String(encoded));
  }

}