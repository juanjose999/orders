package com.orders.direccion;

import com.orders.common.persistence.AuditableEntity;
import com.orders.usuario.Usuario;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "addresses")
@Getter
@Setter
@NoArgsConstructor
public class Direccion extends AuditableEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private Usuario usuario;

    @Column(name = "nombre_destinatario", nullable = false, length = 160)
    private String nombreDestinatario;

    @Column(name = "telefono", length = 20)
    private String telefono;

    @Column(name = "direccion_linea", nullable = false)
    private String direccionLinea;

    @Column(name = "detalle_direccion")
    private String detalleDireccion;

    @Column(name = "ciudad", nullable = false, length = 100)
    private String ciudad;

    @Column(name = "departamento", length = 100)
    private String departamento;


}