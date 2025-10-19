public class reverseStringStack {
    public static void main(String[] args) {
       String str="hello";
       Stack2 stack=new Stack2(str.length());
       //push all the characters
       for (int i=0; i<str.length();i++) {
        stack.push(str.charAt(i));
       }
       //pop all characters to get reversed
       String reversed="";
       while(!stack.isEmpty()){
        reversed+=stack.pop();
       }
       System.out.println("Original: " + str);
       System.out.println("Reversed: " + reversed);
    }
}
