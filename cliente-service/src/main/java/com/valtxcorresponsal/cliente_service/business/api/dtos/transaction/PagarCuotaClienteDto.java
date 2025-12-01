package com.valtxcorresponsal.cliente_service.business.api.dtos.transaction;

import lombok.Builder;

@Builder
public record PagarCuotaClienteDto(
        String codPrest,      // número de crédito
        Integer nroCuota,     // número de la cuota
        Double montoPagado,   // opcional: monto pagado
        String usuarioActualizacion // usuario que realiza la operación
        ) {
}
