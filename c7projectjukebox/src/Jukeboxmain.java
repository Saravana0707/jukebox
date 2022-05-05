import java.util.Scanner;

public class Jukeboxmain
{
    public static void main(String [] args)
    {
        Scanner sc=new Scanner(System.in);
        int choice1;
        do {
            System.out.println("Enter 1 for songs or Enter 2 for Podcast or Enter 3 for playlist and Playng songs or Enter 4 for Exit");
             choice1 = sc.nextInt();
            Songoperations so=new Songoperations();
            Podcastoperations podop=new Podcastoperations();
            Playlistoperations plo=new Playlistoperations();
            switch (choice1) {
                case 1: {
                    System.out.println(" Enter 1 for display all songs \n Enter 2 for Album \n Enter 3 for Song name based display \n Enter 4 for Artist based display \n Enter 5 for gener based display \n Enter 6 for year based display");
                    int choice = sc.nextInt();
                    switch (choice) {
                        case 1: {
                            so.displaysongs();
                            break;
                        }
                        case 2: {
                            System.out.println("Enter album name");
                            String album = sc.next();
                            so.searchalbumname(album);
                            break;
                        }
                        case 3: {
                            System.out.println("Enter song name");
                            String song1 = sc.next();
                            so.searchsongname(song1);
                            break;
                        }
                        case 4: {
                            System.out.println("Enter Artist name");
                            String artist = sc.next();
                            so.searchartistname(artist);
                            break;
                        }
                        case 5: {
                            System.out.println("Enter genre name");
                            String genre = sc.next();
                            so.searchgenrename(genre);
                            break;
                        }
                        case 6: {
                            System.out.println("Enter the year");
                            String year = sc.next();
                            so.searchyear(year);
                            break;
                        }
                        default: {
                            System.out.println("Enter the keyword");
                            String keyword = sc.next();
                            so.searchbyany(keyword);
                            break;
                        }
                    }
                    break;
                }
                case 2: {
                    System.out.println(" Enter 1 for display all Postcast \n Enter 2 for Podcastname \n Enter 3 for episode based display \n Enter 4 for Celibrity based display \n Enter 5 for Narrator based display \n Enter 6 for gener based display \n Enter 7 for date based display \n Enter 8 for display based on episode number");
                    int choice = sc.nextInt();
                    switch (choice) {
                        case 1: {
                            podop.displaypod();
                            break;
                        }
                        case 2: {
                            System.out.println("Enter Postcast name");
                            String Postcast = sc.next();
                            podop.searchpodcastname(Postcast);
                            break;
                        }
                        case 3: {
                            System.out.println("Enter episode name");
                            String episode = sc.next();
                            podop.searchepiname(episode);
                            break;
                        }
                        case 4: {
                            System.out.println("Enter celebrity name");
                            String celebrity = sc.next();
                            podop.searchcelibrity(celebrity);
                            break;
                        }
                        case 5: {
                            System.out.println("Enter Narrator name");
                            String narrator = sc.next();
                            podop.searchnarratorname(narrator);
                            break;
                        }
                        case 6: {
                            System.out.println("Enter the genre name");
                            String genre = sc.next();
                            podop.searchgenre(genre);
                            break;
                        }
                        case 7: {
                            System.out.println("Enter date");
                            String date = sc.next();
                            podop.searchdate(date);
                            break;
                        }
                        case 8: {
                            System.out.println("Enter Episode number");
                            int eno = sc.nextInt();
                            podop.searchbyepno(eno);
                            break;
                        }
                        default: {
                            System.out.println("Enter the keyword");
                            String keyword = sc.next();
                            podop.searchbyany(keyword);
                            break;
                        }
                    }
                    break;
                }
                case 3: {
                    System.out.println(" Enter 1 to create your Playlist \n Enter 2 for adding songs to Playlist \n Enter 3 for adding album to Playlist \n Enter 4 for adding artist to Playlist \n Enter 5 for adding podcast episode to Playlist \n Enter 6 for adding podcast title to Playlist \n Enter 7 for adding a celebrity podcast to Playlist \n Enter 8 displaying Playlist \n Enter 9 for Playing Songs");
                    int playlistchoice = sc.nextInt();
                    switch (playlistchoice) {
                        case 1: {
                            System.out.println("Enter a name for playlist:");
                            String name = sc.next();
                            plo.creatplaylist(name);
                            break;
                        }
                        case 2: {
                            System.out.println("Enter a name for playlist:");
                            String name = sc.next();
                            System.out.println("Enter a song name");
                            String name1=sc.next();
                            Playlist pl=new Playlist(name1);
                            plo.addsongtolist(name,pl);
                            break;
                        }
                        case 3:
                        {
                            System.out.println("Enter a name for playlist:");
                            String name = sc.next();
                            System.out.println("Enter a album name");
                            String name1=sc.next();
                            plo.addsongtolistbyalbum(name,name1);
                            break;
                        }
                        case 4:
                        {
                            System.out.println("Enter a name for playlist:");
                            String name = sc.next();
                            System.out.println("Enter a artist name");
                            String name1=sc.next();
                            plo.addsongtolistbyartist(name,name1);
                            break;
                        }
                        case 5: {
                            System.out.println("Enter a name for playlist:");
                            String name = sc.next();
                            System.out.println("Enter a podcast episode name");
                            String name1=sc.next();
                            Playlist pl=new Playlist(name1);
                            plo.addpodcasttolist(name,pl);
                            break;
                        }
                        case 6:
                        {
                            System.out.println("Enter a name for playlist:");
                            String name = sc.next();
                            System.out.println("Enter a podcast title name");
                            String name1=sc.next();
                            plo.addsongtolistbypcastname(name,name1);
                            break;
                        }
                        case 7:
                        {
                            System.out.println("Enter a name for playlist:");
                            String name = sc.next();
                            System.out.println("Enter a celebrity name");
                            String name1=sc.next();
                            plo.addsongtolistbycelebrity(name,name1);
                            break;
                        }
                        case 8:
                        {
                            System.out.println("Enter a name for playlist:");
                            String name = sc.next();
                            plo.display(name);
                            break;
                        }
                        case 9:
                        {
                            try
                            {
                                Playsong audioPlayer = new Playsong();
                                System.out.println("Enter a playlist name");
                                String playlist=sc.next();
                                audioPlayer.getsongnamefromplaylist(playlist);
                                System.out.println("Do you want to play the songs from beginning(Y/N)");
                                String choice=sc.next();
                                if(choice.equalsIgnoreCase("Y"))
                                {
                                    audioPlayer.getsongchoice(1);
                                }
                                else if(choice.equalsIgnoreCase("N"))
                                {
                                    System.out.println("Enter a song number to be played");
                                    int num=sc.nextInt();
                                    audioPlayer.getsongchoice(num);
                                }
                                while (true)
                                {
                                    System.out.println("0. Play");
                                    System.out.println("1. pause");
                                    System.out.println("2. restart");
                                    System.out.println("3. stop");
                                    System.out.println("4. next");
                                    System.out.println("5. previous");
                                    int c = sc.nextInt();
                                    audioPlayer.playsong(c);
                                    if (c == 3)
                                        break;
                                }
                            }

                            catch (Exception ex)
                            {
                                System.out.println("Error with playing sound.");
                                ex.printStackTrace();

                            }
                        }
                    }
                }
                default:
                    break;
            }
        }while(choice1!=4);
        System.out.println("Thank you for Jukeboxing...");
    }
}

