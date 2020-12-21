import java.util.ArrayList;
enum CharacterSet {
    SIMPLIFIED,
    TRADITIONAL;
}
public class Word {

    // Every word (at least in our abstraction, this is not meant to fit the linguistic model) has:
    //      1. A set of English equivalents/translations (so a collection of strings)
    //      2. A transcription in (so two strings, one for each)
    //          a. pinyin
    //          b. zhuyin
    //      3. A string of one or more Chinese characters (so obviously a single string)
    private ArrayList<String> english;
    private String pinyin;
    private String zhuyin;
    private String simplified;
    private String traditional;

    // For doing conversions
    private Conversions converter;

    // Make this more sophisticated later to take a transcription instead of either pinyin or zhuyin and a character, then convert
    // converting pinyin --> zhuyin or zhuyin --> pinyin is simple, converting the character tricky
    public Word(CharacterSet type, String transcription, String characters) {
        // Base
        this.english = new ArrayList<String>();
        this.converter = new Conversions();
        if (type == CharacterSet.SIMPLIFIED) {
            initializeTraditional(transcription, characters);
        } else {
            initializeSimplified(transcription, characters);
        }
    }

    // Initialization for traditional character input
    private void initializeTraditional(String zhuyin, String traditional) {
        // Easy part
        this.zhuyin = zhuyin;
        this.traditional = traditional;
        // Conversion part
        this.pinyin = converter.convertZhuyin(zhuyin);
        
    }
    // Initialization for simplified character input
    private void initializeSimplified(String pinyin, String simplified) {
        // Easy part
        this.pinyin = this.converter.convertNumericPinyin(pinyin);
        this.simplified = simplified;
        // Conversion part
        this.zhuyin = converter.convertPinyin(pinyin);
        // Convert the characters, whew, this'll be difficult
    }
    // Accessors and mutators for the different fields
    public ArrayList<String> getEnglish() { return this.english; }
    public String getPinyin() { return this.pinyin; }
    public String getZhuyin() { return this.zhuyin; }
    public String getSimplified() { return this.simplified; }
    public String getTraditional() { return this.traditional; }
    public void setEnglish(ArrayList<String> english) { this.english = english; }
    public void addEnglish(String word) { this.english.add(word); }
    public void setPinyin(String pinyin) { this.pinyin = pinyin; }
    public void setZhuyin(String zhuyin) { this.zhuyin = zhuyin; }
    public void setSimplified(String simplified) { this.simplified = simplified; }
    public void setTraditional(String traditional) { this.traditional = traditional; }
}
