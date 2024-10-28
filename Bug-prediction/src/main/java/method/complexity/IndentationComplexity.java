/*
Java based Implementation of,
 Reading Beside the Lines: Indentation as a Proxy for Complexity Metrics
Abram Hindle et al., ICPC paper.
Section 5 states that logical indentation does not matter.. So we only care about raw indentation..

 */

package method.complexity;
import org.apache.commons.math3.analysis.function.Sqrt;
import org.apache.commons.math3.stat.descriptive.DescriptiveStatistics;

import java.text.DecimalFormat;

public class IndentationComplexity {

    static DecimalFormat decimalFormat = new DecimalFormat("##.00");

    private int sum;
    private double average;
    private double median;
    private double standardDeviation;
    private double variance;
    private DescriptiveStatistics descriptiveStatistics;

    public IndentationComplexity(){
        sum=0;
        average =-1;
        median =-1;
        standardDeviation =-1;
        variance = -1;
        descriptiveStatistics = new DescriptiveStatistics();
    }

    public void calculateIndentation(String code){
        String[] lines = code.split("\r\n|\r|\n");

        int spaceCount;
        boolean isBlankLine;

        for(String line: lines){

            // adopted and modified from https://stackoverflow.com/questions/9655753/how-to-count-the-spaces-in-a-java-string
            spaceCount = 0;
            isBlankLine = true;
            for (char c : line.toCharArray()) {
                if (c == ' ' || c == '\t') {
                    if(c==' '){
                        spaceCount++;
                    }
                    else{
                        spaceCount += 8; // tab is equivalent to 8 spaces, section 3.1, last paragraph
                    }
                }
                else{
                    isBlankLine = false;// it was not a blank line
                    break;
                }
            }

            if(!isBlankLine){
                this.sum += spaceCount;
                this.descriptiveStatistics.addValue(spaceCount);
            }

        }

       this.average = descriptiveStatistics.getMean();
        // because from figure 1 in the paper, and communication with Abram,
        //he calculated population standard deviation..
       this.standardDeviation = Double.parseDouble(decimalFormat.format(Math.sqrt(descriptiveStatistics.getPopulationVariance())));
       this.variance = descriptiveStatistics.getVariance();
       this.median = descriptiveStatistics.getPercentile(50);
      // System.out.print(average+","+median+","+standardDeviation+","+variance);
    }

    public int getSum() {
        return sum;
    }

    public void setSum(int sum) {
        this.sum = sum;
    }

    public double getAverage() {
        return average;
    }

    public void setAverage(double average) {
        this.average = average;
    }

    public double getMedian() {
        return median;
    }

    public void setMedian(int median) {
        this.median = median;
    }

    public double getStandardDeviation() {
        return standardDeviation;
    }

    public void setStandardDeviation(double standardDeviation) {
        this.standardDeviation = standardDeviation;
    }

    public double getVariance() {
        return variance;
    }

    public void setVariance(double variance) {
        this.variance = variance;
    }

}
