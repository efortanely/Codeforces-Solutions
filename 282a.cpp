#include <bits/stdc++.h>

int main(){
    std::string curOp;
    int i = 0;
    int numOps;
    std::cin >> numOps;
    
    while(numOps--){
        std::cin >> curOp;
        bool isPlus = curOp.find("++") != std::string::npos;
        i += isPlus ? 1 : -1;
    }

    std::cout << i << std::endl;
}
