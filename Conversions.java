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
    HashMap<String,String> zhuyinToPinyinDoubleCharacter;
    HashMap<String,String> pinyinToZhuyinDoubleCharacter;

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
        specialPinyin1 = new ArrayList<String>(23);
        specialPinyin2 = new ArrayList<String>(23);
        specialPinyin3 = new ArrayList<String>(23);
        specialPinyin4 = new ArrayList<String>(23);
        specialPinyin0 = new ArrayList<String>(23);
        this.specialPinyin0.add("yi");
        this.specialPinyin0.add("ya");
        this.specialPinyin0.add("yan");
        this.specialPinyin0.add("yang");
        this.specialPinyin0.add("yao");
        this.specialPinyin0.add("ye");
        this.specialPinyin0.add("yin");
        this.specialPinyin0.add("ying");
        this.specialPinyin0.add("yong");
        this.specialPinyin0.add("you");
        this.specialPinyin0.add("wu");
        this.specialPinyin0.add("wa");
        this.specialPinyin0.add("wai");
        this.specialPinyin0.add("wan");
        this.specialPinyin0.add("wang");
        this.specialPinyin0.add("wei");
        this.specialPinyin0.add("wen");
        this.specialPinyin0.add("weng");
        this.specialPinyin0.add("wo");
        this.specialPinyin0.add("yu");
        this.specialPinyin0.add("yuan");
        this.specialPinyin0.add("yue");
        this.specialPinyin0.add("yun");
        this.specialPinyin0.add("a");
        this.specialPinyin0.add("ai");
        this.specialPinyin0.add("an");
        this.specialPinyin0.add("ang");
        this.specialPinyin0.add("ao");
        this.specialPinyin0.add("e");
        this.specialPinyin0.add("ei");
        this.specialPinyin0.add("en");
        this.specialPinyin0.add("eng");
        this.specialPinyin0.add("er");
        this.specialPinyin0.add("o");
        this.specialPinyin0.add("ou");

        for (String special : specialPinyin0) {
            this.specialPinyin1.add(special + "1");
            this.specialPinyin2.add(special + "2");
            this.specialPinyin3.add(special + "3");
            this.specialPinyin4.add(special + "4");
        }
        // Need to be very careful with this
        this.pinyinInitials = new ArrayList<String>(10);
        this.pinyinInitials.add("b");
        this.pinyinInitials.add("p");
        this.pinyinInitials.add("m");
        this.pinyinInitials.add("f");
        this.pinyinInitials.add("d");
        this.pinyinInitials.add("t");
        this.pinyinInitials.add("n");
        this.pinyinInitials.add("l");
        this.pinyinInitials.add("g");
        this.pinyinInitials.add("k");
        this.pinyinInitials.add("h");
        this.pinyinInitials.add("j");
        this.pinyinInitials.add("q");
        this.pinyinInitials.add("x");
        this.pinyinInitials.add("z");
        this.pinyinInitials.add("c");
        this.pinyinInitials.add("s");
        this.pinyinInitials.add("zh");
        this.pinyinInitials.add("ch");
        this.pinyinInitials.add("sh");
        this.pinyinInitials.add("r");
        // Be very careful with this
        this.pinyinFinals1 = new ArrayList<String>();
        this.pinyinFinals2 = new ArrayList<String>();
        this.pinyinFinals3 = new ArrayList<String>();
        this.pinyinFinals4 = new ArrayList<String>();
        this.pinyinFinals0 = new ArrayList<String>(38);
        this.pinyinFinals0.add("a");
        this.pinyinFinals0.add("ai");
        this.pinyinFinals0.add("an");
        this.pinyinFinals0.add("ang");
        this.pinyinFinals0.add("ao");
        this.pinyinFinals0.add("e");
        this.pinyinFinals0.add("ei");
        this.pinyinFinals0.add("en");
        this.pinyinFinals0.add("eng");
        this.pinyinFinals0.add("er");
        this.pinyinFinals0.add("i");
        this.pinyinFinals0.add("ia");
        this.pinyinFinals0.add("ian");
        this.pinyinFinals0.add("iang");
        this.pinyinFinals0.add("iao");
        this.pinyinFinals0.add("ie");
        this.pinyinFinals0.add("in");
        this.pinyinFinals0.add("ing");
        this.pinyinFinals0.add("iong");
        this.pinyinFinals0.add("iou");
        this.pinyinFinals0.add("iu");
        this.pinyinFinals0.add("o");
        this.pinyinFinals0.add("ong");
        this.pinyinFinals0.add("ou");
        this.pinyinFinals0.add("u");
        this.pinyinFinals0.add("ua");
        this.pinyinFinals0.add("uai");
        this.pinyinFinals0.add("uan");
        this.pinyinFinals0.add("uang");
        this.pinyinFinals0.add("uei");
        this.pinyinFinals0.add("ui");
        this.pinyinFinals0.add("uen");
        this.pinyinFinals0.add("un");
        this.pinyinFinals0.add("ueng");
        this.pinyinFinals0.add("uo");
        this.pinyinFinals0.add("\u00FC");
        this.pinyinFinals0.add("\u00FCn");
        this.pinyinFinals0.add("\u00FCan");
        this.pinyinFinals0.add("\u00FCe");
        for (String fin : pinyinFinals0) {
            this.pinyinFinals1.add(fin + "1");
            this.pinyinFinals2.add(fin + "2");
            this.pinyinFinals3.add(fin + "3");
            this.pinyinFinals4.add(fin + "4");
        }
        this.zhuyinInitials = new ArrayList<String>();
        this.zhuyinFinals = new ArrayList<String>();

        // Initialize the indexes of all diacritic marks for finals
        this.pinyinDiacriticAt0 = new ArrayList<String>();
        this.pinyinDiacriticAt0.add("a");
        this.pinyinDiacriticAt0.add("ai");
        this.pinyinDiacriticAt0.add("an");
        this.pinyinDiacriticAt0.add("ang");
        this.pinyinDiacriticAt0.add("ao");
        this.pinyinDiacriticAt0.add("e");
        this.pinyinDiacriticAt0.add("ei");
        this.pinyinDiacriticAt0.add("en");
        this.pinyinDiacriticAt0.add("eng");
        this.pinyinDiacriticAt0.add("er");
        this.pinyinDiacriticAt0.add("i");
        this.pinyinDiacriticAt0.add("in");
        this.pinyinDiacriticAt0.add("ing");
        this.pinyinDiacriticAt0.add("o");
        this.pinyinDiacriticAt0.add("ong");
        this.pinyinDiacriticAt0.add("ou");
        this.pinyinDiacriticAt0.add("u");
        this.pinyinDiacriticAt0.add("un");
        this.pinyinDiacriticAt0. add("\u00FC");
        this.pinyinDiacriticAt0.add("\u00FCn");
        this.pinyinDiacriticAt1 = new ArrayList<String>();
        this.pinyinDiacriticAt1.add("ia");
        this.pinyinDiacriticAt1.add("ian");
        this.pinyinDiacriticAt1.add("iang");
        this.pinyinDiacriticAt1.add("iao");
        this.pinyinDiacriticAt1.add("ie");
        this.pinyinDiacriticAt1.add("iong");
        this.pinyinDiacriticAt1.add("iu");
        this.pinyinDiacriticAt1.add("ua");
        this.pinyinDiacriticAt1.add("uai");
        this.pinyinDiacriticAt1.add("uan");
        this.pinyinDiacriticAt1.add("uang");
        this.pinyinDiacriticAt1.add("ui");
        this.pinyinDiacriticAt1.add("uo");
        this.pinyinDiacriticAt1.add("ue");
        this.pinyinDiacriticAt1.add("\u00FCe");
        this.pinyinDiacriticAt1.add("uan");
        this.pinyinDiacriticAt1.add("\u00FCan");

        // Initialize the diacritic arrays for easy access and manipulation
        this.aTones = new ArrayList<Character>();
            // Lower case
            this.aTones.add('a');       // neutral tone
            this.aTones.add('\u0101');  // 1st tone
            this.aTones.add('\u00E1');  // 2nd tone
            this.aTones.add('\u01CE');  // 3rd tone
            this.aTones.add('\u00E0');  // 4th tone
            // Upper case
            this.aTones.add('A');
            this.aTones.add('\u0100');
            this.aTones.add('\u00C1');
            this.aTones.add('\u01CD');
            this.aTones.add('\u00C0');
        this.eTones = new ArrayList<Character>();
            // Lower case
            this.eTones.add('e');
            this.eTones.add('\u0113');
            this.eTones.add('\u00E9');
            this.eTones.add('\u011B');
            this.eTones.add('\u00E8');
            // Upper case
            this.eTones.add('E');
            this.eTones.add('\u0112');
            this.eTones.add('\u00C9');
            this.eTones.add('\u011A');
            this.eTones.add('\u00C8');
        this.iTones = new ArrayList<Character>();
            // Lower case
            this.iTones.add('i');
            this.iTones.add('\u012B');
            this.iTones.add('\u00ED');
            this.iTones.add('\u01D0');
            this.iTones.add('\u00EC');
            // Upper case
            this.iTones.add('I');
            this.iTones.add('\u012A');
            this.iTones.add('\u00CD');
            this.iTones.add('\u01CF');
            this.iTones.add('\u00CC');
        this.oTones = new ArrayList<Character>();
            // Lower case
            this.oTones.add('o');
            this.oTones.add('\u014D');
            this.oTones.add('\u00F3');
            this.oTones.add('\u01D2');
            this.oTones.add('\u00F2');
            // Upper case
            this.oTones.add('O');
            this.oTones.add('\u014C');
            this.oTones.add('\u00D3');
            this.oTones.add('\u01D1');
            this.oTones.add('\u00D2');
        this.uTones = new ArrayList<Character>();
            // Lower case
            this.uTones.add('u');
            this.uTones.add('\u016B');
            this.uTones.add('\u00FA');
            this.uTones.add('\u01D4');
            this.uTones.add('\u00F9');
            // Upper case
            this.uTones.add('U');
            this.uTones.add('\u016A');
            this.uTones.add('\u00DA');
            this.uTones.add('\u01D3');
            this.uTones.add('\u00D9');
        this.uUmlautTones = new ArrayList<Character>();
            // Lower case
            this.uUmlautTones.add('\u00FC');
            this.uUmlautTones.add('\u01D6');
            this.uUmlautTones.add('\u01D8');
            this.uUmlautTones.add('\u01DA');
            this.uUmlautTones.add('\u01DC');
            // Upper case
            this.uUmlautTones.add('\u00DC');
            this.uUmlautTones.add('\u01D5');
            this.uUmlautTones.add('\u01D7');
            this.uUmlautTones.add('\u01D9');
            this.uUmlautTones.add('\u01DB');

        // Named as such because they'll only ever be used for zhuyin characters
        this.zhuyinTones = new ArrayList<Character>();
        this.zhuyinTones.add('\u02C9');
        this.zhuyinTones.add('\u02CA');
        this.zhuyinTones.add('\u02C7');
        this.zhuyinTones.add('\u02CB');
        this.zhuyinTones.add('\u02D9');

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
        this.zhuyinToPinyinSingleCharacter = new HashMap<String,String>();
            // First the single character ones
            this.zhuyinToPinyinSingleCharacter.put("\u3105","b");
            this.zhuyinToPinyinSingleCharacter.put("\u3106","p");
            this.zhuyinToPinyinSingleCharacter.put("\u3107","m");
            this.zhuyinToPinyinSingleCharacter.put("\u3108","f");
            this.zhuyinToPinyinSingleCharacter.put("\u3109","d");
            this.zhuyinToPinyinSingleCharacter.put("\u310A","t");
            this.zhuyinToPinyinSingleCharacter.put("\u310B","n");
            this.zhuyinToPinyinSingleCharacter.put("\u310C","l");
            this.zhuyinToPinyinSingleCharacter.put("\u310D","g");
            this.zhuyinToPinyinSingleCharacter.put("\u310E","k");
            this.zhuyinToPinyinSingleCharacter.put("\u310F","h");
            this.zhuyinToPinyinSingleCharacter.put("\u3110","j");
            this.zhuyinToPinyinSingleCharacter.put("\u3111","q");
            this.zhuyinToPinyinSingleCharacter.put("\u3112","x");
            this.zhuyinToPinyinSingleCharacter.put("\u3116","r");
            this.zhuyinToPinyinSingleCharacter.put("\u3117","z");
            this.zhuyinToPinyinSingleCharacter.put("\u3118","c");
            this.zhuyinToPinyinSingleCharacter.put("\u3119","s");
            this.zhuyinToPinyinSingleCharacter.put("\u311A","a");
            this.zhuyinToPinyinSingleCharacter.put("\u311B","o");
            this.zhuyinToPinyinSingleCharacter.put("\u311C","e");
            // this.zhuyinToPinyinSingleCharacter.put("\u311D","eh"); // This one does not need to be included in the mapping because \u311D is never used on its own
            this.zhuyinToPinyinSingleCharacter.put("\u311E","ai");
            this.zhuyinToPinyinSingleCharacter.put("\u311F","ei");
            this.zhuyinToPinyinSingleCharacter.put("\u3120","ao");
            this.zhuyinToPinyinSingleCharacter.put("\u3121","ou");
            this.zhuyinToPinyinSingleCharacter.put("\u3122","an");
            this.zhuyinToPinyinSingleCharacter.put("\u3123","en");
            this.zhuyinToPinyinSingleCharacter.put("\u3124","ang");
            this.zhuyinToPinyinSingleCharacter.put("\u3125","eng");
            this.zhuyinToPinyinSingleCharacter.put("\u3126","er");
            this.zhuyinToPinyinSingleCharacter.put("\u3127","i");
            this.zhuyinToPinyinSingleCharacter.put("\u3128", "u");
            this.zhuyinToPinyinSingleCharacter.put("\u3129","\u00FC");
        this.pinyinToZhuyinSingleCharacter = new HashMap<String,String>();
        for (HashMap.Entry<String,String> pair : this.zhuyinToPinyinSingleCharacter.entrySet()) {
            pinyinToZhuyinSingleCharacter.put(pair.getValue(), pair.getKey());
        }

        // Now we do the double character mappings
        this.pinyinToZhuyinDoubleCharacter = new HashMap<String,String>(22);
            this.pinyinToZhuyinDoubleCharacter.put("sh","\u3115");
            this.pinyinToZhuyinDoubleCharacter.put("ch","\u3114");
            this.pinyinToZhuyinDoubleCharacter.put("zh","\u3113");
            this.pinyinToZhuyinDoubleCharacter.put("ia", "\u3127\u311A");
            this.pinyinToZhuyinDoubleCharacter.put("ie", "\u3127\u311D");
            this.pinyinToZhuyinDoubleCharacter.put("iao", "\u3127\u3120");
            this.pinyinToZhuyinDoubleCharacter.put("iu", "\u3127\u3121"); //iu
            this.pinyinToZhuyinDoubleCharacter.put("ian", "\u3127\u3122"); //ian
            this.pinyinToZhuyinDoubleCharacter.put("in", "\u3127\u3123"); //in
            this.pinyinToZhuyinDoubleCharacter.put("iang", "\u3127\u3124"); //iang
            this.pinyinToZhuyinDoubleCharacter.put("ing", "\u3127\u3125"); //ing
            this.pinyinToZhuyinDoubleCharacter.put("ua", "\u3128\u311A"); //ua
            this.pinyinToZhuyinDoubleCharacter.put("uo", "\u3128\u311B"); //uo
            this.pinyinToZhuyinDoubleCharacter.put("uai", "\u3128\u311E"); //uai
            this.pinyinToZhuyinDoubleCharacter.put("ui", "\u3128\u311F"); //ui
            this.pinyinToZhuyinDoubleCharacter.put("uan", "\u3128\u3122"); //uan
            this.pinyinToZhuyinDoubleCharacter.put("un", "\u3128\u3123"); //un
            this.pinyinToZhuyinDoubleCharacter.put("uang", "\u3128\u3124"); //uang
            this.pinyinToZhuyinDoubleCharacter.put("ue", "\u3129\u311D"); //\u00FCe and ue, but this is handled later
            this.pinyinToZhuyinDoubleCharacter.put("\u00FCan", "\u3129\u3122"); // uan
            this.pinyinToZhuyinDoubleCharacter.put("\u00FCn", "\u3129\u3123"); // un and /u00FCn, but this is handled later
            this.pinyinToZhuyinDoubleCharacter.put("ong", "\u3128\u3125"); // ong
            this.zhuyinToPinyinDoubleCharacter = new HashMap<String,String>();
        for (HashMap.Entry<String,String> pair : this.pinyinToZhuyinDoubleCharacter.entrySet()) {
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

    // Helper function for converting from numeric pinyin to diacritic pinyin
    public String getPinyinFinal(String syllable) {
        String fin;
        // Check if it's a single character, in which case we treat it like a syllable starting with y or w
        if (syllable.length() == 1) {
            return syllable;
        }
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
                    System.out.printf("Error in getPinyinFinal(), %s has 'h' as the second character, but didn't match z, c, or s to the first.\n", syllable);
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
                case 'r':
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
        if (fin.contains("0") || fin.contains("1") || fin.contains("2") || fin.contains("3") || fin.contains("4")) {
            fin = fin.substring(0,fin.length()-1);
        }
        return fin;
    }

    public String getPinyinInitial(String syllable) {
        // Case where it's a single letter, like the particle 'a'
        if (syllable.length() == 1) {
            // We treat it the same as the y- and w-started ones and say there is no initial 
            return "";
        }
        if (syllable.charAt(1) == 'h') {
            switch(syllable.charAt(0)) {
                case 'c':
                    return "ch";
                case 's':
                    return "sh";
                case 'z':
                    return "zh";
                default:
                    System.out.printf("Error in getPinyinInitial(), %s is an invalid pinyin syllable.\n", syllable);
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
                case 'r':
                    return syllable.substring(0,1);
                case 'w':
                case 'y':
                case 'a':
                case 'e':
                    return "";
                default:
                    System.out.printf("Error in getPinyinInitial(), %s is an invalid pinyin syllable.\n", syllable);
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

    // Convert an integer to a zhuyin tone marker
    public String convertIntToZhuyinToneMark(int number) {
        if (number == 0) {
            return "\u02D9"; 
        } else if (number == 1) {
            return "\u02C9";
        } else if (number == 2) {
            return "\u02CA";
        } else if (number == 3) {
            return "\u02C7";
        } else if (number == 4) {
            return "\u02CB";
        } else {
            System.out.printf("Error in convertIntToZhuyinToneMark(), number given (%d) didn't belong to {0,1,2,3,4}.\n", number);
            System.exit(0);
            return "";
        }
    }

    // Take the easily typed (numeric) pinyin and convert to the easily read (diacritic mark) pinyin
    // Shouldn't ever need to convert the other way
    public String convertNumericPinyin(String numeric) {
        // Get syllable information
        int tone  = getNumericTone(numeric);
        String init = getPinyinInitial(numeric);
        String fin = getPinyinFinal(numeric);
        String tempFinal = fin;
        if (init.equals("") && (fin.charAt(0) == 'y' || fin.charAt(0) == 'w')) { // This makes it tricky with getting the final in the diacriticAtX sets
            tempFinal = fin.substring(1); // chop off the w or y
        }
        String marked = "";

        // Determine by the final where the diacritic mark will go
        if (pinyinDiacriticAt0.contains(tempFinal)) {
            switch(tempFinal.charAt(0)) {
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
                case '\u00FC': // Note that the pinyin keyboard treats v as equivalent to ü since Chinese pinyin has no 'v'
                    if (tone <= 4 && tone >= 0) {
                        fin = fin.replace('\u00FC', uUmlautTones.get(tone));
                    } else {
                        System.out.printf("Error in convertNumericPinyin(). Invalid numeric tone given.\n");
                        System.exit(0);
                    }
                    break;
                default:
                    System.out.printf("Error in convertNumericPinyin. Couldn't determine which letter needs the diacritic mark.\n");
                    System.exit(0);
            }
        } else if (pinyinDiacriticAt1.contains(tempFinal)) {
            switch(tempFinal.charAt(1)) {
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
                case '\u00FC':
                    if (tone <= 4 && tone >= 0) {
                        fin = fin.replace('\u00FC',uUmlautTones.get(tone));
                    } else {
                        System.out.printf("Error in convertNumericPinyin(). Invalid numeric tone given.\n");
                        System.exit(0);
                    }
                    break;
                default:
                    System.out.printf("Error in convertNumericPinyin. Couldn't determine which letter needs the diacritic mark.\n");
                    System.exit(0);
            }
        } else {
            System.out.printf("Error in convertNumericPinyin(), invalid pinyin given. %s could not be found in either of the diacritic sets.\n", numeric);
            System.exit(0);
            return marked;
        }
        marked = marked.concat(init);
        marked = marked.concat(fin);
        return marked;
    }

    // Take a pinyin string and return the zhuyin equivalent
    public String convertPinyin(String pinyin) {
        // All pretty straightforward except for the syllables involving v (u umlaut) which omit the umlaut
        // Might just be best to do an extra conversion step to handle the u umlaut cases
        String pinyinInitial, pinyinFinal, zhuyinInitial, zhuyinFinal, toneMark = "";
        pinyinInitial = getPinyinInitial(pinyin);
        pinyinFinal = getPinyinFinal(pinyin);
        toneMark = convertIntToZhuyinToneMark(getNumericTone(pinyin));

        // For special case 1
        ArrayList<String> trickyEndings = new ArrayList<String>(12);
            trickyEndings.add("u");
            trickyEndings.add("\u00FC");
            trickyEndings.add("ue");
            trickyEndings.add("\u00FCe");
            trickyEndings.add("uan");
            trickyEndings.add("\u00FCan");
            trickyEndings.add("un");
            trickyEndings.add("\u00FCn");
        ArrayList<String> nljqx = new ArrayList<String>(5);
            nljqx.add("n");
            nljqx.add("l");
            nljqx.add("j");
            nljqx.add("q");
            nljqx.add("x");
        ArrayList<String> jqx = new ArrayList<String>(3);
            jqx.add("j");
            jqx.add("q");
            jqx.add("x");

        // For special case 2
        ArrayList<String> specialInitials = new ArrayList<String>(7);
            specialInitials.add("s");
            specialInitials.add("c");
            specialInitials.add("z");
            specialInitials.add("zh");
            specialInitials.add("ch");
            specialInitials.add("sh");
            specialInitials.add("r");

        // Our formalization for the special cases is as follows:
        //  "Given syllable S with initial I and final F, we have the special cases:"

        // Special case #1: I is one character AND  F belongs to {u, u_Umlaut_, ue, u_Umlaut_e, uan, u_Umlaut_an, un, u_Umlaut_n} (= trickyEndings)
        if (pinyinInitial.length() == 1 && trickyEndings.contains(pinyinFinal)) {
            // In all subcases, I is directly translated
            zhuyinInitial = pinyinToZhuyinSingleCharacter.get(pinyinInitial);
            //  a) I belongs to {n,l,j,q,x} AND  F belongs to {u,u_Umlaut_}
            if (nljqx.contains(pinyinInitial) && pinyinFinal.equals("\u00FC")) {
                // F --> \u3129
                zhuyinFinal = "\u3129";
            }
            else if (nljqx.contains(pinyinInitial) && pinyinFinal.equals("u")) {
                zhuyinFinal = "\u3128";
            }
            //  b) I belongs to {j,q,x} AND F = "uan"
            else if (jqx.contains(pinyinInitial) && pinyinFinal.equals("uan")) {
                // F --> \u3129\u3122
                zhuyinFinal = "\u3129\u3122";
            }
            //  c) I belongs to {n,l,j,q,x} AND F belongs to {ue,u_Umlaut_e}
            else if (nljqx.contains(pinyinInitial) && (pinyinFinal.equals("ue") || pinyinFinal.equals("\u00FCe"))) {
                // F --> \u3129\311D
                zhuyinFinal = "\u3129\311D";
            }
            //  d) I belongs to {j,q,x} AND F = "un"
            else if (jqx.contains(pinyinInitial) && pinyinFinal.equals("un")) {
                // F --> \u3129\u3123
                zhuyinFinal = "\u3129\u3123";
            }
            // ERROR
            else {
                // System.out.printf("Error in convertPinyin() for special case #1, couldn't find an appropriate subcase for %s.\n",pinyin);
                // System.exit(0);
                // return "";
                zhuyinFinal = "ㄨ";
            }
            String result = zhuyinInitial.concat(zhuyinFinal);
            return result.concat(toneMark);
        }
        // Special case #2: I belongs to {s,c,z,zh,ch,sh,r} AND F = "i"
        else if (specialInitials.contains(pinyinInitial) && pinyinFinal.equals("i")) {
            // Directly translate I and ignore F
            if (pinyinInitial.length() == 1) {
                zhuyinInitial = pinyinToZhuyinSingleCharacter.get(pinyinInitial);
            } else {
                zhuyinInitial = pinyinToZhuyinDoubleCharacter.get(pinyinInitial);
            }
            return zhuyinInitial.concat(toneMark);
        }
        // Special case #3: I = "" AND F belongs to {yALPHA, wALPHA}, where ALPHA denotes any final for which w or y is a valid first letter
        else if (pinyinInitial.length() == 0 && specialPinyin0.contains(pinyinFinal)) {
            // I --> ""
            zhuyinInitial = "";
            // F is directly translated
            // Using a switch statement that's probably a bit too long because I don't want another HashMap
            switch(pinyinFinal) {
                case "a":
                    zhuyinFinal = "\u311A";
                    break;
                case "ai":
                    zhuyinFinal = "\u311E";
                    break;
                case "an":
                    zhuyinFinal = "\u3122";
                    break;
                case "ang":
                    zhuyinFinal = "\u3124";
                    break;
                case "ao":
                    zhuyinFinal = "\u3120";
                    break;
                case "e":
                    zhuyinFinal = "\u311C";
                    break;
                case "ei":
                    zhuyinFinal = "\u311F";
                    break;
                case "en":
                    zhuyinFinal = "\u3123";
                    break;
                case "eng":
                    zhuyinFinal = "\u3125";
                    break;
                case "er":
                    zhuyinFinal = "\u3126";
                    break;
                case "yi":
                    zhuyinFinal = "\u3127";
                    break;
                case "yan":
                    zhuyinFinal = "\u3127\u3122";
                    break;
                case "yang":
                    zhuyinFinal = "\u3127\u3124";
                    break;
                case "yao":
                    zhuyinFinal = "\u3127\u3120";
                    break;
                case "ye":
                    zhuyinFinal = "\u3127\u311D";
                    break;
                case "yin":
                    zhuyinFinal = "\u3127\u3123";
                    break;
                case "ying":
                    zhuyinFinal = "\u3127\u3125";
                    break;
                case "yong":
                    zhuyinFinal = "\u3129\u3125";
                    break;
                case "you":
                    zhuyinFinal = "\u3127\u3121";
                    break;
                case "o":
                    zhuyinFinal = "\u311B";
                    break;
                case "ou":
                    zhuyinFinal = "\u3121";
                    break;
                case "wu":
                    zhuyinFinal = "\u3128";
                    break;
                case "wa":
                    zhuyinFinal = "\u3128\u311A";
                    break;
                case "wai":
                    zhuyinFinal = "\u3128\u311E";
                    break;
                case "wan":
                    zhuyinFinal = "\u3128\u3122";
                    break;
                case "wang":
                    zhuyinFinal = "\u3128\u3124";
                    break;
                case "wei":
                    zhuyinFinal = "\u3128\u311F";
                    break;
                case "wen":
                    zhuyinFinal = "\u3128\u3123";
                    break;
                case "weng":
                    zhuyinFinal = "\u3128\u3125";
                    break;
                case "wo":
                    zhuyinFinal = "\u3128\u311B";
                    break;
                case "yu":
                    zhuyinFinal = "\u3129";
                    break;
                case "yuan":
                    zhuyinFinal = "\u3129\u3122";
                    break;
                case "yue":
                    zhuyinFinal = "\u3129\u311D";
                    break;
                case "yun":
                    zhuyinFinal = "\u3129\u3123";
                    break;
                default:
                    zhuyinFinal = "";
                    System.out.printf("Error in convertPinyin() for special case #3, couldn't match %s to any given 'special' pinyin syllables.\n", pinyin);
                    System.exit(0);
            }
            return zhuyinFinal.concat(toneMark);
        }
        // Case 4: DEFAULT CASE (for a valid pinyin string)
        else {
            zhuyinInitial = pinyinToZhuyinSingleCharacter.get(pinyinInitial);
            if (zhuyinInitial == null) {
                zhuyinInitial = pinyinToZhuyinDoubleCharacter.get(pinyinInitial);
                if (zhuyinInitial == null) {
                    System.out.printf("Error in convertPinyin(), couldn't find a valid mapping for the initial of %s in the default case.\n", pinyin);
                    System.exit(0);
                }
            }
            zhuyinFinal = pinyinToZhuyinSingleCharacter.get(pinyinFinal);
            if (zhuyinFinal == null) {
                zhuyinFinal = pinyinToZhuyinDoubleCharacter.get(pinyinFinal);
                if (zhuyinFinal == null) {
                    System.out.printf("Error in convertPinyin(), couldn't find a valid mapping for the final of %s in the default case.\n", pinyin);
                    System.exit(0);
                }
            }
            String result = zhuyinInitial.concat(zhuyinFinal);
            return result.concat(toneMark);
        }
    }
    
    // Get the initial of a zhuyin string
    public String getZhuyinInitial(String syllable) {
        switch (syllable.charAt(0)) {
            case '\u3105':
            case '\u3106':
            case '\u3107':
            case '\u3108':
            case '\u3109':
            case '\u310A':
            case '\u310B':
            case '\u310C':
            case '\u310D':
            case '\u310E':
            case '\u310F':
            case '\u3110':
            case '\u3111':
            case '\u3112':
            case '\u3113':
            case '\u3114':
            case '\u3115':
            case '\u3116':
            case '\u3117':
            case '\u3118':
            case '\u3119':
                return syllable.substring(0,1);
            default:
                return "";
        }
    }

    // Convert the zhuyin initial to a pinyin initial
    public String convertZhuyinInitial(String syllable) {
        // There are no double character initials in Zhuyin (not a terrible design!) so we can just do a simple switch statement
        switch(syllable.charAt(0)) {
            case '\u3105': return "b";
            case '\u3106': return "p";
            case '\u3107': return "m";
            case '\u3108': return "f";
            case '\u3109': return "d";
            case '\u310A': return "t";
            case '\u310B': return "n";
            case '\u310C': return "l";
            case '\u310D': return "g";
            case '\u310E': return "k";
            case '\u310F': return "h";
            case '\u3110': return "j";
            case '\u3111': return "q";
            case '\u3112': return "x";
            case '\u3113': return "zh";
            case '\u3114': return "ch";
            case '\u3115': return "sh";
            case '\u3116': return "r";
            case '\u3117': return "z";
            case '\u3118': return "c";
            case '\u3119': return "s";
            default: return "";
        }
    }

    // Convert the zhuyin final into a pinyin equivalent
    // Assumes that you passed it a syllable in which the tone mark has been chopped off
    public String convertZhuyinFinal(String syllable) {
        // Because in zhuyin the initial is always only a single character, we have the following cases:
        //  1) there is an initial
        //  2) it's one of the special ones which is only a final
        //  3) it's one character long 
        
        if (getZhuyinInitial(syllable).length() == 0) {
            // Handle case 2: syllable is only a final
            switch (syllable) {
                case "\u3127": return "yi";
                case "\u3126": return "er";
                case "\u3128": return "wu";
                case "\u3129": return "yu";
                case "\u3127\u311A": return "ya";
                case "\u3127\u311D": return "ye";
                case "\u3127\u3120": return "yao";
                case "\u3127\u3121": return "you";
                case "\u3127\u3122": return "yan";
                case "\u3127\u3123": return "yin";
                case "\u3127\u3124": return "yang";
                case "\u3127\u3125": return "ying";
                case "\u3128\u311A": return "wa";
                case "\u3128\u311B": return "wo";
                case "\u3128\u311E": return "wai";
                case "\u3128\u311F": return "wei";
                case "\u3128\u3122": return "wan";
                case "\u3128\u3123": return "wen";
                case "\u3128\u3124": return "wang";
                case "\u3128\u3125": return "weng";
                case "\u3129\u311D": return "yue";
                case "\u3129\u3122": return "yuan";
                case "\u3129\u3123": return "yun";
                case "\u3129\u3125": return "yong";
                default:
                    System.out.printf("Error in convertZhuyinFinal(), couldn't match a match for %s.\n", syllable);
                    System.exit(0);
                    return "";
            }
        }
        // Handle case 3
        else if (syllable.length() == 1) {
            switch(syllable) {
                case "\u3119": return "si";
                case "\u3118": return "ci";
                case "\u3117": return "zi";
                case "\u3116": return "ri";
                case "\u3115": return "shi";
                case "\u3114": return "chi";
                case "\u3113": return "zhi";
                default:
                    System.out.printf("Error in convertZhuyinFinal(), couldn't match a match for %s.\n", syllable);
                    System.exit(0);
                    return "";
            }
        } else {
            // Handle case 1
            switch (syllable.substring(1)) {
                // Single character finals
                case "\u311A": return "a";
                case "\u311B": return "o";
                case "\u311C": return "e";
                case "\u311E": return "ai";
                case "\u311F": return "ei";
                case "\u3120": return "ao";
                case "\u3121": return "ou";
                case "\u3122": return "an";
                case "\u3123": return "en";
                case "\u3124": return "ang";
                case "\u3215": return "eng";
                case "\u3126": return "er";
                case "\u3127": return "i";
                case "\u3128": return "u";
                case "\u3129": // tricky case because pinyin's shit
                    if (syllable.charAt(0) == '\u3110' || syllable.charAt(0) == '\u3111' || syllable.charAt(0) == '\u3112') {
                        return "u"; 
                    } else if (syllable.charAt(0) == '\u310B' || syllable.charAt(0) == '\u310C') {
                        return "\u00FC";
                    } else {
                        System.out.printf("Error in convertZhuyinFinal(), couldn't find a valid final for %s.\n", syllable);
                        System.exit(0);
                        return "";
                    }
                // Two character finals
                case "\u3127\u311A": return "ia";
                case "\u3127\u311D": return "ie";
                case "\u3127\u3120": return "iao";
                case "\u3127\u3121": return "iu";
                case "\u3127\u3122": return "ian";
                case "\u3127\u3123": return "in";
                case "\u3127\u3124": return "iang";
                case "\u3127\u3125": return "ing";
                case "\u3128\u311A": return "ua";
                case "\u3128\u311B": return "uo";
                case "\u3128\u311E": return "uai";
                case "\u3128\u311F": return "ui";
                case "\u3128\u3122": return "uan";
                case "\u3128\u3123": return "un";
                case "\u3128\u3124": return "uang";
                case "\u3128\u3125": return "ong";
                case "\u3129\u311D": // tricky case
                    if (syllable.charAt(0) == '\u310B' || syllable.charAt(0) == '\u310C') {
                        return "\u00FCe";
                    } else if (syllable.charAt(0) == '\u3110' || syllable.charAt(0) == '\u3111' || syllable.charAt(0) == '\u3112') {
                        return "ue";
                    } else {
                        System.out.printf("Error in convertZhuyinFinal(), %s matched to the '\u3129\311D' final, but has an invalid initial.\n");
                        System.exit(0);
                        return "";
                    }
                case "\u3129\u3122": return "uan";
                case "\u3129\u3123": return "un";
                case "\u3129\u3125": return "iong";
                default:
                    System.out.printf("Error in convertZhuyinFinal(), %s couldn't be matched to a valid final.\n", syllable);
                    System.exit(0);
                    return "";
            }
        }
    }

    // Take a zhuyin string and return the number equivalent of the tone mark
    public int getToneNumberFromZhuyin(String syllable) {
        switch(syllable.charAt(syllable.length()-1)) {
            case '\u02D9':
                return 0;
            case '\u02C9':
                return 1;
            case '\u02CA':
                return 2;
            case '\u02C7':
                return 3;
            case '\u02CB':
                return 4;
            default:
                return 1;
        }
    }

    // Take a zhuyin string and return the pinyin equivalent
    public String convertZhuyin(String zhuyin) {
        String init = convertZhuyinInitial(zhuyin.substring(0,zhuyin.length()-1));
        String fin = convertZhuyinFinal(zhuyin.substring(0,zhuyin.length()-1)); // recall that this function assumes you pass it without the tone mark
        if ((init.equals("zh") || init.equals("sh") || init.equals("ch")) && (fin.equals("zhi") || fin.equals("shi") || fin.equals("chi"))) { // Might need to add a ri case
            init = "";
        }
        int tone = getToneNumberFromZhuyin(zhuyin);
        
        String numericPinyin = init.concat(fin.concat(Integer.toString(tone)));
        
        return convertNumericPinyin(numericPinyin);
        // return numericPinyin;
    }

    // Function to take a "string" of pinyin syllables and convert it into an equivalent "string" of zhuyin syllables
    public String convertPinyinString(String pinyin) {
        // We can assume that there are two types of ways in which the syllables we want to process are grouped:
        //  1. stuck together
        //  2. separated by spaces
        //  MAYBE eventually by apostrophes
        // We want to first take it apart and process the string by the space delimited pieces (which we refer to as 'blocks'),
        //  then we want to handle the 'glued' pieces within each block
        String result = "";
        
        // Maybe try iterating along and then passing substrings when you hit a number instead of splitting? 
        //  This seems much easier and intuitive, plus it doesn't require extra overhead (data structures and function calls)
        //  the trick is we must remember the last spot where we saw a digit
        int lastStart = 0;
        for (int i = 0; i < pinyin.length(); i++) {
            if (pinyin.charAt(i) == ' ') {
                result += " ";
                lastStart = i+1;
            }
            if (Character.isDigit(pinyin.charAt(i))) {
                result += convertPinyin(pinyin.substring(lastStart,i+1));
                lastStart = i+1;
            }
        }
        // Check if the last character isn't a digit
        if (!Character.isDigit(pinyin.charAt(pinyin.length()-1))) {
            result += convertPinyin(pinyin.substring(lastStart));
        }
        return result;
    }

    // Extension of the convertNumericPinyin function which handles "strings" of multiple syllables
    public String convertNumericPinyinString(String numeric) {
        // Exact same as the above function, but instead of converting to zhuyin, we just replace a given character with
        //  a character featuring the appropriate diacritic mark
        String result = "";
        
        int lastStart = 0;
        for (int i = 0; i < numeric.length(); i++) {
            if (numeric.charAt(i) == ' ') {
                result += " ";
                lastStart = i+1;
            }
            if (Character.isDigit(numeric.charAt(i))) {
                result += convertNumericPinyin(numeric.substring(lastStart,i+1));
                lastStart = i+1;
            }
        }
        // Check if the last character isn't a digit
        if (!Character.isDigit(numeric.charAt(numeric.length()-1))) {
            result += convertNumericPinyin(numeric.substring(lastStart));
        }
        return result;
    }

    // Function to take a "string" of zhuyin syllables and convert it into an equivalent "string" of pinyin syllables
    public String convertZhuyinString(String zhuyin) {
        // Essentially the exact same process as with pinyin, but instead of digits, we deal with tone marks
        String result = "";

        int lastStart = 0;
        for (int i = 0; i < zhuyin.length(); i++) {
            if (zhuyin.charAt(i) == ' ') {
                result += " ";
                lastStart += 1;
            }
            if (isToneMark(zhuyin.charAt(i))) {
                result += convertZhuyin(zhuyin.substring(lastStart,i+1));
                lastStart = i+1;
            }
        }
        // Check if the last character is not a tone mark (i.e. it's the first tone)
        if (!isToneMark(zhuyin.charAt(zhuyin.length()-1))) {
            result += convertZhuyin(zhuyin.substring(lastStart));
        }
        return result;
    }
    public boolean isToneMark(char character) {
        return character == '\u02D9' || character == '\u02C9' || character == '\u02CA' || character == '\u02C7' || character == '\u02CB';
    }
}
