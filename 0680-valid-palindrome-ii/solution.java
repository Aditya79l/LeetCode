class Solution {
    public boolean validPalindrome(String s) {
        
        int n = s.length();
    	
    	int l = 0;
    	int r = n-1;
        int cnt = 0;
    	
    	while(l<=r) {
    		
    		if(s.charAt(l) != s.charAt(r)) {
                return isPalindrome(s,l+1,r) || isPalindrome(s,l,r-1);
    		}
    		
    		l++;
    		r--;
    	}

        return true; 
    }

    private boolean isPalindrome(String s, int l, int r){
    while(l < r){
        if(s.charAt(l) != s.charAt(r)){
            return false;
        }
        l++;
        r--;
    }
    return true;
    }
}