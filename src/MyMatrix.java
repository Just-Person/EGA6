import java.util.HashMap;
import java.util.Random;

public class MyMatrix {
    private HashMap<Integer,Integer> candidates = new HashMap<>();
    private HashMap<Integer,Integer> solution = new HashMap<>();
    private int first = 0, size = 0, type = 0, maxlentgh = 10;
    private int sum = 0;
    private int[][] matrix;
    //if type = 0 - matrix [i][j] != matrix[i][j], another - matrix[i][j] ==matrix[i][j]
    MyMatrix (int first, int size,int type)
    {
        this.size = size;
        this.first = first;
        this.type = type;
        this.matrix = new int[size][size];
        Random rnd = new Random();
        if (this.type == 0) {
            for (int i = 0; i < this.size; i++)
                for (int j = 0; j<this.size; j++)
                    matrix[i][j] = Math.abs(rnd.nextInt()) % maxlentgh + 1;
        }
        else {
            for (int i = 0; i < this.size; i++)
                for (int j = i; j < this.size; j++) {
                    matrix[i][j] = Math.abs(rnd.nextInt()) % maxlentgh + 1;
                    matrix[j][i] = matrix[i][j];
                }
        }
        for (int i = 0; i<this.size; i++)
        {
            matrix[i][i] = 0;
            candidates.put(i,i);
        }
        candidates.remove(first);
        solution.put(0,first);
    }
    public void printsolution()
    {
        if (solution.size()<this.size) System.out.println("Текущий обход: ");
        for (int i = 0; i < solution.size();i++) {
            if (i != solution.size() - 1)
                System.out.print(solution.get(i) + "---");
            else System.out.print(solution.get(i));
        }
        System.out.println();

    }
    public void printend()
    {
        System.out.println("Итоговый цикл:");
        printsolution();
        sum +=matrix[solution.get(solution.size()-1)][solution.get(0)];
        System.out.println("Итоговая длина цикла: " + sum);
    }
    public void printmatrix()
    {
        for (int i = 0; i<this.size; i++) {
            for (int j = 0; j < this.size; j++)
                System.out.print(matrix[i][j] + " ");
            System.out.println();
        }
    }
    public HashMap<Integer, Integer> getCandidates() {
        return candidates;
    }


    public void searchmin(int index)
    {
        int min = this.maxlentgh*2;
        int minindex = 0;
        for (int i = 0; i<this.size; i++)
        {
            if (candidates.get(i)!=null)
            {
                if (matrix[solution.get(index)][i]<min
                        && i!=solution.get(index))
                {
                    min = matrix[solution.get(index)][i];
                    minindex = i;
                }
            }
        }
        candidates.remove(minindex);
        solution.put(index+1,minindex);
        this.sum +=min;
        System.out.println("Выбранный город - " + minindex);
        System.out.println("Расстояние от выбранного до предыдущего - "+ min);
        System.out.println("Полученная длина текущего обхода - " + this.sum);
    }
    public void setCandidates(HashMap<Integer, Integer> candidates) {
        this.candidates = candidates;
    }

    public HashMap<Integer, Integer> getSolution() {
        return solution;
    }

    public void setSolution(HashMap<Integer, Integer> solution) {
        this.solution = solution;
    }

    public int getSum() {
        return sum;
    }

    public void setSum(int sum) {
        this.sum = sum;
    }

}
