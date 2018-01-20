# Sineq
### Tabelka z [Zadaniami](https://docs.google.com/spreadsheets/d/1BypFIafhVVTjv7vNVr8oSsat7g7ht2KIvQ8CR8vwZyM/edit#gid=796185151)
## Jak sklonować repozytorium do siebie?
1. Odpalasz **Android Studio** -> **New** -> **Check out project from Version Control** -> **GitHub**
2. Jako **Git Repository URL:** `https://github.com/ronek22/Sineq2.git`
> **Uwaga:** The <i class="icon-refresh"></i> Jeśli wyskoczył Ci błąd "nie znaleziono git.exe" pobierz i zainstaluj to: [Git Bash](https://git-scm.com/download/win) .
3. Jeśli wyskoczy okno z pytaniem `Would you like to open it?` wciskamy **NO**. Wybieramy **Import project (Eclipse ADT, Gradle, etc.)**. Szukamy folderu z zaimportowanym repo, powinno mieć zieloną ikonkę. Wciskamy Ok, projekt powinien się prawidłowo utworzyć. 
4. Jeśli napisałeś jakiś kod i zaczyna coś działać możesz puścic commita
**VCS** -> **Commit Changes** 
5. W ***Commit Message*** wpisujesz co zrobiłeś, jakie zmiany wprowadziłeś etc.
6. Najeżdzasz na **Commit**, ale wybierasz **Commit and Push**
7. Dla każdego utworzona jest gałąź, commitujemy tylko na swoją gałąź.
8. Na koniec robimy **Pull Request** na GitHubie i razem zatwierdzamy lub odrzucamy zmiany.
## Jak pracować na swojej gałezi w Android Studio?
1. Za każdym razem, aby wczytać wszystkie zmiany w repo odpalamy  
**VCS** -> **Update Project**
2. W dolnym prawym rogu znajdziecie `Git:master`
3. Po naciśnięciu szukacie swojej gałęzi i wybiracie `Checkout as new local branch`
4. Jeśli jesteście w tyłu w stosunku do mastera, a chcecie mieć aktualny kod to:
    * Naciskacie `Git:wasza_gałąź` najeżdzacie na mastera i teraz dwie opcje 
    albo **merge** albo **rebase onto**  
    [Tu](https://www.atlassian.com/git/tutorials/merging-vs-rebasing) zalety i wady obu opcji.
