/**
 * Louis Boursier
 * 30/09/2018
 */

import java.util.Random;

public class Main {

    public static void main(String[] args) {


        // Graph of f(x) = 4+5x-2x^2+7x^3
        // Let suppose we can guess how many degrees the equation got by visualizing the number of curves
        // The degree of the polynomial is hard coded in the variable defaultGeneLength of the IndividualExample class
        // so for this one we put IndividualExample = 4 (the constant + 3 degrees)

        Vec2d[] graph = new Vec2d[100];
        for(int i=0 ; i<graph.length ; i++){
            graph[i] = new Vec2d();
            Random random = new Random();
            double randomValue = random.nextDouble() * 1000 - random.nextDouble() * 1000;
            graph[i].x = randomValue;
            graph[i].y = 4 + (randomValue * 5) + (-2 * randomValue * randomValue) + (7 * randomValue * randomValue * randomValue);
        }
        FitnessCalc.setData(graph);

        // Create an initial population
        Population myPop = new Population(50, true);

        // Evolve our population until we reach an optimum solution
        int generationCount = 0;
        while (generationCount<100) {
            generationCount++;
            //System.out.println("Generation: " + generationCount + " Fittest: " + myPop.getFittest().getFitness());
            IndividualExample current = (IndividualExample) myPop.getFittest();
            //System.out.println(current.getGenes());
            myPop = Algorithm.evolvePopulation(myPop);
        }
        System.out.println("Solution found!");
        System.out.println("Generation: " + generationCount);
        System.out.println("Genes:");
        System.out.println(myPop.getFittest());

    }
}