package bookstoread;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.Month;
import java.time.Year;
import java.util.*;

import static java.util.Arrays.asList;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

public class BookShelfSpec {

    // Instance principale utilisée pour les tests
    private BookShelf shelf;

    // Livres utilisés comme données de test
    private Book effectiveJava;
    private Book codeComplete;
    private Book mythicalManMonth;
    private Book refactoring;

    /**
     * Méthode exécutée avant chaque test unitaire.
     * Elle recrée une étagère vide ainsi que les livres nécessaires.
     */
    @BeforeEach
    void init() {

        // Initialisation d'une nouvelle étagère
        shelf = new BookShelf();

        // Création des livres de test
        effectiveJava = new Book(
                "Effective Java",
                "Joshua Bloch",
                LocalDate.of(2008, Month.MAY, 8)
        );

        codeComplete = new Book(
                "Code Complete",
                "Steve McConnell",
                LocalDate.of(2004, Month.JUNE, 9)
        );

        mythicalManMonth = new Book(
                "The Mythical Man-Month",
                "Frederick Phillips Brooks",
                LocalDate.of(1975, Month.JANUARY, 1)
        );

        refactoring = new Book(
                "Refactoring",
                "Martin Fowler",
                LocalDate.of(2018, Month.NOVEMBER, 20)
        );
    }

    // =====================
    // Tests : étagère vide
    // =====================

    /**
     * Vérifie que l'étagère ne contient aucun livre
     * lorsqu'aucune insertion n'a été effectuée.
     */
    @Test
    @DisplayName("L'étagère est vide quand aucun livre n'est ajouté")
    void shelfEmptyWhenNoBookAdded() {

        List<Book> books = shelf.books();

        assertTrue(books.isEmpty());
    }

    /**
     * Vérifie que l'ajout sans paramètre
     * ne modifie pas le contenu de l'étagère.
     */
    @Test
    @DisplayName("L'étagère reste vide si add() est appelé sans argument")
    void emptyBookShelfWhenAddIsCalledWithoutBooks() {

        shelf.add();

        List<Book> books = shelf.books();

        assertTrue(books.isEmpty());
    }

    // =====================
    // Tests : ajout de livres
    // =====================

    /**
     * Vérifie que plusieurs livres ajoutés
     * sont correctement stockés dans l'étagère.
     */
    @Test
    @DisplayName("Deux livres ajoutés sont bien présents dans l'étagère")
    void bookshelfContainsTwoBooksWhenTwoBooksAdded() {

        shelf.add(effectiveJava, codeComplete);

        List<Book> books = shelf.books();

        assertEquals(2, books.size());
    }

    /**
     * Vérifie que la collection retournée
     * ne peut pas être modifiée par le client.
     */
    @Test
    @DisplayName("La collection de livres est immuable côté client")
    void booksReturnedFromBookShelfIsImmutableForClient() {

        shelf.add(effectiveJava, codeComplete);

        List<Book> books = shelf.books();

        assertThrows(UnsupportedOperationException.class, () -> {
            books.add(mythicalManMonth);
        });
    }

    // =====================
    // Tests : tri des livres
    // =====================

    /**
     * Vérifie que les livres sont triés
     * par ordre alphabétique des titres.
     */
    @Test
    @DisplayName("Tri des livres selon l'ordre alphabétique des titres")
    void bookshelfArrangedByBookTitle() {

        shelf.add(effectiveJava, codeComplete, mythicalManMonth);

        List<Book> books = shelf.arrange();

        assertEquals(
                asList(codeComplete, effectiveJava, mythicalManMonth),
                books
        );
    }

    /**
     * Vérifie que l'ordre d'insertion
     * reste inchangé après un tri.
     */
    @Test
    @DisplayName("L'ordre d'insertion est conservé après arrange()")
    void booksInBookShelfAreInInsertionOrderAfterCallingArrange() {

        shelf.add(effectiveJava, codeComplete, mythicalManMonth);

        shelf.arrange();

        List<Book> books = shelf.books();

        assertEquals(
                asList(effectiveJava, codeComplete, mythicalManMonth),
                books
        );
    }

    /**
     * Vérifie que le tri peut être personnalisé
     * via un comparateur fourni par l'utilisateur.
     */
    @Test
    @DisplayName("Tri personnalisé avec un comparateur utilisateur (ordre décroissant)")
    void bookshelfArrangedByUserProvidedCriteria() {

        shelf.add(effectiveJava, codeComplete, mythicalManMonth);

        List<Book> books = shelf.arrange(
                Comparator.<Book>naturalOrder().reversed()
        );

        assertEquals(
                asList(mythicalManMonth, effectiveJava, codeComplete),
                books,
                () -> "Les livres doivent être triés en ordre décroissant de titre"
        );
    }

    /**
     * Vérifie que les livres peuvent être triés
     * selon leur date de publication.
     */
    @Test
    @DisplayName("Tri des livres par date de publication croissante")
    void bookshelfArrangedByBookPublicationDate() {

        shelf.add(effectiveJava, codeComplete, mythicalManMonth, refactoring);

        List<Book> books = shelf.arrange(
                Comparator.comparing(Book::getPublishedOn)
        );

        assertEquals(
                asList(mythicalManMonth, codeComplete, effectiveJava, refactoring),
                books,
                () -> "Les livres doivent être triés par date de publication croissante"
        );
    }

    // =====================
    // Tests : regroupement
    // =====================

    /**
     * Vérifie que les livres sont correctement regroupés
     * selon leur année de publication.
     */
    @Test
    @DisplayName("Regroupement des livres par année de publication")
    void bookshelfGroupedByPublicationYear() {

        shelf.add(effectiveJava, codeComplete, mythicalManMonth, refactoring);

        Map<Year, List<Book>> result = shelf.groupByPublicationYear();

        assertThat(result)
                .containsKey(Year.of(2008))
                .containsKey(Year.of(2004))
                .containsKey(Year.of(1975))
                .containsKey(Year.of(2018));

        assertThat(result.get(Year.of(2008))).containsExactly(effectiveJava);
        assertThat(result.get(Year.of(2004))).containsExactly(codeComplete);
        assertThat(result.get(Year.of(1975))).containsExactly(mythicalManMonth);
        assertThat(result.get(Year.of(2018))).containsExactly(refactoring);
    }

    /**
     * Vérifie que le regroupement peut être effectué
     * selon un critère défini par l'utilisateur.
     */
    @Test
    @DisplayName("Regroupement des livres selon un critère personnalisé")
    void bookshelfGroupedByUserProvidedCriteria() {

        shelf.add(effectiveJava, codeComplete, mythicalManMonth, refactoring);

        Map<String, List<Book>> result = shelf.groupBy(Book::getAuthor);

        assertThat(result)
                .containsKey("Joshua Bloch")
                .containsKey("Steve McConnell")
                .containsKey("Frederick Phillips Brooks")
                .containsKey("Martin Fowler");

        assertThat(result.get("Joshua Bloch")).containsExactly(effectiveJava);
        assertThat(result.get("Steve McConnell")).containsExactly(codeComplete);
        assertThat(result.get("Frederick Phillips Brooks")).containsExactly(mythicalManMonth);
        assertThat(result.get("Martin Fowler")).containsExactly(refactoring);
    }
}