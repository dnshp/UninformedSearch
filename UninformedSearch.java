import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

/**
 * Created by dineshparimi on 1/31/17.
 */
public class UninformedSearch {

    public static int[] sortedIndices(int[] arr) {
        int[] arrCopy = new int[arr.length];
        System.arraycopy(arr, 0, arrCopy, 0, arr.length);
        Arrays.sort(arr);
        int[] indices = new int[arr.length];
        for (int i = 0; i < arr.length; i ++) {
            for (int j = 0; j < arr.length; j ++) {
                if (arrCopy[j] == arr[i]) {
                    indices[i] = j;
                }
            }
        }
        return indices;
    }

    public static void BreadthFirst(City start, City target) {
        LinkedListQueue<CityTree> queue = new LinkedListQueue<>();
        boolean found = false;
        queue.addFirst(new CityTree(start, null, new ArrayList<CityTree>()));
        CityTree current = null;
        while (queue.size() != 0) {
            current = queue.removeFirst();
            if (current.city == target) {
                found = true;
                break;
            }
            current.generateBranches();
            for (CityTree branch : current.branches) {
                queue.addLast(branch);
            }
        }
        if (found) {
            for (CityTree ancestor : current.ancestors) {
                System.out.print(ancestor.city.name + " > ");
            }
            System.out.println(current.city.name);
        } else {
            System.out.println("Not found.");
        }
    }

    public static void DepthFirst(City start, City target) {
        LinkedListQueue<CityTree> queue = new LinkedListQueue<>();
        queue.addFirst(new CityTree(start, null, new ArrayList<CityTree>()));
        CityTree current = null;
        while (queue.size() != 0) {
            current = queue.removeFirst();
            if (!current.isAncestorOfSelf()) {
                if (current.city == target) {
                    break;
                }
                current.generateBranches();
                for (CityTree branch : current.branches) {
                    queue.addFirst(branch);
                }
            }
        }
        for (CityTree ancestor : current.ancestors) {
            System.out.print(ancestor.city.name + " > ");
        }
        System.out.println(current.city.name);
    }

    //Currently does not work; need to implement priority queue
    public static void UniformCost(City start, City target) {
        LinkedListQueue<CityTree> queue = new LinkedListQueue<>();
        boolean found = false;
        ArrayList<City> seen = new ArrayList<>();
        queue.addFirst(new CityTree(start, null, new ArrayList<CityTree>()));
        CityTree current = null;
        while (queue.size() != 0) {
            current = queue.removeFirst();
            if (!current.isAncestorOfSelf()) {
                seen.add(current.city);
                if (current.city == target) {
                    found = true;
                }
                current.generateBranches();
                int[] indices = sortedIndices(current.branchCosts);
                for (int n = indices.length - 1; n >= 0; n --) {
                    queue.addFirst(current.branches[indices[n]]);
                }
            }
            if (found) {
                found = false;
                for (CityTree ancestor : current.ancestors) {
                    System.out.print(ancestor.city.name + " > ");
                }
                System.out.println(current.city.name);
            }
        }
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

        BreadthFirst(arad, bucharest);
    }
}
