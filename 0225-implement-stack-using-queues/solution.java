class MyStack {

Queue<Integer> q;

    public MyStack() {
        // ✍️ initialize queues
    	
    	q = new LinkedList<Integer>();
    }
    
    public void push(int x) {
        // ✍️ push element
    	
    	q.add(x);
    	
    	  for(int i = 0; i < q.size() - 1; i++){
              q.add(q.remove());
          }
    }
    
    public int pop() {
        // ✍️ remove top element
        return q.remove();
    }
    
    public int top() {
        // ✍️ return top element
        return q.peek();
    }
    
    public boolean empty() {
        // ✍️ check if empty
        return q.isEmpty();
        
    }
}

/**
 * Your MyStack object will be instantiated and called as such:
 * MyStack obj = new MyStack();
 * obj.push(x);
 * int param_2 = obj.pop();
 * int param_3 = obj.top();
 * boolean param_4 = obj.empty();
 */