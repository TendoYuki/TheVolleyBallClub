# Volley-SAE

## Structure

Le projet est séparé en deux modules disctincs

- [(1) Administrateur](./src/admin-app/README.md)
- [(2) Site web.](./src/web-app/README.md)

Le code source ainsi que les tests sont trouvables dans le sous dossier "src" des deux modules respectifs.

## Partie web

La partie web fait usage des outils suivants :

- **npm (nodejs) -> Webpack** Permettant de pouvoir compiler / optimiser / packer le SCSS et le javascript du projet.
- **composer** Permettant de pouvoir tester et séparer le code php en packages à travers le systeme de "namespaces"

### Tests web

Les tests de l'application web ont été réalisé à l'aide de la librairie PHPUnit.

## Partie Administrateur (Java)

La partie administrateur fait usage des outils suivants:

- **Maven** Permettant de compiler et tester le projet ainsi que l'utilisation des librairies

### Tests Administrateur

Les tests de l'application java ont été réalisées à l'aide de la librairie JUnit
