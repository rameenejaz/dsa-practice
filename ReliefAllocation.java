public class ReliefAllocation {
    private String regionID;
    private int urgencyLevel;
    private int population;
    private int accessIndex;
    public ReliefAllocation(String regionID, int urgencyLevel, int population, int accessIndex) {
        this.regionID=regionID;
        this.urgencyLevel=urgencyLevel;
        this.population=population;
        this.accessIndex=accessIndex;
    }
    
}
