package com.alkemy.wallet.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.UpdateTimestamp;
import org.hibernate.annotations.Where;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "Users")
@SQLDelete(sql = "UPDATE Users SET SOFT_DELETE = true WHERE USER_ID = ?")
@Where(clause = "soft_Delete = false")
public class UserEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "USER_ID", nullable = false)
    private Long userId;

    @Column(name = "firstName", nullable = false)
    @NotNull(message = "el campo firstName no puede ser nulo")
    @Pattern(regexp = "[a-zA-Z ]{2,64}", message = "Debe contener solo letras ni estar vacio.")
    private String firstName;

    @Column(name = "lastName", nullable = false)
    @NotNull(message = "el campo firstName no puede ser nulo")
    @Pattern(regexp = "[a-zA-Z ]{2,64}", message = "Debe contener solo letras ni estar vacio.")
    private String lastName;

    @Column(name = "email", nullable = false, unique = true)
    @Pattern(regexp ="^[A-Za-z0-9+_.-]+@(.+)$", message = "Ingrese un mail valido")
    private String email;

    @Column(name = "password", nullable = false)
    @NotNull(message = "el campo password no puede ser nulo")
    @NotBlank(message = "el campo password no tener espacios en blanco")
    private String password;

    @ManyToOne()
    @JoinColumn(name = "roleId")
    private RoleEntity role;

    @CreationTimestamp
    @Column(name = "creationDate")
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate creationDate;

    @UpdateTimestamp
    @Column(name = "updateDate")
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate updateDate;

    @Column(name = "SOFT_DELETE")
    @Builder.Default
    private Boolean softDelete = Boolean.FALSE;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    @Builder.Default
    private List<AccountEntity> accountsList = new ArrayList();

}
