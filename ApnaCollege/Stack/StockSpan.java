package ApnaCollege.Stack;

import java.util.Stack;
// Span - The span of a stock's price on a given day is defined as the maximum number of consecutive days (including the current day) for which the price of the stock has been less than or equal to its price on that day. For example, if the prices of a stock over 7 days are {100, 80, 60, 70, 60, 85, 100}, then the span of prices for these days would be {1, 1, 1, 2, 1, 4, 6}.
public class StockSpan {
    public static void stockSpan(int stocks[]) {
        Stack<Integer> s = new Stack<>();
        int span[] = new int[stocks.length];
        span[0] = 1;
        s.push(0);
        
        for(int i=1; i<stocks.length; i++) {
            int curr = stocks[i];
            while(!s.isEmpty() && curr >= stocks[s.peek()]) {
                s.pop();
            }
            if(s.isEmpty()) {
                span[i] = i+1;
            } else {
                int prevHigh = s.peek();
                span[i] = i - prevHigh;
            }
            
            s.push(i);
        }

        for(int i=0; i<span.length; i++) {
            System.out.print(span[i]+" ");
        }
    }
    public static void main(String args[]) {
        int stocks[] = {100, 80, 60, 70, 60, 85, 100};
        stockSpan(stocks);
    }
}
