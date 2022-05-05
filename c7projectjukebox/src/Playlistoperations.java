import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class Playlistoperations {
    List<Playlist> playlist;
    List<Song> listsong;
    List<Podcast> listpodcast;
    Songoperations so=new Songoperations();
    Podcastoperations po=new Podcastoperations();
    public Playlistoperations()
    {

        playlist=fetchplaylist();
        listsong=so.fetchdata();
        listpodcast=po.fetchdata();
    }
    public long creatplaylist(String name) {
        long playlistid = 0l;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/jukebox", "root", "vskumar7796");
            String query = "select playlist_id from playlist_list_temp where playlist_name=?";
            PreparedStatement ps = con.prepareStatement(query);
            ps.setString(1, name);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                playlistid = (rs.getLong(1));
            }
            if (playlistid != 0) {
                System.out.println("playlist already exist");
            } else if (playlistid == 0) {
                playlistid = addplaylist(name);
            }
            ps.close();
            con.close();
        } catch (Exception e) {
            System.out.println(e);
        }
        return playlistid;
    }
    private long addplaylist(String name)
    {
        long playlistid=0l;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/jukebox", "root", "vskumar7796");
            String query1 = "insert into playlist_list_temp(playlist_name) values(?)";
            PreparedStatement ps1 = con.prepareStatement(query1, Statement.RETURN_GENERATED_KEYS);
            ps1.setString(1, name);
            int res = ps1.executeUpdate();
            if (res == 1) {
                ResultSet rs1 = ps1.getGeneratedKeys();
                if (rs1.next()) {
                    playlistid = rs1.getLong(1);
                    System.out.println("Playlist added with id" + rs1.getLong(1));

                }
            }
        }
        catch(Exception e){
            System.out.println(e);
        }
        return playlistid;
    }
    private long getplaylistid(String name)
    {
        long playlistid = 0l;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/jukebox", "root", "vskumar7796");
            String query = "select playlist_id from playlist_list_temp where playlist_name=?";
            PreparedStatement ps = con.prepareStatement(query);
            ps.setString(1, name);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                playlistid = (rs.getLong(1));
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        return playlistid;
    }
    private long getsongid(String name,long albumid)
    {
        long trackid = 0l;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/jukebox", "root", "vskumar7796");
            String query1 = "select song_id from song_details where song_name=? and album_id=?";
            PreparedStatement ps1 = con.prepareStatement(query1);
            ps1.setString(1, name);
            ps1.setLong(2, albumid);
            ResultSet rs1 = ps1.executeQuery();
            while (rs1.next()) {
                trackid = (rs1.getLong(1));
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        return trackid;
    }
    public void addsongtolist(String name,Playlist songname)
        {
            List<Song> getsong;
            long albumid = 0l;
            long trackid = 0l;
            long playlistid = getplaylistid(name);
            if (playlistid != 0) {
                Optional<Song> check = listsong.stream().filter(p -> (p.getSongname().equalsIgnoreCase(songname.getTrack_name()))).findAny();
                if (check.isPresent()) {
                    getsong = listsong.stream().filter(p -> (p.getSongname().equalsIgnoreCase(songname.getTrack_name()))).collect(Collectors.toList());
                    for (Song s : getsong) {
                        Optional<Playlist> check1 = playlist.stream().filter(p -> (p.getPlaylist_name().equalsIgnoreCase(name) && p.getTrack_name().equalsIgnoreCase(s.getSongname()) && p.getAlbum_name().equalsIgnoreCase(s.getAlbumname()) && p.getArtist_name().equalsIgnoreCase(s.getArtistname()))).findAny();
                        if (check1.isPresent()) {
                            System.out.println("Song already exist in playlist");
                        } else {
                            albumid = so.getalbum(s.getAlbumname(), s.getYear());
                            trackid=getsongid(songname.getTrack_name(),albumid);
                            insertsongintoplaylist(playlistid, albumid, trackid, s.getAlbumname(), s.getSongname(), s.getArtistname(), s.getDuration());
                        }
                    }
                } else {
                    System.out.println("Song not available");
                }
            } else if (playlistid == 0) {
                System.out.println("playlist not available creating new");
                addplaylist(name);
                addsongtolist(name, songname);
            }
        }
    private void insertsongintoplaylist(long playlistid,long albumid,long trackid,String albumname,String trackname,String artistname,String duration)
    {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/jukebox", "root", "vskumar7796");
            String query1 = "insert into playlist_details_temp2(playlist_id,album_id,track_id,album_name,track_name,artist_name,duration_in_min) values(?,?,?,?,?,?,?)";
            PreparedStatement ps1 = con.prepareStatement(query1);
            ps1.setLong(1, playlistid);
            ps1.setLong(2, albumid);
            ps1.setLong(3, trackid);
            ps1.setString(4, albumname);
            ps1.setString(5, trackname);
            ps1.setString(6, artistname);
            ps1.setString(7, duration);
            int res = ps1.executeUpdate();
            if (res == 1) {
                System.out.println("Song added");
            }
        }
        catch(Exception e){
            System.out.println(e);
        }
    }
    public void addsongtolistbyalbum(String name,String albumname)
    {
        List<Song> getsong;
        long albumid = 0l;
        long trackid = 0l;
        long playlistid = getplaylistid(name);
        if (playlistid != 0) {
            getsong = listsong.stream().filter(p -> (p.getAlbumname().equalsIgnoreCase(albumname))).collect(Collectors.toList());
            for (Song s : getsong) {
                Optional<Playlist> check1 = playlist.stream().filter(p -> (p.getPlaylist_name().equalsIgnoreCase(name) && p.getAlbum_name().equalsIgnoreCase(albumname) && p.getTrack_name().equalsIgnoreCase(s.getSongname()))).findAny();
                if (check1.isPresent()) {
                    System.out.println("Song exist in playlist");
                } else {
                    albumid = so.getalbum(s.getAlbumname(), s.getYear());
                    trackid = getsongid(s.getSongname(), albumid);
                    insertsongintoplaylist(playlistid, albumid, trackid, albumname, s.getSongname(), s.getArtistname(), s.getDuration());
                }
            }
        }else if (playlistid == 0) {
                System.out.println("playlist not available creating new");
                addplaylist(name);
            addsongtolistbyalbum(name, albumname);
            }
            }
    public void addsongtolistbyartist(String name,String artistname)
    {
        List<Song> getsong;
        long albumid = 0l;
        long trackid = 0l;
        long playlistid = getplaylistid(name);
        if (playlistid != 0) {
                getsong=listsong.stream().filter(p->(p.getArtistname().equalsIgnoreCase(artistname))).collect(Collectors.toList());
                for(Song s:getsong)
                {
                    Optional<Playlist> check1 = playlist.stream().filter(p -> (p.getPlaylist_name().equalsIgnoreCase(name) && p.getArtist_name().equalsIgnoreCase(artistname) && p.getTrack_name().equalsIgnoreCase(s.getSongname()))).findAny();
                    if (check1.isPresent())
                    {
                        System.out.println("Song exist in playlist");
                    } else {
                        albumid = so.getalbum(s.getAlbumname(), s.getYear());
                        trackid=getsongid(s.getSongname(),albumid);
                        insertsongintoplaylist(playlistid, albumid, trackid, s.getAlbumname(), s.getSongname(), artistname, s.getDuration());
                    }
                }
    }else if (playlistid == 0) {
        System.out.println("playlist not available creating new");
        addplaylist(name);
        addsongtolistbyartist(name, artistname);
    }
    }
    private long getepisodeid(String name,long podid)
    {
        long trackid=0l;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/jukebox", "root", "vskumar7796");
        String query1 = "select podcast_episode_id from podcast_episode_details where podcast_episode_name=? and podcast_id=?";
        PreparedStatement ps1 = con.prepareStatement(query1);
        ps1.setString(1,name);
        ps1.setLong(2, podid);
        ResultSet rs1 = ps1.executeQuery();
        while (rs1.next()) {
            trackid = (rs1.getLong(1));

        }
        }
        catch(Exception e){
            System.out.println(e);
        }
        return trackid;
    }
    public void addpodcasttolist(String name,Playlist episodename)
    {
        List<Podcast> getpod=new ArrayList<Podcast>();
        long podid=0l;
        long trackid=0l;
        long playlistid=getplaylistid(name);
            if (playlistid != 0) {
                Optional<Podcast> check = listpodcast.stream().filter(p -> (p.getPodcast_episode_name().equalsIgnoreCase(episodename.getTrack_name()))).findAny();
                if (check.isPresent()) {
                    getpod = listpodcast.stream().filter(p -> (p.getPodcast_episode_name().equalsIgnoreCase(episodename.getTrack_name()))).collect(Collectors.toList());
                    for (Podcast pod : getpod) {
                        Optional<Playlist> check1 = playlist.stream().filter(p -> (p.getPlaylist_name().equalsIgnoreCase(name) && p.getTrack_name().equalsIgnoreCase(episodename.getTrack_name()) && p.getAlbum_name().equalsIgnoreCase(pod.getPodcast_name()) && p.getArtist_name().equalsIgnoreCase(pod.getCelebrity_name()))).findAny();
                        if (check1.isPresent()) {
                            System.out.println("Episode already exist in playlist");
                        } else {
                            podid = pod.getPodcast_id();
                            trackid=getepisodeid(episodename.getTrack_name(),podid);
                            insertpodcastintoplaylist(playlistid, podid, trackid, pod.getPodcast_name(), pod.getPodcast_episode_name(), pod.getCelebrity_name(), pod.getDuration_in_min());
                        }
                    }
                }
                else {
                    System.out.println("podcast not available");
                }
            } else if (playlistid == 0) {
                System.out.println("playlist not available creating new");
                addplaylist(name);
                addpodcasttolist(name,episodename);
            }
    }
    private void insertpodcastintoplaylist(long playlistid,long albumid,long trackid,String albumname,String trackname,String artistname,String duration)
    {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/jukebox", "root", "vskumar7796");
            String query1 = "insert into playlist_details_temp2(playlist_id,album_id,track_id,album_name,track_name,artist_name,duration_in_min) values(?,?,?,?,?,?,?)";
            PreparedStatement ps1 = con.prepareStatement(query1);
            ps1.setLong(1, playlistid);
            ps1.setLong(2, albumid);
            ps1.setLong(3, trackid);
            ps1.setString(4, albumname);
            ps1.setString(5, trackname);
            ps1.setString(6, artistname);
            ps1.setString(7, duration);
            int res = ps1.executeUpdate();
            if (res == 1) {
                System.out.println("Podcast added");
            }
        }
        catch(Exception e){
            System.out.println(e);
        }
    }
    public void addsongtolistbypcastname(String name,String podcastname)
    {
        List<Podcast> getpod;
        long podid=0l;
        long trackid=0l;
        long playlistid=getplaylistid(name);
        if(playlistid!=0)
        {
        getpod=listpodcast.stream().filter(p->(p.getPodcast_name().equalsIgnoreCase(podcastname))).collect(Collectors.toList());
        for(Podcast pod:getpod)
        {
            Optional<Playlist> check1 = playlist.stream().filter(p -> (p.getPlaylist_name().equalsIgnoreCase(name) && p.getAlbum_name().equalsIgnoreCase(podcastname) && p.getTrack_name().equalsIgnoreCase(pod.getPodcast_episode_name()) && p.getArtist_name().equalsIgnoreCase(pod.getCelebrity_name()))).findAny();
            if (check1.isPresent())
            {
                System.out.println("Episode exist in playlist");
            } else {
                podid = pod.getPodcast_id();
                trackid=getepisodeid(pod.getDuration_in_min(),podid);
                insertpodcastintoplaylist(playlistid, podid, trackid, podcastname, pod.getPodcast_episode_name(), pod.getCelebrity_name(), pod.getDuration_in_min());
            }
        }
    } else if (playlistid == 0) {
        System.out.println("playlist not available creating new");
        addplaylist(name);
            addsongtolistbypcastname(name,podcastname);
    }
    }
    public void addsongtolistbycelebrity(String name,String celebrity)
    {
        List<Podcast> getpod;
        long podid=0l;
        long trackid=0l;
        long playlistid=getplaylistid(name);
        if(playlistid!=0)
        {
        getpod=listpodcast.stream().filter(p->(p.getCelebrity_name().equalsIgnoreCase(celebrity))).collect(Collectors.toList());
        for(Podcast pod:getpod)
        {
            Optional<Playlist> check1 = playlist.stream().filter(p -> (p.getPlaylist_name().equalsIgnoreCase(name) && p.getArtist_name().equalsIgnoreCase(celebrity) && p.getTrack_name().equalsIgnoreCase(pod.getPodcast_episode_name())&& p.getAlbum_name().equalsIgnoreCase(pod.getPodcast_name()))).findAny();
            if (check1.isPresent())
            {
                System.out.println("Episode exist in playlist");
            } else {
                podid = pod.getPodcast_id();
                trackid=getepisodeid(pod.getDuration_in_min(),podid);
                insertpodcastintoplaylist(playlistid, podid, trackid, pod.getPodcast_name(), pod.getPodcast_episode_name(), celebrity, pod.getDuration_in_min());
            }
        }
    } else if (playlistid == 0) {
        System.out.println("playlist not available creating new");
        addplaylist(name);
        addsongtolistbycelebrity(name,celebrity);
    }
    }
    public List<Playlist> fetchplaylist()
    {
        playlist=new ArrayList<>();
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/jukebox", "root", "vskumar7796");
            String query = "select * from vw_playlist_details_temp2";
            Statement st=con.createStatement();
            ResultSet rs=st.executeQuery(query);
            while(rs.next())
            {
                Playlist pl=new Playlist(rs.getString(1),rs.getString(2),rs.getString(3),
                        rs.getString(4),rs.getString(5));
                playlist.add(pl);
            }
            rs.close();
            st.close();
            con.close();
        }
        catch (Exception e)
        {
            System.out.println(e);
        }
        return playlist;
    }
    public void display(String name)
    {
        Optional<Playlist> check1 = playlist.stream().filter(p -> (p.getPlaylist_name().equalsIgnoreCase(name))).findAny();
        if(check1.isPresent())
        {
            playlist.stream().filter(p -> (p.getPlaylist_name().equalsIgnoreCase(name))).forEach(System.out::println);
        }
    }
}
