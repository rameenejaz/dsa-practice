public class ReliefAllocation {
    private String regionID;
    private double urgencyLevel;
    private double population;
    private double accessIndex;
    public ReliefAllocation(String regionID, double urgencyLevel, double population, double accessIndex) {
        this.regionID=regionID;
        this.urgencyLevel=urgencyLevel;
        this.population=population;
        this.accessIndex=accessIndex;
    }
    public double calcPriority(double urgencyLevel, double population, double accessIndex) {
        double priority=0.6*urgencyLevel+0.3*(population/1000) + 0.1*(100-accessIndex);
        return priority;
    }

}
