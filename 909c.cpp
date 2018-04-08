#include <bits/stdc++.h>
#define MOD 1000000007

int main() {
    int commands;
    std::cin >> commands;
	std::string code;
    for (int i = 0; i < commands; i++){
        std::string statement;
        std::cin >> statement;
        code += statement;
    }

    long dp[commands][commands];
	std::memset(dp,0,sizeof(dp));
    dp[0][0] = 1;

    for (int i = 1; i < commands; i++){
        if (code[i-1] == 'f'){
            for (int j = 1; j <= i; j++)
                dp[i][j] = dp[i-1][j-1];
        }else {
            long perm = 0;
            for (int j = i - 1; j >= 0; j--)
                dp[i][j] = perm = (perm%MOD + dp[i-1][j]%MOD)%MOD;
        }
    }

    long answer = 0;
    for (int j = 0; j < commands; j++)
        answer = (answer%MOD + dp[commands-1][j]%MOD)%MOD;

    std::cout << answer << std::endl;
}
