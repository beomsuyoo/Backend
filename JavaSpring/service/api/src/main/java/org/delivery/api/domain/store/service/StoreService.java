package org.delivery.api.domain.store.service;

import lombok.RequiredArgsConstructor;
import org.delivery.api.common.error.ErrorCode;
import org.delivery.api.common.exception.ApiException;
import org.delivery.db.store.StoreEntity;
import org.delivery.db.store.StoreRepository;
import org.delivery.db.store.enums.StoreCategory;
import org.delivery.db.store.enums.StoreStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class StoreService {
    private final StoreRepository storeRepository;

    // get valid store
    public StoreEntity getStoreWithThrow(Long id){
        return storeRepository.findFirstByIdAndStatusOrderByIdDesc(id, StoreStatus.REGISTERED)
                .orElseThrow(()->new ApiException(ErrorCode.NULL_POINT));
    }

    // register store

    public StoreEntity register(StoreEntity store){
        return Optional.ofNullable(store)
                .map(it->{
                    it.setStar(0.0);
                    it.setStatus(StoreStatus.REGISTERED);
                    //todo registered at
                    return storeRepository.save(it);
                })
                .orElseThrow(()->new ApiException(ErrorCode.NULL_POINT));
    }

    // search by category
    public List<StoreEntity> searchByCategory(StoreCategory storeCategory){
        var list = storeRepository.findAllByStatusAndCategoryOrderByStarDesc(StoreStatus.REGISTERED,storeCategory);
        return list;
    }

    // all stores

    public List<StoreEntity> registerStore(){
        var list = storeRepository.findAllByStatusOrderByIdDesc(StoreStatus.REGISTERED);
        return list;
    }
}
