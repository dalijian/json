package com.lijian.guava;

import com.google.common.graph.Graph;
import com.google.common.graph.GraphBuilder;
import com.google.common.graph.MutableGraph;
import org.junit.jupiter.api.Test;

public class GraphTest {


    @Test
    public void createTest(){


        MutableGraph<String> graph = GraphBuilder.directed().build();

        graph.addNode("A");
        graph.addNode("B");
        graph.putEdge("A", "B");

        graph.nodes().stream().forEach(x-> System.out.println(x));


    }
}
