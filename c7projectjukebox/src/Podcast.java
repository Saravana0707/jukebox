public class Podcast {
    private long podcast_id;
    private String podcast_name;
    private int podcast_episode_number;
    private String podcast_episode_name;
    private String short_description;
    private String duration_in_min;
    private String celebrity_name;
    private String narrator_name;
    private String genres_name;
    private String upload_date;
    private int number_of_episodes;

    public Podcast(String podcast_name, int podcast_episode_number,String podcast_episode_name, String short_description, String duration_in_min, String celebrity_name, String narrator_name, String genres_name, String upload_date,int number_of_episodes)
    {
        this.podcast_name=podcast_name;
        this.podcast_episode_number=podcast_episode_number;
        this.podcast_episode_name = podcast_episode_name;
        this.short_description = short_description;
        this.duration_in_min = duration_in_min;
        this.celebrity_name = celebrity_name;
        this.narrator_name = narrator_name;
        this.genres_name = genres_name;
        this.upload_date = upload_date;
        this.number_of_episodes=number_of_episodes;
    }
    public Podcast(long podcast_id,String podcast_name, int podcast_episode_number,String podcast_episode_name, String short_description, String duration_in_min, String celebrity_name, String narrator_name, String genres_name, String upload_date,int number_of_episodes)
    {
        this.podcast_id=podcast_id;
        this.podcast_name=podcast_name;
        this.podcast_episode_number=podcast_episode_number;
        this.podcast_episode_name = podcast_episode_name;
        this.short_description = short_description;
        this.duration_in_min = duration_in_min;
        this.celebrity_name = celebrity_name;
        this.narrator_name = narrator_name;
        this.genres_name = genres_name;
        this.upload_date = upload_date;
        this.number_of_episodes=number_of_episodes;
    }

    public long getPodcast_id() {
        return podcast_id;
    }

    public void setPodcast_id(long podcast_id) {
        this.podcast_id = podcast_id;
    }

    public int getNumber_of_episodes() {
        return number_of_episodes;
    }

    public void setNumber_of_episodes(int number_of_episodes) {
        this.number_of_episodes = number_of_episodes;
    }

    public String getPodcast_name() {
        return podcast_name;
    }

    public void setPodcast_name(String podcast_name) {
        this.podcast_name = podcast_name;
    }

    public int getPodcast_episode_number() {
        return podcast_episode_number;
    }

    public void setPodcast_episode_number(int podcast_episode_number) {
        this.podcast_episode_number = podcast_episode_number;
    }

    public String getPodcast_episode_name() {
        return podcast_episode_name;
    }

    public void setPodcast_episode_name(String podcast_episode_name) {
        this.podcast_episode_name = podcast_episode_name;
    }

    public String getShort_description() {
        return short_description;
    }

    public void setShort_description(String short_description) {
        this.short_description = short_description;
    }

    public String getDuration_in_min() {
        return duration_in_min;
    }

    public void setDuration_in_min(String duration_in_min) {
        this.duration_in_min = duration_in_min;
    }

    public String getCelebrity_name() {
        return celebrity_name;
    }

    public void setCelebrity_name(String celebrity_name) {
        this.celebrity_name = celebrity_name;
    }

    public String getNarrator_name() {
        return narrator_name;
    }

    public void setNarrator_name(String narrator_name) {
        this.narrator_name = narrator_name;
    }

    public String getGenres_name() {
        return genres_name;
    }

    public void setGenres_name(String genres_name) {
        this.genres_name = genres_name;
    }

    public String getUpload_date() {
        return upload_date;
    }

    public void setUpload_date(String upload_date) {
        this.upload_date = upload_date;
    }

    @Override
    public String toString() {
        return podcast_name+","+podcast_episode_number+","+podcast_episode_name+","+short_description+","+duration_in_min+","+celebrity_name+","+narrator_name+","+genres_name+","+upload_date+","+number_of_episodes;
    }
}
