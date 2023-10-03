package org.delivery.api.domain.userorder.service;

import lombok.RequiredArgsConstructor;
import org.delivery.api.common.error.ErrorCode;
import org.delivery.api.common.exception.ApiException;
import org.delivery.db.userorder.UserOrderEntity;
import org.delivery.db.userorder.UserOrderRepository;
import org.delivery.db.userorder.enums.UserOrderStatus;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserOrderService {
    private final UserOrderRepository userOrderRepository;

    public UserOrderEntity getUserOrderWithOutStatusWithThrow(Long id, Long userId){
        return userOrderRepository.findAllByIdAndUserId(id, userId)
                .orElseThrow(()-> new ApiException(ErrorCode.NULL_POINT));
    }
    public UserOrderEntity getUserOrderWithThrow(Long Id, Long userId){
        return userOrderRepository.findAllByIdAndStatusAndUserIdOrderByIdDesc(Id, UserOrderStatus.REGISTERED,userId)
                .orElseThrow(()->new ApiException(ErrorCode.NULL_POINT));
    }

    public List<UserOrderEntity> getUserOrderListWithThrow(Long userId){
        return userOrderRepository.findAllByUserIdAndStatusOrderByIdDesc(userId, UserOrderStatus.REGISTERED);
    }


    public List<UserOrderEntity> getUserOrderListWithThrow(Long userId, List<UserOrderStatus> statusList){
        return userOrderRepository.findAllByUserIdAndStatusInOrderByIdDesc(userId, statusList);
    }
    // currently processing list
    public List<UserOrderEntity> current(Long userId){
        return getUserOrderListWithThrow(userId,List.of(UserOrderStatus.ORDER,UserOrderStatus.ACCEPT,UserOrderStatus.COOKING,UserOrderStatus.DELIVERY));
    }

    // past history
    public List<UserOrderEntity> history(Long userId){
        return getUserOrderListWithThrow(userId,List.of(UserOrderStatus.RECEIVE));
    }

    // order
    public UserOrderEntity order(UserOrderEntity userOrderEntity){
        return Optional.ofNullable(userOrderEntity)
                .map(it->{
                    it.setOrderedAt(LocalDateTime.now());
                    it.setStatus(UserOrderStatus.ORDER);
                    return userOrderRepository.save(it);
                })
                .orElseThrow(()->new ApiException(ErrorCode.NULL_POINT));
    }

    // change status

    public UserOrderEntity setStatus(UserOrderEntity userOrderEntity, UserOrderStatus status){
        userOrderEntity.setStatus(status);
        return userOrderRepository.save(userOrderEntity);
    }

    // check order
    public UserOrderEntity accept(UserOrderEntity userOrderEntity){
        userOrderEntity.setAcceptedAt(LocalDateTime.now());
        return setStatus(userOrderEntity,UserOrderStatus.ACCEPT);
    }

    // cooking start

    public UserOrderEntity cooking(UserOrderEntity userOrderEntity){
        userOrderEntity.setCookingStartedAt(LocalDateTime.now());
        return setStatus(userOrderEntity,UserOrderStatus.COOKING);
    }

    // delivery start

    public UserOrderEntity delivery(UserOrderEntity userOrderEntity){
        userOrderEntity.setDeliveryStartedAt(LocalDateTime.now());
        return setStatus(userOrderEntity,UserOrderStatus.DELIVERY);
    }

    // delivery complete

    public UserOrderEntity receive(UserOrderEntity userOrderEntity){
        userOrderEntity.setReceivedAt(LocalDateTime.now());
        return setStatus(userOrderEntity,UserOrderStatus.RECEIVE);
    }
}
