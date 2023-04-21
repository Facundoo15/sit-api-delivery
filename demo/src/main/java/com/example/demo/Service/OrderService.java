package com.example.demo.Service;

import com.example.demo.entitites.Food;
import com.example.demo.entitites.Order;
import org.springframework.stereotype.Service;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class OrderService {

    private List<Order> orderList;
    private List<Food> foodList;

    public OrderService() {
        this.foodList = new ArrayList<>();
        this.orderList = new ArrayList<>();
    }

    // TODO: US1 Como usuario, quiero poder ver un menú de comida para seleccionar lo que
    // quiero pedir.

    public void addFood(Food food) {
        this.foodList.add(food);
    }

    public List<Food> getAllMenu() {
        return this.foodList;
    }

    // TODO: US2 Como usuario, quiero poder agregar varios platos a un pedido y ver el precio
    //total del pedido.

    public void addOrder(Order or) {
        or.setCreationTime(LocalTime.now());
        LocalTime estimated = or.getCreationTime();
        estimated = estimated.plusMinutes(or.getListFood().size() * 10L);
        or.setEstimatedDeliveryTime(estimated);
        this.orderList.add(or);
    }

    // Se debe calcular el precio total del pedido en función de los platos
    // seleccionados.
    public double calculateDeliveryPrice(String id) {
        Optional<Order> orderOptional = this.orderList.stream().filter(e -> e.getId().equals(id)).findFirst();
        if (orderOptional.isPresent()) {
            Order or = orderOptional.get();
            return or.getListFood().stream().map(Food::getPrice).reduce(0.0, Double::sum);
        }
        return 0;
    }

    // Actualizar el estado del pedido

    public Order updateStatusOrder(String id) {
        Optional<Order> orderUpdate = this.orderList.stream().filter(e -> e.getId().equals(id)).findFirst();
        return orderUpdate.map(this::updateStatus).orElse(null);
    }

    private Order updateStatus(Order order) {
        String status = order.getStatus();
        switch (status) {
            case "CONFIRMADO" -> order.setStatus("EN PREPARACIÓN");
            case "EN PREPARACIÓN" -> order.setStatus("PREPARADO");
            case "PREPARADO" -> order.setStatus("ENTREGADO");
        }
        return order;
    }

    // TODO: US3 Como usuario, quiero ver el estado actual de mi pedido, la hora de creación del
    //pedido y la hora estimada de entrega.

    public String getStatusOrder(String id) {
        Optional<Order> order = this.orderList.stream().filter(e -> e.getId().equals(id)).findFirst();
        if (order.isPresent()) {
            Order orderStatus = order.get();
            return "ORDER: " + orderStatus.getId() + "\nESTADO: " + orderStatus.getStatus() + "\nHORA DE CREACIÓN: " + orderStatus.getCreationTime() + "\nHORA ESTIMADA DE ENTREGA: " + orderStatus.getEstimatedDeliveryTime();
        }
        return "Orden no encontrada";

    }


}
