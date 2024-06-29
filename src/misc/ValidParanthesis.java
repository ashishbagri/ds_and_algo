package misc;

import utils.IOUtil;

import java.util.Objects;
import java.util.Stack;

public class ValidParanthesis {

    public static void main(String[] args) {
        System.out.println(valid(Objects.requireNonNull(IOUtil.readLine())));
    }

    public static boolean valid(String s) {
        Stack<Character>  chars = new Stack<>();
        for(int i =0 ; i< s.length(); i++) {
            char c = s.charAt(i);
            if(isOpen(c)) {
                chars.push(c);
                continue;
            }
            if(chars.isEmpty())
                return false;
            char ch = chars.pop();
            if(!compare(c, ch))
                return false;
        }

        return chars.isEmpty();
    }

    private static boolean isOpen(char c) {
        return c == '{' || c =='(' || c == '[';
    }

    private static boolean compare(char c, char ch) {
        switch (ch) {
            case '(' :
                return c == ')';
            case  '{' :
                return c == '}';
            case '[' :
                return c == ']';
            default:
                return false;
        }
    }
}
