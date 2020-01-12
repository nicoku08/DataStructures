package friends;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;

import structures.Queue;
import structures.Stack;

public class Friends {

	/**
	 * Finds the shortest chain of people from p1 to p2.
	 * Chain is returned as a sequence of names starting with p1,
	 * and ending with p2. Each pair (n1,n2) of consecutive names in
	 * the returned chain is an edge in the graph.
	 * 
	 * @param g Graph for which shortest chain is to be found.
	 * @param p1 Person with whom the chain originates
	 * @param p2 Person at whom the chain terminates
	 * @return The shortest chain from p1 to p2. Null if there is no
	 *         path from p1 to p2
	 */
	public static ArrayList<String> shortestChain(Graph g, String p1, String p2) {
		
		/** COMPLETE THIS METHOD **/
		boolean[] visited = new boolean[g.members.length];
		int index = g.map.get(p1);
		
		Queue<Person> people = new Queue<>();
		people.enqueue(g.members[index]);
		
		
		Queue<ArrayList<String>> paths = new Queue<>();
		//Initialize and enqueue first list
		ArrayList<String> inList = new ArrayList<>();
		inList.add(g.members[index].name);
		paths.enqueue(inList);
		
		while(!people.isEmpty()) {
			Person person = people.dequeue();
			int numPerson = g.map.get(person.name);
			visited[numPerson] = true; //mark the person as visited
			
			
			ArrayList<String> list = paths.dequeue();
			Friend temp = g.members[numPerson].first;
			while(temp != null) {
				if(!visited[temp.fnum]) {
					
					ArrayList<String> res = new ArrayList<>(list);
					String name = g.members[temp.fnum].name;
					res.add(name);
					if(name.equals(p2)) return res; // returns result list
					
					people.enqueue(g.members[temp.fnum]);
					paths.enqueue(res);
				}

				temp = temp.next;
			}
		}
		
		return null;				
	
	}
	
	/**
	 * Finds all cliques of students in a given school.
	 * 
	 * Returns an array list of array lists - each constituent array list contains
	 * the names of all students in a clique.
	 * 
	 * @param g Graph for which cliques are to be found.
	 * @param school Name of school
	 * @return Array list of clique array lists. Null if there is no student in the
	 *         given school
	 */
	public static ArrayList<ArrayList<String>> cliques(Graph g, String school) {
		
		/** COMPLETE THIS METHOD **/
		int len = g.members.length;
		boolean[] visited = new boolean[len];
		ArrayList<ArrayList<String>> resCliques = new ArrayList<>();
		
		for(int i = 0; i < len; i++) {
			Person p = g.members[i];
			if(visited[i] || !p.student) 
				continue;
			
			ArrayList<String> otherClique = new ArrayList<>();
			cliqueDFS(g, visited, otherClique, school, i);
			
			//Discard it if the clique is empty
			if(otherClique != null && otherClique.size() > 0)
				resCliques.add(otherClique);
		}
		
		return resCliques;
		


	}
	
	/**
	 * Finds and returns all connectors in the graph.
	 * 
	 * @param g Graph for which connectors needs to be found.
	 * @return Names of all connectors. Null if there are no connectors.
	 */
	public static ArrayList<String> connectors(Graph g) {
		
		/** COMPLETE THIS METHOD **/
		int len = g.members.length;
		boolean[] visited = new boolean[len];
		ArrayList<String> totalConnectors = new ArrayList<>();
		HashMap<String, Integer> dfsnums = new HashMap<>();
		HashMap<String, Integer> backnums = new HashMap<>();
		HashSet<String> backedUp = new HashSet<>();
		
		for(int i = 0; i < len; i++) {
			if(visited[i])continue;
			
			connectorDFS(g, visited, totalConnectors, new int[] {0,0}, i,
					true, dfsnums, backnums, backedUp);
		}
		
		
		// FOLLOWING LINE IS A PLACEHOLDER TO MAKE COMPILER HAPPY
		// CHANGE AS REQUIRED FOR YOUR IMPLEMENTATION
		return totalConnectors;
			
	}
	
	private static void cliqueDFS(Graph g, boolean[] visited, ArrayList<String> cliqueMembers, String school, int index) {
		
		Person person = g.members[index];
		
		if(!visited[index] && person.student && person.school.equals(school))
			cliqueMembers.add(person.name);
		
		visited[g.map.get(person.name)] = true;

		Friend temp = g.members[index].first;
		while(temp != null) {
			int num = temp.fnum;
			Person friendPerson = g.members[num];
			
			if(visited[num] == false && friendPerson.student
					&& friendPerson.school.equals(school)) {
				
				cliqueDFS(g, visited, cliqueMembers, school, num);
			}
			
			temp = temp.next;
		}
		
	}
	
	private static void connectorDFS(Graph g, boolean[] visited, 
			ArrayList<String> connectors, int[] nums, int index, boolean startingPoint,
			HashMap<String, Integer> dfsnums, HashMap<String, Integer> backnums,
			HashSet<String> backedUp) {
		
		Person person = g.members[index];		
		visited[g.map.get(person.name)] = true;
		
		
		dfsnums.put(person.name, nums[0]);
		backnums.put(person.name, nums[1]);

		Friend temp = g.members[index].first;
		while(temp != null) {
			int personIndex = temp.fnum;
			Person friendPerson = g.members[personIndex];
			
			if(!visited[personIndex]) {
				
				nums[0]++;
				nums[1]++;
				
				connectorDFS(g, visited, connectors, nums, personIndex,
						false, dfsnums, backnums, backedUp);
				
				if(dfsnums.get(person.name) > backnums.get(friendPerson.name)) {
					int minBack = Math.min(backnums.get(person.name), 
							backnums.get(friendPerson.name));
					
					backnums.put(person.name, minBack);
				}
				
				if(dfsnums.get(person.name) <= backnums.get(friendPerson.name)) {
					
					if(!startingPoint || backedUp.contains(person.name)) {
						if(!connectors.contains(person.name))
							connectors.add(person.name);
					}
				}
				
				backedUp.add(person.name);
				
			} else {
				
				int minBack = Math.min(backnums.get(person.name), 
						dfsnums.get(friendPerson.name));
				
				backnums.put(person.name, minBack);
			}
			
			temp = temp.next;
		}
	}
	
	
	
	
	
	
	
}
	

