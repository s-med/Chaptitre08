package bookstoread;

import java.time.LocalDate;

/**
 * Classe représentant un ouvrage avec son titre,
 * son auteur ainsi que sa date de publication.
 * Implémente Comparable afin de permettre
 * un tri naturel basé sur le titre.
 */
public class Book implements Comparable<Book> {

    /** Nom du livre */
    private final String title;

    /** Nom de l'auteur */
    private final String author;

    /** Date à laquelle le livre a été publié */
    private final LocalDate publishedOn;

    /**
     * Constructeur servant à initialiser
     * les différentes informations du livre.
     *
     * @param title       correspond au titre du livre
     * @param author      correspond à l'auteur du livre
     * @param publishedOn correspond à la date de publication
     */
    public Book(String title, String author, LocalDate publishedOn) {
        this.title = title;
        this.author = author;
        this.publishedOn = publishedOn;
    }

    /**
     * Permet d'obtenir le titre du livre.
     *
     * @return le titre associé au livre
     */
    public String getTitle() {
        return title;
    }

    /**
     * Permet de récupérer le nom de l'auteur.
     *
     * @return l'auteur du livre
     */
    public String getAuthor() {
        return author;
    }

    /**
     * Renvoie la date de publication du livre.
     *
     * @return la date à laquelle le livre a été publié
     */
    public LocalDate getPublishedOn() {
        return publishedOn;
    }

    /**
     * Fournit une représentation textuelle
     * contenant les informations du livre.
     *
     * @return une chaîne décrivant le livre
     */
    @Override
    public String toString() {
        return "Book{" +
                "title='" + title + '\'' +
                ", author='" + author + '\'' +
                ", publishedOn=" + publishedOn +
                '}';
    }

    /**
     * Compare ce livre avec un autre
     * en se basant sur l'ordre alphabétique des titres.
     * Cette méthode sert au tri naturel des livres.
     *
     * @param that livre utilisé pour la comparaison
     * @return une valeur négative, nulle ou positive
     * selon l'ordre lexicographique des titres
     */
    @Override
    public int compareTo(Book that) {
        return this.title.compareTo(that.title);
    }
}