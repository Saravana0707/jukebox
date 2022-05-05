import java.sql.*;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.function.Consumer;

public class Songoperations {
    List<Song> listsong;
    Songoperations()
    {
        listsong=fetchdata();
    }
    public void addsong(Song song) {
        Optional<Song> listsongcheck = listsong.stream().filter(p -> (p.getSongname().equalsIgnoreCase(song.getSongname())
                && p.getAlbumname().equalsIgnoreCase(song.getAlbumname()) && p.getGenrename().equalsIgnoreCase(song.getGenrename()) && p.getArtistname().equalsIgnoreCase(song.getArtistname()))).findAny();
        if (listsongcheck.isPresent()) {
            System.out.println("Song already exist");
        } else {
            long artistid = getartistid(song.getArtistname());
            long albumid = getalbum(song.getAlbumname(), song.getYear());
            long genreid = getgenre(song.getGenrename());

            try {
                Class.forName("com.mysql.cj.jdbc.Driver");
                Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/jukebox", "root", "vskumar7796");
                String query1 = "insert into song_details(song_name,artist_id,album_id,genres_id,duration) values(?,?,?,?,?)";
                PreparedStatement ps1 = con.prepareStatement(query1, Statement.RETURN_GENERATED_KEYS);
                ps1.setString(1, song.getSongname());
                ps1.setLong(2, artistid);
                ps1.setLong(3, albumid);
                ps1.setLong(4, genreid);
                ps1.setString(5, song.getDuration());
                int res = ps1.executeUpdate();
                if (res == 1) {
                    ResultSet rs1 = ps1.getGeneratedKeys();
                    if (rs1.next()) {
                        System.out.println("Song added with id" + rs1.getLong(1));
                        fetchdata();
                    }
                }
                ps1.close();
                con.close();
            } catch (Exception e) {
                System.out.println(e);
            }
        }
    }
    private long getartistid(String artistname)
    {
        Long aid=0l;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/jukebox", "root", "vskumar7796");
            String query = "select artist_id from artist_details where artist_name=?";
            PreparedStatement ps=con.prepareStatement(query);
            ps.setString(1,artistname);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                aid=rs.getLong(1);
            }
            if(aid!=0)
            {
                return aid;
            }
            else if(aid==0)
            {
                aid=addartist(artistname);
            }
            ps.close();
            con.close();
        } catch (Exception e)
        {
            System.out.println(e);
        }
        return aid;
    }
    private long addartist(String artistname)
    {
        long aid=0l;
        try
        {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/jukebox", "root", "vskumar7796");
            String query1 = "insert into artist_details (artist_name)  values(?)";
            PreparedStatement ps1=con.prepareStatement(query1,Statement.RETURN_GENERATED_KEYS);
            ps1.setString(1,artistname);
            int res=ps1.executeUpdate();
            if(res==1)
            {
                ResultSet rs1=ps1.getGeneratedKeys();
                if(rs1.next())
                {
                    System.out.println("Artist added");
                    aid= rs1.getLong(1);
                }
            }
            ps1.close();
            con.close();
        }
        catch (Exception e)
        {
            System.out.println(e);
        }
        return aid;
    }
    public long getalbum(String albumname,String year)
    {
        Long aid=0l;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/jukebox", "root", "vskumar7796");
            String query = "select album_id from album_details where album_name=? and year_released=?";
            PreparedStatement ps=con.prepareStatement(query);
            ps.setString(1,albumname);
            ps.setString(2,year);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                aid=(rs.getLong(1));
            }
            if(aid!=0)
            {
                return aid;
            }
            else if(aid==0)
            {
                aid=addalbum(albumname,year);

            }
            ps.close();
            con.close();
        } catch (Exception e)
        {
            System.out.println(e);
        }
        return aid;
    }
    private long addalbum(String albumname,String year)
    {
        long aid=0l;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/jukebox", "root", "vskumar7796");
            String query1 = "insert into album_details (album_name,year_released) values(?,?)";
            PreparedStatement ps1 = con.prepareStatement(query1, Statement.RETURN_GENERATED_KEYS);
            ps1.setString(1, albumname);
            ps1.setString(2, year);
            int res = ps1.executeUpdate();
            if (res == 1) {
                ResultSet rs1 = ps1.getGeneratedKeys();
                if (rs1.next()) {
                    System.out.println("Album added");
                    aid = rs1.getLong(1);
                }
            }
            ps1.close();
            con.close();
        }
        catch (Exception e)
        {
            System.out.println(e);
        }
        return aid;
    }
    private long getgenre(String genre)
    {
        Long gid=0l;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/jukebox", "root", "vskumar7796");
            String query = "select genres_id from genres_details where genres_name=?";
            PreparedStatement ps=con.prepareStatement(query);
            ps.setString(1,genre);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                gid=(rs.getLong(1));
            }
            if(gid!=0)
            {
                return gid;
            }
            else if(gid==0)
            {
                gid=addgenre(genre);
            }
            ps.close();
            con.close();
        } catch (Exception e)
        {
            System.out.println(e);
        }
        return gid;
    }
    private long addgenre(String genre)
    {
        long gid=0l;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/jukebox", "root", "vskumar7796");
            String query1 = "insert into genres_details (genres_name) values(?)";
            PreparedStatement ps1=con.prepareStatement(query1,Statement.RETURN_GENERATED_KEYS);
            ps1.setString(1,genre);
            int res=ps1.executeUpdate();
            if(res==1)
            {
                ResultSet rs1=ps1.getGeneratedKeys();
                if(rs1.next())
                {
                    System.out.println("Genre added");
                    gid= rs1.getLong(1);
                }
            }
            ps1.close();
            con.close();
        }
        catch (Exception e)
        {
            System.out.println(e);
        }
        return gid;
    }
    public void displaysongs()
    {
        listsong.stream().forEach(System.out::println);
    }
    public List<Song> fetchdata()
    {
        listsong=new ArrayList<Song>();
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/jukebox", "root", "vskumar7796");
            String query = "select * from vw_song_details";
            Statement st=con.createStatement();
            ResultSet rs=st.executeQuery(query);
            while(rs.next())
            {
                Song song=new Song(rs.getString(1),rs.getString(2),rs.getString(3),
                        rs.getString(4),rs.getString(5),rs.getString(6));
                listsong.add(song);
            }
            rs.close();
            st.close();
            con.close();
        }
        catch (Exception e)
        {
            System.out.println(e);
        }
        return listsong;
    }
    public void searchsongname(String songname)
    {
        if(listsong.size()!=0)
        {
            Optional<Song> songcheck=listsong.stream().filter(p->(p.getSongname().equalsIgnoreCase(songname))).findAny();
            if(songcheck.isPresent())
            {
                listsong.stream().filter(p->(p.getSongname().equalsIgnoreCase(songname))).sorted(Comparator.comparing(Song::getSongname)).forEach(System.out::println);
            }
            else
            {
                System.out.println("Song not present");
            }
        }
    }
    public void searchartistname(String artistname)
    {

        if(listsong.size()!=0)
        {
            Optional<Song> songcheck=listsong.stream().filter(p->(p.getArtistname().equalsIgnoreCase(artistname))).findAny();
            if(songcheck.isPresent())
            {
                listsong.stream().filter(p->(p.getArtistname().equalsIgnoreCase(artistname))).sorted(Comparator.comparing(Song::getSongname)).forEach(System.out::println);
            }
            else
            {
                System.out.println("Song not present");
            }
        }
    }
    public void searchalbumname(String albumname)
    {

        if(listsong.size()!=0)
        {
            Optional<Song> songcheck=listsong.stream().filter(p->(p.getAlbumname().equalsIgnoreCase(albumname))).findAny();
            if(songcheck.isPresent())
            {
                listsong.stream().filter(p->(p.getAlbumname().equalsIgnoreCase(albumname))).sorted(Comparator.comparing(Song::getSongname)).forEach(System.out::println);
            }
            else
            {
                System.out.println("Song not present");
            }
        }
    }
    public void searchgenrename(String gener)
    {
        if(listsong.size()!=0)
        {
            Optional<Song> songcheck=listsong.stream().filter(p->(p.getGenrename().equalsIgnoreCase(gener))).findAny();
            if(songcheck.isPresent())
            {
                listsong.stream().filter(p->(p.getGenrename().equalsIgnoreCase(gener))).sorted(Comparator.comparing(Song::getSongname)).forEach(System.out::println);
            }
            else
            {
                System.out.println("Song not present");
            }
        }
    }
    public void searchyear(String year)
    {
        if(listsong.size()!=0)
        {
            Optional<Song> songcheck=listsong.stream().filter(p->(p.getYear().equalsIgnoreCase(year))).findAny();
            if(songcheck.isPresent())
            {
                listsong.stream().filter(p->(p.getYear().equalsIgnoreCase(year))).sorted(Comparator.comparing(Song::getSongname)).forEach(System.out::println);
            }
            else
            {
                System.out.println("Song not present");
            }
        }
    }
    public void searchbyany(String any)
    {
        if(listsong.size()!=0)
        {

            Optional<Song> songcheck=listsong.stream().filter(p -> (p.getSongname().equalsIgnoreCase(any)
                    || p.getAlbumname().equalsIgnoreCase(any) || p.getGenrename().equalsIgnoreCase(any) || p.getArtistname().equalsIgnoreCase(any))).findAny();
            if(songcheck.isPresent())
            {
                listsong.stream().filter(p -> (p.getSongname().equalsIgnoreCase(any)
                        || p.getAlbumname().equalsIgnoreCase(any) || p.getGenrename().equalsIgnoreCase(any) || p.getArtistname().equalsIgnoreCase(any))).forEach(System.out::println);
            }
            else
            {
                System.out.println("Song not present");
            }
        }
    }
}
