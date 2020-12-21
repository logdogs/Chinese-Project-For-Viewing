import java.util.HashMap;
import java.util.ArrayList;
public class Conversions {
    // All initials and finals separated
    ArrayList<String> pinyinInitials;
    ArrayList<String> pinyinFinals0;
    ArrayList<String> pinyinFinals1;
    ArrayList<String> pinyinFinals2;
    ArrayList<String> pinyinFinals3;
    ArrayList<String> pinyinFinals4;
    ArrayList<Character> allZhuyin;
    ArrayList<String> zhuyinInitials;
    ArrayList<String> zhuyinFinals;
    // Special ones
    ArrayList<String> specialPinyin0;
    ArrayList<String> specialPinyin1;
    ArrayList<String> specialPinyin2;
    ArrayList<String> specialPinyin3;
    ArrayList<String> specialPinyin4;

    // For conversions between transcription systems
    HashMap<String,String> zhuyinToPinyinSingleCharacter;
    HashMap<String,String> pinyinToZhuyinSingleCharacter;
    HashMap<String,ArrayList<String>> zhuyinToPinyinDoubleCharacter;
    HashMap<ArrayList<String>,String> pinyinToZhuyinDoubleCharacter;

    // Purely for the convenience of the programmer and user
    // This takes the easily typed pinyin and converts it to the easily read pinyin
    // Format of the easily typed pinyin is:
    //      X#, where X is some syllable and # is some integer in {1,2,3,4} or the empty string
    HashMap<String,String> numericToDiacritic;

    // Integers representing where in a given pinyin final a diacritic mark is
    ArrayList<String> pinyinDiacriticAt0;
    ArrayList<String> pinyinDiacriticAt1;

    // Letters with diacritic marks to make conversions easier
    ArrayList<Character> aTones;
    ArrayList<Character> eTones;
    ArrayList<Character> iTones;
    ArrayList<Character> oTones;
    ArrayList<Character> uTones;
    ArrayList<Character> uUmlautTones;
    ArrayList<Character> zhuyinTones;

