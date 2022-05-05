public class Song {
    private String songname;
    private String albumname;
    private String artistname;
    private String duration;
    private String year;
    private String genrename;

    public Song(String songname,String albumname,String artistname,String duration,String year,String genrename)
    {
        this.songname=songname;
        this.albumname=albumname;
        this.artistname=artistname;
        this.duration=duration;
        this.year=year;
        this.genrename=genrename;
    }


    public String getSongname() {
        return songname;
    }

    public void setSongname(String songname) {
        this.songname = songname;
    }

    public String getAlbumname() {
        return albumname;
    }

    public void setAlbumname(String albumname) {
        this.albumname = albumname;
    }

    public String getArtistname() {
        return artistname;
    }

    public void setArtistname(String artistname) {
        this.artistname = artistname;
    }

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public String getGenrename() {
        return genrename;
    }

    public void setGenrename(String genrename) {
        this.genrename = genrename;
    }
    @Override
    public String toString()
    {
        return songname+","+albumname+","+artistname+","+duration+","+year+","+genrename;
    }
}
