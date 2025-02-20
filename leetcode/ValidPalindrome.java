package leetcode;

public class ValidPalindrome {
    public static void main(String[] args) {
        final String input = "A man, a plan, a canal: Panama";

        System.out.println(isPalindrome(input));
    }

    /* Using two pointers approach */
    static boolean isPalindrome(String s) {
        if (s.length() == 0) return true;
        int i = 0, j = s.length() - 1;

        while (i < j) {
            char a = s.charAt(i);
            char b = s.charAt(j);

            if (!((a >= 65 && a <= 90) /* upper case letters */ ||
                    (a >= 97 && a <= 122) /* lower case letters */ ||
                    (a >= 48 && a <= 57) /* numbers */ ) 
            ) {
                i++;
                continue;
            }

            if (!((b >= 65 && b <= 90) /* upper case letters */ ||
                    (b >= 97 && b <= 122) /* lower case letters */ ||
                    (b >= 48 && b <= 57)  /* numbers */)
            ) {
                j--;
                continue;
            }

            if ((a >= 65 && a <= 90)) {
                a = (char) (a + 32);
            }
            if ((b >= 65 && b <= 90)) {
                b = (char) (b + 32);
            }

            if (a != b) return false;
            i++;
            j--;
        }

        return true;
    }
}
