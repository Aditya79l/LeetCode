class Solution {
    public void reverseString(char[] s) {
        
        int m = 0;
    	int n = s.length-1;
    		
    	while(m<n) {
    		
    		char a = s[n];
    		s[n] = s[m];
    		s[m] = a;
            
    		m++;
    		n--;
    		
    	}
    }
}