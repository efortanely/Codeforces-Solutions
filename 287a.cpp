#include <bits/stdc++.h>

int main(){
    int size = 4;
    char input[size][size];
    for(int i = 0; i < size; i++){
        for(int j = 0; j < size; j++){
            std::cin >> input[i][j];
        }
    }

    for(int i = 0; i < size-1; i++){
        for(int j = 0; j < size-1; j++){
            int sym = 0;
            for(int a = i; a < i+2; a++){
                for(int b = j; b < j+2; b++){
                    sym += input[a][b] == '#';
                }
            }
            //one of two cases is a perfect square (0/4)
            //or has just 1 piece left (1/3)
            if(sym != 2){
                std::cout << "YES" << std::endl;
                return 0;
            }
        }
    }

    std::cout << "NO" << std::endl;
}
