package ac.essex.graphing.charts.continuous;

import ac.essex.graphing.plotting.ContinuousFunctionPlotter;

public class PolynomialFour extends ContinuousFunctionPlotter {

    public String getName() {
        return "GeneticAlgorithmPolynomial.Polynomial of the genetic algorithm";
    }

    public double getY(double x) {

        return (4 + (x * 5) + (-2 * x * x) + (7 * x * x * x)+ (-4 * x * x * x * x));
    }
}

