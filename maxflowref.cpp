#include <bits/stdc++.h>

bool bfs(int N, int** graph, int source, int sink, int previous[N], int maxFlow[N]){
    std::memset(previous,-1,N * sizeof(int));
    std::memset(maxFlow,-1,N * sizeof(int));

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
            int capacity = graph[location][nextLocation];

            if(previous[nextLocation] == -1 && capacity > 0){
                previous[nextLocation] = location;
                maxFlow[nextLocation] = std::min(flow,capacity);
                queue.push(nextLocation);
            }
        }
    }

    return foundSink;
}

int fordFulkerson(int N, int** graph, int source, int sink){
    int flow = 0;
    int maxFlow[N];
    int previous[N];

    while(bfs(N, graph, source, sink, previous, maxFlow)){
        int currentFlow = maxFlow[sink];
        flow += currentFlow;

        int node = sink;
        while(node != source){
            int previousNode = previous[node];
            graph[previousNode][node] -= currentFlow;
            graph[node][previousNode] += currentFlow;
            node = previousNode;
        }
    }
    return flow;
}

int main(){
    int numCases, intersections, segments, safeIntersections, people;
    std::cin >> numCases;
    while(numCases--){
        std::cin >> intersections >> segments >> safeIntersections >> people;
        int* graph[intersections];
        for(int i = 0; i < intersections; i++){
            graph[i] = (int *)malloc(intersections * sizeof(int));
        }

        for(int i = 0; i < segments; i++){
            int start,end,weight;
            std::cin >> start >> end >> weight;
            graph[start-1][end-1] = weight;
        }

        int totalSaved = 0;
        for(int i = 0; i < safeIntersections; i++){
            int safe;
            std::cin >> safe;
            totalSaved += fordFulkerson(segments, graph, 0, safe-1);
        }

        if(people <= totalSaved){
            std::cout << "YES" << std::endl;
        }else{
            std::cout << "NO " << (people - totalSaved) << std::endl;
        }
       
        for(int i = 0; i < intersections; i++){
            free(graph[i]);
        }
    }
}
