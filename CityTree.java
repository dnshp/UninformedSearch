import java.util.ArrayList;
import java.util.Comparator;

/**
 * Created by dineshparimi on 1/31/17.
 */
public class CityTree {
    public City city;
    public CityTree[] branches;
    public int[] branchCosts;
    public ArrayList<CityTree> ancestors = new ArrayList<>();
    public int depth;
    public int pathCost;

    public CityTree(City c, CityTree[] b, ArrayList<CityTree> a, int d, int p) {
        city = c;
        branches = b;
        ancestors = a;
        depth = d;
        pathCost = p;
    }

    public void generateBranches() {
        branches = new CityTree[city.neighbors.length];
        for (int i = 0; i < city.neighbors.length; i ++) {
            ArrayList<CityTree> newAncestors = new ArrayList<>();
            for (CityTree ancestor : ancestors) {
                newAncestors.add(ancestor);
            }
            newAncestors.add(this);
            branches[i] = new CityTree(city.neighbors[i], null, newAncestors, depth + 1, pathCost + city.neighborCosts[i]);
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

    public static class totalCostComparator implements Comparator<CityTree> { // Returns negative if a has a total cost less than b, 0 if same total cost, positive if greater total cost
        public int compare(CityTree a, CityTree b) {
            return a.pathCost - b.pathCost;
        }
    }
}
