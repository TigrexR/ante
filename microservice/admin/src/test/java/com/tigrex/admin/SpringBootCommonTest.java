package com.tigrex.admin;

import com.tigrex.admin.context.SystemData;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@SpringBootTest
@RunWith(SpringRunner.class)
public class SpringBootCommonTest {

    @Test
    public void test() {
        System.out.println(SystemData.configMap.get("george"));
    }
}
