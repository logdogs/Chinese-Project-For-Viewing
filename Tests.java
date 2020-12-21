import java.io.PrintStream;
import java.io.UnsupportedEncodingException;
import java.io.File;
import java.io.FileNotFoundException;
public class Tests {

    //
    // Test the transcription mappings
    public void testMappings() {
        Conversions converter = new Conversions();
        try {
            System.setOut(new PrintStream(new File("OUTPUT.txt"), "UTF-8"));
            System.out.printf("Mapping from pinyin --> zhuyin.\n");
            converter.printMap(converter.pinyinToZhuyinSingleCharacter);
            converter.printMap3(converter.pinyinToZhuyinDoubleCharacter);
            System.out.printf("\n\nMapping from zhuyin --> pinyin\n");
            converter.printMap(converter.zhuyinToPinyinSingleCharacter);
            converter.printMap2(converter.zhuyinToPinyinDoubleCharacter);
        } catch (FileNotFoundException e) { e.printStackTrace(); } catch (UnsupportedEncodingException e) { e.printStackTrace(); }
    }

    // Working
    public void testZhuyinSets() {
        Conversions converter = new Conversions();
        try {
            System.setOut(new PrintStream(new File("OUTPUT.txt"),"UTF-8"));
            converter.printArrayList(converter.zhuyinInitials);
        } catch (FileNotFoundException e) { e.printStackTrace(); } catch (UnsupportedEncodingException e) { e.printStackTrace(); }
    }

    // Working
    public void testConvertNumericPinyin() {
        Conversions converter = new Conversions();

        String syllable0 = "shao";
        String syllable1 = "dao1";
        String syllable2 = "tang2";
        String syllable3 = "deng3";
        String syllable4 = "zai4";

        String converted0 = converter.convertNumericPinyin(syllable0);
        String converted1 = converter.convertNumericPinyin(syllable1);
        String converted2 = converter.convertNumericPinyin(syllable2);
        String converted3 = converter.convertNumericPinyin(syllable3);
        String converted4 = converter.convertNumericPinyin(syllable4);

        try {
            System.setOut(new PrintStream(new File("OUTPUT.txt"),"UTF-8"));
            System.out.printf("%s --> %s\n", syllable0, converted0);
            System.out.printf("%s --> %s\n", syllable1, converted1);
            System.out.printf("%s --> %s\n", syllable2, converted2);
            System.out.printf("%s --> %s\n", syllable3, converted3);
            System.out.printf("%s --> %s\n", syllable4, converted4);
    
            System.out.printf("\n\n");
    
            System.out.printf("(%s == %s) = %b\n", syllable0, converted0, syllable0.equals(converted0));
            System.out.printf("(%s == %s) = %b\n", syllable1.substring(0,syllable1.length()-1), converted1, syllable1.substring(0,syllable1.length()-1).equals(converted1));
            System.out.printf("(%s == %s) = %b\n", syllable2.substring(0,syllable1.length()-1), converted2, syllable2.substring(0,syllable2.length()-1).equals(converted2));
            System.out.printf("(%s == %s) = %b\n", syllable3.substring(0,syllable1.length()-1), converted3, syllable3.substring(0,syllable3.length()-1).equals(converted3));
            System.out.printf("(%s == %s) = %b\n", syllable4.substring(0,syllable1.length()-1), converted4, syllable4.substring(0,syllable4.length()-1).equals(converted4));

        } catch (FileNotFoundException e) {
        } catch (UnsupportedEncodingException e) {
        }
    }

