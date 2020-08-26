package com.google.univiz;

import com.google.inject.Guice;
import com.google.inject.Injector;
import com.google.inject.grapher.graphviz.GraphvizGrapher;
import com.google.inject.grapher.graphviz.GraphvizModule;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;

public final class Grapher {
  public static void main(String... args) throws IOException {
    String filename = "guice_graph.dot";
    PrintWriter out = new PrintWriter(new File(filename), "UTF-8");
    Injector demoInjector = Guice.createInjector(new UnivizBootstrapModule());
    Injector injector = Guice.createInjector(new GraphvizModule());
    GraphvizGrapher grapher = injector.getInstance(GraphvizGrapher.class);
    grapher.setOut(out);
    grapher.setRankdir("TB");
    grapher.graph(demoInjector);
  }
}