    // Constructor
    public Conversions() {
        specialPinyin1 = new ArrayList<String>();
        specialPinyin2 = new ArrayList<String>();
        specialPinyin3 = new ArrayList<String>();
        specialPinyin4 = new ArrayList<String>();
        specialPinyin0 = new ArrayList<String>() {{
            add("yi");
            add("ya");
            add("yan");
            add("yang");
            add("yao");
            add("ye");
            add("yin");
            add("ying");
            add("yong");
            add("you");
            add("wu");
            add("wa");
            add("wai");
            add("wan");
            add("wang");
            add("wei");
            add("wen");
            add("weng");
            add("wo");
            add("yu");
            add("yuan");
            add("yue");
            add("yun");
        }};
        for (String special : specialPinyin0) {
            specialPinyin1.add(special + "1");
            specialPinyin2.add(special + "2");
            specialPinyin3.add(special + "3");
            specialPinyin4.add(special + "4");
        }
        // Need to be very careful with this
        this.pinyinInitials = new ArrayList<String>(10) {{
            add("b");
            add("p");
            add("m");
            add("f");
            add("d");
            add("t");
            add("n");
            add("l");
            add("g");
            add("k");
            add("h");
            add("j");
            add("q");
            add("x");
            add("z");
            add("c");
            add("s");
            add("zh");
            add("ch");
            add("sh");
            add("r");
        }};
        // Be very careful with this
        this.pinyinFinals1 = new ArrayList<String>();
        this.pinyinFinals2 = new ArrayList<String>();
        this.pinyinFinals3 = new ArrayList<String>();
        this.pinyinFinals4 = new ArrayList<String>();
        this.pinyinFinals0 = new ArrayList<String>(){{
            add("a");
            add("ai");
            add("an");
            add("ang");
            add("ao");
            add("e");
            add("ei");
            add("en");
            add("eng");
            add("er");
            add("i");
            add("ia");
            add("ian");
            add("iang");
            add("iao");
            add("ie");
            add("in");
            add("ing");
            add("iong");
            add("iou");
            add("iu");
            add("o");
            add("ong");
            add("ou");
            add("u");
            add("ua");
            add("uai");
            add("uan");
            add("uang");
            add("uei");
            add("ui");
            add("uen");
            add("un");
            add("ueng");
            add("uo");
            add("\u00FC");
            add("\u00FCn");
            add("\u00FCan");
            add("\u00FCe");
        }};
        for (String fin : pinyinFinals0) {
            this.pinyinFinals1.add(fin + "1");
            this.pinyinFinals2.add(fin + "2");
            this.pinyinFinals3.add(fin + "3");
            this.pinyinFinals4.add(fin + "4");
        }
        this.zhuyinInitials = new ArrayList<String>();
        this.zhuyinFinals = new ArrayList<String>();

        // Initialize the indexes of all diacritic marks for finals
        pinyinDiacriticAt0 = new ArrayList<String>() {{
            add("a");
            add("ai");
            add("an");
            add("ang");
            add("ao");
            add("e");
            add("ei");
            add("en");
            add("eng");
            add("er");
            add("i");
            add("in");
            add("ing");
            add("o");
            add("ong");
            add("ou");
            add("u");
            add("un");
            add("ü");
            add("ün");
        }};
        pinyinDiacriticAt1 = new ArrayList<String>() {{
            add("ia");
            add("ian");
            add("iang");
            add("iao");
            add("ie");
            add("iong");
            add("iu");
            add("ua");
            add("uai");
            add("uan");
            add("uang");
            add("ui");
            add("uo");
            add("ue");
            add("üe");
        }};

        // Initialize the diacritic arrays for easy access and manipulation
        this.aTones = new ArrayList<Character>(){{
            // Lower case
            add('a');
            add('\u0101');
            add('\u00E1');
            add('\u01CE');
            add('\u00E0');
            // Upper case
            add('A');
            add('\u0100');
            add('\u00C1');
            add('\u01CD');
            add('\u00C0');
        }};
        this.eTones = new ArrayList<Character>() {{
            // Lower case
            add('e');
            add('\u0113');
            add('\u00E9');
            add('\u011B');
            add('\u00E8');
            // Upper case
            add('E');
            add('\u0112');
            add('\u00C9');
            add('\u011A');
            add('\u00C8');
        }};
        this.iTones = new ArrayList<Character>(){{
            // Lower case
            add('i');
            add('\u012B');
            add('\u00ED');
            add('\u01D0');
            add('\u00EC');
            // Upper case
            add('I');
            add('\u012A');
            add('\u00CD');
            add('\u01CF');
            add('\u00CC');
        }};
        this.oTones = new ArrayList<Character>(){{
            // Lower case
            add('o');
            add('\u014D');
            add('\u00F3');
            add('\u01D2');
            add('\u00F2');
            // Upper case
            add('O');
            add('\u014C');
            add('\u00D3');
            add('\u01D1');
            add('\u00D2');
        }};
        this.uTones = new ArrayList<Character>(){{
            // Lower case
            add('u');
            add('\u016B');
            add('\u00FA');
            add('\u01D4');
            add('\u00F9');
            // Upper case
            add('U');
            add('\u016A');
            add('\u00DA');
            add('\u01D3');
            add('\u00D9');
        }};
        this.uUmlautTones = new ArrayList<Character>(){{
            // Lower case
            add('\u00FC');
            add('\u01D6');
            add('\u01D8');
            add('\u01DA');
            add('\u01DC');
            // Upper case
            add('\u00DC');
            add('\u01D5');
            add('\u01D7');
            add('\u01D9');
            add('\u01DB');
        }};

        // Named as such because they'll only ever be used for zhuyin characters
        this.zhuyinTones = new ArrayList<Character>(){{
            add('\u02C9');
            add('\u02CA');
            add('\u02C7');
            add('\u02CB');
            add('\u02D9');
        }};

        // All the zhuyin letters
        this.allZhuyin = new ArrayList<Character>();
        for (char c = '\u3105'; c <= '\u312F'; c += '\u0001') {
            this.allZhuyin.add(c);
        }
        // Add all the initials
        for (char c = '\u3105'; c <= '\u3119'; c += '\u0001') {
            this.zhuyinInitials.add(Character.toString(c));
        }
        // Add the zhuyin finals that are single characters
        for (char c = '\u311A'; c <= '\u3129'; c += '\u0001') {
            this.zhuyinFinals.add(Character.toString(c));
        }
        // Add the zhuyin finals that aren't single characters
        this.zhuyinFinals.add("\u3127\u311A");
        this.zhuyinFinals.add("\u3127\u311D");
        this.zhuyinFinals.add("\u3127\u3120");
        this.zhuyinFinals.add("\u3127\u3121");
        this.zhuyinFinals.add("\u3127\u3122");
        this.zhuyinFinals.add("\u3127\u3123");
        this.zhuyinFinals.add("\u3127\u3124");
        this.zhuyinFinals.add("\u3127\u3125");
        this.zhuyinFinals.add("\u3128\u311A");
        this.zhuyinFinals.add("\u3128\u311B");
        this.zhuyinFinals.add("\u3128\u311E");
        this.zhuyinFinals.add("\u3128\u311F");
        this.zhuyinFinals.add("\u3128\u3122");
        this.zhuyinFinals.add("\u3128\u3123");
        this.zhuyinFinals.add("\u3128\u3124");
        this.zhuyinFinals.add("\u3128\u3125");
        this.zhuyinFinals.add("\u3129\u311D");
        this.zhuyinFinals.add("\u3129\u3122");
        this.zhuyinFinals.add("\u3129\u3123");
        this.zhuyinFinals.add("\u3129\u3125");

        // Both are 1:1 correspondences, so this is very straightforward 
        // The second one is done simply by reversing it, mainly to avoid polluting the code any more than it already is
        this.zhuyinToPinyinSingleCharacter = new HashMap<String,String>() {{
            // First the single character ones
            put("\u3105","b");
            put("\u3106","p");
            put("\u3107","m");
            put("\u3108","f");
            put("\u3109","d");
            put("\u310A","t");
            put("\u310B","n");
            put("\u310C","l");
            put("\u310D","g");
            put("\u310E","k");
            put("\u310F","h");
            put("\u3110","j");
            put("\u3111","q");
            put("\u3112","x");
            put("\u3113","zh");
            put("\u3114","ch");
            put("\u3115","sh");
            put("\u3116","r");
            put("\u3117","z");
            put("\u3118","c");
            put("\u3119","s");
            put("\u311A","a");
            put("\u311B","o");
            put("\u311C","e");
            // put("\u311D","eh"); // This one does not need to be included in the mapping because \u311D is never used on its own
            put("\u311E","ai");
            put("\u311F","ei");
            put("\u3120","ao");
            put("\u3121","ou");
            put("\u3122","an");
            put("\u3123","en");
            put("\u3124","ang");
            put("\u3125","eng");
            put("\u3126","er");
            put("\u3127","i");
            put("\u3128","u");
            put("\u3129","\u00FC");
        }};
        this.pinyinToZhuyinSingleCharacter = new HashMap<String,String>();
        for (HashMap.Entry<String,String> pair : this.zhuyinToPinyinSingleCharacter.entrySet()) {
            pinyinToZhuyinSingleCharacter.put(pair.getValue(), pair.getKey());
        }

        // Now we do the double character mappings
        this.pinyinToZhuyinDoubleCharacter = new HashMap<ArrayList<String>,String>(){{
            put(new ArrayList<String>(){{add("ia");add("ya");}}, "\u3127\u311A");
            put(new ArrayList<String>(){{add("ie");add("ye");}}, "\u3127\u311D");
            put(new ArrayList<String>(){{add("iao");add("yao");}}, "\u3127\u3120");
            put(new ArrayList<String>(){{add("iu");add("you");}}, "\u3127\u3121");
            put(new ArrayList<String>(){{add("ian");add("yan");}}, "\u3127\u3122");
            put(new ArrayList<String>(){{add("in");add("yin");}}, "\u3127\u3123");
            put(new ArrayList<String>(){{add("iang");add("yang");}}, "\u3127\u3124");
            put(new ArrayList<String>(){{add("ing");add("ying");}}, "\u3127\u3125");
            put(new ArrayList<String>(){{add("ua");add("wa");}}, "\u3128\u311A");
            put(new ArrayList<String>(){{add("uo");add("wo");}}, "\u3128\u311B");
            put(new ArrayList<String>(){{add("uai");add("wai");}}, "\u3128\u311E");
            put(new ArrayList<String>(){{add("ui");add("wei");}}, "\u3128\u311F");
            put(new ArrayList<String>(){{add("uan");add("wan");}}, "\u3128\u3122");
            put(new ArrayList<String>(){{add("un");add("wen");}}, "\u3128\u3123");
            put(new ArrayList<String>(){{add("uang");add("wang");}}, "\u3128\u3124");
            put(new ArrayList<String>(){{add("weng");}}, "\u3128\u3125");
            put(new ArrayList<String>(){{add("\u00FCe");add("y\u00FCe");}}, "\u3129\u311D");
            put(new ArrayList<String>(){{add("\u00FCan");add("y\u00FCan");}}, "\u3129\u3122");
            put(new ArrayList<String>(){{add("\u00FCn");add("y\u00FCn");}}, "\u3129\u3123");
            put(new ArrayList<String>(){{add("ong");}}, "\u3128\u3125");
        }};
        this.zhuyinToPinyinDoubleCharacter = new HashMap<String,ArrayList<String>>();
        for (HashMap.Entry<ArrayList<String>,String> pair : this.pinyinToZhuyinDoubleCharacter.entrySet()) {
            this.zhuyinToPinyinDoubleCharacter.put(pair.getValue(), pair.getKey());
        }
    }

