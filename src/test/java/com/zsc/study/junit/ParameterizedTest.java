package com.zsc.study.junit;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.Arrays;
import java.util.Collection;
import static org.junit.Assert.*;
/**
 * @Auther: zhangshanchuang
 * @Date: 19/3/19 10:28
 * @Description: 参数化的测试运行容器运行你使用不同的参数多次运行同一个测试
 */
@RunWith(value = Parameterized.class)
public class ParameterizedTest {
    private double expected;

    private double valueone;

    private double valuetwo;

    @Parameterized.Parameters
    public static Collection<Integer[]> getParameters() {
        return Arrays.asList(new Integer[][]{{2,1,1},{3,2,1},{4,3,1},});
    }

    public ParameterizedTest(double expected, double valueone, double valuetwo) {
        this.expected = expected;
        this.valueone = valueone;
        this.valuetwo = valuetwo;
    }

    @Test
    public void testSum(){

        Calculator calculator = new Calculator();
        assertEquals(expected,calculator.add(valueone,valuetwo),0);
    }
}
