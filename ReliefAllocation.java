public class ReliefAllocation {
    
    protected String regionID;
    protected int urgencyLevel;
    protected int population;
    protected int accessIndex;
    public ReliefAllocation(String regionID, int urgencyLevel, int population, int accessIndex) {
        this.regionID=regionID;
        this.urgencyLevel=urgencyLevel;
        this.population=population;
        this.accessIndex=accessIndex;
    }
    public double calcPriority(int urgencyLevel, int population, int accessIndex) {
        double priority=0.6*urgencyLevel+0.3*(population/1000) + 0.1*(100-accessIndex);
        return priority;
    }

}
