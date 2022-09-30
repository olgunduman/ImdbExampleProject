package com.example.bootcampodev.adapter.jpa.account;

import com.example.bootcampodev.adapter.jpa.common.BaseEntity;
import lombok.*;

import javax.persistence.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "account")
@Entity(name = "account")
public class AccountEntity extends BaseEntity {

    private String email;
    private String password;
}
