import java.util.*;

public class stacks {

    // next greater
    // ngr -> next greater on right
    private static int[] ngr(int[] arr) {
        Stack<Integer> st = new Stack<>();
        // push index in stack
        int[] res = new int[arr.length];
        for(int i = 0; i < arr.length; i++) {
            while(st.size() > 0 && arr[st.peek()] < arr[i]) {
                res[st.pop()] = i;
            }
            st.push(i);
        }
        while(st.size() > 0) {
            res[st.pop()] = -1;
        }
        return res;
    }
    // ngl -> next greater on left
    private static int[] ngl(int[] arr) {
        Stack<Integer> st = new Stack<>();
        // push index in stack
        int[] res = new int[arr.length];
        for(int i = arr.length - 1; i >= 0; i--) {
            while(st.size() > 0 && arr[st.peek()] < arr[i]) {
                res[st.pop()] = i;
            }
            st.push(i);
        }
        while(st.size() > 0) {
            res[st.pop()] = -1;
        }
        return res;
    }

    // next smaller
    
    
    // nsr -> next smaller on right
    private static int[] nsr(int[] arr) {
        Stack<Integer> st = new Stack<>();
        // push index in stack
        int[] res = new int[arr.length];
        for(int i = 0; i < arr.length; i++) {
            while(st.size() > 0 && arr[st.peek()] > arr[i]) {
                res[st.pop()] = i;
            }
            st.push(i);
        }
        while(st.size() > 0) {
            res[st.pop()] = -1;
        }
        return res;
    }
    
    // nsl -> next smaller on left
    private static int[] nsl(int[] arr) {
        Stack<Integer> st = new Stack<>();
        // push index in stack
        int[] res = new int[arr.length];
        for(int i = arr.length - 1; i >= 0; i--) {
            while(st.size() > 0 && arr[st.peek()] > arr[i]) {
                res[st.pop()] = i;
            }
            st.push(i);
        }
        while(st.size() > 0) {
            res[st.pop()] = -1;
        }
        return res;
    }

    // next greater - I
    public static int[] nextGreaterElement(int[] arr, int[] query) {
        Stack<Integer> st = new Stack<>();
        // push index in stack
        HashMap<Integer, Integer> map = new HashMap<>();
        for(int i = 0; i < arr.length; i++) {
            while(st.size() > 0 && arr[st.peek()] < arr[i]) {
                map.put(arr[st.pop()], arr[i]);
            }
            st.push(i);
        }

        int[] res = new int[query.length];
        for(int i = 0; i < res.length; i++) {
            res[i] = map.getOrDefault(query[i], -1);
        }
        return res;
    }

    // largest area histogram, leetcode 84

    private int[] nsrIndex(int[] arr) {
        Stack<Integer> st = new Stack<>();
        // push index in stack
        int[] res = new int[arr.length];
        for(int i = 0; i < arr.length; i++) {
            while(st.size() > 0 && arr[st.peek()] > arr[i]) {
                res[st.pop()] = i;
            }
            st.push(i);
        }
        while(st.size() > 0) {
            res[st.pop()] = arr.length;
        }
        return res;
    }

    private int[] nslIndex(int[] arr) {
        Stack<Integer> st = new Stack<>();
        // push index in stack
        int[] res = new int[arr.length];
        for(int i = arr.length - 1; i >= 0; i--) {
            while(st.size() > 0 && arr[st.peek()] > arr[i]) {
                res[st.pop()] = i;
            }
            st.push(i);
        }
        while(st.size() > 0) {
            res[st.pop()] = -1;
        }
        return res;
    }

    public int largestRectangleArea(int[] heights) {
        int[] nsr = nsrIndex(heights);
        int[] nsl = nslIndex(heights);
        int ans = 0;
        for(int i = 0; i < heights.length; i++) {
            int area = (nsr[i] - nsl[i] - 1) * heights[i];
            ans = Math.max(ans, area);
        }
        return ans;
    }

    // maximal rectangle, leetcode 85
    public int maximalRectangle(char[][] matrix) {
        int[] ht = new int[matrix[0].length];
        int res = 0;
        for(int i = 0; i < ht.length; i++) {
            
            for(int j = 0; j < matrix[0].length; j++) {
                if(matrix[i][j] == '0') {
                    ht[j] = 0;
                } else {
                    ht[j] += 1;
                }
            }
            res = Math.max(res, largestRectangleArea(ht));
        }
        return res;
    }

