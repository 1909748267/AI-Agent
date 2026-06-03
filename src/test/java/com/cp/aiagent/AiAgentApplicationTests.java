package com.cp.aiagent;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Stack;

@SpringBootTest
class AiAgentApplicationTests {

    @Test
    void contextLoads() {
        String[] arr=new String[3];
        String s1=new String("hello");
        arr[0]=s1;
        s1=new String("word");
        System.out.println(arr[0]);
        Stack<Object> stack = new Stack<>();

        // 步骤1：创建第一个StringBuilder对象
        StringBuilder currentStr = new StringBuilder("a");
        System.out.println("currentStr初始地址：" + System.identityHashCode(currentStr)); // 输出：比如123456

        // 步骤2：把currentStr压入栈
        stack.push(currentStr);
        System.out.println("栈中元素地址：" + System.identityHashCode(stack.peek())); // 输出：123456（和上面一样）

        // 步骤3：new一个新的StringBuilder，让currentStr指向它
        currentStr = new StringBuilder("b");
        System.out.println("currentStr新地址：" + System.identityHashCode(currentStr)); // 输出：比如789012（变了）

        // 步骤4：再看栈中的元素地址
        System.out.println("栈中元素地址仍然是：" + System.identityHashCode(stack.peek())); // 输出：123456（完全没变！）

        // 步骤5：弹出栈中的元素，看看它的值
        StringBuilder fromStack = (StringBuilder) stack.pop();
        System.out.println("栈中元素的值：" + fromStack.toString()); // 输出：a（不是b！）
    }

}
