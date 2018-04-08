#include <bits/stdc++.h>
#define M_PIl 3.141592653589793238462643383279502884L
typedef long double ld;

int main(){
    std::vector<ld> angles;
    int man;
    ld x, y;
    std::cin >> man;
    for(int i = 0; i < man; i++){
        std::cin >> x >> y;
        angles.push_back(std::fmod(360 + atan2(y,x) * 180 / M_PIl,360));
    }

    std::sort(angles.begin(),angles.end());
    ld max = LDBL_MIN;
    for(int i = 0; i < man; i++){
        max = std::max(max, angles[i+1] - angles[i]);
    }
    max = std::max(max,angles[0] - angles[man-1] + 360);
    printf("%.10Lf\n",360-max);
}
