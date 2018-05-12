num = raw_input()
birds = map(int,raw_input().split())
shotsFired = int(raw_input())

for shot in range(shotsFired):
    wire,bird = map(int,raw_input().split())
    wire -= 1
    if wire > 0: 
        birds[wire-1] += bird - 1
    if wire < len(birds) - 1: 
        birds[wire+1] += birds[wire] - bird
    birds[wire] = 0

for bird in birds:
    print bird
