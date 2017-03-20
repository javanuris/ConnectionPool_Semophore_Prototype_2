package nuris.epam;

import nuris.epam.entity.*;
import nuris.epam.service.GenreService;


/**
 * Created by User on 09.03.2017.
 */
public class Main {

    public static void main(String[] args) throws Exception {

        Genre genre = new Genre();
        genre.setName("Lurk");
        GenreService genreService = new GenreService();
        genreService.insert(genre);
        System.out.println(genre);
    }
}
