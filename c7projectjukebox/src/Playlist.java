public class Playlist {
    private String playlist_name;
    private String album_name;
    private String track_name;
    private String artist_name;
    private String duration_in_min;

    public Playlist(String album_name, String track_name,String artist_name) {
        this.album_name = album_name;
        this.track_name = track_name;
        this.artist_name=artist_name;
    }
    public Playlist( String track_name) {
        this.track_name = track_name;
    }

    public Playlist(String playlist_name, String album_name, String track_name, String artist_name, String duration_in_min) {
        this.playlist_name = playlist_name;
        this.album_name = album_name;
        this.track_name = track_name;
        this.artist_name = artist_name;
        this.duration_in_min = duration_in_min;
    }

    public String getPlaylist_name() {
        return playlist_name;
    }

    public void setPlaylist_name(String playlist_name) {
        this.playlist_name = playlist_name;
    }

    public String getAlbum_name() {
        return album_name;
    }

    public void setAlbum_name(String album_name) {
        this.album_name = album_name;
    }

    public String getTrack_name() {
        return track_name;
    }

    public void setTrack_name(String track_name) {
        this.track_name = track_name;
    }

    public String getArtist_name() {
        return artist_name;
    }

    public void setArtist_name(String artist_name) {
        this.artist_name = artist_name;
    }

    public String getDuration_in_min() {
        return duration_in_min;
    }

    public void setDuration_in_min(String duration_in_min) {
        this.duration_in_min = duration_in_min;
    }
    @Override
    public String toString()
    {
        return album_name+","+track_name+","+artist_name+","+duration_in_min;
    }
}
