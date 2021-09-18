package niuedu.com.andfirststep;

/**
 * Created by nkm on 2017/8/25.
 */

public class MusicInfo {
    private String singer;//歌手名
    private String title;//歌曲名
    private int like;//星级

    public MusicInfo(String singer, String title, int like) {
        this.singer = singer;
        this.title = title;
        this.like = like;
    }

    public String getSinger() {
        return singer;
    }

    public void setSinger(String singer) {
        this.singer = singer;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getLike() {
        return like;
    }

    public void setLike(int like) {
        this.like = like;
    }
}
