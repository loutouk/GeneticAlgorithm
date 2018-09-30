package GeneticAlgorithmPolynomial; /**
 * Louis Boursier
 * 30/09/2018
 */

import java.util.Random;

public class IndividualExample extends Individual {

    // number of degrees for the polynomial
    // the larger it is, the harder it is to find the solution
    private static int defaultGeneLength = 10;
    // the maximum absolute value of the possible coefficients
    // the larger it is, the harder it is to find the solution
    private static final int MAX_COEFFICIENT = 10;
    private Polynomial genes;
    private double fitness = -1;

    public IndividualExample(){
        int[] array = new int[defaultGeneLength];
        for (int i = 0; i < defaultGeneLength; i++) {
            int value = getRandom(-MAX_COEFFICIENT,MAX_COEFFICIENT);
            array[i] = value;
        }
        genes = new Polynomial(array);
    }

    // Create a random individual
    public void generateIndividual() {
        int[] array = new int[defaultGeneLength];
        for (int i = 0; i < defaultGeneLength; i++) {
            int value = getRandom(-MAX_COEFFICIENT,MAX_COEFFICIENT);
            array[i] = value;
        }
        genes = new Polynomial(array);
    }

    /* Getters and setters */
    // Use this if you want to create individuals with different gene lengths
    public static void setDefaultGeneLength(int length) {
        defaultGeneLength = length;
    }

    public int getGene(int index) {
        return genes.getTerm(index);
    }

    public void setGene(int index, int value) {
        genes.setTerm(index, value);
        fitness = -1;
    }

    /* Public methods */
    public int size() {
        return genes.size();
    }

    public double getFitness() {
        if (fitness == -1) {
            fitness = FitnessCalc.getFitness(this);
        }
        return fitness;
    }

    @Override
    public String toString() {
        return genes.toString();
    }

    // Crossover individuals
    public IndividualExample crossover(Individual other) {
        IndividualExample newSol = new IndividualExample();
        // Loop through genes
        for (int i = 0; i < this.genes.size(); i++) {
            // Crossover
            if (Math.random() <= Algorithm.uniformRate) {
                newSol.setGene(i, this.getGene(i));
            } else {
                newSol.setGene(i, other.getGene(i));
            }
        }
        return newSol;
    }

    // Mutate an individual
    public void mutate() {
        // Loop through genes
        for (int i = 0; i < this.genes.size(); i++) {
            if (Math.random() <= Algorithm.mutationRate) {
                // Create random gene
                int value = getRandom(-MAX_COEFFICIENT,MAX_COEFFICIENT);
                this.setGene(i, value);
            }
        }
    }

    private int getRandom(int start, int end) {
        Random rand = new Random();
        // value is between start and end
        int value = rand.nextInt(end-start);
        value += start;
        return value;
    }

    public Polynomial getGenes() {
        return genes;
    }
}