    // leetcode 921.
    public int minAddToMakeValid(String s) {
        Stack<Character> st = new Stack<>();
        
        for(int i = 0; i < s.length(); i++) {
            char ch = s.charAt(i);
            if(ch == '(' || st.size() == 0) {
                st.push(ch);
            } else if(st.peek() == '('){
                st.pop();
            } else {
                st.push(ch);
            }
        }
        return st.size();
    }

    // leetcode 1093 https://leetcode.com/problems/minimum-number-of-swaps-to-make-the-string-balanced/
    public int minSwaps(String s) {
        Stack<Character> st = new Stack<>();
        for(int i = 0; i < s.length(); i++) {
            char ch = s.charAt(i);
            if(ch == '[' || st.size() == 0) {
                st.push(ch);
            } else if(st.peek() == '['){
                st.pop();
            } else {
                st.push(ch);
            }
        }
        int pair = st.size() / 2;
        return (int)Math.ceil(pair / 2.0);
    }
    
    // count reversal https://www.geeksforgeeks.org/minimum-number-of-bracket-reversals-needed-to-make-an-expression-balanced/
    private int countRev (String s) {
        Stack<Character> st = new Stack<>();
        for(int i = 0; i < s.length(); i++) {
            char ch = s.charAt(i);
            if(ch == '{' || st.size() == 0) {
                st.push(ch);
            } else if(st.peek() == '}'){
                st.pop();
            } else {
                st.push(ch);
            }
        }
        int op = 0;
        int cl = 0;
        while(st.size() > 0) {
            char ch = st.pop();
            if(ch == '{') {
                op++;
            } else {
                cl++;
            }
        }

        return (int)(Math.ceil(op / 2.0) + Math.ceil(cl / 2.0));
    }

    // leetcode 946, https://leetcode.com/problems/validate-stack-sequences/
    public boolean validateStackSequences(int[] pushed, int[] popped) {
        int j = 0;
        Stack<Integer> st = new Stack<>();

        for(int i = 0; i < pushed.length; i++) {
            st.push(pushed[i]);
            while(st.size() > 0 && st.peek() == popped[j]) {
                st.pop();
                j++;
            }
        }
        return st.size() == 0;
    }

    // leetcode 1021, https://leetcode.com/problems/remove-outermost-parentheses/
    public String removeOuterParentheses(String s) {
        StringBuilder str = new StringBuilder();
        
        int op = 0;
        int cl = 0;
        int si = 0;
        for(int i = 0; i < s.length(); i++) {
            if(s.charAt(i) == '(') {
                op++;
            } else {
                cl++;
            }

            if(op == cl) {
                for(int j = si + 1; j < i; j++) {
                    str.append(s.charAt(j));
                }
                op = 0;
                cl = 0;
                si = i + 1;
            }
        }

        return str.toString();
    }

    // leetcode 856. https://leetcode.com/problems/score-of-parentheses/
    public int scoreOfParentheses(String s) {
        // marker -> -1 means opening bracket, and number is score of parenthesis, ()-> 1, (A) -> 2*A, AB -> A+B
        Stack<Integer> st = new Stack<>();
        for(int i = 0; i < s.length(); i++) {
            char ch = s.charAt(i);
            if(ch == '(') {
                st.push(-1);
            } else if(st.peek() == -1){
                st.pop();
                st.push(1);
            } else {
                int sum = 0;
                while(st.peek() != -1) {
                    sum += st.pop();
                }
                st.pop();
                st.push(2 * sum);
            }
        }
        return st.pop();
    }

    // leetcode 1190. https://leetcode.com/problems/reverse-substrings-between-each-pair-of-parentheses/
    public String reverseParentheses(String s) {
        Stack<Character> st = new Stack<>();

        for(int i = 0; i < s.length(); i++) {
            char ch = s.charAt(i);
            if(ch != ')') {
                st.push(ch);
            } else {
                StringBuilder str = new StringBuilder();
                while(st.peek() != '(') {
                    str.append(st.pop());
                }
                st.pop();
                for(int j = 0; j < str.length(); j++) {
                    st.push(str.charAt(j));
                }
            }
        }
        StringBuilder str = new StringBuilder();
        while(st.size() > 0) {
            str.append(st.pop());
        }
        str.reverse();
        return str.toString();
    }

