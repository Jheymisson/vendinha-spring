package io.github.venda.domain.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Entity
@Table(name = "cliente")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ClienteEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Integer id;

    @Column(name = "cpf", length = 11)
    private String cpf;

    @Column(name = "nome", length = 100)
    private String nome;

    @JsonIgnore
    @OneToMany(mappedBy = "clienteEntity", fetch = FetchType.LAZY)
    private Set<PedidoEntity> pedidoEntity;

    public ClienteEntity(String nome) {
        this.nome = nome;
    }

    public ClienteEntity(Integer id, String nome) {
        this.nome = nome;
        this.id = id;
    }

}
