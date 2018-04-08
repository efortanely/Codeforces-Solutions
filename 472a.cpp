#include <bits/stdc++.h>

bool isPrime(int num){
    for(int i = 2; i * i <= num; i++){
        if(num % i == 0)
            return false;
    }
    return true;
}

bool isComposite(int num){
    return !isPrime(num);
}

int main(){
    int n;
    std::cin >> n;
    for(int i = n/2; i >= 0; i--){
        if(isComposite(i) && isComposite(n-i)){
            std::cout << i << " " << (n - i) << std::endl;
            break;
        }
    }
}
