class Solution {
    public int minimumDifference(int[] nums, int k) {

       if (k == 1) return 0;
        Arrays.sort(nums);
        int minD = Integer.MAX_VALUE;

        for(int i=0;i <= nums.length-k;i++){

            int curr = nums[i + k -1] - nums[i];
            minD = Math.min(minD, curr);

        }

        return minD;

    }
}