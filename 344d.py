input = input()
stack = []

for wire in input:
    if len(stack) == 0 or wire != stack[-1]:
        stack.append(wire)
    else:
        stack.pop()

print("YES" if len(stack) == 0 else "NO")
