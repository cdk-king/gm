package com.cdk.controller;


import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

//单元测试
@RunWith(SpringRunner.class)
@SpringBootTest(classes = Main.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class TestControllerTest {
    @Test
    public void testOne() {
        System.out.println("test hello 1");
    }

    @Test
    public void testTwo() {
        System.out.println("test hello 2");
    }

    @Before
    public void testBefore() {
        System.out.println("before");
    }

    @After
    public void testAfter() {
        System.out.println("after");
    }

}
