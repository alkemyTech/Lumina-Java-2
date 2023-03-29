package com.alkemy.wallet.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.UpdateTimestamp;
import org.hibernate.annotations.Where;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "Users")
@SQLDelete(sql = "UPDATE users SET softDelete = true WHERE id = ?")
@Where(clause = "softDelete = false")
public class UserModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "userId", nullable = false)
    private Long userId;

    @Column(name = "firstName", nullable = false)
    private String firstName;

    @Column(name = "lastName", nullable = false)
    private String lastName;

    @Column(name = "email", nullable = false, unique = true)
    private String email;

    @Column(name = "password", nullable = false)
    private String password;

    @ManyToOne()
    @JoinColumn(name = "roleId")
    private Role role;

    @CreationTimestamp
    @Column(name = "creationDate")
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate creationDate;

    @UpdateTimestamp
    @Column(name = "updateDate")
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate updateDate;

    @Column(name = "softDelete")
    private Boolean softDelete = false;

    @OneToMany(mappedBy = "user")
    private List<Account> accountsList = new ArrayList();
}
