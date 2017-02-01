/**
 * Created by dineshparimi on 1/31/17.
 */
public class UninformedSearch {
    public static void BreadthFirst(City start, City target) {
        LinkedListQueue<CityTree> queue = new LinkedListQueue<>();
        boolean found = false;
        queue.addFirst(new CityTree(start, null, null));
        CityTree current;
        while (true) {
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
        while (current.parent != null) {
            System.out.print(current.city.name + " < ");
            current = current.parent;
        }
        System.out.println(current.city.name);
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
        zerind.neighbors = new City[]{oradea, arad};
        arad.neighbors = new City[]{zerind, sibiu, timisoara};
        timisoara.neighbors = new City[]{arad, lugoj};
        lugoj.neighbors = new City[]{timisoara, mehadia};
        mehadia.neighbors = new City[]{lugoj, dobreta};
        dobreta.neighbors = new City[]{mehadia, craiova};
        sibiu.neighbors = new City[]{oradea, arad, fagaras, rimnicu_vilcea};
        rimnicu_vilcea.neighbors = new City[]{sibiu, pitesti, craiova};
        craiova.neighbors = new City[]{dobreta, rimnicu_vilcea, pitesti};
        fagaras.neighbors = new City[]{sibiu, bucharest};
        pitesti.neighbors = new City[]{rimnicu_vilcea, craiova, bucharest};
        bucharest.neighbors = new City[]{fagaras, pitesti, giurgiu, urziceni};
        giurgiu.neighbors = new City[]{bucharest};
        urziceni.neighbors = new City[]{bucharest, vaslui, hirsova};
        neamt.neighbors = new City[]{iasi};
        iasi.neighbors = new City[]{neamt, vaslui};
        vaslui.neighbors = new City[]{iasi, urziceni};
        hirsova.neighbors = new City[]{urziceni, eforie};
        eforie.neighbors = new City[]{hirsova};

        BreadthFirst(arad, bucharest);
    }
}
