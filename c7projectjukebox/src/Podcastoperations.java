import java.sql.*;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class Podcastoperations {
    List<Podcast> podcastlist;
    public Podcastoperations()
    {
        podcastlist=fetchdata();
    }
    public void checkpodcast(Podcast pc) {
        long podcastid = 0l;
        Optional<Podcast> podcheck=podcastlist.stream().filter(p -> (p.getPodcast_name().equalsIgnoreCase(pc.getPodcast_name())
                && p.getCelebrity_name().equalsIgnoreCase(pc.getCelebrity_name()) && p.getNarrator_name().equalsIgnoreCase(pc.getNarrator_name()))).findAny();
        if(podcheck.isPresent())
        {
            List <Podcast> podcheck1=podcastlist.stream().filter(p -> (p.getPodcast_name().equalsIgnoreCase(pc.getPodcast_name())
                    &&  p.getCelebrity_name().equalsIgnoreCase(pc.getCelebrity_name()) && p.getNarrator_name().equalsIgnoreCase(pc.getNarrator_name()))).collect(Collectors.toList());
            System.out.println("Podcast Title already exist");
            addpodcastepisode(podcheck1.get(0).getPodcast_id(),pc);
        }
        else
        {
            addpodcast(pc);
        }
    }
    private void addpodcast(Podcast pc)
    {
        long podcastid=0l;
        long cid = getcelebrityid(pc.getCelebrity_name());
        long nid = getnarratorid(pc.getNarrator_name());
        long genreid = getgenre(pc.getGenres_name());
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/jukebox", "root", "vskumar7796");
            String query1 = "insert into podcast_details(podcast_name,narrator_id,celebrity_id,number_of_episodes,genres_id) values(?,?,?,?,?)";
            PreparedStatement ps1 = con.prepareStatement(query1, Statement.RETURN_GENERATED_KEYS);
            ps1.setString(1, pc.getPodcast_name());
            ps1.setLong(2, nid);
            ps1.setLong(3, cid);
            ps1.setInt(4, pc.getNumber_of_episodes());
            ps1.setLong(5, genreid);
            int res = ps1.executeUpdate();
            if (res == 1) {
                ResultSet rs1 = ps1.getGeneratedKeys();
                if (rs1.next()) {
                    podcastid = rs1.getLong(1);
                    System.out.println("Podcast Title added with id" + rs1.getLong(1));
                    addpodcastepisode(podcastid, pc);
                }
            }
        }
        catch(Exception e){
            System.out.println(e);
        }
    }
    private long getcelebrityid(String name)
    {
        Long cid=0l;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/jukebox", "root", "vskumar7796");
            String query = "select celebrity_id from celebrity_details where celebrity_name=?";
            PreparedStatement ps=con.prepareStatement(query);
            ps.setString(1,name);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                cid=(rs.getLong(1));
            }
            if(cid!=0)
            {
                return cid;
            }
            else if(cid==0)
            {
                cid=addcelebrityid(name);
            }
            ps.close();
            con.close();
        } catch (Exception e)
        {
            System.out.println(e);
        }
        return cid;
    }
    private long addcelebrityid(String name)
    {
        long cid=0l;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/jukebox", "root", "vskumar7796");
            String query1 = "insert into celebrity_details (celebrity_name) values(?)";
            PreparedStatement ps1=con.prepareStatement(query1,Statement.RETURN_GENERATED_KEYS);
            ps1.setString(1,name);
            int res=ps1.executeUpdate();
            if(res==1)
            {
                ResultSet rs1=ps1.getGeneratedKeys();
                if(rs1.next())
                {
                    System.out.println("Celebrity added");
                    cid= rs1.getLong(1);
                }
            }
        } catch (Exception e)
        {
            System.out.println(e);
        }
        return cid;
    }
    private long getnarratorid(String name)
    {
        Long nid=0l;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/jukebox", "root", "vskumar7796");
            String query = "select narrator_id from narrator_details where narrator_name=?";
            PreparedStatement ps=con.prepareStatement(query);
            ps.setString(1,name);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                nid=(rs.getLong(1));
            }
            if(nid!=0)
            {
                return nid;
            }
            else if(nid==0)
            {
                nid=addnarrator(name);
            }
            ps.close();
            con.close();
        } catch (Exception e)
        {
            System.out.println(e);
        }
        return nid;
    }
    private long addnarrator(String name)
    {
        long nid=0l;
        try
        {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/jukebox", "root", "vskumar7796");
            String query1 = "insert into narrator_details (narrator_name) values(?)";
            PreparedStatement ps1=con.prepareStatement(query1,Statement.RETURN_GENERATED_KEYS);
            ps1.setString(1,name);
            int res=ps1.executeUpdate();
            if(res==1)
            {
                ResultSet rs1=ps1.getGeneratedKeys();
                if(rs1.next())
                {
                    System.out.println("Narrator added");
                    nid= rs1.getLong(1);
                }
            }
            ps1.close();
            con.close();
        } catch (Exception e)
        {
            System.out.println(e);
        }
        return  nid;
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
    private void addpodcastepisode(long cid,Podcast pc)
    {
        Optional<Podcast> podcastlistcheck = podcastlist.stream().filter(p -> (p.getPodcast_name().equalsIgnoreCase(pc.getPodcast_name())
                && p.getPodcast_episode_name().equalsIgnoreCase(pc.getPodcast_episode_name()) && p.getCelebrity_name().equalsIgnoreCase(pc.getCelebrity_name())
                && p.getNarrator_name().equalsIgnoreCase(pc.getNarrator_name()))).findAny();
        if (podcastlistcheck.isPresent()) {
            System.out.println("Podcast episode already exist");
        } else {
            try {
                Class.forName("com.mysql.cj.jdbc.Driver");
                Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/jukebox", "root", "vskumar7796");
                String query1 = "insert into podcast_episode_details(podcast_episode_number,podcast_episode_name,short_description,duration_in_min,podcast_id,upload_date) values(?,?,?,?,?,?)";
                PreparedStatement ps = con.prepareStatement(query1, Statement.RETURN_GENERATED_KEYS);
                ps.setInt(1, pc.getPodcast_episode_number());
                ps.setString(2, pc.getPodcast_episode_name());
                ps.setString(3, pc.getShort_description());
                ps.setString(4, pc.getDuration_in_min());
                ps.setLong(5, cid);
                ps.setString(6, pc.getUpload_date());
                int res = ps.executeUpdate();
                if (res == 1) {
                    ResultSet rs1 = ps.getGeneratedKeys();
                    if (rs1.next()) {
                        System.out.println("Podcast episode added with id" + rs1.getLong(1));
                        fetchdata();
                    }
                }
                ps.close();
                con.close();
            } catch (Exception e) {
                System.out.println(e);
            }
        }
    }
    public List<Podcast> fetchdata()
    {
        podcastlist=new ArrayList<Podcast>();
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/jukebox", "root", "vskumar7796");
            String query = "select * from vw_podcast_details";
            Statement st=con.createStatement();
            ResultSet rs=st.executeQuery(query);
            while(rs.next())
            {
                Podcast pod=new Podcast(rs.getLong(1),rs.getString(2),rs.getInt(3),
                        rs.getString(4),rs.getString(5),rs.getString(6),
                        rs.getString(7),rs.getString(8),rs.getString(9),rs.getString(10),rs.getInt(11));
                podcastlist.add(pod);
            }
            rs.close();
            st.close();
            con.close();
        }
        catch (Exception e)
        {
            System.out.println(e);
        }
        return podcastlist;
    }
    public void displaypod()
    {
        podcastlist.stream().forEach(System.out::println);
    }
    public void searchpodcastname(String podcastname)
    {
        if(podcastlist.size()!=0)
        {
            Optional<Podcast> podcheck=podcastlist.stream().filter(p->(p.getPodcast_name().trim().equalsIgnoreCase(podcastname.trim()))).findAny();
            if(podcheck.isPresent())
            {
                podcastlist.stream().filter(p->(p.getPodcast_name().trim().equalsIgnoreCase(podcastname.trim()))).sorted(Comparator.comparing(Podcast::getPodcast_name)).forEach(System.out::println);
            }
            else
            {
                System.out.println("Podcast not present");
            }
        }
    }
    public void searchepiname(String episodename)
    {
        if(podcastlist.size()!=0)
        {
            Optional<Podcast> podcheck=podcastlist.stream().filter(p->(p.getPodcast_episode_name().equalsIgnoreCase(episodename))).findAny();
            if(podcheck.isPresent())
            {
                podcastlist.stream().filter(p->(p.getPodcast_episode_name().equalsIgnoreCase(episodename))).sorted(Comparator.comparing(Podcast::getPodcast_name)).forEach(System.out::println);
            }
            else
            {
                System.out.println("Podcast not present");
            }
        }
    }
    public void searchcelibrity(String celibritynamme)
    {
        if(podcastlist.size()!=0)
        {
            Optional<Podcast> podcheck=podcastlist.stream().filter(p->(p.getCelebrity_name().equalsIgnoreCase(celibritynamme))).findAny();
            if(podcheck.isPresent())
            {
                podcastlist.stream().filter(p->(p.getCelebrity_name().equalsIgnoreCase(celibritynamme))).sorted(Comparator.comparing(Podcast::getPodcast_name)).forEach(System.out::println);
            }
            else
            {
                System.out.println("Podcast not present");
            }
        }
    }
    public void searchnarratorname(String narratorname)
    {
        if(podcastlist.size()!=0)
        {
            Optional<Podcast> podcheck=podcastlist.stream().filter(p->(p.getNarrator_name().equalsIgnoreCase(narratorname))).findAny();
            if(podcheck.isPresent())
            {
                podcastlist.stream().filter(p->(p.getNarrator_name().equalsIgnoreCase(narratorname))).sorted(Comparator.comparing(Podcast::getPodcast_name)).forEach(System.out::println);
            }
            else
            {
                System.out.println("Podcast not present");
            }
        }
    }
    public void searchgenre(String genrename)
    {
        if(podcastlist.size()!=0)
        {
            Optional<Podcast> podcheck=podcastlist.stream().filter(p->(p.getGenres_name().equalsIgnoreCase(genrename))).findAny();
            if(podcheck.isPresent())
            {
                podcastlist.stream().filter(p->(p.getGenres_name().equalsIgnoreCase(genrename))).sorted(Comparator.comparing(Podcast::getPodcast_name)).forEach(System.out::println);
            }
            else
            {
                System.out.println("Podcast not present");
            }
        }
    }
    public void searchdate(String date)
    {
        if(podcastlist.size()!=0)
        {
            Optional<Podcast> podcheck=podcastlist.stream().filter(p->(p.getUpload_date().equalsIgnoreCase(date))).findAny();
            if(podcheck.isPresent())
            {
                podcastlist.stream().filter(p->(p.getUpload_date().equalsIgnoreCase(date))).sorted(Comparator.comparing(Podcast::getPodcast_name)).forEach(System.out::println);
            }
            else
            {
                System.out.println("Podcast not present");
            }
        }
    }
    public void searchbyepno(int number)
    {
        if(podcastlist.size()!=0)
        {
            Optional<Podcast> podcheck=podcastlist.stream().filter(p->(p.getPodcast_episode_number()==number)).findAny();
            if(podcheck.isPresent())
            {
                podcastlist.stream().filter(p->(p.getPodcast_episode_number()==number)).sorted(Comparator.comparing(Podcast::getPodcast_name)).forEach(System.out::println);
            }
            else
            {
                System.out.println("Podcast not present");
            }
        }
    }
    public void searchbyany(String any)
    {
        if(podcastlist.size()!=0)
        {
            Optional<Podcast> podcheck=podcastlist.stream().filter(p -> (p.getPodcast_name().equalsIgnoreCase(any)
                    || p.getPodcast_episode_name().equalsIgnoreCase(any) || p.getCelebrity_name().equalsIgnoreCase(any) || p.getNarrator_name().equalsIgnoreCase(any))).findAny();
            if(podcheck.isPresent())
            {
                podcastlist.stream().filter(p -> (p.getPodcast_name().equalsIgnoreCase(any)
                        || p.getPodcast_episode_name().equalsIgnoreCase(any) || p.getCelebrity_name().equalsIgnoreCase(any) || p.getNarrator_name().equalsIgnoreCase(any))).forEach(System.out::println);
            }
            else
            {
                System.out.println("Podcast not present");
            }
        }
    }
}
