Evan Cohen-Doty
12/15/2020

***** OVERALL DESCRIPTION *****

*******************************

***** HOW TO RUN *****

**********************

***** ASSUMPTIONS AND DEFINITIONS *****
    Because the pinyin system, like any nonphoenetic writing system, has exceptions, we have the following assumptions:
        if a pinyin syllable begins with either 'w' or 'y', we treat the entire syllable as a final and we treat the initial for the
            syllable as if it were the empty string (i.e. "").
***************************************

***** Driver.java *****

***********************

***** Word.java *****

*********************

***** Conversions.java *****
    Contains all the universal data and algorithms to convert from different formats. This includes 
        1. Zhuyin --> Pinyin
        2. Pinyin --> Zhuyin
****************************

***** NEED TO ADD *****
    1. Support for pinyin that ends in consonant + r (i.e. dianr)
    2. Support for pinyin whose final is ar (i.e. nar)
    This is tricky, because in some cases the r is optional (as in dianr), but in others it's essential (as in nar)
***********************

***** TESTS TO TRY *****
    1. dianr, wanr, kongr 
    2. wanr
    3. kongr
    4. xi
    5. shi, zhi, chi
    6. shir