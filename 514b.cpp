#include <bits/stdc++.h>
#define ebs 0.0000000001

class RoseVector{
public:
    long double x;
    long double y;

    long double magnitude(){
        return std::sqrt(std::pow(x,2) + std::pow(y,2));
    }

    RoseVector(long double xx, long double yy): x(xx), y(yy){}
    
    RoseVector times(long double scalar){
        return {x * scalar, y * scalar};
    }

    RoseVector normal(){
        long double mag = magnitude();
        return {x/mag, y/mag};
    }

    bool operator<(const RoseVector& v) const{
        if(std::abs(x - v.x) <= ebs && std::abs(y - v.y) <= ebs)
            return 0;
        else if(std::abs(x - v.x) <= ebs)
            return y < v.y;
        else
            return x < v.x;
    }
};

int main(){
    std::set<RoseVector> slopes;
    long double st, x, y, sx, sy;
    std::cin >> st >> x >> y;
    while(st--){
        std::cin >> sx >> sy;
        RoseVector sA{sx-x,sy-y};
        RoseVector a = sA.normal();
        RoseVector b = a.times(-1);
        if(slopes.find(a) == slopes.end() && slopes.find(b) == slopes.end())
            slopes.insert(a);
    }
    std::cout << slopes.size() << std::endl;
}
