package com.alkemy.wallet.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "Roles")
public class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "roleId", nullable = false)
    private Long roleId;
    @Column(name = "name", nullable = false)
    @Enumerated(EnumType.STRING)
    private RoleName name;

    @Column(name = "description", nullable = true)
    private String description;

    @CreationTimestamp
    @Column(name = "creationDate")
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate creationDate = LocalDate.now();
    @UpdateTimestamp
    @Column(name = "updateDate")
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate updateDate;

}