    // leetcode 1249. https://leetcode.com/problems/minimum-remove-to-make-valid-parentheses/
    public String minRemoveToMakeValid(String s) {
        Stack<Integer> st = new Stack<>();
        
        for(int i = 0; i < s.length(); i++) {
            char ch = s.charAt(i);
            if(ch == '(') {
                st.push(i);
            } else if(ch == ')') {
                if(st.size() == 0 || s.charAt(st.peek()) == ')') {
                    st.push(i);
                } else {
                    // stack peek is equal to opening bracket
                    st.pop();
                }
            }
        }

        StringBuilder str = new StringBuilder();
        for(int i = s.length() - 1; i >= 0; i--) {
            if(st.peek() == i) {
                st.pop();
            } else {
                str.append(s.charAt(i));
            }
        }
        return str.reverse().toString();
    }

    // online stock span
    class StockSpanner {
        private class Pair {
            int val;
            int indx;
            Pair(int val, int indx) {
                this.val = val;
                this.indx = indx;
            }
        }

        private int indx;
        private Stack<Pair> st;
        
        public StockSpanner() {
            st = new Stack<>();
            indx = -1;
            st.push(new Pair(Integer.MAX_VALUE, indx));
            indx++;
        }
        
        public int next(int price) {
            while(st.size() > 0 && st.peek().val <= price) {
                st.pop();
            }
            int span = indx - st.peek().indx;
            st.push(new Pair(price, indx));
            indx++;
            return span;
        }
    }

    // leetcode 739. https://leetcode.com/problems/daily-temperatures/
    public int[] dailyTemperatures(int[] temp) {
        Stack<Integer> st = new Stack<>();
        int[] res = new int[temp.length];
        for(int i = 0; i < temp.length; i++) {
            while(st.size() > 0 && temp[st.peek()] < temp[i]) {
                res[st.pop()] = i;
            }
            st.push(i);
        }

        while(st.size() > 0) {
            res[st.pop()] = temp.length;
        }

        for(int i = 0; i < temp.length; i++) {
            if(res[i] == temp.length) {
                res[i] = 0;
                continue;
            }
            res[i] = res[i] - i;
        }
        return res;
    }

    // leetcode 844. https://leetcode.com/problems/backspace-string-compare/
    private Stack<Character> makeStack(String s) {
        Stack<Character> st = new Stack<>();
        for(int i = 0; i < s.length(); i++) {
            char ch = s.charAt(i);

            if(ch != '#') {
                st.push(ch);
            } else {
                if(st.size() > 0)
                    st.pop();
            }
        }
        return st;
    }

    public boolean backspaceCompare(String s, String t) {
        Stack<Character> st1 = makeStack(s);
        Stack<Character> st2 = makeStack(t);
        if(st1.size() != st2.size()) return false;
        while(st1.size() > 0) {
            if(st1.peek() != st2.peek()) return false;
            st1.pop();
            st2.pop();
        }
        return true;
    }

    // leetcode 636, https://leetcode.com/problems/exclusive-time-of-functions/
    private class EThelper {
        int id;
        int stime;
        int cet; // child execution time

        public EThelper(int id, int stime, int cet) {
            this.id = id;
            this.stime = stime;
            this.cet = cet;
        }
    }
    public int[] exclusiveTime(int n, List<String> logs) {
        // n-> number of IDs
        int[] res = new int[n];
        Stack<EThelper> st = new Stack<>();
        
        for(String str : logs) {
            String[] info = str.split(":");
            int id = Integer.parseInt(info[0]);
            String status = info[1];
            int timeStamp = Integer.parseInt(info[2]);

            if(status.equals("start")) {
                st.push(new EThelper(id, timeStamp, 0));
            } else {
                int fn_diff = timeStamp - st.peek().stime + 1; // function difference time
                int etime = fn_diff - st.peek().cet;  // child execution time

                res[id] += etime;
                st.pop();
                if(st.size() > 0) {
                    st.peek().cet += fn_diff;
                }
            }
        }
        return res;
    }

    // leetcode 456, https://leetcode.com/problems/132-pattern/
    public boolean find132pattern(int[] nums) {
        
    }

    // leetcode 735, https://leetcode.com/problems/asteroid-collision/
    public int[] asteroidCollision(int[] asteroids) {
        Stack<Integer> st = new Stack<>();
        for(int val : asteroids) {
            if(val > 0) {
                st.push(val);
                continue;
            }
            // val is -ve
            // peek is positive but smaller in temrs of size then pop untile this condition will not break
            while(st.size() > 0 && st.peek() < -val && st.peek() > 0) {
                st.pop();
            }
            if(st.size() > 0 && st.peek() == -val) {
                st.pop(); // equal in size but opposite in direction
            } else if(st.size() == 0 || st.peek() < 0) {
                st.push(val);
            } else {
                // nothing to do
            }
        }
        int[] res = new int[st.size()];
        for(int i = res.length - 1; i >= 0; i--) {
            res[i] = st.pop();
        }
        return res;
    }

