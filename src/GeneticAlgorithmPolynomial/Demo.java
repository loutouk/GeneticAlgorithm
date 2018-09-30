package GeneticAlgorithmPolynomial;

import ac.essex.graphing.charts.continuous.PolynomialFour;
import ac.essex.graphing.charts.continuous.PolynomialGeneticAlgorithm;
import ac.essex.graphing.plotting.Graph;
import ac.essex.graphing.plotting.PlotSettings;
import ac.essex.graphing.swing.GraphApplication;

import java.awt.*;
import java.util.Random;
import java.util.concurrent.TimeUnit;

/**
 * Demonstrates how to display a graph using JavaPlot.
 * <p>
 * <p>
 * This program is free software; you can redistribute it and/or
 * modify it under the terms of the GNU General Public License
 * as published by the Free Software Foundation; either version 2
 * of the License, or (at your option) any later version,
 * provided that any use properly credits the author.
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 * GNU General Public License for more details at http://www.gnu.org
 * </p>
 *
 * @author Olly Oechsle, University of Essex
 * @version 1.0
 */
public class Demo {

    static Graph graph;

    public static void main(String[] args) {


        Vec2d[] graph = new Vec2d[100];
        for (int i = 0; i < graph.length; i++) {
            graph[i] = new Vec2d();
            Random random = new Random();
            double randomValue = random.nextDouble() * 100 - random.nextDouble() * 100;
            graph[i].x = randomValue;
            graph[i].y = 4 + (randomValue * 5) + (-2 * randomValue * randomValue) + (7 * randomValue * randomValue * randomValue) + (-4 * randomValue * randomValue * randomValue * randomValue);
        }
        FitnessCalc.setData(graph);

        // Create an initial population
        Population myPop = new Population(100, true);
        GraphApplication g = new GraphApplication(getExampleGraph2(myPop));

        // Evolve our population until we reach an optimum solution
        int generationCount = 0;
        while (generationCount < 2000 && Math.round(myPop.getFittest().getFitness()) != 0) {
            generationCount++;
            //System.out.println("Generation: " + generationCount + " Fittest: " + myPop.getFittest().getFitness());
            //GeneticAlgorithmPolynomial.IndividualExample current = (GeneticAlgorithmPolynomial.IndividualExample) myPop.getFittest();
            //System.out.println(current.getGenes());
            myPop = Algorithm.evolvePopulation(myPop);
            // Use the Graph Application to display the data

            editGraph(myPop, generationCount);
            g.update();

            try {
                TimeUnit.MILLISECONDS.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        if (Math.round(myPop.getFittest().getFitness()) == 0) {
            System.out.println("Solution found!");
        } else {
            System.out.println("No solution found. Best result:");
        }
        System.out.println("Generation: " + generationCount);
        System.out.print("Genes: ");
        System.out.println(myPop.getFittest());
        System.out.print("Fitness: ");
        System.out.println(Math.round(myPop.getFittest().getFitness()));


    }

    public static Graph getExampleGraph2(Population myPop) {

        PlotSettings p = new PlotSettings(-2.5, 2.5, -60, 60);
        p.setPlotColor(Color.RED);
        p.setGridSpacingX(100.0);
        p.setGridSpacingY(100.0);
        p.setTitle("Generation 0");
        graph = new Graph(p);
        graph.functions.add(new PolynomialFour());
        p.setPlotColor(Color.BLUE);
        graph.functions.add(new PolynomialGeneticAlgorithm(myPop));

        return graph;
    }

    public static void editGraph(Population myPop, int generation) {
        graph.plotSettings.setTitle("Generation " + generation);
        graph.functions.remove(1);
        graph.functions.add(new PolynomialGeneticAlgorithm(myPop));
    }

}
