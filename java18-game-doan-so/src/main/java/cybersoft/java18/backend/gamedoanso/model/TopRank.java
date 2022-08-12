package cybersoft.java18.backend.gamedoanso.model;

public class TopRank {
    private String name;
    private int guessTimes;

    public TopRank(String name, int guessTimes) {
        this.name = name;
        this.guessTimes = guessTimes;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getGuessTimes() {
        return guessTimes;
    }

    public void setGuessTimes(int guessTimes) {
        this.guessTimes = guessTimes;
    }
}