    // Helper function that tests if there is an initial, because the initial can be the empty string (i.e. there isn't one)
    //      or it can be an semi-vowel (y or w)
    public boolean irregularPinyinInitial(String pinyin) {
        // return true if first letter belongs to {y,e,w,a,o}
        switch(pinyin.substring(0,1)) {
            case "y":
            case "e":
            case "w":
            case "a":
            case "o":
                return true;
            default:
                return false;
        }
    }

    // Helper function for testing
    public void printArrayList(ArrayList<String> arraylist) {
        for (String str : arraylist) {
            System.out.printf("%s  ", str);
        }
        System.out.printf("\n\n");
    }
    // Helper functions to print a map, I didn't feel like making them generic
    public void printMap(HashMap<String,String> map) {
        for (HashMap.Entry<String,String> pair : map.entrySet()) {
            System.out.printf("%s --> %s\n", pair.getKey(),pair.getValue());
        }
        System.out.printf("\n\n");
    }
    public void printMap2(HashMap<String,ArrayList<String>> map) {
        
        for (HashMap.Entry<String,ArrayList<String>> pair : map.entrySet()) {
            System.out.printf("%s --> { ", pair.getKey());
            for (String pinyin : pair.getValue()) {
                System.out.printf("%s ",pinyin);
            }
            System.out.printf("}\n");
        }
        System.out.printf("\n\n");
    }
    public void printMap3(HashMap<ArrayList<String>,String> map) {
        for (HashMap.Entry<ArrayList<String>,String> pair : map.entrySet()) {
            System.out.printf("{ ");
            for (String pinyin : pair.getKey()) {
                System.out.printf("%s ", pinyin);
            }
            System.out.printf("} --> %s\n", pair.getValue());
        }
        System.out.printf("\n\n");
    }

