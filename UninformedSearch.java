import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;

/**
 * Created by dineshparimi on 1/31/17.
 */
public class UninformedSearch {

    // Breadth-first search
    public static void BreadthFirst(City start, City target) {
        LinkedListQueue<CityTree> queue = new LinkedListQueue<>();
        boolean found = false; // Target node located?
        queue.addFirst(new CityTree(start, null, new ArrayList<>(), 0, 0)); // Begin generating tree
        CityTree current = null;
        while (queue.size() != 0) {
            current = queue.removeFirst();
            if (current.city == target) {
                found = true;
                break;
            }
            current.generateBranches(); // Generate next level of tree
            for (CityTree branch : current.branches) {
                queue.addLast(branch);
            }
        }
        if (found) {
            System.out.println(getPath(current));
        } else {
            System.out.println("Not found.");
        }
    }

    // Depth-first search
    public static void DepthFirst(City start, City target) {
        LinkedListQueue<CityTree> queue = new LinkedListQueue<>();
        boolean found = false;
        queue.addFirst(new CityTree(start, null, new ArrayList<>(), 0, 0)); // Begin generating tree
        CityTree current = null;
        while (queue.size() != 0) {
            current = queue.removeFirst();
            if (!current.isAncestorOfSelf()) { // Avoid loops
                if (current.city == target) {
                    found = true;
                    break;
                }
                current.generateBranches(); // Continue generating tree
                for (CityTree branch : current.branches) {
                    queue.addFirst(branch);
                }
            }
        }
        if (found) {
            System.out.println(getPath(current));
        }
        else {
            System.out.println("Not found");
        }
    }

    // Helper method for iterative depth-first search that runs depth-first search to a certain depth
    private static CityTree DepthIterator(City start, City target, int targetDepth, LinkedListQueue<CityTree> q) {
        LinkedListQueue<CityTree> queue = q;
        boolean found = false;
        queue.addFirst(new CityTree(start, null, new ArrayList<>(), 0, 0)); // Begin generating tree
        CityTree current = null;
        while (queue.size() != 0) {
            current = queue.removeFirst();
            if ((!current.isAncestorOfSelf()) && (current.depth <= targetDepth)) { // Avoid loops
                if (current.city == target) {
                    found = true;
                    break;
                }
                current.generateBranches();
                for (CityTree branch: current.branches) {
                    queue.addFirst(branch);
                }
            }
        }
        if (found) {
            return current;
        }
        return null;
    }

    // Depth-first search with iterative deepening
    public static void IterativeDepthFirst(City start, City target) {
        int currentDepth = -1;
        LinkedListQueue<CityTree> queue = new LinkedListQueue<>();
        CityTree returnValue = null;
        while (returnValue == null) { // Run iterator in loop, increasing depth each time until solution found
            currentDepth ++;
            returnValue = DepthIterator(start, target, currentDepth, queue);
        }
        System.out.println(currentDepth);
        System.out.println(getPath(returnValue));
    }

    //Currently does not work; need to implement priority queue
    public static void UniformCost(City start, City target) {
        LinkedListQueue<CityTree> queue = new LinkedListQueue<>();
        boolean found = false;
        Comparator<CityTree> ctc = new CityTree.totalCostComparator();
        queue.addFirst(new CityTree(start, null, new ArrayList<>(), 0, 0));
        CityTree current = null;
        while (queue.size() != 0) {
            current = queue.removeFirst();
            if (!current.isAncestorOfSelf()) { // Avoid loops
                if (current.city == target) {
                    found = true;
                    break;
                }
                current.generateBranches();
                for (CityTree branch: current.branches) {
                    queue.addSorted(branch, ctc);
                }
            }
        }
        if (found) {
            System.out.println(getPath(current));
            System.out.println(current.pathCost);
        }
        else {
            System.out.println("Not found.");
        }
    }

    private static String getPath(CityTree ct) {
        String rv = "";
        for (CityTree ancestor : ct.ancestors) {
            rv += (ancestor.city.name + " > ");
        }
        rv += ct.city.name;
        return rv;
    }

