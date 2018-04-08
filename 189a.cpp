#include <bits/stdc++.h>

int numCuts(const int &newLength, int cuts[], const int &a, const int &b, const int &c){
    if(newLength == 0)
        return 0;
    else if(newLength < 0)
        return std::numeric_limits<int>::min();
    else if(cuts[newLength] != -1)
        return cuts[newLength];
    else
        return cuts[newLength] = std::max(1 + numCuts(newLength - a,cuts,a,b,c),
                                 std::max(1 + numCuts(newLength - b,cuts,a,b,c),
                                          1 + numCuts(newLength - c,cuts,a,b,c)));
}

int main(){
    int length;
    int a;
    int b;
    int c;
    std::cin  >> length >> a >> b >> c;
    int cuts[length+1];
    memset(cuts, -1, sizeof(cuts));
    std::cout << numCuts(length,cuts,a,b,c) << std::endl;
}
