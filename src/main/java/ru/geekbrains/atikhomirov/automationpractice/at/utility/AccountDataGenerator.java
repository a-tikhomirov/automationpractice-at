package ru.geekbrains.atikhomirov.automationpractice.at.utility;

import java.util.ArrayList;
import java.util.List;

/*
Для генерации имен можно использовать библиотеку: org.ajbrown:name-machine
Для генерации имен, адресов и прочего: com.github.javafaker:javafaker
 */
public class AccountDataGenerator {
    private static final String[] FIRST_NAMES = {
            "David",
            "Jack",
            "John",
            "Jorge",
            "Joseph",
            "Kevin",
            "Matthew",
            "Michael",
            "Ronald",
            "William"
    };

    private static final String[] LAST_NAMES = {
            "Allen",
            "Davis",
            "Gillette",
            "Gray",
            "Hammond",
            "Pierce",
            "Roden",
            "Silver",
            "Snyder",
            "Wright"
    };

    private static final int MAX_STATE_ID = 53;

    private static final int CHAR_NUM_MIN = 48;
    private static final int CHAR_NUM_MAX = 57;
    private static final int CHAR_SYMBOL_MIN = 97;
    private static final int CHAR_SYMBOL_MAX = 122;

    private int maxNamesCount;
    private int[][] firstAndLastNames;

    public AccountDataGenerator() {
        maxNamesCount = FIRST_NAMES.length * LAST_NAMES.length;
        firstAndLastNames = new int[maxNamesCount][2];
        int index = 0;
        for (int firstNameId = 0; firstNameId < FIRST_NAMES.length ; ++firstNameId) {
            for (int lastNameId = 0; lastNameId < LAST_NAMES.length; ++lastNameId) {
                firstAndLastNames[index][0] = firstNameId;
                firstAndLastNames[index][1] = lastNameId;
                ++index;
            }
        }
    }

    private int getRandomInt(int min, int max) {
        max = max - min + 1;
        return (int) (Math.random() * max) + min;
    }

    private String getRandomString(int len, int charMin, int charMax) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < len; ++i) {
            sb.append((char)getRandomInt(charMin, charMax));
        }
        return sb.toString();
    }

    public String genEmail(String firstName, String lastName) {
        StringBuilder emailBuilder = new StringBuilder();
        return emailBuilder.
                append(firstName.toLowerCase()).append(".").
                append(lastName.toLowerCase()).append("@").
                append(getRandomString(5, CHAR_SYMBOL_MIN, CHAR_SYMBOL_MAX)).
                append(".gmail.com").toString();
    }

    public Account genAccount() {
        if (maxNamesCount == 0) {
            throw new RuntimeException("No more possible unique accounts");
        }
        int pairId = getRandomInt(0, maxNamesCount - 1);
        String firstName = FIRST_NAMES[firstAndLastNames[pairId][0]];
        String lastName = LAST_NAMES[firstAndLastNames[pairId][1]];
        firstAndLastNames[pairId] = firstAndLastNames[maxNamesCount - 1];
        --maxNamesCount;

        return new Account(
                firstName,
                lastName,
                genEmail(firstName, lastName),
                getRandomString(5, CHAR_SYMBOL_MIN, CHAR_SYMBOL_MAX),
                getRandomString(3, CHAR_SYMBOL_MIN, CHAR_SYMBOL_MAX),
                getRandomString(3, CHAR_SYMBOL_MIN, CHAR_SYMBOL_MAX),
                getRandomInt(1, MAX_STATE_ID),
                getRandomString(5, CHAR_NUM_MIN, CHAR_NUM_MAX),
                21,
                getRandomString(9, CHAR_NUM_MIN, CHAR_NUM_MAX),
                getRandomString(3, CHAR_SYMBOL_MIN, CHAR_SYMBOL_MAX)
        );
    }

    public List<Account> genAccounts(int count) {
        if (count <= 0 || count > maxNamesCount) {
            throw new RuntimeException(String.format("There is %d possible unique accounts number left. Can't generate %d accounts", maxNamesCount, count));
        }
        List<Account> accounts = new ArrayList<>(count);
        for (int i = 0; i < count; ++i) {
            accounts.add(genAccount());
        }
        return accounts;
    }
}
