#include <iostream>

//@author Rose

//O(n)
int main(){
    int days;
    int totalHours;
    int sumOfDifferences = 0;
    
    std::cin >> days;
    std::cin >> totalHours;
    
    int diffForDays[days];
    int minForDays[days];

    for(int i = 0; i < days; i++){
        int min;
        int max;
        std::cin >> min;
        std::cin >> max;
        minForDays[i] = min;
        totalHours -= min;
        diffForDays[i] = max - min;
        sumOfDifferences += diffForDays[i];
    }

    if(0 <= totalHours && totalHours <= sumOfDifferences){
        std::cout << "YES" << std::endl;
        for(int i = 0; i < days; i++){
            int hours = minForDays[i];
            if(totalHours > 0){
                int hoursToAdd = totalHours - diffForDays[i] < 0? totalHours : diffForDays[i];
                hours += hoursToAdd;
                totalHours -= hoursToAdd;
            }
            std::cout << hours << " ";
        }
        std::cout << std::endl;
    }else{
        std::cout << "NO" << std::endl;
    }
}
