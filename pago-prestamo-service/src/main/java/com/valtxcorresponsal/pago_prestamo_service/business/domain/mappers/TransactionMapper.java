package com.valtxcorresponsal.pago_prestamo_service.business.domain.mappers;

import com.valtxcorresponsal.pago_prestamo_service.business.data.model.entities.TransactionEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import com.valtxcorresponsal.pago_prestamo_service.business.api.dtos.TransactionRequestDto;
import com.valtxcorresponsal.pago_prestamo_service.business.api.dtos.TransactionResponseDto;

@Mapper(componentModel = "spring")
public interface TransactionMapper {


  TransactionResponseDto toDto(TransactionEntity transactionEntity);

    //@Mapping(target = "id", ignore = true)
    TransactionEntity toEntity(TransactionRequestDto transactionDto);

}
