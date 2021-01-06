import java.util.ArrayList;
// Named as such because Character was taken
public class Hanzi {
    Conversions converter;

    private char simplified;
    private char traditional;
    private String zhuyin;
    private String pinyin;
    private ArrayList<String> meanings; // the english words and/or phrases which describe the meaning of the traditional

    public Hanzi(char simplified, char traditional, String transcription, boolean zhuyin, String meanings) {
        this.converter = new Conversions();
        this.meanings = new ArrayList<String>();
        this.simplified = simplified;
        this.traditional = traditional;
        if (zhuyin) {
            this.zhuyin = transcription;
            this.pinyin = converter.convertZhuyin(transcription);
        } else {
            this.pinyin = transcription;
            this.zhuyin = converter.convertPinyin(transcription);
        }
        for (String meaning : meanings.split("\\|/")) {
            this.meanings.add(meaning);
        }
    }

    // Accessors
    public char getTraditional() { return this.traditional; }
    public char getSimplified() { return this.simplified; }
    public String getZhuyin() { return this.zhuyin; }
    public String getPinyin() { return this.pinyin; }
    // Mutators
    public void setTraditional(char traditional) { this.traditional = traditional; }
    public void setSimplified(char simplified) { this.simplified = simplified; }
    public void setZhuyin(String zhuyin) { this.zhuyin = zhuyin; }
    public void setPinyin(String pinyin) { this.pinyin = pinyin; }

    // toString method, mainly for consistent printing to files, but formatted in such a way to make debugging easier
    public String toString() {
        String result = Character.toString(this.traditional) + "\t" + Character.toString(this.simplified) + "\n" + this.zhuyin + "\t" + this.pinyin + "\n";
        for (int i = 0; i < this.meanings.size(); i++) {
            result += this.meanings.get(i);
            if (i < this.meanings.size()-1) {
                result += "\\";
            }
        }
        return result;
    }
}
