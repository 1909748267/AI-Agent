package com.cp.aiagent.rag;

import jakarta.annotation.Resource;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.ai.rag.Query;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class MyQueryExpanderTest {
    @Resource
    private MyQueryExpander myQueryExpander;

    @Test
    void getExpanderQueries() {
        List<Query> expanderQueries = myQueryExpander.getExpanderQueries("陈鹏是谁？");
        Assertions.assertNotNull(expanderQueries);
    }
}