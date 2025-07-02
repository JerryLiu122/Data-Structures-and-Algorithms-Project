# Solution 1
def remove_element(nums, val):
        result = []
        index = 0
        for number in nums:
            if number == val:
                continue
            else:
                result.append(number)
                index += 1
        nums[:] = result
        return index

# Solution 2
def remove_element2(nums, val):
    i = 0
    for j  in range(len(nums)):
        if nums[j] != val:
            nums[i] = nums[j]
            i += 1
    return i


nums = [3,2,2,3]
val = 2

print(remove_element2(nums, val))
print(nums[:2])