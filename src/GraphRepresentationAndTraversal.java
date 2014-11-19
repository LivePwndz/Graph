
/*
 * 
 * Author: LivePwndz
 * email: pwndz172@gmail.com
 * License: GPL (Open source)
 * 
 * 
 */

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Queue;
import java.util.Stack;


public class GraphRepresentationAndTraversal {

	public static void main(String[] args) {
		Graph g  = new Graph(new int[]{1,3,2,4,5,7,6,8,9,10,11});
		g.addEges(1, new int[]{3,4,7});
		g.addEges(3, new int[]{2,6});
		g.addEges(4, new int[]{5,8});
		g.addEges(5, new int[]{9});
		g.addEges(6, new int[]{10});
		
		System.out.println("Total Edges: "+g.getTotalEdges());
		g.bfs(1);
		g.dfs(1);

	}
	
	
	//Representation Using Adjacency Matrix
	static class Graph{
		ArrayList<Integer> vertices;
		boolean[][] matrix;
		int len;
		Graph(int[] vertices){
			
			addVertices(vertices);
			this.matrix = new boolean[vertices.length][vertices.length];
			len = this.matrix.length;
			
		}
		
		public void addVertices(int[] vertices){
			this.vertices = new ArrayList<>(vertices.length);
			for(int vertex: vertices)
				this.vertices.add(vertex);
		}
		
		public void addEges(int parent, int[] children){
			for(int c: children)
				matrix[vertices.indexOf(parent)][vertices.indexOf(c)] = true;
		}
		
		public int getTotalEdges(){
			int count = 0;
			for(int r = 0; r < len; r++){
				for(int c = 0; c < len; c++){
					if(matrix[r][c])count++;
				}
			}
			
			return count;
		}
		
		public boolean isConnected(int parent, int c){
			return matrix[vertices.indexOf(parent)][vertices.indexOf(c)];
		}
		
		public int getUnVisitedConnectedChild(int parent, boolean[] isVisited){
			for(int c: vertices)
				if(isConnected(parent, c))
					if(!isVisited[vertices.indexOf(c)])
						return c;
			return -1;
		}
		
		
		
		//Depth First Search Algorithm
		public void dfs(int root){
			if(vertices.indexOf(root) == -1){
				System.out.println(root+" does not exist in graph.");
				return;
			}
			boolean[] isVisited = new boolean[vertices.size()];
			ArrayList<Integer> dfsOrder = new ArrayList<>();
			Stack<Integer> stack = new Stack<Integer>();
			int parent = root;
			dfsOrder.add(parent);
			isVisited[vertices.indexOf(parent)] = true;
			stack.push(parent);
			
			while(!stack.isEmpty()){
				
				parent = stack.peek();
				int c = getUnVisitedConnectedChild(parent, isVisited);
				
				if(c != -1){
					if(!isVisited[vertices.indexOf(c)]){
						dfsOrder.add(c);
						isVisited[vertices.indexOf(c)] = true;
						stack.push(c);
					}
				}else{
						stack.pop();
				}
			}
			
			System.out.print("DFS: ");
			for(int v: dfsOrder){
				System.out.print(v+"-->");
			}
			System.out.print("end");
			System.out.println();
		}
		
		
		
		//Breadth First Search Algorithm
		public void bfs(int root){
			if(vertices.indexOf(root) == -1){
				System.out.println(root+" does not exist in graph.");
				return;
			}
			
			boolean[] isVisited = new boolean[vertices.size()];
			ArrayList<Integer> dfsOrder = new ArrayList<>();
			Queue<Integer> stack = new ArrayDeque<Integer>();
			int parent = root;
			dfsOrder.add(parent);
			
			isVisited[vertices.indexOf(parent)] = true;
			stack.add(parent);
			
			while(!stack.isEmpty()){
				parent = stack.peek();
				int c = getUnVisitedConnectedChild(parent, isVisited);
				
				if(c != -1){
					if(!isVisited[vertices.indexOf(c)]){
						dfsOrder.add(c);
						isVisited[vertices.indexOf(c)] = true;
						stack.add(c);
					}
				}else{
						stack.remove();
				}
			}
			
			System.out.print("BFS: ");
			for(int v: dfsOrder){
				System.out.print(v+"-->");
			}
			System.out.print("end");
			System.out.println();
		}
			
		
	}

	
}
