# Expression Engine

Moteur de calcul symbolique en Java — évaluation, simplification et **dérivation formelle** d'expressions arithmétiques contenant variables, constantes et opérateurs.

Implémentation centrée sur les design patterns **Composite** (représentation en arbre) et **Visitor** (traitements extensibles sans modification des nœuds).

## Fonctionnalités

### Évaluation
- Évaluation d'expressions constantes (sans variable)
- Évaluation dans un environnement `Map<String, Integer>` qui associe des valeurs aux variables

### Manipulation symbolique
- **Test de constance** d'une expression (contient-elle des variables ?)
- **Simplification** selon des règles classiques :
  - Évaluation des opérations entre constantes : `1 + 3 → 4`
  - Éléments neutres : `e + 0 → e`, `e * 1 → e`
  - Élément absorbant : `e * 0 → 0`
- **Dérivation formelle** par rapport à une variable :
  - `(c)' = 0`
  - `(v)' = 1` si `v = x`, `0` sinon
  - `(e₁ + e₂)' = e₁' + e₂'`
  - `(e₁ * e₂)' = e₁ * e₂' + e₁' * e₂`
- **Composition de visiteurs** pour enchaîner les traitements (par exemple dériver puis simplifier)

## Architecture

### Pattern Composite — représentation des expressions

```
         Expression (interface)
              │
    ┌─────────┼──────────┬────────┐
    │         │          │        │
Constant    Var         BinOp (abstract)
                          │
                     ┌────┴────┐
                    Add       Mult
```

Chaque expression est un **arbre immuable** :
- **Feuilles** : `Constant` (nombre entier), `Var` (variable nommée)
- **Nœuds internes** : `Add`, `Mult` (sous-classes de `BinOp`, chacune avec deux fils `left` et `right`)

### Pattern Visitor — traitements extensibles

Chaque traitement est isolé dans un visiteur. Pour ajouter une nouvelle opération, pas besoin de modifier les classes d'expression — il suffit de créer un nouveau visiteur.

| Visiteur | Traitement |
|----------|-----------|
| `VisitorToString` | Conversion en chaîne lisible (affichage infixe) |
| `VisitorEval` | Évaluation d'une expression sans variable |
| `VisitorEvalVar` | Évaluation avec variables, via un environnement |
| `VisitorConstant` | Test : l'expression est-elle constante ? |
| `VisitorSimplify` | Simplification récursive |
| `VisitorDerive` | Dérivation formelle par rapport à une variable |

Interface commune générique :

```java
public interface IVisitor<T> {
    T visit(Constant c);
    T visit(Add e);
    T visit(Mult e);
    T visit(Var v);
}
```

Chaque expression implémente `<T> T accept(IVisitor<T> v)` qui double-dispatch vers la bonne méthode `visit`.

## Exemple

```java
// (x + 10) * (y + -8)
Expression e = new Mult(
    new Add(new Var("x"), new Constant(10)),
    new Add(new Var("y"), new Constant(-8))
);

// Dérivée par rapport à x, puis simplification
VisitorDerive derive = new VisitorDerive(new Var("x"));
VisitorSimplify simplify = new VisitorSimplify();
Expression derivee = Question13.compose(simplify, derive, e);

System.out.println(derivee.accept(new VisitorToString()));
// → ( y + -8 )
```

## Structure

```
src/pobj/expr/
├── Expression.java          # Interface racine
├── Constant.java            # Nombre entier
├── Var.java                 # Variable nommée
├── BinOp.java               # Classe abstraite (opérateur binaire)
├── Add.java                 # Addition
├── Mult.java                # Multiplication
├── IVisitor.java            # Interface visiteur générique
├── VisitorToString.java
├── VisitorEval.java
├── VisitorEvalVar.java
├── VisitorConstant.java
├── VisitorSimplify.java
├── VisitorDerive.java
└── test/                    # Tests JUnit 5 (TestQ1 à TestQ13)
```

## Exécution

### Prérequis
- Java 17+
- JUnit 5

Importer le projet dans Eclipse, lancer les tests avec *Run As > JUnit Test*.

## Auteur

Hani Slimani
