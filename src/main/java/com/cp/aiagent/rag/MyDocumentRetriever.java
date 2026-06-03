package com.cp.aiagent.rag;

import jakarta.annotation.Resource;
import org.springframework.ai.document.Document;
import org.springframework.ai.rag.Query;
import org.springframework.ai.rag.retrieval.search.DocumentRetriever;
import org.springframework.ai.rag.retrieval.search.VectorStoreDocumentRetriever;
import org.springframework.ai.vectorstore.VectorStore;
import org.springframework.ai.vectorstore.filter.FilterExpressionBuilder;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class MyDocumentRetriever {
    @Resource
    private VectorStore pgVectorVectorStore;
    public List<Document> getDocument(String query) {
        DocumentRetriever retriever = VectorStoreDocumentRetriever.builder()
                .vectorStore(pgVectorVectorStore)
                .similarityThreshold(0.0)
                .topK(2)
                .filterExpression(new FilterExpressionBuilder()
                        .eq("status", "单身")
                        .build())
                .build();
        return retriever.retrieve(new Query(query));
    }
}
