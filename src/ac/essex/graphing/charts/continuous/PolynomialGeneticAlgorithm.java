package ac.essex.graphing.charts.continuous;

import GeneticAlgorithmPolynomial.IndividualExample;
import GeneticAlgorithmPolynomial.Population;
import ac.essex.graphing.plotting.ContinuousFunctionPlotter;

public class PolynomialGeneticAlgorithm extends ContinuousFunctionPlotter {

    private Population population;

    public PolynomialGeneticAlgorithm(Population population) {
        this.population = population;
    }

    public String getName() {
        return "Polynomial of the genetic algorithm";
    }

    @Override
    public double getY(double x) {
        IndividualExample ind = (IndividualExample) population.getFittest();
        return ind.getGenes().calculate(x);
    }
}