    // Working
    // Test the diacritic marked letters
    public void testToneSets() {
        Conversions converter = new Conversions();
        File f = new File("OUTPUT.txt");
        try {
            PrintStream strm = new PrintStream(f,"UTF-8");
            System.setOut(strm);

            for (Character chr : converter.aTones) {
                System.out.printf("%c\n",chr);
            }
            System.out.printf("\n\n");
            for (Character chr : converter.eTones) {
                System.out.printf("%c\n",chr);
            }
            System.out.printf("\n\n");
            for (Character chr : converter.iTones) {
                System.out.printf("%c\n",chr);
            }
            System.out.printf("\n\n");
            for (Character chr : converter.oTones) {
                System.out.printf("%c\n",chr);
            }
            System.out.printf("\n\n");
            for (Character chr : converter.uTones) {
                System.out.printf("%c\n",chr);
            }
            System.out.printf("\n\n");
            for (Character chr : converter.uUmlautTones) {
                System.out.printf("%c\n",chr);
            }
            strm.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
    }

    // Working
    // Tests to see if a couple of test strings are in the diacriticAtX sets
    public void testDiacriticSets() {
        Conversions converter = new Conversions();

        String testFinal1 = "ao";
        String testFinal2 = "ing";
        String testFinal3 = "uang";

        // Test 1
        if (converter.pinyinDiacriticAt0.contains(testFinal1)) {
            System.out.println("'ao' is contained in the diacritic at 0 set.\n");
        } else if (converter.pinyinDiacriticAt1.contains(testFinal1)) {
            System.out.println("'ao' is contained in the diacritic at 1 set.\n");
        } else {
            System.out.println("'ao' is not contained in either set.\n");
        }

        // Test 2
        if (converter.pinyinDiacriticAt0.contains(testFinal2)) {
            System.out.println("'ing' is contained in the diacritic at 0 set.\n");
        } else if (converter.pinyinDiacriticAt1.contains(testFinal2)) {
            System.out.println("'ing' is contained in the diacritic at 1 set.\n");
        } else {
            System.out.println("'ing' is not contained in either set.\n");
        }

        // Test 3
        if (converter.pinyinDiacriticAt0.contains(testFinal3)) {
            System.out.println("'uang' is contained in the diacritic at 0 set.\n");
        } else if (converter.pinyinDiacriticAt1.contains(testFinal3)) {
            System.out.println("'uang' is contained in the diacritic at 1 set.\n");
        } else {
            System.out.println("'uang' is not contanied in either set.\n");
        }
    }

    // Working
    // Test to make sure that the encodings sets are working properly (because I'm paranoid)
    public void testCharacterEncodings() {
        Conversions converter = new Conversions();

        Character testString1 = '\u00FC';
        Character testString2 = '\u00E0';
        Character testString3 = 'a';

        // Test 1
        if (testString1.equals(converter.uUmlautTones.get(0))) {
            System.out.printf("Test character 1, '\u00FC', passed the test.\nCharacter literal matched the UTF-8 encoding.\n\n");
        } else {
            System.out.printf("Test character 1, '\u00FC', failed the test.\nCharacter literal did not match the UTF-8 encoding.\n\n");
        }

        // Test 2
        if (testString2.equals(converter.aTones.get(4))) {
            System.out.printf("Test character 2, '\u00E0', passed the test.\nCharacter literal matched the UTF-8 encoding.\n\n");
        } else {
            System.out.printf("Test character 2, '\u00E0', failed the test.\nCharacter literal did not match the UTF-8 encoding.\n\n");
        }

        // Test 3
        if (testString3.equals(converter.aTones.get(0))) {
            System.out.printf("Test character 3, 'a', passed the test.\nCharacter literal matched the UTF-8 encoding.\n\n");
        } else {
            System.out.printf("Test character 3, 'a', failed the test.\nCharacter literal did not match the UTF-8 encoding.\n\n");
        }
    }

    // Working
    // Display the sets I've made to make sure everything's where it should be (again because I'm paranoid)
    public void testSets() {
        Conversions con = new Conversions();
        con.printArrayList(con.specialPinyin0);
        con.printArrayList(con.specialPinyin1);
        con.printArrayList(con.specialPinyin2);
        con.printArrayList(con.specialPinyin3);
        con.printArrayList(con.specialPinyin4);
        con.printArrayList(con.pinyinInitials);
        con.printArrayList(con.pinyinFinals0);
        con.printArrayList(con.pinyinFinals1);
        con.printArrayList(con.pinyinFinals2);
        con.printArrayList(con.pinyinFinals3);
        con.printArrayList(con.pinyinFinals4);
        con.printArrayList(con.pinyinDiacriticAt0);
        con.printArrayList(con.pinyinDiacriticAt1);
    }

    // Working
    // Test the function that chops the pinyin final off
    public void testGetPinyinFinal() {
        Conversions con = new Conversions();
        String syl1 = "dao";
        String syl2 = "ping";
        String syl3 = "ci";
        String syl4 = "shuo";
        String syl5 = "yao";
        String syl6 = "wo";
        System.out.printf("Final of 'dao' is %s\n",con.getPinyinFinal(syl1));
        System.out.printf("Final of 'ping' is %s\n",con.getPinyinFinal(syl2));
        System.out.printf("Final of 'ci' is %s\n",con.getPinyinFinal(syl3));
        System.out.printf("Final of 'shuo' is %s\n",con.getPinyinFinal(syl4));
        System.out.printf("Final of 'yao' is %s\n",con.getPinyinFinal(syl5));
        System.out.printf("Final of 'wo' is %s\n",con.getPinyinFinal(syl6));
    }
}
