import java.util.ArrayList;
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
    public Word(String transcription, String simplified, String traditional, boolean zhuyin, String english) {
        this.english = new ArrayList<String>();
        this.converter = new Conversions();

        // Need to parse the English
        String words[] = english.split("\\|/");
        for (String word : words) {
            this.english.add(word);
        }

        // Check which system was used for the sound transcription and convert as is appropriate
        if (zhuyin) {
            this.zhuyin = transcription;
            this.pinyin = converter.convertZhuyinString(transcription);
        } else {
            this.pinyin = converter.convertNumericPinyinString(transcription);
            this.zhuyin = converter.convertPinyinString(transcription);
        }

        // Set the characters
        this.simplified = simplified;
        this.traditional = traditional;
    }
    // Constructor for reading from files (avoids unnecessary computation from translating pinyin --> zhuyin or vice versa)
    public Word(String traditional, String simplified, String zhuyin, String pinyin, ArrayList<String> english) {
        this.traditional = traditional;
        this.simplified = simplified;
        this.zhuyin = zhuyin;
        this.pinyin = pinyin;
        this.english = english;
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

    // toString method, mainly for debugging
    public String toString() {
        String retval = this.traditional + "\t" + this.simplified + "\n" + this.zhuyin + "\t" + this.pinyin + "\n";
        for (int i = 0; i < this.english.size(); i++) {
            if (i < this.english.size()-1) {
                retval += "\\";
            }
            retval += this.english.get(i);
        }
        return retval;
    }
    // toString method for the English meanings, only for use in GUIs
    public String getEnglishString() {
        String retval = "";
        for (int i = 0; i < this.english.size(); i++) {
            retval += this.english.get(i);
            if (i < this.english.size()-1) {
                retval += "\\";
            }
        }
        return retval;
    }
    
    // Methods for studying that test whether input given was acceptable
    public boolean matchEnglish(String answer) {
        for (String word : this.english) {
            if (word.equalsIgnoreCase(answer)) {
                return true;
            }
        }
        return false;
    }
    public boolean matchChinese(String answer) {
        return this.traditional.equalsIgnoreCase(answer) || this.simplified.equalsIgnoreCase(answer);
    }
}
