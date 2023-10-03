package org.delivery.db.userorder;

import org.delivery.db.userorder.enums.UserOrderStatus;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface UserOrderRepository extends JpaRepository<UserOrderEntity,Long> {
    // user's all orders: select * from user_order where user_id=? and status = ? order by id desc limit 1
    List<UserOrderEntity> findAllByUserIdAndStatusOrderByIdDesc(Long userId, UserOrderStatus status);

    // user's all orders: select * from user_order where user_id=? and status in (?,?,?) order by id desc limit 1
    List<UserOrderEntity> findAllByUserIdAndStatusInOrderByIdDesc(Long userId, List<UserOrderStatus> status);

    // user's particular order: select * from user_order where id=? and status = ? and user_id=? order by id desc limit 1
    Optional<UserOrderEntity>findAllByIdAndStatusAndUserIdOrderByIdDesc(Long Id, UserOrderStatus status, Long userId);

    Optional<UserOrderEntity> findAllByIdAndUserId(Long id, Long userId);
}
