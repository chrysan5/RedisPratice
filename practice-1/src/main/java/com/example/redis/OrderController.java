package com.example.redis;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("orders")
public class OrderController {
    private final OrderRepository orderRepository;

    public OrderController(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    @PostMapping
    public ItemOrder create(@RequestBody ItemOrder order) {
        return orderRepository.save(order);
    }

    @GetMapping
    public List<ItemOrder> readAll() {
        List<ItemOrder> orders = new ArrayList<>();
        orderRepository.findAll().forEach(orders::add);
        return orders;
    }

    @GetMapping("{id}")
    public ItemOrder readOne(@PathVariable("id") String id) {
        return orderRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    @PutMapping("{id}")
    public ItemOrder update(@PathVariable("id") String id, @RequestBody ItemOrder order) {
        ItemOrder target = orderRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        target.setItem(order.getItem());
        target.setCount(order.getCount());
        target.setTotalPrice(order.getTotalPrice());
        target.setStatus(order.getStatus());
        return orderRepository.save(target);
    }

    @DeleteMapping("{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    //@ResponseStatus로 특정 HTTP 상태 코드를 설정할 수 있습니다.
    //이 애너테이션을 통해 특정 HTTP 응답에 대해 상태 코드를 지정할 수 있으며, 주로 RESTful API에서 사용됩니다.
    //204 No Content 상태 코드는 요청이 성공적으로 처리되었지만 응답 본문에 콘텐츠가 없다는 것을 나타냅니다. 주로 데이터가 없거나 응답이 필요하지 않은 상황에서 사용됩니다.
    public void delete(@PathVariable("id") String id) {
        orderRepository.deleteById(id);
    }
}
