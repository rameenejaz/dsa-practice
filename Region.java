public class Region {
    String id;
    double urgency;
    int population;
    int accessibility;
    Region(String id, double urgency, int population, int accessibility) {
        this.id = id;
        this.urgency = urgency;
        this.population = population;
        this.accessibility = accessibility;
    }
    double getPriority() {
        return 0.6 * urgency + 0.3 * (population / 1000.0) + 0.1 * (100 - accessibility);
    }

}

