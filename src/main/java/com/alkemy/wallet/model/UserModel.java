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
public class UserModel {
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
    @Pattern(regexp = "^\\\\w+[\\\\.\\\\w]*@[\\\\w]+([\\\\.\\\\w]+)+$", message = "Ingrese un mail valido")
    private String email;

    @Column(name = "password", nullable = false)
    @NotNull(message = "el campo password no puede ser nulo")
    @Pattern(regexp = "^(?=.[a-z])(?=.[A-Z])(?=.\\d)(?=.[@#$%^&+=!])(?=.*[^\\s]).{8,20}$",
            message = "La contraseña debe tener: " +
                    " -al menos una letra mayúscula.\n" +
                    " -al menos una letra minúscula.\n" +
                    " -al menos un número.\n" +
                    " -al menos un carácter especial, como @, #, $, etc.\n" +
                    " -una longitud mínima y máxima especificada."
    )
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

    @Column(name = "SOFT_DELETE")
    @Builder.Default
    private Boolean softDelete = Boolean.FALSE;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    @Builder.Default
    private List<Account> accountsList = new ArrayList();

}
