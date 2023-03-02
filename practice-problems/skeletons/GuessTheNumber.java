/*
 * Test Case - 1
 * Final Number = 2500
 * Operations = {"X + 10", "X - 5", "X * 5", "X ^ 2"}
 * Expected Output: 5
 * 
 * Test Case - 2
 * Final Number = 1000
 * Operations = {"X * 5", "X / 0", "X ^ 3"}
 * Expected Output: -1
 * 
 * Test Case - 3
 * Final Number: 10
 * Operations: {"X * 5", "X * 0", "X + 10"}
 * Exepcted Output : -1
 * 
 * Test Case - 4
 * Final Number: 617283948
 * Operations: {"X + 5”, “X - 0”, “X + 1”, “X / 2”, “X ^ 1”}
 * Exected Output: 1234567890
 */

public class GuessTheNumber {
  public static void main(String[] args) throws Exception {
    int finalNumber =  617283948;
    String[] operations = {"X + 5", "X - 0", "X + 1", "X / 2", "X ^ 1"};

    int actualNumber = GuessTheNumber.getActualNumber(finalNumber, operations, operations.length);
    System.out.println("The actual number will be " + actualNumber + " after performing given operations.");
  }

  public static int getActualNumber(int finalNumber, String[] operations, int totalOperations) throws Exception{
    int num = finalNumber;
    for(int i = totalOperations-1; i >= 0; i--) {
      char opr = operations[i].charAt(2);
      int op = Integer.parseInt(operations[i].substring(4));
      //divide by 0 case
      if(opr == '/' && op == 0) return -1;
      //if multiple answers are possible then return -2
      if(opr == '%'|| (opr=='^' && op == 0) || (opr == '*' && op == 0)) return -2;
      switch (opr) {
        case '+' -> num -= op;
        case '-' -> num += op;
        case '*' -> num /= op;
        case '/' -> num *= op;
        case '^' -> num = (int) Math.pow(num, 1f / op);
      }
    }

    return num;
  }
}
