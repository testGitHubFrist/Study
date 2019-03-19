package com.zsc.study.junit.example;

import com.zsc.study.junit.example.impl.DefaultController;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * @Auther: zhangshanchuang
 * @Date: 19/3/19 18:00
 * @Description:
 */
public class DefaultControllerTest {

    private DefaultController defaultController;

    @Before
    public void instantiate() throws Exception {
        defaultController = new DefaultController();
    }

    @Test
    public void testMethod(){
        throw new RuntimeException("me");
    }
}