    // leetcode 402, https://leetcode.com/problems/remove-k-digits/
    public String removeKdigits(String num, int k) {
    }

    // leetcode 316, https://leetcode.com/problems/remove-duplicate-letters/
    public String removeDuplicateLetters(String s) {
    }

    // leetcode 42. trapping rain water, https://leetcode.com/problems/trapping-rain-water/
    // method 1. using array, left max and rightmax, think about head of building

    // method 2. Using Stack
    public int trap2(int[] height) {
        Stack<Integer> st = new Stack<>();
        int water = 0;
        for(int i = 0; i < height.length; i++) {
            int val = height[i];
            int rmax = val; // right max
            int rindx = i; // right index
            while(st.size() > 0 && height[st.peek()] < val) {
                int cht = height[st.pop()];
                if(st.size() == 0) break;
                int lmaxIndx = st.peek();
                int lmax = height[lmaxIndx];
                water += (Math.min(lmax, rmax) - cht) * (rindx - lmaxIndx - 1);
            }
            st.push(i);
        }
        return water;
    }

    // method 3. Two Pointer Approach
    public int trap3(int[] height) {
        int left = 0;
        int right = height.length - 1;

        int lmax = 0;
        int rmax = 0;

        int water = 0;
        while(left < right) {
            lmax = Math.max(lmax, height[left]);
            rmax = Math.max(rmax, height[right]);

            if(lmax < rmax) {
                water += lmax - height[left];
                left++;
            } else {
                water += rmax - height[right];
                right--;
            }
        }
        return water;
    }

    // method 4. using dual iteration
    public int trap4(int[] height) {
        int owater = 0; // overall water
        int maxIndx = 0;
        int rwater = 0; // runnning water
        for(int i = 0; i < height.length; i++) {
            if(height[maxIndx] <= height[i]) {
                owater += rwater;
                rwater = 0;
                maxIndx = i;
            }
            rwater += height[maxIndx] - height[i];
        }
        rwater = 0;
        int rmaxIndx = height.length - 1;
        // some amount of water is not safe
        for(int i = height.length - 1; i >= maxIndx; i--) {
            if(height[rmaxIndx] <= height[i]) {
                owater += rwater;
                rwater = 0;
                rmaxIndx = i;
            }
            rwater += height[rmaxIndx] - height[i];
        }
        return owater;
    }

    // leetcode 407, https://leetcode.com/problems/trapping-rain-water-ii/
    private class trwHelper implements Comparable<trwHelper>{
        int x;
        int y;
        int ht;

        public trwHelper(int x, int y, int ht) {
            this.x = x;
            this.y = y;
            this.ht = ht;
        }

        public int compareTo(trwHelper o) {
            return this.ht - o.ht;
        }
    }

    private void addBoundaryTRW(PriorityQueue<trwHelper> pq, int[][] hts, boolean[][] vis) {
        // top wall
        for(int c = 0; c < hts[0].length; c++) {
            if(vis[0][c] == false) {
                pq.add(new trwHelper(0, c, hts[0][c]));
                vis[0][c] = true;
            }
        }
        // left wall
        for(int r = 0; r < hts.length; r++) {
            if(vis[r][0] == false) {
                pq.add(new trwHelper(r, 0, hts[r][0]));
                vis[r][0] = true;
            }
        }
        // down wall
        for(int c = 0; c < hts[0].length; c++) {
            if(vis[hts.length - 1][c] == false) {
                pq.add(new trwHelper(hts.length - 1, c, hts[hts.length - 1][c]));
                vis[hts.length - 1][c] = true;
            }
        }
        // right wall
        for(int r = 0; r < hts.length; r++) {
            if(vis[r][hts[0].length - 1] == false) {
                pq.add(new trwHelper(r, hts[0].length - 1, hts[r][hts[0].length - 1]));
                vis[r][hts[0].length - 1] = true;
            }
        }
    }

    static int[] xdir = {-1, 0, 1, 0};
    static int[] ydir = {0, -1, 0, 1};

