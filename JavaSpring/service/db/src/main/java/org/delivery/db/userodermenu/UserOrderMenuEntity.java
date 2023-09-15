package org.delivery.db.userodermenu;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import org.delivery.db.BaseEntity;
import org.delivery.db.userorder.enums.UserOrderStatus;

import javax.persistence.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
@SuperBuilder()
@Entity
@Table(name = "user_order_menu")
public class UserOrderMenuEntity extends BaseEntity {
    @Column(nullable = false)
    private Long storeMenuId;

    @Column(nullable = false)
    private Long userOrderId;

    @Column(length = 50, nullable = false)
    @Enumerated(EnumType.STRING)
    private UserOrderStatus status;
}
