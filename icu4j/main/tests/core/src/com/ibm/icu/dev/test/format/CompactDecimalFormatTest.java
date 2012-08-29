/*
 *******************************************************************************
 * Copyright (C) 1996-2012, Google, International Business Machines Corporation and
 * others. All Rights Reserved.                                                *
 *******************************************************************************
 */
package com.ibm.icu.dev.test.format;

import com.ibm.icu.dev.test.TestFmwk;
import com.ibm.icu.text.CompactDecimalFormat;
import com.ibm.icu.text.NumberFormat;
import com.ibm.icu.text.NumberFormat.CompactStyle;
import com.ibm.icu.util.ULocale;

public class CompactDecimalFormatTest extends TestFmwk {

    public static void main(String[] args) {
        new CompactDecimalFormatTest().run(args);
    }

    Object[][] EnglishTestData = {
            // default is 2 digits of accuracy
            {0.0d, "0.0"},
            {0.1d, "0.1"},
            {1d, "1"},
            {1234, "1.2K"},
            {12345, "12K"},
            {123456, "120K"},
            {1234567, "1.2M"},
            {12345678, "12M"},
            {123456789, "120M"},
            {1234567890, "1.2B"},
            {12345678901f, "12B"},
            {123456789012f, "120B"},
            {1234567890123f, "1.2T"},
            {12345678901234f, "12T"},
            {123456789012345f, "120T"},
            {12345678901234567890f, "12000000T"},
    };
    
    Object[][] SerbianTestData = {
            {1234, "1200"},
            {12345, "12 хиљ"},
            {123456, "120 хиљ"},
            {1234567, "1,2 мил"},
            {12345678, "12 мил"},
            {123456789, "120 мил"},
            {1234567890, "1,2 млрд"},
            {12345678901f, "12 млрд"},
            {123456789012f, "120 млрд"},
            {1234567890123f, "1,2 бил"},
            {12345678901234f, "12 бил"},
            {123456789012345f, "120 бил"},
    };

    Object[][] JapaneseTestData = {
            {1234, "1200"},
            {12345, "1.2万"},
            {123456, "12万"},
            {1234567, "120万"},
            {12345678, "1200万"},
            {123456789, "1.2億"},
            {1234567890, "12億"},
            {12345678901f, "120億"},
            {123456789012f, "1200億"},
            {1234567890123f, "1.2兆"},
            {12345678901234f, "12兆"},
            {123456789012345f, "120兆"},
    };

    Object[][] SwahiliTestData = {
            {1234, "elfu\u00a01.2"},
            {12345, "elfu\u00a012"},
            {123456, "laki1.2"},
            {1234567, "M1.2"},
            {12345678, "M12"},
            {123456789, "M120"},
            {1234567890, "B1.2"},
            {12345678901f, "B12"},
            {123456789012f, "B120"},
            {1234567890123f, "T1.2"},
            {12345678901234f, "T12"},
            {12345678901234567890f, "T12000000"},
    };

    public void TestEnglish() {
        checkLocale(ULocale.ENGLISH, EnglishTestData);
    }
    
    public void TestSerbian() {
        checkLocale(ULocale.forLanguageTag("sr"), SerbianTestData);
    }

    public void TestJapanese() {
//         checkLocale(ULocale.JAPANESE, JapaneseTestData); Not decided yet by CLDR TC.
         checkLocale(ULocale.JAPANESE, EnglishTestData);
    }

    public void TestJapaneseGermany() {
        // check fallback.
//        checkLocale(ULocale.forLanguageTag("ja-DE"), JapaneseTestData); Not decided yet by CLDR TC.
        checkLocale(ULocale.forLanguageTag("ja-DE"), EnglishTestData);
    }

    public void TestSwahili() {
        checkLocale(ULocale.forLanguageTag("sw"), SwahiliTestData);
    }

    public void checkLocale(ULocale locale, Object[][] testData) {
        CompactDecimalFormat cdf = NumberFormat.getCompactDecimalInstance(locale, CompactStyle.SHORT);
        for (Object[] row : testData) {
            assertEquals(locale + " (" + locale.getDisplayName(locale) + ")", row[1], cdf.format(row[0]));
        }
    }
}
