package net.vnict.shop;

import net.vnict.shop.domain.entities.User;
import net.vnict.shop.domain.model.Item;
import net.vnict.shop.domain.model.Order;
import net.vnict.shop.service.OrderService;
import net.vnict.shop.service.UserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

@RunWith(SpringRunner.class)
@SpringBootTest
public class OrderTest {
    @Autowired
    OrderService orderService;

    @Autowired
    UserService userService;

    @Test
    public void orderTest_saveOK(){
        User user = userService.findOne(1);
        net.vnict.shop.domain.model.User dbUser = new net.vnict.shop.domain.model.User();
        dbUser.setEmail(user.getEmail());
        dbUser.setName(user.getName());
        dbUser.setPassword(user.getPassword());
        dbUser.setId(user.getId());
        Order order = new Order();
        Item item = new Item();
        item.setProductId(1);
        item.setQuantity(2);
        List<net.vnict.shop.domain.model.Item> items = new ArrayList<>();
        items.add(item);
        order.setAddress("Da Nang");
        order.setName("Mua hang");
        order.setPhone("123456789");
        order.setNote("note note");
        order.setItems(items);
        order.setUser(dbUser);
        orderService.save(order);
    }

    @Test
    public void orderTest_saveFail_NoItems(){
        User user = userService.findOne(1);
        net.vnict.shop.domain.model.User dbUser = new net.vnict.shop.domain.model.User();
        dbUser.setEmail(user.getEmail());
        dbUser.setName(user.getName());
        dbUser.setPassword(user.getPassword());
        dbUser.setId(user.getId());
        Order order = new Order();
        order.setAddress("Da Nang2");
        order.setName("Mua hang");
        order.setPhone("123456789");
        order.setNote("note note");
        order.setUser(dbUser);
        assertThrows(NullPointerException.class, () -> {
            orderService.save(order);
        });
    }

    @Test
    public void orderTest_saveFail_NoUser(){
        Order order = new Order();
        Item item = new Item();
        item.setProductId(1);
        item.setQuantity(2);
        List<net.vnict.shop.domain.model.Item> items = new ArrayList<>();
        items.add(item);
        order.setAddress("Da Nang");
        order.setName("Mua hang");
        order.setPhone("123456789");
        order.setNote("note note");
        order.setItems(items);
        assertThrows(NullPointerException.class, () -> {
            orderService.save(order);
        });
    }

    @Test
    public void orderTest_saveFail_NullObject(){
        Order order = null;
        assertThrows(NullPointerException.class, () -> {
            orderService.save(order);
        });
    }

    @Test
    public void orderTest_deleteFail(){
        assertThrows(NoSuchElementException.class, () -> {
            orderService.delete(222);
        });
    }

    @Test
    public void orderTest_deleteOK(){
        List<net.vnict.shop.domain.entities.Order> orders = (List<net.vnict.shop.domain.entities.Order>) orderService.findAll();
        int id = orders.get(orders.size()-1).getId();
        assertThat(orderService.delete(id)).isEqualTo(true);
    }

    @Test
    public void orderTest_findOrdersByUser(){
        User user = userService.findOne(1);
        List<net.vnict.shop.domain.entities.Order> orders = orderService.findByUser(user);
        assertThat(orders.size()).isGreaterThan(0);
    }
}
