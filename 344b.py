input = [int(i) for i in input().split()]
sum = sum(input)
if sum % 2 != 0:
    print("Impossible")
else:
    sum //= 2
    a = sum-input[2]
    b = sum-input[0]
    c = sum-input[1]

    if a >= 0 and b >= 0 and c >= 0:
        print(a,b,c)
    else:
        print("Impossible")
