package org.delivery.db.storeuser;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import org.delivery.db.BaseEntity;
import org.delivery.db.storemenu.enums.StoreMenuStatus;

import javax.persistence.*;
import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "store_store")
public class StoreUserEntity extends BaseEntity {

    @Column(nullable = false)
    private Long storeId;

    @Column(length= 100, nullable = false)
    private String email;

    @Column(length= 100, nullable = false)
    private String password;

    @Column(length = 50, nullable = false)
    @Enumerated(EnumType.STRING)
    private StoreUserStatus status;

    @Column(length= 100, nullable = false)
    private String role;
}
