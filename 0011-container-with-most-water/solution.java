class Solution {
    public int maxArea(int[] height) {
            	int n = height.length;
    	
    	int l = 0;
    	int r = n-1;
    	int area = 0;
    	
    	while(l<r) {
    		
    		int sum = Math.min(height[l],height[r])*(r-l);
    		area = Math.max(area,sum);
    		
    		if(height[l]<height[r]) {
    			l++;
    			
    		}
    		else
    			r--;
    	}

        return area;
    }
}