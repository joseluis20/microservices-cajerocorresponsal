package com.valtxcorresponsal.pago_prestamo_service.business.domain.services;

import com.valtxcorresponsal.pago_prestamo_service.business.api.dtos.TransactionRequestDto;
import com.valtxcorresponsal.pago_prestamo_service.business.api.dtos.TransactionResponseDto;

public interface TransactionService {


    // Para traer un Cliente por su id
     TransactionResponseDto createTransaction(TransactionRequestDto transactionDto);


}
