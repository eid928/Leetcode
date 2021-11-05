package Graph;

import java.util.Comparator;
import java.util.PriorityQueue;

public class MinHeapTest {

	public static void main(String[] args) {
		
		int[] distance = {0, 10, 10, 10};
		
		PriorityQueue<Integer> queue = new PriorityQueue<>(new Comparator<Integer>() {

			@Override
			public int compare(Integer o1, Integer o2) {
				
				return distance[o1] - distance[o2];
			}
		});
		
		queue.add(1);
		queue.add(2);
		queue.add(3);
		
		System.out.println(queue);
		
		distance[1] = 2;
		queue.add(1);
		
		System.out.println(queue);
		
		distance[2] = 3;
		queue.add(2);
		
		System.out.println(queue);
		
		System.out.println("Poll: "+queue.poll());
		System.out.println("Poll: "+queue.poll());
		System.out.println("Poll: "+queue.poll());
		System.out.println("Poll: "+queue.poll());
		System.out.println("Poll: "+queue.poll());
		
		PriorityQueue<int[]> queue2 = new PriorityQueue<>(new Comparator<int[]>() {

			@Override
			public int compare(int[] o1, int[] o2) {
				
				return o1[1] - o2[1];
			}
		});
		
		queue2.add(new int[] {1,10});
		queue2.add(new int[] {2,12});
		queue2.add(new int[] {3,Integer.MAX_VALUE});
		
		queue2.add(new int[] {1,2});
		
		queue2.add(new int[] {2,3});
		
		for (int[] pair : queue2) System.out.print(pair[0]+":"+pair[1]+",");
		System.out.println();
		
		System.out.println("Poll: "+queue2.poll()[0]);
		for (int[] pair : queue2) System.out.print(pair[0]+":"+pair[1]+",");
		System.out.println();
		System.out.println("Poll: "+queue2.poll()[0]);
		for (int[] pair : queue2) System.out.print(pair[0]+":"+pair[1]+",");
		System.out.println();
		System.out.println("Poll: "+queue2.poll()[0]);
		for (int[] pair : queue2) System.out.print(pair[0]+":"+pair[1]+",");
		System.out.println();
		System.out.println("Poll: "+queue2.poll()[0]);
		for (int[] pair : queue2) System.out.print(pair[0]+":"+pair[1]+",");
		System.out.println();
		System.out.println("Poll: "+queue2.poll()[0]);
	}
}
