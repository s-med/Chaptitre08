package bookstoread;

import java.time.Year;
import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * Représente une étagère virtuelle contenant des livres.
 * Elle permet l'ajout, le tri et le regroupement des ouvrages selon divers critères.
 * Conçue selon une approche de développement piloté par les tests (TDD).
 */
public class BookShelf {

    /**
     * Collection interne stockant les livres de l'étagère.
     * Elle ne peut être modifiée que via la méthode add().
     */
    private final List<Book> books = new ArrayList<>();

    /**
     * Fournit la liste complète des livres présents sur l'étagère.
     * Cette liste est en lecture seule afin d'éviter toute modification externe.
     *
     * @return une vue non modifiable des livres contenus dans l'étagère
     */
    public List<Book> books() {
        return Collections.unmodifiableList(books);
    }

    /**
     * Ajoute un ou plusieurs livres dans l'étagère.
     * Le paramètre varargs permet d'insérer plusieurs ouvrages en une seule opération.
     *
     * @param booksToAdd ensemble des livres à ajouter
     */
    public void add(Book... booksToAdd) {
        books.addAll(Arrays.asList(booksToAdd));
    }

    /**
     * Retourne les livres triés selon leur ordre naturel (titre alphabétique).
     * Cette méthode s'appuie sur arrange(Comparator) avec Comparator.naturalOrder().
     *
     * @return une nouvelle liste contenant les livres triés par titre
     */
    public List<Book> arrange() {
        return arrange(Comparator.naturalOrder());
    }

    /**
     * Trie les livres selon un critère défini par l'utilisateur.
     * La liste d'origine reste intacte et une nouvelle liste est générée.
     *
     * @param criteria règle de comparaison utilisée pour le tri
     * @return une liste triée selon le comparateur fourni
     */
    public List<Book> arrange(Comparator<Book> criteria) {
        return books.stream()
                .sorted(criteria)
                .collect(Collectors.toList());
    }

    /**
     * Regroupe les livres en fonction de leur année de publication.
     * Utilise la méthode groupBy() en utilisant l'année comme clé.
     *
     * @return une structure Map associant chaque année à ses livres correspondants
     */
    public Map<Year, List<Book>> groupByPublicationYear() {
        return this.groupBy(book -> Year.of(book.getPublishedOn().getYear()));
    }

    /**
     * Permet de regrouper les livres selon un critère personnalisé.
     * Peut être utilisé pour grouper par auteur, année, titre ou autre attribut.
     *
     * @param fx fonction définissant le critère de regroupement
     * @param <K> type de la clé utilisée pour le regroupement
     * @return une Map associant chaque clé à la liste des livres correspondants
     */
    public <K> Map<K, List<Book>> groupBy(Function<Book, K> fx) {
        return books.stream()
                .collect(Collectors.groupingBy(fx));
    }
}