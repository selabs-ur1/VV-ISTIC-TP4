# Roman numerals

Any natural number between 0 and 3999 can be represented in *roman numerals* using the following rules:

1. Only symbols *M*, *D*, *C*, *X*, *V*, *I* can be used. Their values are shown below:

    |   M  |  D  |  C  | L  |  X | V | I |
    |------|-----|-----|----|----|---|---|
    | 1000 | 500 | 100 | 50 | 10 | 5 | 1 |

2. Les symboles M, C, X, I peuvent être répétés consécutivement jusqu'à trois fois.
3. Les symboles D, L et V ne peuvent pas être répétés.
4. Lorsqu'un symbole de valeur inférieure apparaît à droite d'un symbole de valeur égale ou supérieure, toutes les valeurs des symboles sont additionnées.
5. Lorsqu'un symbole de valeur inférieure apparaît à gauche d'un symbole de valeur supérieure, la valeur inférieure est soustraite de la valeur supérieure. Seuls les symboles C, X, V peuvent être soustraits. Chaque symbole ne peut être soustrait qu'une seule fois. Le symbole soustrait doit être un cinquième ou un dixième du plus grand.

*Examples:*

-    1 = I
-    4 = IV
-    8 = VIII
-    9 = IX
-   14 = XIV
-   16 = XVI
-   19 = XIX
-   99 = XCIX
-  105 = CV
- 1001 = MI
- 2289 = MMCCLXXXIX

Implement the following methods in the `RomanNumeralUtils` class:

```java
class RomanNumeralUtils {

    public static boolean isValidRomanNumeral(String value) { ... }

    public static int parseRomanNumeral(String numeral) { ... }

    public static String toRomanNumeral(int number) { ... }

}
```

Use [jqwik](https://jqwik.net/) to create property based tests verifying these three methods. Create the tests before implementing the methods. Document any bugs you found with the help of these tests during the process.

**NOTE:** 
- Do not use any existing implementation, write your own code. 
- Use the provided project template as a starting point.
- In the project you can launch the tests with `mvn test`.
