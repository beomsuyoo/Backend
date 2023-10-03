package org.delivery.api.domain.userordermenu.service;

import lombok.RequiredArgsConstructor;
import org.delivery.api.common.error.ErrorCode;
import org.delivery.api.common.exception.ApiException;
import org.delivery.db.userodermenu.UserOrderMenuEntity;
import org.delivery.db.userodermenu.UserOrderMenuRepository;
import org.delivery.db.userodermenu.enums.UserOrderMenuStatus;
import org.delivery.db.userorder.enums.UserOrderStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class UserOrderMenuService {
    private final UserOrderMenuRepository userOrderMenuRepository;

    public List<UserOrderMenuEntity> getUserOrderMenu(Long userOrderId){
        return userOrderMenuRepository.findAllByUserOrderIdAndStatus(userOrderId, UserOrderStatus.REGISTERED);
    }

    public UserOrderMenuEntity order(
            UserOrderMenuEntity userOrderMenuEntity
    ){

        return Optional.ofNullable(userOrderMenuEntity)
                .map(it->{
                    it.setStatus(UserOrderStatus.REGISTERED);
                    return userOrderMenuRepository.save(it);
                })
                .orElseThrow(()->new ApiException(ErrorCode.NULL_POINT));
    }
}
