class Solution {
    public String simplifyPath(String path) {
        
            	
    	Stack<String> st =  new Stack<String>();
    	
    	String[] s = path.split("/");
    	
    	for(String p : s) {
    		
    		if(p.equals("") || p.equals(".")) {
    			continue;
    		}
    		
    		else if(p.equals("..")){
                if(!st.isEmpty()){
                    st.pop();
                }
            }
    		else {
    			st.push(p);
    		}
    		
    	}

        StringBuilder res = new StringBuilder();

        for(String dir : st){
            res.append("/").append(dir);
        }

        return res.length() == 0 ? "/" : res.toString(); 
    }

    
}