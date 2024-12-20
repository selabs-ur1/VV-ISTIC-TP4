# Roman numerals

Any natural number between 0 and 3999 can be represented in *roman numerals* using the following rules:

1. Only symbols *M*, *D*, *C*, *X*, *V*, *I* can be used. Their values are shown below:

    |   M  |  D  |  C  | L  |  X | V | I |
    |------|-----|-----|----|----|---|---|
    | 1000 | 500 | 100 | 50 | 10 | 5 | 1 |

2. Symbols M, C, X, I can be repeated consecutively up to three times.
3. Symbols D, L and V can not be repeated.
4. When a symbol of lower value of appears to the right of a symbol of equal or higher value, all symbol values are added.
5. When a symbols of lower value appears to the left of a symbols of higher value, the lower value is subtracted from the higher value. Only symbols C, X, V can be subtracted. Each symbol can be subtracted only once. The subtracted symbol must be one fifth or one tenth of the larger.

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

## Answers

**WARNING $\LaTeX$ rendering of GitHub seems kinda bad, it may be preferable to read this doc using a proper IDE (preferably using VS Code or IntelliJ)**

We define 3 functions : `isValidRomanNumeral`, `parseRomanNumeral` and 
`toRomanNumeral`. To specify their properties, we need to do a bit of
formalizing.

Let $\text{String}$ be the set of Java string, 
$\text{Roman} \subset \text{String}$ the set of strings which are valid roman
numerals. We can treat $\texttt{isValidRomanNumeral}$ as a predicate over
$\texttt{String}$, and define $\texttt{Roman}$ as the following :

$$\text{Roman} = \left\{\ s \in \text{String} \ | \ \texttt{isValidRomanNumeral}(s) \ \right\}$$

We define the functions :

- $\texttt{parseRomanNumeral} : \text{String} \to [ 0, 3999 ]$ : partial 
function mapping a string, supposed to be a roman number, to the corresponding integer 
value of such roman number. $\texttt{parseRomanNumeral}(s)$ is defined only and only $s \in \text{Roman}$ ; 
if not, the Java implementation will raise a `NumberFormatException`. Thus, 
the restriction of the function over $\text{Roman}$, noted $\texttt{parseRomanNumeral}_{|\text{Roman}}$ 
is a bijective function, as each roman numeral maps to exactly one value 
between 0 and 3999.

- $\texttt{toRomanNumeral} : \mathbb{N} \to \text{Roman}$ : partial 
function mapping integers to a roman numeral. The function is only defined for 
integers between 0 and 3999, and is not defined for other integers, and  will throw 
an `IllegalArgumentException` in the Java implementation if a bad value is passed.
The restiction $\texttt{toRomanNumeral}_{|[ 0, 3999 ]}$ is 
bijective as it maps to each integer between 0 and 3999 its corresponding
representation using roman numerals.

We check, in the `jqwik` tests, the following properties :

1. $\texttt{parseRomanNumeral}(s)$ is undefined for $s \notin \text{Roman}$ :
    we check that a `NumberFormatException` is raised with the test case
    `parseRomanNumeralUndefinedForAllNonRoman`.
2. $\texttt{toRomanNumeral}(n)$ is undefined for $n \notin [ 
    0, 3999 ]$ : we check that an `IllegalArgumentException` is 
    raised with test cases `toRomanNumeralUndefinedUnderZero` and 
`toRomanNumeralUndefinedOverMax`.
3. Defined values are implicitly checked by other test cases, which 
    work only on valid inputs. Especially, to produce valid roman 
    litterals, we use the following function as provider :
    ```java
    @Provide
    Arbitrary<String> romanNumeralProvider() {
        return Arbitraries.strings()
                .filter(RomanNumeraUtils::isValidRomanNumeral);
    }
    ```
4. $\texttt{toRomanNumeral}$ always produce valid roman numerals :

    $$\forall n \in [ 0, 3999 ]. \ \texttt{isValidRomanNumeral}(\texttt{toRomanNumeral}(n))$$

    This property is tested by the case `toRomanNumeralAlwaysRoman`.
    

5. $\texttt{parseRomanNumeral}$ always produce in-range values:

    $$\forall s \in \text{Roman}. \ \texttt{parseRomanNumeral}(s) \in [ 0, 3999 ]$$

    This property is checked using `parseRomanNumeralNumeralAlwaysInRange` test case.

6. Intuitively, if we pass a valid roman numeral as input to `parseRomanNumeral`, and
    repass the result to `toRomanNumeral`, we retrieve the original roman numeral :

    ```java
    String num = "XII";
    assert parseRomaNumeral(num) == 12;
    assert toRomanNumeral(parseRomanNumeral(num)).equals(num);
    ```

    The processes work also by doing the inverse composition :

    ```java
    int n = 12;
    assert toRomanNumeral(n).equals("XII");
    assert parseRomanNumeral(toRomanNumeral(n)) == n;
    ```

    More formally, $\texttt{toRomanNumeral}_{|[ 0, 3999 ]}$ and $\texttt{parseRomanNumeral}_{|\text{Roman}}$ are
    isomorphisms, i.e.

    $$\forall n \in [ 0, 3999 ]. \ \texttt{parseRomanNumeral}_{|\text{Roman}}\left(\texttt{toRomanNumeral}_{|[ 0, 3999 ]}(n)\right) = n$$

    and

    $$\forall s \in \text{Roman}. \ \texttt{toRomanNumeral}_{|[ 0, 3999 ]}\left(\texttt{parseRomanNumeral}_{|\text{Roman}}(s)\right) = s$$

    These properties are respectively checked by test cases `parseRomanNumeralIsomorphism` 
    and `toRomanNumeralIsomorphism`.