    // Helper function for converting from numeric pinyin to diacritic pinyin
    public String getPinyinFinal(String syllable) {
        String fin;
        // First split between the initial and final
        //      Note that in all 2 character initials, the second character is 'h'
        if (syllable.charAt(1) == 'h') {
            switch (syllable.charAt(0)) {
                case 'z':
                    fin = syllable.substring(2);
                    break;
                case 'c':
                    fin = syllable.substring(2);
                    break;
                case 's':
                    fin = syllable.substring(2);
                    break;
                default:
                    fin = "";
                    System.out.printf("ERROR INVALID PINYIN GIVEN\n");
                    System.exit(0);
            }
        } else { 
            switch(syllable.charAt(0)) {
                case 'b':
                case 'p':
                case 'm':
                case 'f':
                case 'd':
                case 't':
                case 'n':
                case 'l':
                case 'g':
                case 'k':
                case 'h':
                case 'j':
                case 'q':
                case 'x':
                case 'z':
                case 'c':
                case 's':
                    fin = syllable.substring(1);
                    break;
                case 'y':
                case 'w':
                    fin = syllable;
                    break;
                default:
                    fin = "";
                    System.out.printf("ERROR INVALID PINYIN GIVEN\n");
                    System.exit(0);
            }
        }
        if (fin.contains("1") || fin.contains("2") || fin.contains("3") || fin.contains("4")) {
            fin = fin.substring(0,fin.length()-1);
        }
        return fin;
    }

