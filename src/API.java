public enum API {
    //IMDB_TOP_MOVIES("https://imdb.api.com/en/API/Top250Movies/" + imdbKey, new ExtratorDeConteudoDoIMDB()),
    //NASA("http://api.nasa.gov/planetary/apod?api_key=DEMO_KEY?start_date=2023-04-01&end_date=2023-04-03", new ExtratorDeConteudoDaNasa()));

    IMDB_TOP_MOVIES("https://raw.githubusercontent.com/alura-cursos/imersao-java-2-api/main/TopMovies.json", new ExtratorDeConteudoDoIMDB()),
    NASA("https://raw.githubusercontent.com/alura-cursos/imersao-java-2-api/main/NASA-APOD.json", new ExtratorDeConteudoDaNasa()),
    LINGUAGENS("http://localhost:8080/linguagens", new ExtratorDeConteudoDoIMDB());

    private String url;
    private ExtratorDeConteudo extrator;

    API(String url, ExtratorDeConteudo extrator){
        this.url = url;
        this.extrator = extrator;
    }

    public String getUrl(){
        return url;
    }

    public ExtratorDeConteudo getExtrator(){
        return extrator;
    }
}
