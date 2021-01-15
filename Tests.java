import java.io.PrintStream;
import java.io.UnsupportedEncodingException;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
public class Tests {

    // 
    public void testHanziCreation() {
        try {
            System.setOut(new PrintStream(System.out, true, "UTF-8"));
            Hanzi h1 = new Hanzi('汉', '漢', "han4", false, "Chinese ehtnicity");
            Hanzi h2 = new Hanzi('紅', '红', "hong2", false, "Red");
            Hanzi h3 = new Hanzi('樣', '样', "ㄧㄤˋ", true, "Style/fashion");
            Hanzi h4 = new Hanzi('过', '過', "ㄍㄨㄛˋ", true, "Chinese ehtnicity");
            System.out.printf("%s\n\n%s\n\n%s\n\n%s\n", h1,h2,h3,h4);
        } catch (UnsupportedEncodingException e) { e.printStackTrace(); }
    }

    // Working
    public void testConvertZhuyinString() {
        Conversions conv = new Conversions();
        String s1 = conv.convertZhuyinString("ㄉㄧㄢˋㄏㄨㄚˋ");
        String s2 = conv.convertZhuyinString("ㄓˉㄉㄠ˙");
        String s3 = conv.convertZhuyinString("ㄍㄟˇ ㄉㄚˇ ㄉㄧㄢˋㄏㄨㄚˋ");
        try {
            System.setOut(new PrintStream(System.out, true, "UTF-8"));
        } catch (UnsupportedEncodingException e) { e.printStackTrace(); }
        System.out.printf("%s\n%s\n%s\n", s1,s2,s3);
    }

    // Working
    public void testConvertPinyinString() {
        Conversions conv = new Conversions();
        String s1 = conv.convertPinyinString("dian4hua4");  // 電話
        String s2 = conv.convertPinyinString("zhi1dao");    // 知道
        String s3 = conv.convertPinyinString("gei3 da3 dian4hua4"); // 給打電話
        try {
            System.setOut(new PrintStream(System.out, true, "UTF-8"));
        } catch (UnsupportedEncodingException e) { e.printStackTrace(); }

        System.out.printf("%s\n%s\n%s\n", s1,s2,s3);

    }

    // Working
    public void testZhuyinModification() {
        Conversions con = new Conversions();
        try {
            System.setOut(new PrintStream(System.out, true, "UTF-8"));
            // test just getting the tone mark's number from a zhuyin string
            System.out.printf("ㄉㄠ˙ --> %s\n", con.getToneNumberFromZhuyin("ㄉㄠ˙")); // neutral tone
            System.out.printf("ㄎㄞ --> %s\n", con.getToneNumberFromZhuyin("ㄎㄞ")); // 1st tone without any tone marker (standard way to write it)
            System.out.printf("ㄌㄞˊ --> %s\n", con.getToneNumberFromZhuyin("ㄌㄞˊ")); // 2nd tone
            System.out.printf("ㄇㄞˇ --> %s\n", con.getToneNumberFromZhuyin("ㄇㄞˇ")); // 3rd tone
            System.out.printf("ㄘㄞˋ --> %s\n", con.getToneNumberFromZhuyin("ㄘㄞˋ")); // 4th tone
        } catch (UnsupportedEncodingException e) { e.printStackTrace(); }
    }

    // Working
    public void testGUI() {
        GUI gui = new GUI("OUTPUT.txt",true);
        gui.start();
    }

    // Not working, reading from stdin results in ? in place of the characters, even after changing the encoding of the inputstream to UTF-8
    public void testReadingChineseCharacters() {
        try {
            // Read from stdin but write to a file (which I know will recognize the characters if properly input)
            // System.setOut(new PrintStream(new File("OUTPUT.txt"), "UTF-8"));
            System.setOut(new PrintStream(System.out, true, "UTF-8"));
            Scanner scanner = new Scanner(System.in, "UTF-8");
            // System.out.printf("When input as a unicode directly: \u4E2D\n\n");
            // System.out.printf("When input as a string of chinese characters from keyboard: 中"); // this works fine
            System.out.printf("Enter a string to print or 'quit' to exit:");
            String input = scanner.nextLine();
            while (!input.equals("quit")) {
                System.out.printf("%s\n",input);
                input = scanner.nextLine();
            }
            scanner.close();
        } catch (UnsupportedEncodingException e) { e.printStackTrace(); } //catch (FileNotFoundException e) { e.printStackTrace(); }

        // Basically equivalent but more convaluted code
        // try {
        //     BufferedReader reader = new BufferedReader(new InputStreamReader(System.in, "UTF-8"));
        //     System.setOut(new PrintStream(new File("OUTPUT.txt"), "UTF-8"));
        //     try {
        //         String message = reader.readLine();
        //         while (!message.equals("quit")) {
        //             System.out.printf("%s\n", message);
        //             message = reader.readLine();
        //         }
        //     } catch (IOException e) {e.printStackTrace();}

        // } catch (UnsupportedEncodingException e) { e.printStackTrace(); } catch (FileNotFoundException e) { e.printStackTrace(); }
    }

