import java.util.ArrayList;

/**
 * Created by dineshparimi on 1/31/17.
 */
public class CityTree {
    public City city;
    public CityTree[] branches;
    public int[] branchCosts;
    public ArrayList<CityTree> ancestors = new ArrayList<>();

    public CityTree(City c, CityTree[] b, ArrayList<CityTree> a) {
        city = c;
        branches = b;
        ancestors = a;
    }

    public void generateBranches() {
        branches = new CityTree[city.neighbors.length];
        for (int i = 0; i < city.neighbors.length; i ++) {
            ArrayList<CityTree> newAncestors = new ArrayList<CityTree>();
            for (CityTree ancestor : ancestors) {
                newAncestors.add(ancestor);
            }
            newAncestors.add(this);
            branches[i] = new CityTree(city.neighbors[i], null, newAncestors);
        }
        branchCosts = new int[city.neighborCosts.length];
        System.arraycopy(city.neighborCosts, 0, branchCosts, 0, city.neighborCosts.length);
    }

    public String toString() {
        return city.name;
    }

    public boolean isAncestorOfSelf() {
        for (CityTree ancestor : ancestors) {
            if (city == ancestor.city) {
                return true;
            }
        }
        return false;
    }
}
