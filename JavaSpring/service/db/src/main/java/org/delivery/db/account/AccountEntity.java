package org.delivery.db.account;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.SuperBuilder;
import org.delivery.db.BaseEntity;

import javax.persistence.Entity;
import javax.persistence.Table;

@SuperBuilder // include inherited fields when building
@Data
@EqualsAndHashCode(callSuper = true) // include inherited fields when comparing objects
@Entity
@Table(name = "account")
public class AccountEntity extends BaseEntity {
}