    public int trapRainWater(int[][] hts) {
        boolean[][] vis = new boolean[hts.length][hts[0].length];
        PriorityQueue<trwHelper> pq = new PriorityQueue<>();

        // add bounday in pq
        addBoundaryTRW(pq, hts, vis);
        int water = 0;
        while(pq.size() > 0) {
            trwHelper rem = pq.remove();
            for(int d = 0; d < 4; d++) {
                int r = rem.x + xdir[d];
                int c = rem.y + ydir[d];

                if(r >= 0 && r < hts.length && c >= 0 && c < hts[0].length && vis[r][c] == false) {
                    vis[r][c] = true;
                    if(hts[r][c] < rem.ht) {
                        water += rem.ht - hts[r][c];
                        pq.add(new trwHelper(r, c, rem.ht));
                    } else {
                        pq.add(new trwHelper(r, c, hts[r][c]));
                    }
                }
            }
        }

        return water;
    }

    // basic calculator 224, https://leetcode.com/problems/basic-calculator/
    public int calculate(String s) {
        Stack<Integer> st = new Stack<>();
        int sign = 1;
        int sum = 0;

        for(int i = 0; i < s.length(); i++) {
            char ch = s.charAt(i);
            if(ch == ' ') {
                continue;
            } else if(ch >= '0' && ch <= '9') {
                // number may have more than one digit
                long n = 0;
                while(i < s.length() && s.charAt(i) >= '0' && s.charAt(i) <= '9') {
                    n *= 10;
                    n += (int)(s.charAt(i) - '0');
                    i++;
                }
                i--;
                n *= sign;
                sum += (int)n;
                sign = 1;
            } else if(ch == '(') {
                st.push(sum);
                st.push(sign);
                sign = 1;
                sum = 0;
            } else if(ch == ')') {
                sum *= st.pop(); // multiply sign from sum
                sum += st.pop(); // add old sum in new one
            } else if(ch == '-') {
                sign *= -1;
            } else {
                // ch is +ve
                // nothing to do
            }
        }
        return sum;
    }

    // basic calculator 3
    private static int evaluate(int val1, int val2, char op) {
        if(op == '*') {
            return val1 * val2;
        } else if(op == '/') {
            return val1 / val2;
        } else if(op == '+') {
            return val1 + val2;
        } else if(op == '-') {
            return val1 - val2;
        } else {
            return 0;
        }
    }

    private static int priority(char op) {
        if(op == '/' || op =='*') {
            return 2; 
        } else if(op == '+' || op =='-') {
            return 1;
        } else {
            return 0;
        }
    }
    
    
    // infix evaluation
    private static int infixEval(String exp) {
        Stack<Integer> vstack = new Stack<>(); // value stack
        Stack<Character> ostack = new Stack<>(); // operator stack
  
        for(int i = 0; i < exp.length(); i++) {
            char ch = exp.charAt(i);
            if(ch == ' ') {
                continue;
            } else if(ch >= '0' && ch <= '9') {
                int j = i;
                StringBuilder n = new StringBuilder();
                while(j < exp.length() && exp.charAt(j) >='0' && exp.charAt(j) <= '9') {
                    n.append(exp.charAt(j));
                    j++;
                }
                i = j - 1;
                if(n.toString().equals("2147483648") == true) {
                    vstack.push(-2147483648);
                } else {
                    vstack.push(Integer.parseInt(n.toString()));
                }
            } else if(ch =='(') {
                ostack.push(ch);
            } else if(ch == ')') {
                while(ostack.peek() != '(') {
                    char op = ostack.pop();
                    int v2 = vstack.pop();
                    int v1 = vstack.pop();

                    // res = val1 operator val2
                    int res = evaluate(v1, v2, op);
                    vstack.push(res);
                }
                ostack.pop(); // this pop is for opening bracket
            } else {
                // ch = operators
                while(ostack.size() > 0 && ostack.peek() != '(' && priority(ostack.peek()) >= priority(ch)) {
                    char op = ostack.pop();
                    int v2 = vstack.pop();
                    int v1 = vstack.pop();

                    // res = val1 operator val2
                    int res = evaluate(v1, v2, op);
                    vstack.push(res);
                }
                ostack.push(ch);
            }
        }

        while(ostack.size() > 0) {
            char op = ostack.pop();
            int v2 = vstack.pop();
            int v1 = vstack.pop();

            // res = val1 operator val2
            int res = evaluate(v1, v2, op);
            vstack.push(res);
        }
        return vstack.peek();
    }

    public int calculate(String s) {
        // Write your code here
        return infixEval(s);
    }


    public static void main(String[] args) {
        
    }
}