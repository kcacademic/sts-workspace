package com.kchandrakant.mongotests.service;

import com.kchandrakant.mongotests.model.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.testcontainers.containers.MongoDBContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
@Testcontainers
public class UserServiceTest {

    @Autowired
    private UserService userService;

    @Container
    static MongoDBContainer mongoDBContainer = new MongoDBContainer("mongo:6.0").withExposedPorts(27017);

    @DynamicPropertySource
    static void mongoDbProperties(DynamicPropertyRegistry registry) {
        mongoDBContainer.start();
        registry.add("spring.data.mongodb.uri", mongoDBContainer::getReplicaSetUrl);
    }

    @Test
    public void givenUser_whenSave_thenUserIsSaved() {

        User user = new User("1", "Tom Sawyer");
        User savedUser = userService.save(user).block();
        assertEquals(user.getName(), savedUser.getName());
        User foundProduct = userService.findById("1").block();
        assertEquals(user.getName(), foundProduct.getName());
    }
}
