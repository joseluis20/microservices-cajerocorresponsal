package com.valtxcorresponsal.pago_prestamo_service.business.api.dtos;

import lombok.AllArgsConstructor;
import lombok.Builder;


@Builder
public record PagarCuotaClienteDto(
        String codPrest,      // número de crédito
        Integer nroCuota,     // número de la cuota
        Double montoPagado,   // opcional: monto pagado
        String usuarioActualizacion // usuario que realiza la operación
        ) {
}
