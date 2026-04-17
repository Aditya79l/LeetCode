class Solution {
    public int calPoints(String[] operations) {
        
            	Stack<Integer> st = new Stack<Integer>();
    	
    	for(String a : operations) {
    		
    		if(a.equals("+")) {
    			int top = st.pop();
    			int sum = st.peek() + top;
    			st.push(top);
    			st.push(sum);	
    		}
    		
    		else if (a.equals("D")) {
				int ns = st.peek() * 2;
				st.push(ns);
			}
    		
    		else if(a.equals("C")) {
    			st.pop();
    		}
    		
    		else {
    			st.push(Integer.parseInt(a));
    		}
    		
    	}
    	
    	int sum = 0;
    	
    	for(int a : st) {
    		
    		sum += a;
    	}

        return sum;
        
    }
}