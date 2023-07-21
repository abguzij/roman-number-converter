public class Main {
    private static final int DECIMAL_BASE = 10;
    private static final int MAX_NUMBER_OF_DIGITS_IN_ROMAN = 3;
    private static final int INDEX_OF_THOUSANDS = 3;
    private static final int INDEX_OF_HUNDREDS = 2;

    private static final int INDEX_OF_LOWEST_VALUE = 0;

    public static void main(String[] args) {
        int number = 5012;

        int decimalDigitWeight = 1;
        int numberOfDigits = 1;
        for (; numberOfDigits <= MAX_NUMBER_OF_DIGITS_IN_ROMAN; numberOfDigits++) {
            if(number <= decimalDigitWeight){
                break;
            }
            decimalDigitWeight *= DECIMAL_BASE;
        }
        int[] valuesOfDigits = new int[numberOfDigits];

        int copyOfNumber = number;
        for (int i = 0; i < valuesOfDigits.length; i++) {
            int digitValueMultipliedOnWeight = copyOfNumber - copyOfNumber % decimalDigitWeight;
            valuesOfDigits[valuesOfDigits.length - i - 1] = digitValueMultipliedOnWeight / decimalDigitWeight;
            copyOfNumber -= digitValueMultipliedOnWeight;
            decimalDigitWeight /= DECIMAL_BASE;
        }

        char[] repeatableCharactersAlphabet = {'I', 'X', 'C', 'M'};
        char[] notRepeatableCharactersAlphabet = {'V', 'L', 'D'};

        String thousands = "";
        int endIndex = valuesOfDigits.length - 1;
        if(valuesOfDigits.length > MAX_NUMBER_OF_DIGITS_IN_ROMAN){
            for (int i = 0; i < valuesOfDigits[INDEX_OF_THOUSANDS]; i++) {
                thousands += repeatableCharactersAlphabet[INDEX_OF_THOUSANDS];
            }
            endIndex = INDEX_OF_HUNDREDS;
        }

        String romanNumber = "";
        for (int i = 0; i <= endIndex; i++) {
            if(valuesOfDigits[i] != 0){
                String[] romanValuesOfRepeatableDigitsValues = {
                    "" + repeatableCharactersAlphabet[i],
                    "" + repeatableCharactersAlphabet[i] + repeatableCharactersAlphabet[i],
                    "" + repeatableCharactersAlphabet[i] + repeatableCharactersAlphabet[i] + repeatableCharactersAlphabet[i]
                };

                int quotientOfDivisionBy5 = valuesOfDigits[i] / 5;
                int remainderOfDivisionBy5 = valuesOfDigits[i] % 5;

                if(quotientOfDivisionBy5 == 0){
                    if (remainderOfDivisionBy5 < 4) {
                        romanNumber = romanValuesOfRepeatableDigitsValues[remainderOfDivisionBy5 - 1] + romanNumber;
                    } else {
                        romanNumber = notRepeatableCharactersAlphabet[i] + romanNumber;
                        romanNumber = romanValuesOfRepeatableDigitsValues[INDEX_OF_LOWEST_VALUE] + romanNumber;
                    }
                } else {
                    if(remainderOfDivisionBy5 < 4){
                        if(remainderOfDivisionBy5 != 0) {
                            romanNumber = romanValuesOfRepeatableDigitsValues[remainderOfDivisionBy5 - 1] + romanNumber;
                        }
                        romanNumber = notRepeatableCharactersAlphabet[i] + romanNumber;
                    } else {
                        romanNumber = repeatableCharactersAlphabet[i + 1] + romanNumber;
                        romanNumber = romanValuesOfRepeatableDigitsValues[INDEX_OF_LOWEST_VALUE] + romanNumber;
                    }
                }
                romanValuesOfRepeatableDigitsValues = null;
            }
        }
        romanNumber = thousands + romanNumber;
        System.out.println(number + " = " + romanNumber);
    }
}