package com.alkemy.wallet.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;
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
@Setter
@Getter
@ToString
@Builder
@Entity
@Table(name = "Users")
@SQLDelete(sql = "UPDATE Users SET soft_delete = true WHERE USER_ID = ?")
@Where(clause = "soft_delete = false")
public class UserEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id", nullable = false)
    private Long userId;

    @Column(name = "first_name", nullable = false)
    @NotNull(message = "el campo firstName no puede ser nulo")
    @Pattern(regexp = "[a-zA-Z ]{2,64}", message = "Debe contener solo letras ni estar vacio.")
    private String firstName;

    @Column(name = "last_name", nullable = false)
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
    @JoinColumn(name = "role_id")
    private RoleEntity roleEntity;

    @CreationTimestamp
    @Column(name = "creation_date")
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate creationDate;

    @UpdateTimestamp
    @Column(name = "update_date")
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate updateDate;

    @Column(name = "soft_delete")
    @Builder.Default
    private Boolean softDelete = Boolean.FALSE;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    @Builder.Default
    private List<AccountEntity> accountsList = new ArrayList();

}
