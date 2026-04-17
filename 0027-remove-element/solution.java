class Solution {
    public int removeElement(int[] nums, int val) {
    
        int cnt = 0;
        int n = nums.length;
        for(int i=0;i<n;i++){
            if(nums[i]!=val)
            {
                nums[cnt] = nums[i];
                cnt++;
            }
        }

        return cnt;

    }
}