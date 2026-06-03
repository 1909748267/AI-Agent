package com.cp.aiagent.rag;

import jakarta.annotation.Resource;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.ai.document.Document;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class MyDocumentRetrieverTest {
    @Resource
    private MyDocumentRetriever myDocumentRetriever;

    @Test
    void getDocument() {
        List<Document> document = myDocumentRetriever.getDocument("婚后关系不亲密怎么办？");
        Assertions.assertNotNull(document);
    }
}