    // Sorta working, doesn't display the Chinese characters unless input directly as UTF-8, but everything else is working fine
    public void testCreateWord() {
        try {
            System.setOut(new PrintStream(new File("OUTPUT.txt"), "UTF-8"));
            Word word = new Word("zhong1", "\u4E2D", "\u4E2D", false, "Middle");
            // Scanner scanner = new Scanner(System.in, "UTF-8");
            // System.out.printf("Enter pinyin:");
            // String pinyin = scanner.nextLine();
            // System.out.printf("Enter simplified Chinese characters:");
            // String simp = scanner.nextLine();
            // System.out.printf("Enter traditional Chinese characters:");
            // String trad = scanner.nextLine();
            // System.out.printf("Enter the English translation:");
            // String eng = scanner.nextLine();

            // Word word = new Word(pinyin, simp, trad, false, eng);

            // System.setOut(new PrintStream(System.out, true, "UTF-8"));
            // System.out.println(word.toString());
            // System.setOut(new PrintStream(new File("OUTPUT.txt"), "UTF-8"));
            System.out.println(word.toString());

            // scanner.close();
        } catch (UnsupportedEncodingException e) { e.printStackTrace(); } catch (FileNotFoundException e) { e.printStackTrace(); }
    }

    // Working
    public void testConvertZhuyin() {
        Conversions converter = new Conversions();
        try {
            System.setOut(new PrintStream(new File("OUTPUT.txt"), "UTF-8"));
            System.out.printf("\u310B\u3129\u02C7 --> %s\n", converter.convertZhuyin("\u310B\u3129\u02C7")); // passed
            System.out.printf("\u3113\u02C9 --> %s\n", converter.convertZhuyin("\u3113\u02C9")); // passed*
            System.out.printf("\u310C\u3128\u02CA --> %s\n", converter.convertZhuyin("\u310C\u3128\u02CA")); // passed
            System.out.printf("\u310E\u311E\u02C9 --> %s\n", converter.convertZhuyin("\u310E\u311E\u02C9")); // passed
            System.out.printf("\u3113\u3126\u02CB --> %s\n", converter.convertZhuyin("\u3113\u3126\u02CB")); // passed
            System.out.printf("\u310F\u3122\u02CB --> %s\n", converter.convertZhuyin("\u310F\u3122\u02CB")); // passed
            System.out.printf("\u3109\u3121\u02C9 --> %s\n", converter.convertZhuyin("\u3109\u3121\u02C9")); // passed
            System.out.printf("\u3129\u3122\u02CA --> %s\n", converter.convertZhuyin("\u3129\u3122\u02CA")); // passed
            System.out.printf("\u3112\u3127\u02D9 --> %s\n", converter.convertZhuyin("\u3112\u3127\u02D9")); // passed
            
        } catch (FileNotFoundException e) { e.printStackTrace(); } catch (UnsupportedEncodingException e) { e.printStackTrace(); }
    }

    // Working
    public void testGetToneNumberFromZhuyin() {
        Conversions converter = new Conversions();
        try {
            System.setOut(new PrintStream(new File("OUTPUT.txt"), "UTF-8"));
            System.out.printf("%s --> %d\n", "\u3109\u3120\u02D9", converter.getToneNumberFromZhuyin("\u3109\u3120\u02D9")); // neutral tone test
            System.out.printf("%s --> %d\n", "\u310A\u311A\u02C9", converter.getToneNumberFromZhuyin("\u310A\u311A\u02C9")); // 1st tone test
            System.out.printf("%s --> %d\n", "\u310F\u311C\u02CA", converter.getToneNumberFromZhuyin("\u310F\u311C\u02CA")); // 2nd tone test
            System.out.printf("%s --> %d\n", "\u310B\u3129\u02C7", converter.getToneNumberFromZhuyin("\u310B\u3129\u02C7")); // 3rd tone test
            System.out.printf("%s --> %d\n", "\u3110\u3127\u3120\u02CB", converter.getToneNumberFromZhuyin("\u3110\u3127\u3120\u02CB")); // 4th tone test
        } catch(FileNotFoundException e) { e.printStackTrace(); } catch(UnsupportedEncodingException e) { e.printStackTrace(); }
    }

