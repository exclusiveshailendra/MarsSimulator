package com.ove.commandhandler;

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

import com.ove.simulator.Canvas;
import com.ove.simulator.Explorer;

public class ExplorerHandler {

	private Explorer explorer;
	private Canvas canvas;
	
	static QNode source;
	static boolean visited[][];

	public ExplorerHandler(Explorer explorer, Canvas canvas) {
		this.explorer = explorer;
		this.canvas = canvas;
	}

	/**
     * Run bunch of methods.
     * updateCanvasArray with the code at the coordinates.
     * findshortestpath prints the path of the explorer from place to explorer
     */
	public void start() {
//		Coordinates coordinates = explorer.getCoordinates();
		markVisted(canvas.getCanvasArray());
		canvas.updateCanvasArray(explorer);
		int result = findShortestPath(canvas.getCanvasArray());
		System.out.println("result "+result);
		if(result != -1) {
			canvas.updateCanvasArrayForExplorer();
		}

	}

	public static void printArray(String[][] arr) {

		for (int i = 0; i < arr.length; i++) {

			for (int j = 0; j < arr[0].length; j++) {

				System.out.print(arr[i][j] + " ");

			}

			System.out.println();

		}
	}

	public void markVisted(String arr[][]) {
		
		int N = arr.length;
		int M = arr[0].length;

		 source = new QNode(0, 0, 0, null);

		visited = new boolean[N][M];

		for (int i = 0; i < N; i++) {

			for (int j = 0; j < M; j++) {

				if (arr[i][j] == "b")
					visited[i][j] = true;

				else

					visited[i][j] = false;

				if (arr[i][j] == "p") {

					source.row = i;

					source.col = j;

				}

			}

		}
	}
	
	public int findShortestPath(String arr[][]) {
		printArray(arr);
		int N = arr.length;
		int M = arr[0].length;

		Queue<QNode> q = new LinkedList<QNode>();

		q.add(source);

		visited[source.row][source.col] = true;

		while (!q.isEmpty()) {

			//System.out.println("size of q ---- " + q.size());

			QNode p = q.peek();

			q.remove();

			// Destination found;

			if (arr[p.row][p.col] == "d") {

				p.getStringPath();

				return p.distance;

			}

			// moving up

			if (p.row - 1 >= 0 && visited[p.row - 1][p.col] == false) {

				q.add(new QNode(p.row - 1, p.col, p.distance + 1, p.getPath()));

				visited[p.row - 1][p.col] = true;

				//System.out.println("move up");

			}

			// moving down

			if (p.row + 1 < N && visited[p.row + 1][p.col] == false) {

				q.add(new QNode(p.row + 1, p.col, p.distance + 1, p.getPath()));

				visited[p.row + 1][p.col] = true;

				//System.out.println("move down");

			}

			// moving right

			if (p.col + 1 < M && visited[p.row][p.col + 1] == false) {

				q.add(new QNode(p.row, p.col + 1, p.distance + 1, p.getPath()));

				visited[p.row][p.col + 1] = true;

				//System.out.println("move right");

			}

			// moving left

			if (p.col - 1 >= 0 && visited[p.row][p.col - 1] == false) {

				q.add(new QNode(p.row, p.col - 1, p.distance + 1, p.getPath()));

				visited[p.row][p.col - 1] = true;

				//System.out.println("move left");

			}

			//System.out.println("------------------");

		}

		return -1;

	}

	private static class QNode {

		int row;

		int col;

		int distance;

		List<QNode> path = new LinkedList<QNode>();

		public List<QNode> getPath() {

			return path;

		}

		public void getStringPath() {

			System.out.print("PATH:");
			for (QNode qNode : path) {

				System.out.print("("+qNode.row + "," + qNode.col+")");
				
				

			}
			System.out.println("");
		}

		QNode(int row, int col, int distance, List<QNode> prePath) {

			this.row = row;

			this.col = col;

			this.distance = distance;

			if (prePath == null)

				this.path.add(this);

			else {

				this.path.addAll(prePath);

				this.path.add(this);

			}

		}

	}

}