    public String getPinyinInitial(String syllable) {
        if (syllable.charAt(1) == 'h') {
            switch(syllable.charAt(0)) {
                case 'c':
                    return "ch";
                case 's':
                    return "sh";
                case 'z':
                    return "zh";
                default:
                    System.out.printf("Error in getPinyinInitial(), invalid pinyin syllable given.\n");
                    System.exit(0);
                    return "";
            }
        } else {
            switch(syllable.charAt(0)){
                case 'b':
                case 'p':
                case 'm':
                case 'f':
                case 'd':
                case 't':
                case 'n':
                case 'l':
                case 'g':
                case 'k':
                case 'h':
                case 'j':
                case 'q':
                case 'x':
                case 'z':
                case 'c':
                case 's':
                    return syllable.substring(0,1);
                case 'w':
                case 'y':
                    return "";
                default:
                    System.out.printf("Error in getPinyinInitial(), invalid pinyin syllable given.\n");
                    System.exit(0);
                    return "";
            }
        }
    }

    // Gets the numeric tone marker
    public int getNumericTone(String syllable) {
        char lastChar = syllable.charAt(syllable.length()-1);
        switch (lastChar) {
            case '1':
                return 1;
            case '2':
                return 2;
            case '3':
                return 3;
            case '4':
                return 4;
            default:
                return 0;
        }
    }

