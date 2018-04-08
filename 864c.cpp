#include <bits/stdc++.h>

//@author Rose

int main(){
    int end;
    int gasCap;
    int gasStation;
    int journeys;

    std::cin >> end >> gasCap >> gasStation >> journeys;

    int loc = 0;
    int gas = gasCap;
    bool movingRight = true;
    int gasUp = 0;
    while(journeys > 0){
        if(gas < 0){
            std::cout << -1 << std::endl;
            return 0;
        }

        if(loc == gasStation){
            bool refuel;
            
            if(journeys > 1)
                refuel = gas < 2 * (movingRight? (end - gasStation) : gasStation);
            else
                refuel = gas < (movingRight? (end - gasStation) : gasStation);
            
            if(refuel){
                gasUp++;
                gas = gasCap;
            }
        }

        if((loc == end && movingRight) || (loc == 0 && !movingRight)){
            movingRight = !movingRight;
            journeys--;
        }else if(movingRight){
            int mov = (gasStation > loc ? gasStation : end) - loc;
            loc+=mov;
            gas-=mov;
        }else if(!movingRight){
            int mov = loc - (gasStation >= loc ? 0 : gasStation);
            loc-=mov;
            gas-=mov;
        }
    }

    std::cout << gasUp << std::endl;
}
