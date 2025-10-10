package com.ecommerce.br.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.ecommerce.br.entity.Pedido;

@Repository
public interface PedidoRepository extends JpaRepository<Pedido, Long> {

}
