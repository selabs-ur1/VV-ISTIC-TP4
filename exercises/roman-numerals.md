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

Cette phrase du sujet n'est pas clair, Il existe de scontre exemple peu importe l'interpretation : "Only symbols C, X, V can be subtracted." je fais donc sans .

J'utilise des regex pour vérifier les propriétés d'un nombre romain valide ou non. Pour les test j'ai créer des list de nombres romains valide est non vlaide pour tester les propriétes. 
Pour tester les conversion je fais deux conversion qui transforme en un nombre dans l'autre écriture puis le retransforme dans l'ecriture d'origine. Le fisant pour chaque je vérifie bien les deux méthodes conjointement.


Le code est [ici](../code/roman-numerals/src/main/java/fr/istic/vv/RomanNumeraUtils.java).
Le test est [ici](../code/roman-numerals/src/test/java/fr/istic/vv/RomanNumeralTest.java).


Voici le resultat des tests :
```bash 
Results:

Tests run: 4, Failures: 0, Errors: 0, Skipped: 0

------------------------------------------------------------------------
BUILD SUCCESS
------------------------------------------------------------------------
Total time:  2.716 s
Finished at: 2024-12-13T12:19:44+01:00
------------------------------------------------------------------------
```