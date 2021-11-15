package Graph;

import java.util.HashMap;
import java.util.Map;

public class FindtheTownJudge {

	public static void main(String[] args) {
		/**
		 * 小鎮裡有n個人，分別以1~n表示
		 * 其中僅一人可能是法官，且滿足下列條件
		 * 1. 法官不相信任何人
		 * 2. 其他所有人皆相信法官
		 * 相信的關係為trust，trust[i][0]相信trust[i][1]
		 * 請找出法官是誰，沒有法官則回傳-1
		 */
		System.out.println(findJudge(2, new int[][] {{1,2}})); //2
		System.out.println(findJudge(3, new int[][] {{1,3},{2,3}})); //3
		System.out.println(findJudge(3, new int[][] {{1,3},{2,3},{3,1}})); //-1
		System.out.println(findJudge(3, new int[][] {{1,2},{2,3}})); //-1
		System.out.println(findJudge(4, new int[][] {{1,3},{1,4},{2,3},{2,4},{4,3}})); //3
	}

    public static int findJudge(int n, int[][] trust) {
        /**
         * 從例4知道，1相信2而2相信3也無法成立3是法官，所以一定要直接connect
         * 所以計算每個節點的進出就好
         * 身為法官的節點，出為0，而進為n-1
         */
    	Map<Integer, int[]> degree = new HashMap<>();
    	for (int i = 1; i <= n; i++) {
    		degree.put(i, new int[2]);
    	}
    	for (int i = 0; i < trust.length; i ++) {
    		
    		int out = trust[i][0];
    		int in = trust[i][1];
    		
    		degree.get(out)[0]++;
    		degree.get(in)[1]++;
    	}
    	
    	for (int person : degree.keySet()) {
    		
    		if (degree.get(person)[0] == 0 && degree.get(person)[1] == n-1) {
				return person;
			}
    	}
    	
    	return -1;
    }
}
