size, k = map(int,input().split())
for r in range(size):
    for c in range(size):
        print(k if r==c else 0,end=" ")
    print()
