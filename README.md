# Chapitre 08 — Tests Unitaires avec JUnit 5

## Description
Ce projet Java met en place une bibliothèque virtuelle nommée `BookShelf`, permettant de gérer une collection de livres.

Ce travail a été réalisé dans le cadre du cours **Projet Informatique II** à **PIGIER Côte d'Ivoire**.

---

## Fonctionnalités implémentées

### 1. Ajout de livres
Permet d’insérer un ou plusieurs livres dans l’étagère en une seule opération grâce au mécanisme de vararg.

### 2. Consultation des livres
Retourne l’ensemble des livres sous forme de collection **immuable**, empêchant toute modification externe.

### 3. Tri des livres par titre
Effectue un tri lexicographique (ordre alphabétique) par défaut via la méthode `arrange()`.

### 4. Tri selon un critère personnalisé
L’utilisateur peut définir son propre ordre de tri en fournissant un `Comparator` via `arrange(Comparator)`.

### 5. Tri par date de publication ⭐ Exercice 1
Permet un tri croissant des livres en fonction de leur date de publication à l’aide de `Comparator.comparing(Book::getPublishedOn)`.

### 6. Regroupement par année de publication
Organise les livres dans une structure `Map<Year, List<Book>>` grâce à la méthode `groupByPublicationYear()`.

### 7. Regroupement personnalisé
Méthode générique `groupBy(Function)` offrant la possibilité de regrouper les livres selon n’importe quel attribut (auteur, année, titre, etc.).

---

## Tests unitaires — 10 tests validés ✅

| Test | Description |
|------|-------------|
| `shelfEmptyWhenNoBookAdded` | Vérifie que l’étagère est vide au départ |
| `emptyBookShelfWhenAddIsCalledWithoutBooks` | Vérifie que l’appel de `add()` sans argument ne modifie pas l’étagère |
| `bookshelfContainsTwoBooksWhenTwoBooksAdded` | Confirme que deux livres ajoutés sont bien présents |
| `booksReturnedFromBookShelfIsImmutableForClient` | Vérifie que la collection retournée est immuable |
| `bookshelfArrangedByBookTitle` | Vérifie le tri des livres par ordre alphabétique |
| `booksInBookShelfAreInInsertionOrderAfterCallingArrange` | Vérifie que l’ordre d’insertion est conservé après `arrange()` |
| `bookshelfArrangedByUserProvidedCriteria` | Vérifie le tri selon un critère personnalisé (ordre décroissant) |
| `bookshelfArrangedByBookPublicationDate` | ⭐ Vérifie le tri des livres par date de publication |
| `bookshelfGroupedByPublicationYear` | Vérifie le regroupement des livres par année |
| `bookshelfGroupedByUserProvidedCriteria` | Vérifie le regroupement des livres par auteur |

---

## Structure du projet
