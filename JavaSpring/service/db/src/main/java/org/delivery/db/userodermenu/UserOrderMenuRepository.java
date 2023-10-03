package org.delivery.db.userodermenu;

import org.delivery.db.userodermenu.enums.UserOrderMenuStatus;
import org.delivery.db.userorder.enums.UserOrderStatus;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserOrderMenuRepository extends JpaRepository<UserOrderMenuEntity, Long> {
    // select * from user_order_menu where userorderid = ? and status = ?
    List<UserOrderMenuEntity> findAllByUserOrderIdAndStatus(Long userOrderId, UserOrderStatus status);
}
