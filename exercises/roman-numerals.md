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


## BUGS trouvés avec les tests 

- Le premier test isARomanNumeral a permis de détecter un bug dû à une erreur
d’inattention où la fonction isValidRomanNumeral rendait true lorsque l’on ne
passait pas la condition de base car aucune branche else n’était définie et elle
passait toute la boucle for sans rendre une fois false. Elle atterrissait donc dans 
le cas où tout allait bien.

- Le test isARomanValidRepeated3Times a permis de détecter un bug dans l’évaluation
de la répétition, c’est à dire qu’un deuxième caractère de la même valeur que le
précédent vu (nous évaluons une chaine à l’envers) est autorisé.

- Le test isARomanValidRepeatedMore a permis de détecter le bug lié au fait que
l’on commençait à compter les répétitions qu’à partir de la seconde répétition. La
variable était initialisée à 0 au lieu de 1, ce qui ne permettait pas de détecter
la 4e répétition et donc de rendre faux (le code considérait qu’il s’agissait que
la 3e.)

- Le test isARomanInvalidSubstracted a permis de détecter que des valeurs plus
petites pouvaient passer à gauche d’une valeur plus grande sans les soustraire à
cause d’une mauvaise évaluation de isValid qui comparait les caractères plutôt que
leurs valeurs romaines associées dans la table symbols.

- De la même manière, isARomanValidSubstracted a permis de détecter une mauvaise
évaluation dans la fonction canBeSubstractedBy qui comparait la valeur romaine d’un
caractère divisée par 10 ou 5 au caractère (char) de ce que l’on souhaitait retirer
(par exemple le caratère ‘« I » avec sa valeur entière).

- convertIntegersToRomanNumerals a permis de détecter que pour générer tous les
nombres entre 0 et 3999, L et D doivent aussi être soustractables pour pouvoir
faire 400 et 40. Au contraire de ce qui nous est indiqué dans l’énoncé où seuls C,
X, V peuvent être soustraits. Ainsi que M pour pouvoir faire 900.

- Ce test a aussi permis de détecter qu’utiliser une simple map ne permet pas de
garder l’ordre d’insertion et nous provoque donc un effet de bord lors de la
conversion où certaines valeurs sont sautées. (Par exemple on obtient VVVV au lieu
de XX car le V est venu avant malgré l’ordre d’insertion).
Il a permis de détecter une nouvelle erreur d’inattention dans la fonction
parseRomanNumeral
Où les caractères étaient comparés à la place de leurs valeurs romaines. 


 
