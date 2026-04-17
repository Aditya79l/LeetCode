class Solution {
    public boolean isAnagram(String s, String t) {
        
      int m = s.length();
		int n = t.length();

		if (m != n)
			return false;

		char a[] = s.toCharArray();
		char b[] = t.toCharArray();

		Arrays.sort(a);
		Arrays.sort(b);


		return Arrays.equals(a, b);
    }
}