import java.util.Stack;

class Solution {
    public String reverseParentheses(String s) {
        Stack<Integer> stack = new Stack<>();
        StringBuilder sb = new StringBuilder();
        
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (c == '(') {
                stack.push(sb.length());
            } else if (c == ')') {
                int start = stack.pop();
                String sub = sb.substring(start);
                sb.delete(start, sb.length());
                sb.append(new StringBuilder(sub).reverse());
            } else {
                sb.append(c);
            }
        }
        
        return sb.toString();
    }
}
