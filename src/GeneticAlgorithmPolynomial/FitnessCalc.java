package GeneticAlgorithmPolynomial;

/**
 * Louis Boursier
 * 30/09/2018
 */

public class FitnessCalc {

    static Vec2d[] data;

    /* Public methods */

    // Set the data
    public static void setData(Vec2d[] givenData) {
        data = givenData;
    }

    // Calculate individuals fitness by comparing it to our candidate solution
    static double getFitness(IndividualExample individual) {
        double fitness = 0;

        // Error sum of squares
        for(Vec2d point : data){
            double difference = individual.getGenes().calculate(point.x) - point.y;
            fitness += (difference * difference);
        }
        return fitness;
    }
}