package org.delivery.api.domain.storemenu.converter;

import org.delivery.api.common.annotation.Converter;
import org.delivery.api.common.error.ErrorCode;
import org.delivery.api.common.exception.ApiException;
import org.delivery.api.domain.storemenu.controller.model.StoreMenuRegisterRequest;
import org.delivery.api.domain.storemenu.controller.model.StoreMenuResponse;
import org.delivery.api.domain.user.model.User;
import org.delivery.db.storemenu.StoreMenuEntity;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Converter
public class StoreMenuConverter {
    public StoreMenuEntity toEntity(StoreMenuRegisterRequest request){
        return Optional.ofNullable(request)
                .map(it->{
                    return StoreMenuEntity.builder()
                            .storeId(it.getStoreId())
                            .name(it.getName())
                            .thumbnailUrl(it.getThumbnailUrl())
                            .price(it.getAmount())
                            .build();

                })
                .orElseThrow(()->new ApiException(ErrorCode.NULL_POINT));
    }

    public StoreMenuResponse toResponse(StoreMenuEntity entity){
        return Optional.ofNullable(entity)
                .map(it->{
                    return StoreMenuResponse.builder()
                            .id(it.getId())
                            .name(it.getName())
                            .storeId(it.getStoreId())
                            .amount(it.getPrice())
                            .status(it.getStatus())
                            .thumbnailUrl(it.getThumbnailUrl())
                            .likeCount(it.getLike_count())
                            .sequence(it.getSequence())
                            .build();

                })
                .orElseThrow(()->new ApiException(ErrorCode.NULL_POINT));
    }
    public List<StoreMenuResponse> toResponse(
            List<StoreMenuEntity> list
    ){
        return list.stream()
                .map(it -> toResponse(it))
                .collect(Collectors.toList());
    }


}
