#include <bits/stdc++.h>

int main(){
    int n;
    int a = 0;
    int i = 0;
    int f = 0;
    char hand;
    std::cin >> n;

    while(n--){
        std::cin >> hand;
        if(hand == 'A')
            a++;
        else if(hand == 'I')
            i++;
        else if(hand == 'F')
            f++;
    }

    int ans;
    if(i == 0)
        ans = a+i;
    else if(i == 1)
        ans = 1;
    else
        ans = 0;
    
    std::cout << ans << std::endl;
}
