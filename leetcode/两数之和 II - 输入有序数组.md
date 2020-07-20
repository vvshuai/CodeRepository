# 两数之和 II - 输入有序数组
## 题解
由于是有序数组，直接双指针遍历即可。
## 代码
```java
class Solution {
    public int[] twoSum(int[] numbers, int target) {
        int l = 0, r = numbers.length - 1;
        while(l < r){
            if(numbers[l] + numbers[r] > target){
                r--;
            } else if(numbers[l] + numbers[r] < target){
                l++;
            }else{
                return new int[]{l+1, r+1};
            }
        }
        return null;
    }
}
```
