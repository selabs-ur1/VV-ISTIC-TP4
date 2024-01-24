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

## Answer
The implement of methods in the `RomanNumeralUtils` class are available [here](/code/roman-numerals/src/main/java/fr/istic/vv/RomanNumeraUtils.java).

The property based tests are available [here](/code/roman-numerals/src/test/java/fr/istic/vv/RomanNumeralTest.java).

At first test launch, following error was reported :
```xml
<testcase name="symbolsCorrectlyRepeated" classname="fr.istic.vv.RomanNumeralTest" time="0.018">
    <failure message="Property [RomanNumeralTest:symbolsCorrectlyRepeated] failed with sample {0=4}" type="org.opentest4j.AssertionFailedError">org.opentest4j.AssertionFailedError: Property [RomanNumeralTest:symbolsCorrectlyRepeated] failed with sample {0=4}
    </failure>
    <system-out><![CDATA[timestamp = 2024-01-23T09:48:57.636687, RomanNumeralTest:symbolsCorrectlyRepeated = 
  org.opentest4j.AssertionFailedError:
    Property [RomanNumeralTest:symbolsCorrectlyRepeated] failed with sample {0=4}

                              |-------------------jqwik-------------------
tries = 1                     | # of calls to property
checks = 1                    | # of not rejected calls
generation = RANDOMIZED       | parameters are randomly generated
after-failure = PREVIOUS_SEED | use the previous seed
edge-cases#mode = MIXIN       | edge cases are mixed in
edge-cases#total = 4          | # of all combined edge cases
edge-cases#tried = 0          | # of edge cases tried in current run
seed = 562458162463211689     | random seed to reproduce generated values

Shrunk Sample (1 steps)
-----------------------
  arg0: 4

Original Sample
---------------
  arg0: 223


]]></system-out>
</testcase>
```

With the help of the report, `toRomanNumeral` has been fixed in [this commit](https://github.com/selabs-ur1/VV-ISTIC-TP4/commit/ae43c124e6b6d5952e410403d58e33b44f9cc737).

After the fix, all tests correctly pass.