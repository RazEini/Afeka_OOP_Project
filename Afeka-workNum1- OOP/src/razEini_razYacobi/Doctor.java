package razEini_razYacobi;

public class Doctor extends Lecturer {
    private String[] articles;
    private int artCount;

    public Doctor() {
        super();
        this.articles = new String[1];
        this.artCount = 0;
    }

    public Doctor(String name, String id, int salary, Department department, Degree degree) {
        super(name, id, salary, department, degree);
        this.articles = new String[1];
        this.artCount = 0;
    }

    public Doctor(Doctor other) {
        super(other);
        if (other != null) {
            this.articles = new String[other.articles.length];
            this.artCount = other.artCount;
            for (int i = 0; i < other.artCount; i++) {
                this.articles[i] = other.articles[i];
            }
        }
    }

    public void addArticles(String article) {
        if (this.artCount == this.articles.length) {
            String[] temp = new String[this.articles.length * 2];
            for (int i = 0; i < this.articles.length; i++) temp[i] = this.articles[i];
            this.articles = temp;
        }
        this.articles[artCount++] = article;
    }

    @Override
    public int getNumOfArticles() {
        return this.artCount;
    }

    @Override
    public String toString() {
        return super.toString() + "\n- Number of Articles: " + artCount;
    }
}