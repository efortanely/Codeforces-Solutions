#include <bits/stdc++.h>

int main(){
    long double w, h, s;
    std::cin >> w >> h >> s;
    std::cout << (long long) (std::ceil(w/s) * std::ceil(h/s)) << std::endl;
}
