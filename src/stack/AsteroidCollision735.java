package stack;

import java.util.LinkedList;
import java.util.Stack;

public class AsteroidCollision735 {
	public int[] asteroidCollision(int[] asteroids) {
		Stack<Integer> stack = new Stack<Integer>();// 存放的是向右跑的
		for (int i = 0; i < asteroids.length; i++) {
			int value = asteroids[i];
			if (value > 0) {
				stack.push(value);
			} else {// 碰到了向左跑的
				stack.push(value);
				if (stack.size() > 1) {
					int left = stack.pop();
					boolean pushLeft = true;
					while(!stack.isEmpty() && stack.peek()>0){
						int right = stack.pop();
						if (right == -left) {
							pushLeft = false;
							break;
						} else if (right > -left) {// 向右跑的大，保留正数
							stack.push(right);
							pushLeft = false;
							break;
						}
					}
					if(pushLeft){
						stack.push(left);
					}
				}
			}
		}
		int[] r = new int[stack.size()];
		for(int i = 0 ; i<r.length;i++){
			r[i] = stack.get(i);
		}
		return r;
    }
	
	/**
	 * 代码更加简洁，能够将情况合并起来
	 * @param asteroids
	 * @return
	 */
	public int[] asteroidCollisionV3(int[] asteroids) {
		Stack<Integer> stack = new Stack<Integer>();
		for (int i = 0; i < asteroids.length; i++) {
			if(asteroids[i] > 0 || stack.isEmpty() || stack.peek() < 0){
				stack.add(asteroids[i]);
			}else if(stack.peek() <= -asteroids[i]){//正数的绝对值<=负数的绝对值
				if(stack.pop() < -asteroids[i]){
					i--;
				}
			}
		}
		int[] r = new int[stack.size()];
		for(int i = 0 ; i<r.length;i++){
			r[i] = stack.get(i);
		}
		return r;
	}
	
	public static void main(String[] args) {
		int[] asteroids= new int[]{-2,1,1,-1};
		int[] r = new AsteroidCollision735().asteroidCollisionV3(asteroids);
		for (int v : r) {
			System.out.print(v + "\t");
		}
	}
}