//Song song=new Song("Soul of Doctor","Doctor","Aniruth","1:30","2021","Classical");
//Song song=new Song("So Baby","Doctor","Aniruth","5:30","2021","Western");
//Song song=new Song("Tum Tum","Enemy","Thaman","5:30","2021","Rock");
//Song song=new Song("Neeum Naanum","Naanum Rowdy Than","Aniruth","4:30","2015","Western");
//Song song=new Song("Neeum Naanum","Imaika Nodigal","Hip hop Tamila","4:00","2018","Rock");
// Song song=new Song("Meherezylaa","Maanaadu","Yuvan Shankar Raja","4:10","2021","Classical");
// Song song=new Song("Life of Ram","96","Pradeep Kumar","6:00","2018","Western");
//Song song=new Song("Thendral Vanthu","Thendral Vanthu","Illayaraja","6:00","1999","Western");
// Song song=new Song("Kadal Rasa","Mariyan","ARR","5:00","2013","Classical");
//Song song=new Song("Mariyan","Mariyan","ARR","5:00","2013","Classical");
// so.addsong(song);



         /* Podcast pod=new Podcast("Playing it my way",1,"About Childhood","About Sachin's childhood","50","Sachin Tendulkar","Hum Jeetenge","Sport","2021-07-07",7);
        Podcast pod1=new Podcast("Playing it my way",2,"Passion for Cricket","About Sachin's cricket carrier","120","Sachin Tendulkar","Hum Jeetenge","Sport","2021-07-08",7);
        Podcast pod2=new Podcast("Playing it my way",3,"About 2011","About Sachin's last world cup","120","Sachin Tendulkar","Boria Majumdar","Sport","2021-07-08",7);*/
//Podcast pod3=new Podcast("Playing it my way",2,"Passion for Cricket","About Sachin's cricket carrier","120","Sachin Tendulkar","Boria Majumdar","Sport","2021-07-01",7);
// Podcast pod3=new Podcast("TED_2",1,"About_me","My Biography","100","Isha Sharma","Isha Sharma","Auto-Biography","2021-12-06",3);
// Podcast pod3=new Podcast("TED_3",1,"About_me","My Biography","100","Isha Sharma1","Isha Sharma1","Auto_Biography","2021-12-06",3);
//Podcast pod3=new Podcast("TED_4",1,"About_me","My Biography","100","Isha Sharma11","Isha Sharma11","Auto_Biography1","2021-12-06",3);
// Podcast pod3=new Podcast("TED_6",1,"About_me","My Biography","100","Isha Sharma112","Isha Sharma112","Biography1","2021-12-06",3);
       /* Podcast pod3=new Podcast("TED_6",3,"About_me_part3","My Biography","100","Isha Sharma112","Isha Sharma112","Biography1","2021-12-07",3);
        podop.checkpodcast(pod3);*/
