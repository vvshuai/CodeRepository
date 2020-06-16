# 制作 m 束花所需的最少天数
## 题解
此题直接二分答案判断即可。
## 代码
```java
class Solution {
    public int minDays(int[] bloomDay, int m, int k) {
        if(m*k>bloomDay.length){
            return -1;
        }
        int l = 0, r = 0;
        int length = bloomDay.length;
        for(int x : bloomDay){
            r = Math.max(r, x);
        }
        while(l <= r){
            int mid = (l+r)/2;
            int cur = 0;
            int s = 0;
            for(int i = 0;i < length; i++){
                if(bloomDay[i] < mid){
                    cur++;
                }else{
                    s += (cur/k);
                    cur = 0;
                }
            }
            if(cur > 0){
                s += (cur/k);
            }
            if(s >= m){
                r = mid-1;
            }else{
                l = mid+1;
            }
        }
        return r;
    }
}
```
