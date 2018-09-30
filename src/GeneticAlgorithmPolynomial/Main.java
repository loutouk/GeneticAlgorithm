package GeneticAlgorithmPolynomial; /**
 * Louis Boursier
 * 30/09/2018
 */

import java.util.Random;

public class Main {

    public static void main(String[] args) {


        // Graph of f(x) = 4+5x-2x^2+7x^3-4x^4
        // Let suppose we can guess how many degrees the equation got by visualizing the number of curves
        // The degree of the polynomial is hard coded in the variable defaultGeneLength of the GeneticAlgorithmPolynomial.IndividualExample class
        // So for this one we put defaultGeneLength = 5 (the constant + 4 degrees)
        // Or we can also put a large number of degrees, and let the algorithm find by itself
        // If we specify defaultGeneLength = 10 : 4 + 5x + -2x^2 + 7x^3 + -4x^4 + 0x^5 + 0x^6 + 0x^7 + 0x^8 + 0x^9
        // We can see that the algorithm uses a 0 coefficient, but this method is less efficient
        // It should be use when we have no idea about the degree of the function, so we put a large value for defaultGeneLength

        // For solving this solution, we can use a MAX_COEFFICIENT (GeneticAlgorithmPolynomial.IndividualExample class) of 10
        // indeed, the max coefficient of our equation is 7 for 7x^3
        // The higher the value MAX_COEFFICIENT is, the harder it is for the algorithm to find the solution

        Vec2d[] graph = new Vec2d[100];
        for(int i=0 ; i<graph.length ; i++){
            graph[i] = new Vec2d();
            Random random = new Random();
            double randomValue = random.nextDouble() * 100 - random.nextDouble() * 100;
            graph[i].x = randomValue;
            graph[i].y = 4 + (randomValue * 5) + (-2 * randomValue * randomValue) + (7 * randomValue * randomValue * randomValue)+ (-4 * randomValue * randomValue * randomValue * randomValue);
        }
        FitnessCalc.setData(graph);

        // Create an initial population
        Population myPop = new Population(100, true);

        // Evolve our population until we reach an optimum solution
        int generationCount = 0;
        while (generationCount<2000 && Math.round(myPop.getFittest().getFitness()) != 0) {
            generationCount++;
            //System.out.println("Generation: " + generationCount + " Fittest: " + myPop.getFittest().getFitness());
            //GeneticAlgorithmPolynomial.IndividualExample current = (GeneticAlgorithmPolynomial.IndividualExample) myPop.getFittest();
            //System.out.println(current.getGenes());
            myPop = Algorithm.evolvePopulation(myPop);
        }
        if(Math.round(myPop.getFittest().getFitness()) == 0){
            System.out.println("Solution found!");
        }else{
            System.out.println("No solution found. Best result:");
        }
        System.out.println("Generation: " + generationCount);
        System.out.print("Genes: ");
        System.out.println(myPop.getFittest());
        System.out.print("Fitness: ");
        System.out.println(Math.round(myPop.getFittest().getFitness()));

    }
}