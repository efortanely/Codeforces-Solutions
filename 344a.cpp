#include <bits/stdc++.h>

int main(){
    int magnets, cur;
    int groups = 0, prev = 0;
    scanf("%d",&magnets);
    while(magnets--){
        scanf("%d",&cur);
        if(cur != prev){
            groups++;
            prev = cur;
        }
    }
    printf("%d\n",groups);
}
