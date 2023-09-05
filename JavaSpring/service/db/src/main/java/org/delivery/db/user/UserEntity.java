package org.delivery.db.user;


import lombok.*;
import lombok.experimental.SuperBuilder;
import org.delivery.db.BaseEntity;
import org.delivery.db.enums.UserStatus;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "user")
@NoArgsConstructor
@AllArgsConstructor
@Data
@EqualsAndHashCode(callSuper = true)
@SuperBuilder
public class UserEntity extends BaseEntity {

    @Column(length=50, nullable = false)
    private String name;

    @Column(length=100, nullable = false)
    private String email;

    @Column(length=100, nullable = false)
    private String password;

    @Column(length=50, nullable = false)
    @Enumerated(EnumType.STRING) // use name (default:index)
    private UserStatus status;

    @Column(length=100, nullable = false)
    private String address;

    private LocalDateTime registered_at;

    private LocalDateTime unregistered_at;

    private LocalDateTime last_login_at;
}
