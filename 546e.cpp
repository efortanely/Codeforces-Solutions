#include <bits/stdc++.h>

bool bfs(int N, int** cap, int source, int sink, int* previous, int* maxFlow){
    std::memset(maxFlow,-1,N*sizeof(int));
    std::memset(previous,-1,N*sizeof(int));
    
    std::queue<int> queue;
    queue.push(source);
    
    maxFlow[source] = INT_MAX;
    bool foundSink = false;

    while(!queue.empty()){
        int location = queue.front();
        queue.pop();
        int flow = maxFlow[location];

        foundSink |= (location == sink);

        for(int nextLocation = 0; nextLocation < N; nextLocation++){
            int capacity = cap[location][nextLocation];
            bool unvisited = previous[nextLocation] == -1;
            bool canFlow = capacity > 0;

            if(unvisited && canFlow){
                previous[nextLocation] = location;
                //set the flow to the next location based on the
                //bottleneck-- minimum of flow going in or out
                maxFlow[nextLocation] = std::min(flow,capacity);
                queue.push(nextLocation);
            }
        }
    }

    return foundSink;
}

int edmondsKarp(int N, int** cap, int** endFlow, int source, int sink){
    int flow = 0;
    int maxFlow[N];
    int previous[N];

    while(bfs(N, cap, source, sink, previous, maxFlow)){
        int currentFlow = maxFlow[sink];
        flow += currentFlow;

        //update capacities and flow after each round of bfs
        //the capacities are updated in the inverse direction,
        //but the flow is update in the actual direction of flow
        int node = sink;
        while(node != source){
            int previousNode = previous[node];
            
            cap[previousNode][node] -= currentFlow;
            cap[node][previousNode] += currentFlow;
            
            endFlow[previousNode][node] += currentFlow;
            endFlow[node][previousNode] -= currentFlow;
            
            node = previousNode;
        }
    }
    return flow;
}

//each city maintains two nodes, one representing the number 
//of soldiers for its starting state, and one for its ending 
//state. the source goes into each starting state city, 
//and each ending state city goes into the sink. you create 
//edges of infinite weight between each starting and ending 
//city if a path exists, and similarly for starting and 
//ending nodes for the same city

//the linear transformation is possible if the total amount 
//of flow is equal to the total amount of soldiers, meaning 
//a legal transformation exists from the starting distribution 
//of soldiers to the ending distribution

int main(){
    int cities, roads;
    std::cin >> cities >> roads;
    //generate a graph maintaining two copies of each city node 
    //(representing starting state A and ending state B), and a 
    //source and a sink
    int N = 2 * (cities+1);
    int* cap[N];
    int* endFlow[N];
    int src = 2*cities;
    int sink = 2*cities + 1;
    int beg = 0;
    int end = 0;
    for(int i = 0; i < N; i++){
        cap[i] = (int *)malloc(N*sizeof(int));
        endFlow[i] = (int *)malloc(N*sizeof(int));
        std::memset(cap[i],0,N*sizeof(int));
        std::memset(endFlow[i],0,N*sizeof(int));
    }

    //create an edge from the source to each city, with capacity
    //of the number of soldiers in state A (the amount of outgoing
    //soldiers during the move stage)
    //the first node for each city is stored in [0,cities-1]
    for(int i = 0; i < cities; i++){
        std::cin >> cap[src][i];
        beg += cap[src][i];
    }

    //create an edge from each city to the sink, with capacity
    //of the number of soldiers in state B (the desired amount
    //of soldiers required to be at each city after move stage)
    //the second node for each city is stored in [cities,2*cities-1]
    for(int i = 0; i < cities; i++){
        std::cin >> cap[cities+i][sink];
        end += cap[cities+i][sink];
    }

    //create a path between initial and ending states A and B for cities
    for(int i = 0; i < cities; i++){
        cap[i][cities+i] = INT_MAX;
    }

    //if a path exists between two cities, allow for infinite 
    //flow between their starting state A and ending state B
    for(int i = 0; i < roads; i++){
        int a, b; 
        std::cin >> a >> b;
        a--;
        b--;
        cap[a][cities+b] = INT_MAX;
        cap[b][cities+a] = INT_MAX;
    }

    std::string ans;

    //total number of soldiers must be conserved
    if(beg != end){
        ans = "NO\n";
    }else{
        int flow = edmondsKarp(N,cap,endFlow,src,sink);

        //a valid solution exists if there exists a max flow
        //from the starting state to the ending state equal
        //to the total number of soldiers we have
        //beg and end both contain the total # of soldiers
        if(flow != beg){
            ans = "NO\n";
        }else{
            //print flow adjacency matrix
            ans += "YES\n";
            for(int i = 0; i < cities; i++){
                for(int j = 0; j < cities; j++){
                    std::ostringstream os;
                    os << endFlow[i][cities+j] << " ";
                    ans += os.str();
                }
                ans += "\n";
            }
        }
    }

    std::cout << ans;

    for(int i = 0; i < N; i++){
        free(cap[i]);
        free(endFlow[i]);
    }
} //hehe
