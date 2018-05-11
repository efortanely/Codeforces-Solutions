input = raw_input().split()
num = 0
for i in range(int(input[0])):
    pair = raw_input().split()
    num += int(pair[1]) - int(pair[0]) + 1

inc = 0
while num % int(input[1]) != 0:
    num+=1
    inc+=1

print(inc)