    public static void main(String[] args) {
        City oradea = new City("Oradea");
        City zerind = new City("Zerind");
        City arad = new City("Arad");
        City timisoara = new City("Timisoara");
        City lugoj = new City("Lugoj");
        City mehadia = new City("Mehadia");
        City dobreta = new City("Dobreta");
        City sibiu = new City("Sibiu");
        City rimnicu_vilcea = new City("Rimnicu Vilcea");
        City craiova = new City("Craiova");
        City fagaras = new City("Fagaras");
        City pitesti = new City("Pitesti");
        City bucharest = new City("Bucharest");
        City giurgiu = new City("Giurgiu");
        City urziceni = new City("Urziceni");
        City neamt = new City("Neamt");
        City iasi = new City("Iasi");
        City vaslui = new City("Vaslui");
        City hirsova = new City("Hirsova");
        City eforie = new City("Eforie");

        oradea.neighbors = new City[]{zerind, sibiu};
        oradea.neighborCosts = new int[]{71, 151};
        zerind.neighbors = new City[]{oradea, arad};
        zerind.neighborCosts = new int[]{71, 75};
        arad.neighbors = new City[]{zerind, sibiu, timisoara};
        arad.neighborCosts = new int[]{75, 140, 118};
        timisoara.neighbors = new City[]{arad, lugoj};
        timisoara.neighborCosts = new int[]{118, 111};
        lugoj.neighbors = new City[]{timisoara, mehadia};
        lugoj.neighborCosts = new int[]{111, 70};
        mehadia.neighbors = new City[]{lugoj, dobreta};
        mehadia.neighborCosts = new int[]{70, 75};
        dobreta.neighbors = new City[]{mehadia, craiova};
        dobreta.neighborCosts = new int[]{75, 120};
        sibiu.neighbors = new City[]{oradea, arad, fagaras, rimnicu_vilcea};
        sibiu.neighborCosts = new int[]{151, 140, 99, 80};
        rimnicu_vilcea.neighbors = new City[]{sibiu, pitesti, craiova};
        rimnicu_vilcea.neighborCosts = new int[]{80, 97, 146};
        craiova.neighbors = new City[]{dobreta, rimnicu_vilcea, pitesti};
        craiova.neighborCosts = new int[]{120, 146, 138};
        fagaras.neighbors = new City[]{sibiu, bucharest};
        fagaras.neighborCosts = new int[]{99, 211};
        pitesti.neighbors = new City[]{rimnicu_vilcea, craiova, bucharest};
        pitesti.neighborCosts = new int[]{97, 138, 101};
        bucharest.neighbors = new City[]{fagaras, pitesti, giurgiu, urziceni};
        bucharest.neighborCosts = new int[]{211, 101, 90, 85};
        giurgiu.neighbors = new City[]{bucharest};
        giurgiu.neighborCosts = new int[]{90};
        urziceni.neighbors = new City[]{bucharest, vaslui, hirsova};
        urziceni.neighborCosts = new int[]{85, 142, 98};
        neamt.neighbors = new City[]{iasi};
        neamt.neighborCosts = new int[]{87};
        iasi.neighbors = new City[]{neamt, vaslui};
        iasi.neighborCosts = new int[]{87, 92};
        vaslui.neighbors = new City[]{iasi, urziceni};
        vaslui.neighborCosts = new int[]{92, 142};
        hirsova.neighbors = new City[]{urziceni, eforie};
        hirsova.neighborCosts = new int[]{98, 86};
        eforie.neighbors = new City[]{hirsova};
        eforie.neighborCosts = new int[]{86};

        City[] allCities = new City[]{oradea, zerind, arad, timisoara, lugoj, mehadia, dobreta, sibiu, rimnicu_vilcea,
                craiova, fagaras, pitesti, bucharest, giurgiu, urziceni, neamt, iasi, vaslui, hirsova, eforie};
        City start = null;
        City target = null;
        for (City city : allCities) {
            if (city.name.equals(args[0])) {
                start = city;
            }
            if (city.name.equals(args[1])) {
                target = city;
            }
        }
        if ((start == null) || (target == null)) {
            System.out.println("Error - invalid city names.");
        }
        else {
            UniformCost(start, target);
        }
    }
}
