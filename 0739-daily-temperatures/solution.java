class Solution {
    public int[] dailyTemperatures(int[] temperatures) {
        
        int n = temperatures.length;
    	int[] res = new int[n];
    	
    	Stack<Integer> st = new Stack<Integer>();
    	
    	for(int i = 0; i < n; i++) {
    		
    		while(!st.isEmpty() && temperatures[i] > temperatures[st.peek()]) {
    			
    			int pre = st.pop();
    			res[pre] = i - pre;
    		}
    		
    		st.push(i);
    	}

        return res;
        
    }
}