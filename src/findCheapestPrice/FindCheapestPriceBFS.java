package findCheapestPrice;

/**
 * Created with IntelliJ IDEA.
 * User: qiang
 * Date: 2018/6/25
 * To change this template use File | Settings | File Templates.
 */

import org.junit.Test;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * 广度优先遍历
 * 最多转k次机的最便宜航线
 */
public class FindCheapestPriceBFS {

    public int findCheapestPriceBFS(int n, int[][] flights, int src, int dst, int K){

        int[][] graph = new int[n][n];
        for (int[] flight : flights){  //{0,1,100}
            graph[flight[0]][flight[1]] = flight[2];
        }

        int[] costs = new int[n]; //经过若干步之后，从src到达某个城市的票价最低
        Arrays.fill(costs,Integer.MAX_VALUE);

        Set<Integer> set1 = new HashSet<>();
        set1.add(src);
        costs[src] = 0;

        while(!(set1.isEmpty()) && K >= 0){ //剪枝

            Set<Integer> set2 = new HashSet<>();
            for (int s : set1){ //s为出发点，
                for (int i = 0; i < n; ++ i){
                    if(graph[s][i] > 0 && costs[s] + graph[s][i] < costs[i]){
                        costs[i] = costs[s] + graph[s][i]; //更新costs[i]
                        set2.add(i); //将s能直接到到的点加到set中
                    }
                }
            }

            set1 = set2;
            K --;
        }

        return costs[dst] == Integer.MAX_VALUE ? -1 : costs[dst];
    }

    @Test
    public static void main(String[] args) {

        int[][] flights = {{0,1,100},{0,2,500},{1,2,100}};
        FindCheapestPriceBFS cheapestPriceBFS = new FindCheapestPriceBFS();
        int i = cheapestPriceBFS.findCheapestPriceBFS(3, flights, 0, 2, 1);
        System.out.println(i);
    }
}
