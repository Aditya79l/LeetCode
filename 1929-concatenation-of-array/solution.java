class Solution {
    public int[] getConcatenation(int[] nums) {
        
        int n = nums.length;
		int[] a = new int[2 * n];

		for (int i = 0; i < a.length; i++) {

			a[i] = nums[i%n];
		}
		
		return  a; 

    }
}