    // Working
    public void testConvertZhuyinFinal() {
        Conversions converter = new Conversions();
        try {
            System.setOut(new PrintStream(new File("OUTPUT.txt"), "UTF-8"));
            System.out.printf("\u310B\u3129 --> %s\n", converter.convertZhuyinFinal("\u310B\u3129")); // passed
            System.out.printf("\u310B\u3128 --> %s\n", converter.convertZhuyinFinal("\u310B\u3128")); // passed
            System.out.printf("\u310C\u3128 --> %s\n", converter.convertZhuyinFinal("\u310C\u3128")); // passed
            System.out.printf("\u310C\u3129 --> %s\n", converter.convertZhuyinFinal("\u310C\u3129")); // passed
            System.out.printf("\u3119 --> %s\n", converter.convertZhuyinFinal("\u3119")); // passed
            System.out.printf("\u3116 --> %s\n", converter.convertZhuyinFinal("\u3116")); // passed
            System.out.printf("\u310D\u3120 --> %s\n", converter.convertZhuyinFinal("\u310D\u3120")); // passed
            System.out.printf("\u3126 --> %s\n", converter.convertZhuyinFinal("\u3126")); // passed
            System.out.printf("\u310F\u3122 --> %s\n", converter.convertZhuyinFinal("\u310F\u3122")); // passed
            
        } catch (UnsupportedEncodingException e) { e.printStackTrace(); } catch (FileNotFoundException e) { e.printStackTrace(); }
    }

    // Working
    public void testConvertZhuyinInitial() {
        Conversions converter = new Conversions();
        try {
            System.setOut(new PrintStream(new File("OUTPUT.txt"), "UTF-8"));
            String tests[] = {"\u3105\u311E", "\u310E\u3120", "\u310D\u311F", "\u3119"};
            for (String test : tests) {
                System.out.printf("%s --> %s\n", test, converter.convertZhuyinInitial(test));
            }
        } catch (FileNotFoundException e) { e.printStackTrace(); } catch (UnsupportedEncodingException e) { e.printStackTrace(); }
    }

    // Working
    public void testGetZhuyinInitial() {
        Conversions converter = new Conversions();
        try {
            System.setOut(new PrintStream(new File("OUTPUT.txt"), "UTF-8"));
            String tests[] = {"\u3105\u311E", "\u310E\u3120", "\u310D\u311F", "\u3119"};
            for (String test : tests) {
                System.out.printf("%s --> %s\n", test, converter.getZhuyinInitial(test));
            }
        } catch (FileNotFoundException e) { e.printStackTrace(); } catch (UnsupportedEncodingException e) { e.printStackTrace(); }
    }

    // Working
    public void testConvertIntToZhuyinToneMark() {
        Conversions converter = new Conversions();
        try {
            System.setOut(new PrintStream(new File("OUTPUT.txt"), "UTF-8"));
            System.out.printf("0 --> %s\n", converter.convertIntToZhuyinToneMark(0));
            System.out.printf("1 --> %s\n", converter.convertIntToZhuyinToneMark(1));
            System.out.printf("2 --> %s\n", converter.convertIntToZhuyinToneMark(2));
            System.out.printf("3 --> %s\n", converter.convertIntToZhuyinToneMark(3));
            System.out.printf("4 --> %s\n", converter.convertIntToZhuyinToneMark(4));
        } catch (FileNotFoundException e) { e.printStackTrace(); } catch (UnsupportedEncodingException e) { e.printStackTrace(); }
    }
    
