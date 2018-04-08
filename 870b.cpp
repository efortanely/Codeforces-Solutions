#include <bits/stdc++.h>

//@author Rose

int main(){
    int size;
    int subarrays;
    int min = 0;
    int max = 0;
    int first = 0;
    int last = 0;

    std::cin >> size;
    std::cin >> subarrays;

    for(int i = 0; i < size; i++){
        if(i == 0){
            int firstVal;
            std::cin >> firstVal;
            first = min = max = firstVal;
        }else if(i == size - 1)
            std::cin >> last;
        else{
            int next;
            std::cin >> next;
            min = std::min(min,next);
            max = std::max(max,next);
        }
    }

    int maxOfMins = 0;
    if(subarrays == 1)
        maxOfMins = min;
    else if(subarrays == 2)
        maxOfMins = std::max(first,last);
    else
        maxOfMins = max;

    std::cout << maxOfMins << std::endl;
}
