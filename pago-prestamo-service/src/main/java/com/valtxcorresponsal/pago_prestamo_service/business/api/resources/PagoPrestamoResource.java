package com.valtxcorresponsal.pago_prestamo_service.business.api.resources;

import com.valtxcorresponsal.pago_prestamo_service.business.api.dtos.PagoCreditoRequestDto;
import com.valtxcorresponsal.pago_prestamo_service.business.api.dtos.PagoCreditoResponseDto;
import com.valtxcorresponsal.pago_prestamo_service.business.domain.services.impl.PagoCreditoServiceImpl;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/creditos")
@AllArgsConstructor
public class PagoPrestamoResource {

    private final PagoCreditoServiceImpl pagoCreditoService;

    @PostMapping("/pagar-cuota")
    public ResponseEntity<PagoCreditoResponseDto> pagarCuota(
            @RequestBody PagoCreditoRequestDto requestCredito) {

        var response = pagoCreditoService.pagarCuota(requestCredito);
        return ResponseEntity.ok(response);
    }

}
