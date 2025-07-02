def sum_of_first_N(n: int) -> int:
#    result = 0
#    for i in range(1,n+1):
#        result = result + i
#    return result

    result = (n*(1+n))//2
    return result


print(sum_of_first_N(7))