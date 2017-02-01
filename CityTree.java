/**
 * Created by dineshparimi on 1/31/17.
 */
public class CityTree {
    public City city;
    public CityTree[] branches;
    public CityTree parent;
    public int[] branchCosts;

    public CityTree(City c, CityTree[] b, CityTree p) {
        city = c;
        branches = b;
        parent = p;
    }

    public void generateBranches() {
        branches = new CityTree[city.neighbors.length];
        for (int i = 0; i < city.neighbors.length; i ++) {
            branches[i] = new CityTree(city.neighbors[i], null, this);
        }
        branchCosts = new int[city.neighborCosts.length];
        System.arraycopy(city.neighborCosts, 0, branchCosts, 0, city.neighborCosts.length);
    }
}