    // Expand to test more tricky cases
    // Working
    public void testConvertPinyin() {
        Conversions converter = new Conversions();
        try {
            System.setOut(new PrintStream(new File("OUTPUT.txt"), "UTF-8"));
            
            // Test the neutral tone
            System.out.printf("5 test cases for syllables with the neutral tone\n");
            System.out.printf("piao --> %s\n", converter.convertPinyin("piao"));
            System.out.printf("zhao --> %s\n", converter.convertPinyin("zhao"));
            System.out.printf("deng --> %s\n", converter.convertPinyin("deng"));
            System.out.printf("mo --> %s\n", converter.convertPinyin("mo"));
            System.out.printf("a --> %s\n", converter.convertPinyin("a"));
            // Extra tests, all are special pinyin cases
            System.out.printf("zher --> %s\n", converter.convertPinyin("zher"));
            System.out.printf("shi --> %s\n", converter.convertPinyin("shi"));
            System.out.printf("xi --> %s\n", converter.convertPinyin("xi"));
            // System.out.printf(" --> %s\n", converter.convertPinyin(""));
            // System.out.printf(" --> %s\n", converter.convertPinyin(""));

            // Test the first tone
            System.out.printf("5 test cases for syllables with the first tone\n");
            System.out.printf("xi1 --> %s\n", converter.convertPinyin("xi1"));
            System.out.printf("zhi1 --> %s\n", converter.convertPinyin("zhi1"));
            System.out.printf("mao1 --> %s\n", converter.convertPinyin("mao1"));
            System.out.printf("qi1 --> %s\n", converter.convertPinyin("qi1"));
            System.out.printf("yin1 --> %s\n", converter.convertPinyin("yin1"));
            // Extra tests, all are special pinyin cases
            System.out.printf("zher1 --> %s\n", converter.convertPinyin("zher1"));
            System.out.printf("shi1 --> %s\n", converter.convertPinyin("shi1"));
            System.out.printf("xi1 --> %s\n", converter.convertPinyin("xi1"));

            // Test the second tone
            System.out.printf("5 test cases for syllables with the second tone\n");
            System.out.printf("qiu2 --> %s\n", converter.convertPinyin("qiu2"));
            System.out.printf("guo2 --> %s\n", converter.convertPinyin("guo2"));
            System.out.printf("xi2 --> %s\n", converter.convertPinyin("xi2"));
            System.out.printf("lu2 --> %s\n", converter.convertPinyin("lu2"));
            System.out.printf("l\u00FC2 --> %s\n", converter.convertPinyin("l\u00FC2"));
            // Extra tests, all are special pinyin cases
            System.out.printf("zher2 --> %s\n", converter.convertPinyin("zher2"));
            System.out.printf("shi2 --> %s\n", converter.convertPinyin("shi2"));
            System.out.printf("xi2 --> %s\n", converter.convertPinyin("xi2"));

            // Test the third tone
            System.out.printf("5 test cases for syllables with the third tone\n");
            System.out.printf("n\u00FC3 --> %s\n", converter.convertPinyin("n\u00FC3"));
            System.out.printf("qi3 --> %s\n", converter.convertPinyin("qi3"));
            System.out.printf("gei3 --> %s\n", converter.convertPinyin("gei3"));
            System.out.printf("wo3 --> %s\n", converter.convertPinyin("wo3"));
            System.out.printf("hao3 --> %s\n", converter.convertPinyin("hao3"));
            // Extra tests, all are special pinyin cases
            System.out.printf("zher3 --> %s\n", converter.convertPinyin("zher3"));
            System.out.printf("shi3 --> %s\n", converter.convertPinyin("shi3"));
            System.out.printf("xi3 --> %s\n", converter.convertPinyin("xi3"));

            // Test the fourth tone
            System.out.printf("5 test cases for syllables with the fourth tone\n");
            System.out.printf("dan4 --> %s\n", converter.convertPinyin("dan4"));
            System.out.printf("qi4 --> %s\n", converter.convertPinyin("qi4"));
            System.out.printf("yao4 --> %s\n", converter.convertPinyin("yao4"));
            System.out.printf("wai4 --> %s\n", converter.convertPinyin("wai4"));
            System.out.printf("ri4 --> %s\n", converter.convertPinyin("ri4"));
            // Extra tests, all are special pinyin cases
            System.out.printf("zher4 --> %s\n", converter.convertPinyin("zher4"));
            System.out.printf("shi4 --> %s\n", converter.convertPinyin("shi4"));
            System.out.printf("xi4 --> %s\n", converter.convertPinyin("xi4"));

        } catch (FileNotFoundException e) { e.printStackTrace(); } catch (UnsupportedEncodingException e) { e.printStackTrace(); }
    }

    // Working
    // Test the transcription mappings
    public void testMappings() {
        Conversions converter = new Conversions();
        try {
            System.setOut(new PrintStream(new File("OUTPUT.txt"), "UTF-8"));
            System.out.printf("Mapping from pinyin --> zhuyin.\n");
            converter.printMap(converter.pinyinToZhuyinSingleCharacter);
            converter.printMap(converter.pinyinToZhuyinDoubleCharacter);
            System.out.printf("\n\nMapping from zhuyin --> pinyin\n");
            converter.printMap(converter.zhuyinToPinyinSingleCharacter);
            converter.printMap(converter.zhuyinToPinyinDoubleCharacter);
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
