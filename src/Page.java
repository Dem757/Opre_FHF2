public class Page {
    private int number;
    private char ch;
    private int freezeTime;
    private boolean sc;
    private boolean frozen;

    public Page(char c) {
        number = 0;
        ch = c;
        freezeTime = 4;
        sc = false;
        frozen = true;
    }

    public void setNumber(int n) {
        number = n;
    }

    public void setFreezeTime(int f) {
        freezeTime = f;
    }

    public void setSc(boolean s) {
        sc = s;
    }

    public boolean isSc() {
        return sc;
    }

    public char getCh() {
        return ch;
    }

    public int getFreezeTime() {
        return freezeTime;
    }

    public int getNumber() {
        return number;
    }

    public boolean isFrozen() {
        return frozen;
    }

    public void setFrozen(boolean frozen) {
        this.frozen = frozen;
    }

    public void decreaseF() {
        if(freezeTime > 0)
            freezeTime--;
        if(freezeTime == 0)
            frozen = false;
    }

}
