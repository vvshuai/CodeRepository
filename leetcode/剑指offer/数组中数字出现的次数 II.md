# 数组中数字出现的次数 II
## 题解
此题要求找出只出现一次的数，已知其他数字可以出现3次，所以我们可以使用位数组进行运算。
## 代码
```java
class Solution {
    public static int singleNumber(int[] nums) {
        int[] bitSet = new int[32];
        for(int i = 0;i < nums.length; i++){
            for(int j = 0;j < 32; j++){
                if((nums[i]&1)==1){
                    bitSet[j]++;
                }
                nums[i]>>=1;
            }
        }
        int ans = 0;
        for(int i = 31;i >= 0; i--){
            ans += bitSet[i]%3;
            ans <<= 1;
        }
        return ans;
    }

    public static void main(String[] args) {
        singleNumber(new int[]{3,3,3,4});
    }
}
```