    // Take the easily typed (numeric) pinyin and convert to the easily read (diacritic mark) pinyin
    // Shouldn't ever need to convert the other way
    public String convertNumericPinyin(String numeric) {
        // Get syllable information
        int tone  = getNumericTone(numeric);
        String init = getPinyinInitial(numeric);
        String fin = getPinyinFinal(numeric);
        String marked = "";

        // Determine by the final where the diacritic mark will go
        if (pinyinDiacriticAt0.contains(fin)) {
            switch(fin.charAt(0)) {
                case 'a':
                    if (tone <= 4 && tone >= 0) {
                        fin = fin.replace('a', aTones.get(tone));
                    } else {
                        System.out.printf("Error in convertNumericPinyin(). Invalid numeric tone given.\n");
                        System.exit(0);
                    }
                    break;
                case 'e':
                    if (tone <= 4 && tone >= 0) {
                       fin = fin.replace('e', eTones.get(tone));
                    } else {
                        System.out.printf("Error in convertNumericPinyin(). Invalid numeric tone given.\n");
                        System.exit(0);
                    }
                    break;
                case 'i':
                    if (tone <= 4 && tone >= 0) {
                        fin = fin.replace('i', iTones.get(tone));
                    } else {
                        System.out.printf("Error in convertNumericPinyin(). Invalid numeric tone given.\n");
                        System.exit(0);
                    }
                    break;
                case 'o':
                    if (tone <= 4 && tone >= 0) {
                        fin = fin.replace('o', oTones.get(tone));
                    } else {
                        System.out.printf("Error in convertNumericPinyin(). Invalid numeric tone given.\n");
                        System.exit(0);
                    }
                    break;
                case 'u':
                    if (tone <= 4 && tone >= 0) {
                        fin = fin.replace('u', uTones.get(tone));
                    } else {
                        System.out.printf("Error in convertNumericPinyin(). Invalid numeric tone given.\n");
                        System.exit(0);
                    }
                    break;
                case 'v': // Note that the pinyin keyboard treats v as equivalent to ü since Chinese pinyin has no 'v'
                    if (tone <= 4 && tone >= 0) {
                        fin = fin.replace('v', uUmlautTones.get(tone));
                    } else {
                        System.out.printf("Error in convertNumericPinyin(). Invalid numeric tone given.\n");
                        System.exit(0);
                    }
                    break;
                default:
                    System.out.printf("Error in convertNumericPinyin. Couldn't determine which letter needs the diacritic mark.\n");
                    //System.exit(0);
            }
        } else if (pinyinDiacriticAt1.contains(fin)) {
            switch(fin.charAt(1)) {
                case 'a':
                    if (tone <= 4 && tone >= 0) {
                        fin = fin.replace('a', aTones.get(0));
                    } else {
                        System.out.printf("Error in convertNumericPinyin(). Invalid numeric tone given.\n");
                        System.exit(0);
                    }
                    break;
                case 'e':
                    if (tone <= 4 && tone >= 0) {
                        fin = fin.replace('e', eTones.get(tone));
                    } else {
                        System.out.printf("Error in convertNumericPinyin(). Invalid numeric tone given.\n");
                        System.exit(0);
                    }
                    break;
                case 'i':
                    if (tone == 0) {
                        fin = fin.replace('i', iTones.get(tone));
                    } else {
                        System.out.printf("Error in convertNumericPinyin(). Invalid numeric tone given.\n");
                        System.exit(0);
                    }
                    break;
                case 'o':
                    if (tone <= 4 && tone >= 0) {
                        fin = fin.replace('o', oTones.get(tone));
                    } else {
                        System.out.printf("Error in convertNumericPinyin(). Invalid numeric tone given.\n");
                        System.exit(0);
                    }
                    break;
                case 'u':
                    if (tone <= 4 && tone >= 0) {
                        fin = fin.replace('u', uTones.get(tone));
                    } else {
                        System.out.printf("Error in convertNumericPinyin(). Invalid numeric tone given.\n");
                        System.exit(0);
                    }
                    break;
                case 'v':
                    if (tone == 0) {
                        fin = fin.replace('v',uUmlautTones.get(tone));
                    } else {
                        System.out.printf("Error in convertNumericPinyin(). Invalid numeric tone given.\n");
                        System.exit(0);
                    }
                    break;
                default:
                    // System.out.printf("WHAT THE HELL\n\n");
                    System.out.printf("Error in convertNumericPinyin. Couldn't determine which letter needs the diacritic mark.\n");
                    System.exit(0);
            }
        } else {
            System.out.printf("Error in convertNumericPinyin(), invalid pinyin given. The final could not be found in either of the diacritic sets.\n");
            System.exit(0);
            return marked;
        }
        marked = marked.concat(init);
        marked = marked.concat(fin);
        return marked;
    }

    // Take a zhuyin string and return the pinyin equivalent
    public String convertZhuyin(String zhuyin) {

        return "";
    }
    // Take a pinyin string and return the zhuyin equivalent
    public String convertPinyin(String pinyin) {

        return "";
    }
}
